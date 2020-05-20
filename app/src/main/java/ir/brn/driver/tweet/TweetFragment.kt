package ir.brn.driver.tweet

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.support.AndroidSupportInjection
import ir.brn.driver.R
import ir.brn.driver.ext.get
import ir.brn.driver.injection.ViewModelFactory
import ir.brn.driver.mapper.wall.WallViewMapper
import ir.brn.driver.model.wall.Wall
import ir.brn.presentation.WallViewModel
import ir.brn.presentation.model.wall.WallView
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import kotlinx.android.synthetic.main.fragment_wall.*
import javax.inject.Inject

class TweetFragment : Fragment() {

    private lateinit var wallViewModel: WallViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var wallViewMapper: WallViewMapper


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_wall, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wallViewModel = ViewModelProviders.of(this, viewModelFactory).get()
        wallViewModel.getLiveDataWallList().observe(this, wallListObserver)
        srlWalls.isRefreshing = true
        setupRecyclerView()
        wallViewModel.fetchWallList()
        srlWalls.setOnRefreshListener {
            wallViewModel.fetchWallList()
        }
        fabAddTweet.setOnClickListener {
            var args = Bundle()
            args.putInt("type", 1) //args 1 is equal to add tweet type
            findNavController().navigate(R.id.toAddTweetFragment, args)
        }
    }

    private fun setupRecyclerView() {
        rvWalls.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
//                    .apply {
//                reverseLayout = true
//                stackFromEnd=true
//            }
            adapter = WallAdapter().apply {
                tweetListener = listener
            }
        }
    }

    private fun setWalls(walls: List<Wall>) {
        walls?.let {
            (rvWalls.adapter as? WallAdapter)?.submitList(walls)
        }
    }

    private val wallListObserver = Observer<Resource<List<WallView>>> { resource ->
        when (resource.status) {
            ResourceState.LOADING -> {
                srlWalls.isRefreshing = true
            }
            ResourceState.ERROR -> {
                srlWalls.isRefreshing = false
            }
            ResourceState.SUCCESS -> {
                srlWalls.isRefreshing = false
                resource.data?.map {
                    wallViewMapper.mapToView(it)
                }?.let { setWalls(it) }
            }
        }
    }


    private val listener = object : TweetListener {
        override fun likeTweet(wallId: Int) {
            srlWalls.isRefreshing = true
            wallViewModel.likeWall(wallId)
        }

        override fun disLikeTweet(wallId: Int) {
            srlWalls.isRefreshing = true
            wallViewModel.disLikeWall(wallId)
        }

        override fun editTweet(wall: Wall) {
            val bundle = Bundle()
            bundle.putInt("type", 2) //args 1 is equal to Edit tweet type
            bundle.putSerializable("wall", wall)
            findNavController().navigate(R.id.toAddTweetFragment, bundle)
        }

        override fun deleteTweet(wallId: Int) {

            wallViewModel.deleteWall(wallId)
            srlWalls.isRefreshing = true
        }
    }

}