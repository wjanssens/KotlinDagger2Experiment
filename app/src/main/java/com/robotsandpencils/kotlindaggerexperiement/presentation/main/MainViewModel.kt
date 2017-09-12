package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.LiveData
import com.robotsandpencils.kotlindaggerexperiement.app.db.User


class MainViewModel : ViewModel() {
    lateinit var users: LiveData<List<User>>
}