package ir.brn.data.source.product

import io.reactivex.Completable
import io.reactivex.Observable
import ir.brn.data.model.product.CategoryEntity
import ir.brn.data.model.product.ProductEntity
import ir.brn.data.repository.product.ProductDataSource
import ir.brn.data.repository.product.ProductRemote
import javax.inject.Inject

class ProductRemoteDataStore @Inject constructor(
        private val productRemote: ProductRemote) : ProductDataSource {

    override fun clearCache(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(token: String): Observable<List<CategoryEntity>> {
        return productRemote
                .getCategories(token)
    }

    override fun getChildCategories(token: String, categoryId: Int): Observable<List<CategoryEntity>> {
        return productRemote
                .getChildCategories(token, categoryId)
    }

    override fun getProducts(token: String): Observable<List<ProductEntity>> {
        return productRemote
                .getProducts(token)
    }

    override fun getProductDetails(token: String, productId: Int): Observable<ProductEntity> {
        return productRemote
                .getProductDetails(token, productId)
    }
}