package com.robotsandpencils.kotlindaggerexperiement

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Scope


/**
 * Created by nealsanche on 2017-09-05.
 */

@Scope
annotation class MainScope

@Module
internal abstract class MainModule {
    @MainScope
    @ContributesAndroidInjector(modules = arrayOf(RepositoryModule::class))
    abstract fun provideMainActivityInjector(): MainActivity
}


// This Kotlin file construction seems odd, but follows from this:
// https://stackoverflow.com/questions/44075860/module-must-be-set
@Module
internal abstract class RepositoryModule {

    @Module
    companion object {
        @Provides
        @MainScope
        @JvmStatic
        internal fun providesMainRepository(): MainRepository {
            return MainRepository()
        }
    }
}
