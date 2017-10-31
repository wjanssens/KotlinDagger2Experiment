package com.robotsandpencils.kotlindaggerexperiement.presentation.clock

import com.github.ajalt.timberkt.e
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.ClockRepository
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Presenter(private val repository: ClockRepository, uiThreadQueue: UiThreadQueue) :
        BasePresenter<Contract.View>(uiThreadQueue), Contract.Presenter {

    private lateinit var disposables: CompositeDisposable

    override fun attach(view: Contract.View) {
        super.attach(view)

        disposables = CompositeDisposable()
        disposables.add(repository.getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { updateViewModel(view.getViewModel(), it) },
                        { e { it.toString() } }
                ))
    }

    private fun updateViewModel(viewModel: ClockViewModel, value: List<String>) {
        viewModel.apply {
            list.value = value
        }
    }

    override fun detach() {
        super.detach()
        disposables.dispose()
    }
}