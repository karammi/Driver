package ir.brn.cache

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import ir.brn.data.model.product.CategoryEntity
import ir.brn.data.model.product.ProductEntity
import ir.brn.data.repository.product.ProductCache
import ir.brn.data.repository.product.ProductRemote
import javax.inject.Inject

class ProductCacheImpl @Inject constructor() : ProductCache {

    override fun clearCache(): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCategories(): Flowable<List<CategoryEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProducts(): Flowable<List<ProductEntity>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun areProductCached(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setLastCacheTime(time: Long): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCacheExpired(): Single<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}