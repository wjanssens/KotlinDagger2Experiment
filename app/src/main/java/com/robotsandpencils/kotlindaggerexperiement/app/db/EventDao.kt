package com.robotsandpencils.kotlindaggerexperiement.app.db

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface EventDao {
    @Query("SELECT count(*) FROM event")
    fun count(): LiveData<Int>

    @Query("SELECT * FROM event")
    fun selectAll(): LiveData<List<Event>>

    @Query("SELECT * FROM event WHERE start >= :now AND end <= :now limit 1")
    fun selectNext(now: Long): LiveData<Event>

    @Insert
    fun insert(vararg events: Event)

    @Delete
    fun delete(event: Event)
}

