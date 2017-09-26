package com.robotsandpencils.kotlindaggerexperiement.app.modules

import android.arch.persistence.room.Room
import com.robotsandpencils.kotlindaggerexperiement.App
import com.robotsandpencils.kotlindaggerexperiement.app.db.AppDatabase
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.XkcdRepository
import com.robotsandpencils.kotlindaggerexperiement.net.xkcd.XkcdAPI
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * App Module
 */

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideAppDatabase(app: App) : AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "database-name").build()
    }

    @Provides
    @Singleton
    fun provideMainRepository(database: AppDatabase) = MainRepository(app, database)

    @Provides
    @Singleton
    fun provideXkcdRepository(api: XkcdAPI) = XkcdRepository(api)

    @Provides
    @Singleton
    fun provideXkcdApi(app: App): XkcdAPI {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://example.io")
                .build()
                .create(XkcdAPI::class.java)
    }
}