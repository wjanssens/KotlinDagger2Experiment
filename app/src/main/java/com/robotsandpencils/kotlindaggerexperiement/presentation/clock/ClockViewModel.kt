package com.robotsandpencils.kotlindaggerexperiement.presentation.clock

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ClockViewModel : ViewModel() {
    var list: MutableLiveData<List<String>> = MutableLiveData()
}