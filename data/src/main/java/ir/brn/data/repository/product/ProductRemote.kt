package ir.brn.data.repository.product

import io.reactivex.Observable
import ir.brn.data.model.product.CategoryEntity
import ir.brn.data.model.product.ProductEntity

interface ProductRemote {

    fun getCategories(token: String): Observable<List<CategoryEntity>>

    fun getChildCategories(token: String, categoryId: Int): Observable<List<CategoryEntity>>

    fun getProducts(token: String): Observable<List<ProductEntity>>

    fun getProductDetails(token: String, productId: Int): Observable<ProductEntity>
}