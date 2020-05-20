package ir.brn.data.repository.product

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import ir.brn.data.model.product.CategoryEntity
import ir.brn.data.model.product.ProductEntity

interface ProductCache {

    fun clearCache(): Completable

    fun getCategories(): Flowable<List<CategoryEntity>>

    fun getProducts(): Flowable<List<ProductEntity>>

    fun areProductCached(): Single<Boolean>

    fun setLastCacheTime(time: Long): Completable

    fun isCacheExpired(): Single<Boolean>
}