package ir.brn.driver

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
//import com.mapbox.mapboxsdk.Mapbox
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import ir.brn.driver.injection.DaggerApplicationComponent
import timber.log.Timber
import javax.inject.Inject

class DriverApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        setupTimber()

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

}