package com.robotsandpencils.kotlindaggerexperiement.presentation.event.module

import com.robotsandpencils.kotlindaggerexperiement.presentation.event.EventFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

// Main Module: Uses ContributesAndroidInjector to generate a component and builder automatically.
// Using this to provide a presenter module for this scope.
@Module
internal abstract class Module {
    @Scope
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class))
    abstract fun provideInjector(): EventFragment
}