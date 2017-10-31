package com.robotsandpencils.kotlindaggerexperiement.presentation.main.module

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.XkcdRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.LifecycleAwareUiThreadQueue
import com.robotsandpencils.kotlindaggerexperiement.presentation.main.Contract
import com.robotsandpencils.kotlindaggerexperiement.presentation.main.MainActivity
import com.robotsandpencils.kotlindaggerexperiement.presentation.main.Presenter
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
        @JvmStatic internal fun providesPresenter(activity: MainActivity): Contract.Presenter {
            return Presenter(LifecycleAwareUiThreadQueue(activity))
        }
    }
}
