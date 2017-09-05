package com.robotsandpencils.kotlindaggerexperiement

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by nealsanche on 2017-09-05.
 */

@Module class AppModule(val app: App) {
    @Provides @Singleton fun provideApp() = app
}