package ir.brn.data.source.product

import ir.brn.data.repository.product.ProductDataSource
import javax.inject.Inject

class ProductDataStoreFactory @Inject constructor(
        private val productCacheDataStore: ProductCacheDataStore,
        private val productRemoteDataStore: ProductRemoteDataStore) {

    fun getDataSource(productCached: Boolean,
                      isExpired: Boolean): ProductDataSource {
        return if (productCached && !isExpired)
            productCacheDataStore
        else
            productRemoteDataStore
    }

    fun getRemoteDataSource(): ProductDataSource {
        return productRemoteDataStore
    }

    fun getCacheDataSource(): ProductDataSource {
        return productCacheDataStore
    }
}