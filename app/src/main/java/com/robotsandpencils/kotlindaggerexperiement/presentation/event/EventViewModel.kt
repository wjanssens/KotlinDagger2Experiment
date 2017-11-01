package com.robotsandpencils.kotlindaggerexperiement.presentation.event

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class EventViewModel : ViewModel() {
    val name: MutableLiveData<String> = MutableLiveData()
    val location: MutableLiveData<String> = MutableLiveData()
    val start: MutableLiveData<Long> = MutableLiveData()
    val end: MutableLiveData<Long> = MutableLiveData()
    val allDay: MutableLiveData<Boolean> = MutableLiveData()
    val timeZone: MutableLiveData<String> = MutableLiveData()
}