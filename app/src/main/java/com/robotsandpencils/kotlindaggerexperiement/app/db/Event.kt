package com.robotsandpencils.kotlindaggerexperiement.app.db

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity data class Event(
        @PrimaryKey val uid: Int,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "location") val location: String,
        @ColumnInfo(name = "start") val start: Long,
        @ColumnInfo(name = "end") val end: Long,
        @ColumnInfo(name = "allday") val allDay: Boolean,
        @ColumnInfo(name = "tz") val timezone: String)
