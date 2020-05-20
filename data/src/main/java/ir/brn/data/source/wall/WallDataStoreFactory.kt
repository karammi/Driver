package ir.brn.data.source.wall

import ir.brn.data.repository.wall.WallDataSource
import javax.inject.Inject

class WallDataStoreFactory @Inject constructor(
        private val wallCacheDataStore: WallCacheDataStore,
        private val wallRemoteDataStore: WallRemoteDataStore) {

    fun getDataSource(wallCached: Boolean,
                      cacheExpired: Boolean): WallDataSource {
        return if (wallCached && !cacheExpired)
            wallCacheDataStore
        else
            wallRemoteDataStore
    }

    fun getCacheDataSource(): WallDataSource {
        return wallCacheDataStore
    }

    fun getRemoteDataSource(): WallDataSource {
        return wallRemoteDataStore
    }
}