package ir.brn.driver.packaging

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.android.support.AndroidSupportInjection
import ir.brn.driver.R
import ir.brn.driver.ext.get
import ir.brn.driver.injection.ViewModelFactory
import ir.brn.driver.mapper.core.PackagingViewMapper
import ir.brn.driver.model.core.Package
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import kotlinx.android.synthetic.main.province_bootom_sheet_dialog.*
import javax.inject.Inject

class PackageBootomDialogFragment : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var mapper: PackagingViewMapper

    private lateinit var packagingViewModel: PackagingViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.province_bootom_sheet_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        packagingViewModel = ViewModelProviders.of(this, viewModelFactory).get()
        setupProvinceRecycler()
        packagingViewModel.getPackagesLiveData().observe(this, packageObserver)
        packagingViewModel.fetchPackages()
    }

    private fun setupProvinceRecycler() {
        rvProvince.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = PackagingAdapter().apply {
                packageListener = listener
            }
        }
    }

    private fun setPackageList(packages: List<Package>?) {
        (rvProvince.adapter as PackagingAdapter).submitList(packages)
    }

    private val packageObserver = Observer<Resource<List<PackagingView>>> { resource ->
        when (resource.status) {
            ResourceState.LOADING -> {
                Toast.makeText(activity, "Loading", Toast.LENGTH_SHORT).show()
            }
            ResourceState.SUCCESS -> {
                setPackageList(resource.data?.map { mapper.mapToView(it) })
            }
            ResourceState.ERROR -> {
                Toast.makeText(activity, "WOW, this is error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private val listener = object : PackageListener {
        override fun onSelectedPackage(currentPackage: Package) {
            (targetFragment as PackageCallback).onSelectedPackegeClick(currentPackage)
            dismiss()

        }
    }
}