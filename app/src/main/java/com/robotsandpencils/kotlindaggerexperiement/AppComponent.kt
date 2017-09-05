package com.robotsandpencils.kotlindaggerexperiement

import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by nealsanche on 2017-09-05.
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class, MainModule::class))
interface AppComponent {
    fun inject(app: App)
}