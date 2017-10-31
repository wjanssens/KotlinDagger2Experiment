package com.robotsandpencils.kotlindaggerexperiement.presentation.counter

import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue

class Presenter(uiThreadQueue: UiThreadQueue) :
        BasePresenter<Contract.View>(uiThreadQueue), Contract.Presenter {

    override fun attach(view: Contract.View) {
        super.attach(view)

        val viewModel = view.getViewModel()
        viewModel.apply {
            count.value = 0
        }
//        viewModel.count = xkcdRepository.getUserDao().getCount()
    }
}