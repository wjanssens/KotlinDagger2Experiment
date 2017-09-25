package com.robotsandpencils.kotlindaggerexperiement.presentation.main.module

import com.robotsandpencils.kotlindaggerexperiement.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Main Module: Uses ContributesAndroidInjector to generate a component and builder automatically.
// Using this to provide a presenter module for this scope.
@Module
internal abstract class MainModule {
    @MainScope
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class))
    abstract fun provideMainActivityInjector(): MainActivity
}