package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository

/**
 * A super simple presenter
 */

class Presenter(mainRepository: MainRepository) : Contract.Presenter {

    override fun attach(view : Contract.View) {
        view.setTitle("Presenter Attached")
    }

    override fun detach() {
        // Nothing to do here
    }

}

