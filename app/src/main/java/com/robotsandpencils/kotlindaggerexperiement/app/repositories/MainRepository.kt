package com.robotsandpencils.kotlindaggerexperiement.app.repositories

import com.robotsandpencils.kotlindaggerexperiement.App
import com.robotsandpencils.kotlindaggerexperiement.app.db.AppDatabase
import com.robotsandpencils.kotlindaggerexperiement.app.db.UserDao

/**
 * Simple, empty main repository.
 */

class MainRepository(val app: App, private val database: AppDatabase) {
    fun getUserDao() : UserDao {
        return database.userDao()
    }
}