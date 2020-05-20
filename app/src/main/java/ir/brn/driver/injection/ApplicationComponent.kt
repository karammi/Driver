package ir.brn.driver.injection

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import ir.brn.driver.DriverApplication
import ir.brn.driver.injection.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    ApplicationModule::class,
    CacheModule::class,
    UiModule::class,
    PresentationModule::class,
    RemoteModule::class,
    DataModule::class])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: DriverApplication)
}