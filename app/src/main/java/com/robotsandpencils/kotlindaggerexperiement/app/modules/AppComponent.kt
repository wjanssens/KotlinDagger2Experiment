package com.robotsandpencils.kotlindaggerexperiement.app.modules

import com.robotsandpencils.kotlindaggerexperiement.App
import com.robotsandpencils.kotlindaggerexperiement.presentation.main.module.MainModule
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Main App Component
 */
@Singleton
@Component(modules = arrayOf(AndroidSupportInjectionModule::class, AppModule::class,
        MainModule::class,
        com.robotsandpencils.kotlindaggerexperiement.presentation.counter.module.Module::class))
interface AppComponent {
    fun inject(app: App)
}