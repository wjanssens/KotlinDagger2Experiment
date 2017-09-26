package com.robotsandpencils.kotlindaggerexperiement.presentation.comic

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class ComicViewModel : ViewModel() {
    val title: MutableLiveData<String> = MutableLiveData()
    val imageUrl: MutableLiveData<String> = MutableLiveData()
    var num: MutableLiveData<Int> = MutableLiveData()
}