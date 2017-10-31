package com.robotsandpencils.kotlindaggerexperiement.presentation.clock.module

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.ClockRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.LifecycleAwareUiThreadQueue
import com.robotsandpencils.kotlindaggerexperiement.presentation.clock.ClockFragment
import com.robotsandpencils.kotlindaggerexperiement.presentation.clock.Contract
import com.robotsandpencils.kotlindaggerexperiement.presentation.clock.Presenter
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
        @JvmStatic internal fun providesPresenter(fragment: ClockFragment, repository: ClockRepository): Contract.Presenter {
            return Presenter(repository, LifecycleAwareUiThreadQueue(fragment))
        }
    }
}
