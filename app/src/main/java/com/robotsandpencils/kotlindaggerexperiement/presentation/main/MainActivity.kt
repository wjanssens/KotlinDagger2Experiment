package com.robotsandpencils.kotlindaggerexperiement.presentation.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.robotsandpencils.kotlindaggerexperiement.R
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.UpdatingGroup
import com.xwray.groupie.ViewHolder
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), Contract.View {

    companion object {
        val CURRENT_TAB_ITEM: String = "CurrentTabItem"
    }

    @Inject lateinit var presenter: Contract.Presenter

    private val groupAdapter = GroupAdapter<ViewHolder>()
    private val updatingGroup = UpdatingGroup()
    private var currentTabItem: Int = R.id.navigation_home

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        currentTabItem = item.itemId
        return@OnNavigationItemSelectedListener presenter.navigate(item.itemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.attach(this)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        if (savedInstanceState != null) {
            currentTabItem = savedInstanceState.getInt(CURRENT_TAB_ITEM)
            navigation.selectedItemId = currentTabItem
        } else {
            showHome()
            hideComic()
            hideClock()
        }

        connectView()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(CURRENT_TAB_ITEM, currentTabItem)
    }

    private fun connectView() {
        connectButton()
        connectRecyclerView()
    }

    private fun connectButton() {
        button.setOnClickListener { _ ->
            Log.d("Button", "${idNumber.text} ${firstName.text} ${lastName.text}")
        }
    }

    private fun connectRecyclerView() {
        list.layoutManager = LinearLayoutManager(this)
        list.adapter = groupAdapter

        groupAdapter.add(updatingGroup)

        groupAdapter.apply {
            setOnItemClickListener { item, _ ->
            }
        }
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun clearFields() {
        idNumber.requestFocus()
        arrayOf(idNumber.text, firstName.text, lastName.text)
                .forEach { it.clear() }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

    override fun setTitle(text: String) {
        message.text = text
    }

    override fun setTitle(text: Int) {
        message.text = getString(text)
    }

    override fun getViewModel(): MainViewModel {
        return ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun showHome() {
        homeLayout.visibility = View.VISIBLE
    }
    override fun hideHome() {
        homeLayout.visibility = View.GONE
    }


    override fun showComic() {
        comicLayout.visibility = View.VISIBLE
    }
    override fun hideComic() {
        comicLayout.visibility = View.GONE
    }

    override fun showClock() {
        clockLayout.visibility = View.VISIBLE
    }
    override fun hideClock() {
        clockLayout.visibility = View.GONE
    }
}
