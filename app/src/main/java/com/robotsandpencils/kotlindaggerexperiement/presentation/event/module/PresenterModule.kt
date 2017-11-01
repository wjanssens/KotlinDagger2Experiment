package com.robotsandpencils.kotlindaggerexperiement.presentation.event.module

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.EventRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.LifecycleAwareUiThreadQueue
import com.robotsandpencils.kotlindaggerexperiement.presentation.event.Contract
import com.robotsandpencils.kotlindaggerexperiement.presentation.event.EventFragment
import com.robotsandpencils.kotlindaggerexperiement.presentation.event.Presenter
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
        @JvmStatic internal fun providesPresenter(fragment: EventFragment,
                                                  repository: EventRepository): Contract.Presenter {
            return Presenter(LifecycleAwareUiThreadQueue(fragment), repository)
        }
    }
}
