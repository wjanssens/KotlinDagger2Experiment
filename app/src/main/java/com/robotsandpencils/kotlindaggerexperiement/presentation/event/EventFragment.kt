package com.robotsandpencils.kotlindaggerexperiement.presentation.event

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.robotsandpencils.kotlindaggerexperiement.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_event.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import javax.inject.Inject

class EventFragment : Fragment(), Contract.View {
    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel(): EventViewModel {
        return ViewModelProviders.of(this).get(EventViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        val fragment = this
        getViewModel().apply {
            name.observe(fragment, Observer { name ->
                textEventName.text.clear()
                textEventName.text.append("$name")
            })
            location.observe(fragment, Observer { loc ->
                textLocation.text.clear()
                textLocation.text.append("$loc")
            })
            start.observe(fragment, Observer { start ->
                textFromDate.text.clear()
                textFromDate.text.append(DateFormat.getDateInstance(SimpleDateFormat.FULL).format(start))
                textFromTime.text.clear()
                textFromDate.text.append(DateFormat.getTimeInstance(SimpleDateFormat.SHORT).format(start))
            })
            end.observe(fragment, Observer { end ->
                textToDate.text.clear()
                textToDate.text.append(DateFormat.getDateInstance(SimpleDateFormat.FULL).format(end))
                textToTime.text.clear()
                textToDate.text.append(DateFormat.getTimeInstance(SimpleDateFormat.SHORT).format(end))
            })
            allDay.observe(fragment, Observer { ad ->
                checkBoxAllDay.isSelected = true // TODO
            })
            timeZone.observe(fragment, Observer { tz ->
                spinnerTimeZone.setSelection(0) // TODO
            })

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detach()
    }
}