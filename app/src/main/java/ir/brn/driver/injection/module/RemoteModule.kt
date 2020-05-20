package ir.brn.driver.injection.module

import dagger.Module
import dagger.Provides
import ir.brn.data.repository.address.AddressRemote
import ir.brn.data.repository.brokerLoad.BrokerLoadRemote
import ir.brn.data.repository.core.PackagingRemote
import ir.brn.data.repository.core.ProvinceRemote
import ir.brn.data.repository.core.TrucksRemote
import ir.brn.data.repository.load.LoadRemote
import ir.brn.data.repository.product.ProductRemote
import ir.brn.data.repository.user.UserRemote
import ir.brn.data.repository.wall.WallRemote
import ir.brn.driver.BuildConfig
import ir.brn.remote.*
import ir.brn.remote.service.ApiService
import ir.brn.remote.service.ApiServiceFactory
import ir.brn.remote.WallRemoteImpl

@Module
class RemoteModule {

    @Provides
    fun provideApiService(): ApiService {
        return ApiServiceFactory.makeApiService(BuildConfig.DEBUG)
    }



    @Provides
    fun bindUserRemote(remoteImpl: UserRemoteImpl): UserRemote = remoteImpl


    @Provides
    fun bindWallRemote(wallRemote: WallRemoteImpl): WallRemote = wallRemote

    @Provides
    fun bindProductRemote(productRemote: ProductRemoteImpl): ProductRemote = productRemote


}