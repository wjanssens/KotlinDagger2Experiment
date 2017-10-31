package com.robotsandpencils.kotlindaggerexperiement.presentation.clock

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.robotsandpencils.kotlindaggerexperiement.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_clock.*
import javax.inject.Inject

class ClockFragment : Fragment(), Contract.View {
    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel(): ClockViewModel {
        return ViewModelProviders.of(this).get(ClockViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_clock, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        val adapter = ArrayAdapter<String>(this.context, android.R.layout.simple_list_item_1)
        clocks.adapter = adapter

        getViewModel().list.observe(this, Observer { v ->
            adapter.clear()
            adapter.addAll(v)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detach()
    }
}