package ir.brn.driver.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides
import ir.brn.cache.*
import ir.brn.cache.db.DriverDatabase
import ir.brn.data.repository.address.AddressCache
import ir.brn.data.repository.brokerLoad.BrokerLoadCache
import ir.brn.data.repository.core.PackagingCache
import ir.brn.data.repository.core.ProvinceCache
import ir.brn.data.repository.core.TrucksCache
import ir.brn.data.repository.load.LoadCache
import ir.brn.data.repository.product.ProductCache
import ir.brn.data.repository.user.UserCache
import ir.brn.data.repository.wall.WallCache

@Module
class CacheModule {

    @Provides
    fun providesDriverDatabase(context: Context): DriverDatabase {
        return DriverDatabase.getInstance(context)
    }

    @Provides
    fun provideUserCache(userCache: UserCacheImpl): UserCache = userCache


    @Provides
    fun provideWallCache(wallCache: WallCacheImpl): WallCache = wallCache

    @Provides
    fun provideProductCache(productCache: ProductCacheImpl): ProductCache = productCache



}