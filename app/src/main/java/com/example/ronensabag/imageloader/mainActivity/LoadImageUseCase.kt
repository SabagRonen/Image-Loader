package com.example.ronensabag.imageloader.mainActivity

import android.graphics.Bitmap
import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import com.gettaxi.kotlinReactiveRoutines.BaseUseCase
import com.gettaxi.kotlinReactiveRoutines.DataStream

class LoadImageRequest {
    lateinit var bitmapEntity : BitmapEntity
}

class LoadImageResponse {
    lateinit var bitmapChannel: DataStream<Bitmap, Unit>
}

class LoadImageUseCase : BaseUseCase<LoadImageRequest, LoadImageResponse>() {
    override fun provideNewRequest(): LoadImageRequest = LoadImageRequest()

    override fun handleRequest(request: LoadImageRequest): LoadImageResponse {
        val response = LoadImageResponse()

        val taskId = inParallel {
            request.bitmapEntity {
                response.bitmapChannel = loadBitmaps()
            }
        }

        waitForAll(listOf(taskId))

        return response
    }
}