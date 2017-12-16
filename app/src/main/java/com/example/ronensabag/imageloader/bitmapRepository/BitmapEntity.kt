package com.example.ronensabag.imageloader.bitmapRepository

import android.content.Context
import android.graphics.Bitmap
import com.gettaxi.kotlinReactiveRoutines.DataStream
import com.gettaxi.kotlinReactiveRoutines.LaunchRunner
import com.gettaxi.kotlinReactiveRoutines.Repository
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject
import kotlin.coroutines.experimental.CoroutineContext

class BitmapEntity @Inject constructor (val context: Context) :
        Repository<Bitmap, Unit>(LaunchRunner()) {
    val urls = arrayListOf<String>("https://s.inyourpocket.com/gallery/133209.jpg",
            "http://is5.mzstatic.com/image/thumb/Purple122/v4/f3/dc/7d/f3dc7d1b-67ea-d93b-2400-cd4488f62e04/source/392x696bb.jpg",
    "https://gett.com/uk/wp-content/uploads/sites/6/2015/11/Home_UK.png")

    fun loadBitmaps() : DataStream<Bitmap, Unit> {
        launch {
            repeat(10) {
                urls.forEach {
                    val bitmap = Picasso.with(this@BitmapEntity.context).load(it).get()
                    setCurrentValue(bitmap)
                    delay(5000)
                }
            }
        }
        return dataStream
    }

    fun loadOneImage(url: String) = Picasso.with(this@BitmapEntity.context).load(url).get()

    operator fun invoke(block: suspend BitmapEntity.() -> Unit) = launch {
        block()
    }
}