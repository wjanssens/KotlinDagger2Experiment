package com.robotsandpencils.kotlindaggerexperiement.presentation.main

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
    }

    /**
     * View Contract
     */
    interface View {
        fun setTitle(text : String)
    }
}