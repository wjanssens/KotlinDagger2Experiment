package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import android.util.Log
import com.robotsandpencils.kotlindaggerexperiement.app.db.User
import com.robotsandpencils.kotlindaggerexperiement.app.repositories.MainRepository
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * A super simple presenter
 */

class Presenter(val mainRepository: MainRepository) : Contract.Presenter {

    private lateinit var view: Contract.View

    override fun attach(view: Contract.View) {
        this.view = view;
        view.setTitle("Presenter Attached")

        val viewModel = view.getViewModel()
        viewModel.users = mainRepository.getUserDao().getAll()
    }

    override fun detach() {
        // Nothing to do here
    }

    override fun addUser(id: String, firstName: String, lastName: String) {

        // So, looked around for a nice way to do something in the background and could have
        // used RxJava here, but decided just to use Anko's doAsync DSL. It's actually simple,
        // allows exception handling, and allows hoisting something back onto the UI thread on
        // success. Sort of a nice, simple way to do database work, actually.

        doAsync(task = {
            mainRepository.getUserDao().insertAll(User(id.toInt(), firstName, lastName))
            uiThread {
                view.setTitle("Record Added")
                view.clearFields()
            }
        }, exceptionHandler = { exception ->
            Log.e("DB", "Unable to save: ${Thread.currentThread().name}", exception)

            // Looks like the errors come out on the pool thread, so we can redirect to the UI
            // thread with another async block, or find a better way.
            doAsync {
                uiThread {
                    view.showError(exception.message)
                }
            }
        })
    }

    override fun removeUser(user: User) {
        doAsync {
            mainRepository.getUserDao().delete(user)
            uiThread {
                view.setTitle("Record Deleted")
            }
        }
    }
}
