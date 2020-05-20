package ir.brn.driver.myLoads

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import ir.brn.driver.R
import ir.brn.driver.ext.get
import ir.brn.driver.injection.ViewModelFactory
import ir.brn.driver.mapper.load.TripViewMapper
import ir.brn.presentation.model.load.TripView
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import kotlinx.android.synthetic.main.fragment_load.*
import timber.log.Timber
import javax.inject.Inject

class MyLoadFragment : Fragment() {

    @Inject
    lateinit var tripViewMapper: TripViewMapper
    private lateinit var loadViewModel: LoadViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_load, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViewModel = ViewModelProviders.of(this, viewModelFactory).get()
        setupLoadRecycler()
        loadViewModel.getLoads().observe(this, loadObserver)
        srlLoads.isRefreshing = true
        loadViewModel.fetchLoads(2)
        srlLoads.setOnRefreshListener {
            loadViewModel.fetchLoads()
        }
    }

    private fun setupLoadRecycler() {
        rvLoads.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = MyLoadAdapter().apply {
            }
        }
    }

    private fun setTrips(trips: List<Trip>?) {
        trips?.let { list ->
            (rvLoads.adapter as? MyLoadAdapter)?.submitList(list)
        }
    }

    private val loadObserver = Observer<Resource<List<TripView>>> { resource ->
        when (resource.status) {
            ResourceState.SUCCESS -> {
                srlLoads.isRefreshing = false
                if (resource.data?.size == 0) {
                    tvNoItem.visibility = View.VISIBLE
                    srlLoads.visibility = View.GONE
                } else {
                    tvNoItem.visibility = View.GONE
                    srlLoads.visibility = View.VISIBLE
                    setTrips(resource.data?.map { tripViewMapper.mapToView(it) })
                }

            }
            ResourceState.LOADING -> {
                srlLoads.isRefreshing = true
            }
            ResourceState.ERROR -> {
                srlLoads.isRefreshing = false
                resource.message?.let { Timber.e(it) }
            }
        }
    }
}