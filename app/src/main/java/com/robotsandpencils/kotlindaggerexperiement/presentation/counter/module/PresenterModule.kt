package com.robotsandpencils.kotlindaggerexperiement.presentation.counter.module

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue
import com.robotsandpencils.kotlindaggerexperiement.presentation.counter.Contract
import com.robotsandpencils.kotlindaggerexperiement.presentation.counter.Presenter
import dagger.Module
import dagger.Provides

// This Kotlin file construction seems odd, but follows from this:
// https://stackoverflow.com/questions/44075860/module-must-be-set
@Module
internal abstract class PresenterModule {
    @Module
    companion object {
        @Provides
        @Scope
        @JvmStatic internal fun providesPresenter(mainRepository: MainRepository): Contract.Presenter {
            return Presenter(mainRepository, UiThreadQueue())
        }
    }
}
