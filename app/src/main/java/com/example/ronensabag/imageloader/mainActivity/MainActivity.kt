package com.example.ronensabag.imageloader.mainActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.mainImage
import com.example.ronensabag.imageloader.R
import dagger.android.AndroidInjection
import javax.inject.Inject
import javax.inject.Provider


class MainActivity : AppCompatActivity() {

    @Inject lateinit var mainViewModelProvider: Provider<MainViewModel>
    @Inject lateinit var presenterProvider: Provider<MainPresenter>

    private val presenter : MainPresenter by lazy { presenterProvider.get() }
    private val viewModel : MainViewModel by lazy { mainViewModelProvider.get() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        presenter.init()

        viewModel.stateTextLiveData.observe({lifecycle}) {
            text -> status.text = text
        }

        viewModel.imageLiveData.observe({ lifecycle }) {
            bitmap -> mainImage.setImageBitmap(bitmap)
        }

        fabParallel.setOnClickListener {
            presenter.loadImageClicked()
        }

        fabFirst.setOnClickListener {
            presenter.loadFirstImageClicked()
        }
    }
}
