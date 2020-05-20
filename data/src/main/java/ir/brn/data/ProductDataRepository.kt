package ir.brn.data

import io.reactivex.Observable
import ir.brn.data.mapper.productMapper.CategoryMapper
import ir.brn.data.mapper.productMapper.ProductMapper
import ir.brn.data.source.product.ProductDataStoreFactory
import ir.brn.data.source.user.UserDataStoreFactory
import ir.brn.domain.model.product.Category
import ir.brn.domain.model.product.Product
import ir.brn.domain.repository.ProductRepository
import javax.inject.Inject

class ProductDataRepository @Inject constructor(
        private val factory: ProductDataStoreFactory,
        private val userFactory: UserDataStoreFactory,
        private val productMapper: ProductMapper,
        private val categoryMapper: CategoryMapper) : ProductRepository {

    override fun getCategories(): Observable<List<Category>> {
        return userFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    factory
                            .getRemoteDataSource()
                            .getCategories(token)
                }.map { list ->
                    list.map {
                        categoryMapper.mapFromEntity(it)
                    }
                }
    }

    override fun getChildCategories(id: Int): Observable<List<Category>> {
        return userFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    factory
                            .getRemoteDataSource()
                            .getChildCategories(token, id)
                }.map { list ->
                    list.map {
                        categoryMapper.mapFromEntity(it)
                    }
                }
    }

    override fun getProducts(): Observable<List<Product>> {
        return userFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap { token ->
                    factory
                            .getRemoteDataSource()
                            .getProducts(token)
                }.map { products ->
                    products.map {
                        productMapper.mapFromEntity(it)
                    }
                }
    }

    override fun getProductDetail(productId: Int): Observable<Product> {
        return userFactory
                .getCacheDataSource()
                .getUserToken()
                .flatMap {
                    token->factory
                        .getRemoteDataSource()
                        .getProductDetails(token,productId)
                }.map {
                    productMapper.mapFromEntity(it)
                }
    }
}