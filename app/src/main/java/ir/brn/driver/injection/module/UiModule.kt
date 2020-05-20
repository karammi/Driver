package ir.brn.driver.injection.module

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import ir.brn.domain.executor.PostExecutionThread
import ir.brn.driver.UiThread
import ir.brn.driver.about.AboutFragment
import ir.brn.driver.broker.*
import ir.brn.driver.load.LoadFragment
import ir.brn.driver.main.MainActivity
import ir.brn.driver.login.ConfirmFragment
import ir.brn.driver.login.LoginActivity
import ir.brn.driver.login.LoginFragment
import ir.brn.driver.main.MainFragment
import ir.brn.driver.map.MapFragment
import ir.brn.driver.myLoads.MyLoadFragment
import ir.brn.driver.packaging.PackageBootomDialogFragment
import ir.brn.driver.product.ProductFragment
import ir.brn.driver.splash.SplashActivity
import ir.brn.driver.support.SupportFragment
import ir.brn.driver.tweet.AddTweet
import ir.brn.driver.user.ProvinceBottomSheetDialogFragment
import ir.brn.driver.user.TruckBottomSheetDialogFragment
import ir.brn.driver.user.UserFragment
import ir.brn.driver.tweet.TweetFragment

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread




    @ContributesAndroidInjector
    abstract fun contributeMainFragment(): MainFragment



    @ContributesAndroidInjector
    abstract fun contributeWallFragment(): TweetFragment

    @ContributesAndroidInjector
    abstract fun contributeAddTweet(): AddTweet




    @ContributesAndroidInjector
    abstract fun contributeProductFragment(): ProductFragment
}