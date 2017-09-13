package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import android.arch.lifecycle.LifecycleActivity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import com.robotsandpencils.kotlindaggerexperiement.R
import com.robotsandpencils.kotlindaggerexperiement.app.db.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.UpdatingGroup
import com.xwray.groupie.ViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : LifecycleActivity(), Contract.View {
    @Inject lateinit var presenter: Contract.Presenter

    private val groupAdapter = GroupAdapter<ViewHolder>()
    private val updatingGroup = UpdatingGroup()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        presenter.attach(this)

        connectView()
    }

    private fun connectView() {
        connectButton()
        connectRecyclerView()
    }

    private fun connectButton() {
        button.setOnClickListener { _ ->
            Log.d("Button", "${idNumber.text} ${firstName.text} ${lastName.text}")

            // Tell the presenter to perform the database insert
            presenter.addUser(idNumber.text.toString(), firstName.text.toString(), lastName.text.toString())
        }
    }

    private fun connectRecyclerView() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = groupAdapter

        groupAdapter.add(updatingGroup)

        getViewModel().users.observe(this, Observer { users ->
            Log.d("USERS", "Got some users: $users thread =  ${Thread.currentThread().name}")

            updatingGroup.update(getUserItems(users))
        })

        groupAdapter.apply {
            setOnItemClickListener { item, _ ->
                presenter.removeUser((item as UserItem).user)
            }
        }
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun getUserItems(users: List<User>?): List<Item<ViewHolder>> {
        val items = ArrayList<UserItem>()

        users?.forEach { user ->
            items.add(UserItem(user))
        }

        return items
    }

    override fun clearFields() {
        idNumber.requestFocus()
        idNumber.text.clear()
        firstName.text.clear()
        lastName.text.clear()
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun setTitle(text: String) {
        message.text = text
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}
