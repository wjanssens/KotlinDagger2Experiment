package com.robotsandpencils.kotlindaggerexperiement.presentation.counter

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

class CounterViewModel : ViewModel() {
    lateinit var count: LiveData<Int>
}