package ir.brn.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.observers.DisposableObserver
import ir.brn.domain.interactor.product.GetCategoryUseCase
import ir.brn.domain.interactor.product.GetChildCategoryUseCase
import ir.brn.domain.interactor.product.GetProductDetailUseCase
import ir.brn.domain.interactor.product.GetProductUseCase
import ir.brn.domain.model.product.Category
import ir.brn.domain.model.product.Product
import ir.brn.presentation.mapper.product.CategoryViewMapper
import ir.brn.presentation.mapper.product.ProductViewMapper
import ir.brn.presentation.model.product.CategoryView
import ir.brn.presentation.model.product.ProductView
import ir.brn.presentation.state.Resource
import ir.brn.presentation.state.ResourceState
import javax.inject.Inject

class ProductViewModel @Inject constructor(
        private val getProductUseCase: GetProductUseCase,
        private val getProductDetailUseCase: GetProductDetailUseCase,
        private val getCategoryUseCase: GetCategoryUseCase,
        private val getChildCategoryUseCase: GetChildCategoryUseCase,
        private val productViewMapper: ProductViewMapper,
        private val categoryViewMapper: CategoryViewMapper) : ViewModel() {

    //region liveData
    private val liveDataCategories: MutableLiveData<Resource<List<CategoryView>>> = MutableLiveData()
    private val liveDataProducts: MutableLiveData<Resource<List<ProductView>>> = MutableLiveData()
    private val liveDataProduct: MutableLiveData<Resource<ProductView>> = MutableLiveData()

    //endregion

    override fun onCleared() {
        getProductUseCase.dispose()
        getProductDetailUseCase.dispose()
        getCategoryUseCase.dispose()
        getChildCategoryUseCase.dispose()
        super.onCleared()
    }

    //region category
    fun getLiveDataCategories(): LiveData<Resource<List<CategoryView>>> {
        return liveDataCategories
    }

    fun fetchCategories() {
        liveDataCategories.postValue(Resource(ResourceState.LOADING))
        getCategoryUseCase.execute(CategorySubscriber())
    }


    inner class CategorySubscriber : DisposableObserver<List<Category>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Category>) {
            liveDataCategories.postValue(Resource(ResourceState.SUCCESS,
                    data = t.map {
                        categoryViewMapper.mapToView(it)
                    }))
        }

        override fun onError(e: Throwable) {
            liveDataCategories.postValue(Resource(ResourceState.ERROR,
                    message = e.localizedMessage))
        }
    }

    //endregion

    //region product
    fun getLiveDataProducts(): LiveData<Resource<List<ProductView>>> {
        return liveDataProducts
    }

    fun getLiveDataProduct(): LiveData<Resource<ProductView>> {
        return liveDataProduct
    }

    fun fetchProducts() {
        liveDataProducts.postValue(Resource(ResourceState.LOADING))
        getProductUseCase.execute(ProductsSubscriber())
    }

    fun fetchSpecificProduct(productId: Int) {
        liveDataProduct.postValue(Resource(ResourceState.LOADING))
        getProductDetailUseCase.execute(ProductSubscriber(), GetProductDetailUseCase.Params.forProductDetail(productId))
    }


    inner class ProductsSubscriber : DisposableObserver<List<Product>>() {
        override fun onComplete() {

        }

        override fun onNext(t: List<Product>) {
            liveDataProducts.postValue(Resource(ResourceState.SUCCESS,
                    data = t.map {
                        productViewMapper.mapToView(it)
                    }))
        }

        override fun onError(e: Throwable) {
            liveDataProducts.postValue(Resource(ResourceState.ERROR,
                    message = e.localizedMessage))
        }
    }

    inner class ProductSubscriber : DisposableObserver<Product>() {

        override fun onComplete() {

        }

        override fun onNext(t: Product) {
            liveDataProduct.postValue(Resource(ResourceState.SUCCESS, data = productViewMapper.mapToView(t)))
        }

        override fun onError(e: Throwable) {
            liveDataProduct.postValue(Resource(ResourceState.ERROR, message = e.localizedMessage))
        }
    }

    //endregion

}