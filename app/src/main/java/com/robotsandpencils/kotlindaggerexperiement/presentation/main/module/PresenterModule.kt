package com.robotsandpencils.kotlindaggerexperiement.presentation.main.module

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
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
            return Presenter(mainRepository)
        }
    }
}