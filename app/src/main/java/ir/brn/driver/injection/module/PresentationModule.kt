package ir.brn.driver.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import ir.brn.driver.injection.ViewModelFactory
import ir.brn.presentation.*
import kotlin.reflect.KClass

@Module
abstract class PresentationModule {


    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(WallViewModel::class)
    abstract fun bindWallViewModel(wallViewModel: WallViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindViewModel(productViewModel: ProductViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}


@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)