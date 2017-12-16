package com.example.ronensabag.imageloader.mainActivity

import android.graphics.Bitmap
import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import com.gettaxi.kotlinReactiveRoutines.DataStream
import com.gettaxi.kotlinReactiveRoutines.LaunchRunner
import com.gettaxi.kotlinReactiveRoutines.UseCaseCanceler
import javax.inject.Inject


class MainModel @Inject constructor(private val bitmapEntity: BitmapEntity,
                                    val lifecycleObserver: MainLifecycleObserver) {

    fun loadImages(block: (DataStream<Bitmap, Unit>) -> Unit) {
        val loadImageUseCase = LoadImageUseCase()
        loadImageUseCase {
            request.bitmapEntity = bitmapEntity
            useCaseCanceler = lifecycleObserver
            onResponse { response ->
                block(response.bitmapChannel)
            }
            onCancel {
                println("I have been stopped")
                request.bitmapEntity.shutdown()
            }
        }
    }

    fun loadOneImage(block: (Bitmap) -> Unit) {
        val loadOneImageUseCase = LoadOneImgaeUseCase()
        loadOneImageUseCase {
            request.bitmapEntity = bitmapEntity
            onResponse { response  ->
                block(response.bitmap)
            }
            onError {
                println("error")
            }
        }
    }
}