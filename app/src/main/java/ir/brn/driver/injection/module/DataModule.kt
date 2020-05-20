package ir.brn.driver.injection.module

import dagger.Binds
import dagger.Module
import ir.brn.data.*
import ir.brn.domain.repository.*

@Module
abstract class DataModule {



    @Binds
    abstract fun bindUserDataRepository(userDataRepository: UserDataRepository): UserRepository



    @Binds
    abstract fun bindWallDataRepository(wallDataRepository: WallDataRepository):WallRepository

    @Binds
    abstract fun bindProductDataRepository(productDataRepository: ProductDataRepository):ProductRepository




}