package com.robotsandpencils.kotlindaggerexperiement.presentation.main.module

import com.github.ajalt.timberkt.e
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue
import com.robotsandpencils.kotlindaggerexperiement.presentation.main.Contract
import com.robotsandpencils.kotlindaggerexperiement.presentation.main.Presenter
import dagger.Module
import dagger.Provides

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

            e { Thread.currentThread().name }

            return Presenter(mainRepository, UiThreadQueue())
        }
    }
}