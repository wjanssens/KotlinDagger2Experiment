package com.robotsandpencils.kotlindaggerexperiement.presentation.counter

import com.github.ajalt.timberkt.e
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue

class Presenter(private val mainRepository: MainRepository, uiThreadQueue: UiThreadQueue) :
        BasePresenter<Contract.View>(uiThreadQueue), Contract.Presenter {

    override fun attach(view: Contract.View) {
        super.attach(view)

        e { "Counter Presenter Attached" }

        val viewModel = view.getViewModel()
        viewModel.count = mainRepository.getUserDao().getCount()
    }
}