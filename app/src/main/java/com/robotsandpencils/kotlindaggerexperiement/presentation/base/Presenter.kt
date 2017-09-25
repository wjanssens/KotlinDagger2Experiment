package com.robotsandpencils.kotlindaggerexperiement.presentation.base

interface Presenter<in V : View> {
    fun attach(view: V)
    fun detach()
}
