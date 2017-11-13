package com.example.ronensabag.imageloader.mainActivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.mainImage
import android.arch.lifecycle.ViewModelProviders
import com.example.ronensabag.imageloader.R
import dagger.android.AndroidInjection
import javax.inject.Inject




class MainActivity : AppCompatActivity() {

    @Inject lateinit var mainModel : MainModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModel: MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val presenter = MainPresenter(viewModel, mainModel)
        viewModel.imageLiveData.observe({ lifecycle }) {
            bitmap -> mainImage.setImageBitmap(bitmap)
        }


        fab.setOnClickListener {
            presenter.loadImageClicked()
        }
    }
}
