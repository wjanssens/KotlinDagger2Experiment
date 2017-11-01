package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import android.support.annotation.StringRes

/**
 * Main Contract
 */
interface Contract {

    /**
     * Presenter Contract
     */
    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun navigate(id: Int): Boolean
    }

    /**
     * View Contract
     */
    interface View : com.robotsandpencils.kotlindaggerexperiement.presentation.base.View {
        fun getViewModel() : MainViewModel
        fun setTitle(text : String)
        fun setTitle(@StringRes text: Int)
        fun clearFields()
        fun showError(message: String?)

        fun showHome()
        fun showComic()
        fun showClock()
        fun showEvent()

        fun hideHome()
        fun hideComic()
        fun hideClock()
        fun hideEvent()
    }
}