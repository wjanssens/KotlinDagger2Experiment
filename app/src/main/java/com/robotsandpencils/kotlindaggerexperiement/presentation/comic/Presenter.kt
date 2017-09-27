package com.robotsandpencils.kotlindaggerexperiement.presentation.comic

import com.github.ajalt.timberkt.e
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.XkcdRepository
import com.robotsandpencils.kotlindaggerexperiement.net.xkcd.XkcdResponse
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.BasePresenter
import com.robotsandpencils.kotlindaggerexperiement.presentation.base.UiThreadQueue
import io.reactivex.Single
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

        when {
            viewModel.num.value == null -> requestLatestComic(viewModel)
            else -> {
                val currentNum = viewModel.num.value!!
                requestComic(currentNum, viewModel)
            }
        }
    }

    override fun detach() {
        super.detach()
        disposables.dispose()
    }

    override fun showPreviousComic() {
        val viewModel = view?.getViewModel() ?: return
        val currentNum = viewModel.num.value ?: return

        requestComic(currentNum - 1, viewModel)
    }

    private fun requestLatestComic(viewModel: ComicViewModel) {
        requestAndUpdateViewModel(repository.getLatestComic(), viewModel)
    }

    private fun requestComic(comicNumber: Int, viewModel: ComicViewModel) {
        requestAndUpdateViewModel(repository.getComic(comicNumber), viewModel)
    }

    private fun requestAndUpdateViewModel(request: Single<XkcdResponse>, viewModel: ComicViewModel) {
        disposables.add(request
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { updateViewModel(viewModel, it) },
                        { e { it.toString() } }
                ))
    }

    private fun updateViewModel(viewModel: ComicViewModel, response: XkcdResponse) {
        viewModel.apply {
            imageUrl.value = response.img
            title.value = response.title
            num.value = response.num
        }
    }
}