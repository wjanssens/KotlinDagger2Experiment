package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import com.robotsandpencils.kotlindaggerexperiement.app.db.User

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
        fun addUser(id: String, firstName: String, lastName: String)
        fun removeUser(user: User)
    }

    /**
     * View Contract
     */
    interface View : com.robotsandpencils.kotlindaggerexperiement.presentation.base.View {
        fun getViewModel() : MainViewModel
        fun setTitle(text : String)
        fun clearFields()
        fun showError(message: String?)
    }
}