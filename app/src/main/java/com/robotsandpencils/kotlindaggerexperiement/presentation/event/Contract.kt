package com.robotsandpencils.kotlindaggerexperiement.presentation.event

interface Contract {

    interface Presenter : com.robotsandpencils.kotlindaggerexperiement.presentation.base.Presenter<View>

    interface View : com.robotsandpencils.kotlindaggerexperiement.presentation.base.View {
        fun getViewModel(): EventViewModel
    }
}