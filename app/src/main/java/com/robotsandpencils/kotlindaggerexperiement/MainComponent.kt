package com.robotsandpencils.kotlindaggerexperiement

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.Subcomponent
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import javax.inject.Scope


/**
 * Created by nealsanche on 2017-09-05.
 */

@Scope
annotation class MainScope

@Module(subcomponents = arrayOf(MainSubComponent::class))
internal abstract class MainModule {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun bindYourActivityInjectorFactory(builder: MainSubComponent.Builder): AndroidInjector.Factory<out Activity>
}

@MainScope
@Subcomponent(modules = arrayOf())
interface MainSubComponent : AndroidInjector<MainActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()
}
