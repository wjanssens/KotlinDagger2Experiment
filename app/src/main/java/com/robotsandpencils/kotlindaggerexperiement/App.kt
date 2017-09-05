package com.robotsandpencils.kotlindaggerexperiement

import android.app.Application

/**
 * Created by nealsanche on 2017-09-05.
 */

class App : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent
                .builder()
                .build()
    }

    override fun onCreate() {
        super.onCreate()
    }
}