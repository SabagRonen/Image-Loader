package com.example.ronensabag.imageloader

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.squareup.picasso.Picasso

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.mainImage
import kotlinx.coroutines.experimental.launch
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import kotlinx.coroutines.experimental.channels.Channel


class Presenter(val viewModel: MyViewModel, val model: Model) {
    fun loadImageClicked() {
        model.loadImage("https://gett.com/uk/wp-content/uploads/sites/6/2015/11/Home_UK.png") {
            viewModel.setImage(it)
        }
    }
}

class Model(val bitmapEntity: BitmapEntity) {
    fun loadImage(url: String, block: (Bitmap) -> Unit) {
        bitmapEntity {
            bitmapUrl = url
            loadBitmap()
            onBitmapLoaded {
                val bitmap = bitmapEntity.bitmapChannel.receive()
                block(bitmap)
            }
        }
    }

}

class BitmapEntity(val picassoContext: Context) {

    val bitmapChannel = Channel<Bitmap>()
    lateinit var bitmapUrl: String

    fun loadBitmap() = launch {
        val bitmap = Picasso.with(picassoContext)
                .load(bitmapUrl).get()
        bitmapChannel.send(bitmap)
    }

    fun onBitmapLoaded(block: suspend () -> Unit) = launch {
        block()
    }

    operator fun invoke(block: suspend BitmapEntity.() -> Unit) = launch {
        block()
    }
}

class MyViewModel : ViewModel() {
    private var image = MutableLiveData<Bitmap>()

    fun getImage(): LiveData<Bitmap> {
        return image
    }

    fun setImage(bitmap: Bitmap) {
        image.postValue(bitmap)
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val viewModel = ViewModelProviders.of(this).get(MyViewModel::class.java)
        val presenter = Presenter(viewModel, Model(BitmapEntity(this)))
        viewModel.getImage().observe({ lifecycle }, { bitmap -> mainImage.setImageBitmap(bitmap) })


        fab.setOnClickListener { view ->
            presenter.loadImageClicked()
        }
    }
}
