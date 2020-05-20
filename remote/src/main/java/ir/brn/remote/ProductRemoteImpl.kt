package ir.brn.remote

import io.reactivex.Observable
import ir.brn.data.model.product.CategoryEntity
import ir.brn.data.model.product.ProductEntity
import ir.brn.data.repository.product.ProductRemote
import ir.brn.remote.mapper.product.CategoryMapper
import ir.brn.remote.mapper.product.ProductMapper
import ir.brn.remote.service.ApiService
import javax.inject.Inject

class ProductRemoteImpl @Inject constructor(
        private val apiService: ApiService,
        private val productMapper: ProductMapper,
        private val categoryMapper: CategoryMapper) : ProductRemote {

    override fun getCategories(token: String): Observable<List<CategoryEntity>> {
        return apiService
                .getCategories(token)
                .map { response ->
                    response.data.map {
                        categoryMapper.mapFromModel(it)
                    }
                }
    }

    override fun getChildCategories(token: String, categoryId: Int): Observable<List<CategoryEntity>> {
        return apiService
                .getChildCategories(token, categoryId)
                .map { response ->
                    response.data.map {
                        categoryMapper.mapFromModel(it)
                    }
                }
    }

    override fun getProducts(token: String): Observable<List<ProductEntity>> {
        return apiService
                .getProducts(token)
                .map { response ->
                    response.data.map {
                        productMapper.mapFromModel(it)
                    }
                }
    }

    override fun getProductDetails(token: String, productId: Int): Observable<ProductEntity> {
        return apiService
                .getProductDetail(token, productId)
                .map { response ->
                    productMapper.mapFromModel(response.data)
                }
    }
}