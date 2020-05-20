package ir.brn.domain.repository

import io.reactivex.Observable
import ir.brn.domain.model.product.Category
import ir.brn.domain.model.product.Product

interface ProductRepository {

    fun getCategories():Observable<List<Category>>

    fun getChildCategories(id:Int):Observable<List<Category>>

    fun getProducts():Observable<List<Product>>

    fun getProductDetail(productId:Int):Observable<Product>


}