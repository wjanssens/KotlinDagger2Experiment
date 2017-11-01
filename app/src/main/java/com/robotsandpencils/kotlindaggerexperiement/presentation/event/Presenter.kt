package com.robotsandpencils.kotlindaggerexperiement.presentation.event

import com.robotsandpencils.kotlindaggerexperiement.app.repositories.EventRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue

class Presenter(uiThreadQueue: UiThreadQueue,
                private val eventRepository: EventRepository) :
        BasePresenter<Contract.View>(uiThreadQueue), Contract.Presenter {

    override fun attach(view: Contract.View) {
        super.attach(view)

        val event = eventRepository.dao().selectNext(System.currentTimeMillis()).value
        if (event != null) {
            val viewModel = view.getViewModel()
            viewModel.apply {
                name.value = event.name
                location.value = event.location
                start.value = event.start
                end.value = event.end
                timeZone.value = event.timezone
                allDay.value = event.allDay
            }
        }
    }
}