package com.example.ronensabag.imageloader.bitmapRepository

import android.content.Context
import android.graphics.Bitmap
import com.squareup.picasso.Picasso
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch

class BitmapEntity(private val context: Context) {

    val bitmapChannel = Channel<Bitmap>()
    lateinit var bitmapUrl: String
    lateinit var bb : suspend () -> Unit

    suspend fun loadBitmap() : Bitmap {
        return async {
            val bitmap = Picasso.with(this@BitmapEntity.context).load(bitmapUrl).get()
            bitmap
        }.await()
    }

    fun onBitmapLoaded(block: suspend () -> Unit) {
        bb = block
    }

    operator fun invoke(block: suspend BitmapEntity.() -> Unit) = launch {
        block()
    }
}