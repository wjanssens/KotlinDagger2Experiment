package com.robotsandpencils.kotlindaggerexperiement

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import javax.inject.Inject
import dagger.android.AndroidInjector
import dagger.android.HasActivityInjector



/**
 * Created by nealsanche on 2017-09-05.
 */

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingActivityInjector : DispatchingAndroidInjector<Activity>

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingActivityInjector
    }
}
