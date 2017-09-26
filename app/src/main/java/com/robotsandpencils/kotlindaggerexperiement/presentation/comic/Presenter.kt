package com.robotsandpencils.kotlindaggerexperiement.presentation.comic

import com.github.ajalt.timberkt.e
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.XkcdRepository
import com.robotsandpencils.kotlindaggerexperiement.net.xkcd.XkcdResponse
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class Presenter(private val repository: XkcdRepository, uiThreadQueue: UiThreadQueue) :
        BasePresenter<Contract.View>(uiThreadQueue), Contract.Presenter {

    private lateinit var disposables: CompositeDisposable

    override fun attach(view: Contract.View) {
        super.attach(view)

        val viewModel = view.getViewModel()

        disposables = CompositeDisposable()

        if (viewModel.num.value == null) {
            disposables.add(repository.getLatestComic()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                updateViewModel(viewModel, it)
                            },
                            {
                                e { it.toString() }
                            }
                    ))
        } else {
            val currentNum = viewModel.num.value!!

            disposables.add(repository.getComic(currentNum)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            {
                                updateViewModel(viewModel, it)
                            },
                            {
                                e { it.toString() }
                            }
                    ))
        }
    }

    private fun updateViewModel(viewModel: ComicViewModel, response: XkcdResponse) {
        viewModel.imageUrl.value = response.img
        viewModel.title.value = response.title
        viewModel.num.value = response.num
    }

    override fun showPreviousComic() {

        val viewModel = view?.getViewModel() ?: return
        val currentNum = viewModel.num.value ?: return

        disposables.add(repository.getComic(currentNum - 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            updateViewModel(viewModel, it)
                        },
                        { error ->
                            e { error.toString() }
                        }
                ))
    }

    override fun detach() {
        super.detach()
        disposables.dispose()
    }
}