package com.robotsandpencils.kotlindaggerexperiement.app.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert


/**
 * Created by nealsanche on 2017-09-08.
 */

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll() : List<User>

    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds : IntArray): List<User>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)
}