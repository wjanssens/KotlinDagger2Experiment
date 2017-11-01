package com.robotsandpencils.kotlindaggerexperiement.app.repositories

import com.robotsandpencils.kotlindaggerexperiement.app.db.AppDatabase
import com.robotsandpencils.kotlindaggerexperiement.app.db.EventDao

class EventRepository(val database: AppDatabase) {
    fun dao(): EventDao = database.eventDao()
}