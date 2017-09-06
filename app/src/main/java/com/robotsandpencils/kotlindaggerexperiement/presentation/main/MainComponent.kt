package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope


/**
 * Main Module boilerplate.
 */

// New scope for component
@Scope
annotation class MainScope

// Main Module: Uses ContributesAndroidInjector to generate a component and builder automatically.
// Using this to provide a presenter module for this scope.
@Module
internal abstract class MainModule {
    @MainScope
    @ContributesAndroidInjector(modules = arrayOf(PresenterModule::class))
    abstract fun provideMainActivityInjector(): MainActivity
}


// This Kotlin file construction seems odd, but follows from this:
// https://stackoverflow.com/questions/44075860/module-must-be-set
@Module
internal abstract class PresenterModule {

    @Module
    companion object {

        /**
         * Provide the Presenter
         */
        @Provides
        @MainScope
        @JvmStatic internal fun providesPresenter(mainRepository: MainRepository): Contract.Presenter {
            return Presenter(mainRepository)
        }
    }
}