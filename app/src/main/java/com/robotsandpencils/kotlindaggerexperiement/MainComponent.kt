package com.robotsandpencils.kotlindaggerexperiement

import dagger.Module
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Scope

/**
 * Created by nealsanche on 2017-09-05.
 */

@Scope
annotation class MainScope

@Module
class MainModule(val activity: MainActivity) {

    @Provides
    @MainScope
    fun provideMainRepository(): MainRepository {
        return MainRepository(activity)
    }

}

@MainScope
@Subcomponent(modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}