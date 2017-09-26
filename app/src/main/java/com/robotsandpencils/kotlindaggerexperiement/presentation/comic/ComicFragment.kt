package com.robotsandpencils.kotlindaggerexperiement.presentation.comic

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.robotsandpencils.kotlindaggerexperiement.R
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_comic.*
import javax.inject.Inject

class ComicFragment : Fragment(), Contract.View {
    @Inject
    lateinit var presenter: Contract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidSupportInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun getViewModel(): ComicViewModel {
        return ViewModelProviders.of(this).get(ComicViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_comic, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attach(this)

        getViewModel().imageUrl.observe(this, Observer { url ->
            // Load the image URL
            val image = imageView
            Glide.with(this).load(url).into(image)
        })

        getViewModel().title.observe(this, Observer { title ->
            titleText.text = title
        })

        previousButton.setOnClickListener {
            presenter.showPreviousComic()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        presenter.detach()
    }
}