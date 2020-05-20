package ir.brn.data.source.product

import io.reactivex.Completable
import io.reactivex.Observable
import ir.brn.data.model.product.CategoryEntity
import ir.brn.data.model.product.ProductEntity
import ir.brn.data.repository.product.ProductCache
import ir.brn.data.repository.product.ProductDataSource
import javax.inject.Inject

class ProductCacheDataStore @Inject constructor(
        private val productCache: ProductCache):ProductDataSource {

    override fun clearCache(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(token: String): Observable<List<CategoryEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChildCategories(token: String, categoryId: Int): Observable<List<CategoryEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProducts(token: String): Observable<List<ProductEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProductDetails(token: String, productId: Int): Observable<ProductEntity> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}