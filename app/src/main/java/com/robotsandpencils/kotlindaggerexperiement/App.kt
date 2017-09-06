package com.robotsandpencils.kotlindaggerexperiement

import android.app.Activity
import android.app.Application
import com.robotsandpencils.kotlindaggerexperiement.app.modules.AppComponent
import com.robotsandpencils.kotlindaggerexperiement.app.modules.AppModule
import com.robotsandpencils.kotlindaggerexperiement.app.modules.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


/**
 * App
 */

class App : Application(), HasActivityInjector {

    @Inject
    internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

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
