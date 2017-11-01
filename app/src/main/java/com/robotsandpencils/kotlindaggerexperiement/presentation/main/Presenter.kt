package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import com.robotsandpencils.kotlindaggerexperiement.R
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue

/**
 * A super simple presenter
 */

class Presenter(uiThreadQueue: UiThreadQueue) :
        BasePresenter<Contract.View>(uiThreadQueue), Contract.Presenter {

    override fun attach(view: Contract.View) {
        super.attach(view)

        view.setTitle("Presenter Attached")

        val viewModel = view.getViewModel()
    }

    override fun navigate(id: Int): Boolean {
        when (id) {
            R.id.navigation_home -> {
                view?.setTitle(R.string.title_home)
                view?.showHome()
                view?.hideComic()
                view?.hideClock()
                view?.hideEvent()
                return true
            }
            R.id.navigation_comic -> {
                view?.setTitle(R.string.title_comic)
                view?.hideHome()
                view?.showComic()
                view?.hideClock()
                view?.hideEvent()
                return true
            }
            R.id.navigation_clock -> {
                view?.setTitle(R.string.title_clock)
                view?.hideHome()
                view?.hideComic()
                view?.showClock()
                view?.hideEvent()
                return true
            }
            R.id.navigation_event -> {
                view?.setTitle(R.string.title_event)
                view?.hideHome()
                view?.hideComic()
                view?.hideClock()
                view?.showEvent()
                return true
            }
        }
        return false
    }
}
