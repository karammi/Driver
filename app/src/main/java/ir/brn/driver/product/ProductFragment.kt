package ir.brn.driver.product

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
import ir.brn.driver.mapper.product.CategoryViewMapper
import ir.brn.driver.mapper.product.ProductViewMapper
import ir.brn.driver.model.product.Product
import ir.brn.presentation.ProductViewModel
import ir.brn.presentation.model.product.ProductView
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import kotlinx.android.synthetic.main.fragment_product.*
import javax.inject.Inject

class ProductFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var productViewMapper: ProductViewMapper
    @Inject
    lateinit var categoryViewMapper: CategoryViewMapper

    private lateinit var productViewModel: ProductViewModel

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel = ViewModelProviders.of(this, viewModelFactory).get()
        productViewModel.getLiveDataProducts().observe(this, productObserver)
        setupRecyclerView()
        productViewModel.fetchProducts()
    }


    private fun setupRecyclerView() {
        rvProducts.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext)
            adapter = ProductAdapter()
        }
    }


    private fun setData(products: List<Product>?) {
        products?.let {
            list-> (rvProducts.adapter as? ProductAdapter)?.submitList(list)
        }

    }

    private val productObserver = Observer<Resource<List<ProductView>>> { resource ->
        when (resource.status) {
            ResourceState.LOADING -> {
                println("LOADING")
            }
            ResourceState.SUCCESS -> {
                setData(resource.data?.map {
                    productViewMapper.mapToView(it)
                })
            }
            ResourceState.ERROR -> {
                println("ERROR")
            }

        }

    }
}