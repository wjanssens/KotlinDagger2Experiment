package com.robotsandpencils.kotlindaggerexperiement

import dagger.Component
import javax.inject.Singleton

/**
 * Created by nealsanche on 2017-09-05.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class, MainModule::class))
interface AppComponent {
    fun inject(app: App)
}