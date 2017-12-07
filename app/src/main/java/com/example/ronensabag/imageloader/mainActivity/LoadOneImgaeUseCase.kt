package com.example.ronensabag.imageloader.mainActivity

import android.graphics.Bitmap
import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import com.gettaxi.kotlinReactiveRoutines.BaseUseCase
import com.gettaxi.kotlinReactiveRoutines.DataStream
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.delay

class LoadOneImageRequest {
    lateinit var bitmapEntity : BitmapEntity
}

class LoadOneImageResponse  {
    lateinit var bitmap: Bitmap
}

class LoadOneImgaeUseCase : BaseUseCase<LoadOneImageRequest, LoadOneImageResponse>() {
    override fun provideNewRequest() = LoadOneImageRequest()

    override fun handleRequest(request: LoadOneImageRequest): LoadOneImageResponse {
        val response = LoadOneImageResponse()

        request.bitmapEntity.shutdown()

        val taskId1 = inParallel {
            println("\"https://s.inyourpocket.com/gallery/133209.jpg\"")
            request.bitmapEntity {
                response.bitmap = loadOneImage("https://s.inyourpocket.com/gallery/133209.jpg")
            }
        }

        val taskId2 = inParallel {
            println("http://is5.mzstatic.com/image/thumb/Purple122/v4/f3/dc/7d/f3dc7d1b-67ea-d93b-2400-cd4488f62e04/source/392x696bb.jpg")
            request.bitmapEntity {
                response.bitmap = loadOneImage("http://is5.mzstatic.com/image/thumb/Purple122/v4/f3/dc/7d/f3dc7d1b-67ea-d93b-2400-cd4488f62e04/source/392x696bb.jpg")
            }
        }

        val taskId3 = inParallel {
            println("https://gett.com/uk/wp-content/uploads/sites/6/2015/11/Home_UK.png")
            request.bitmapEntity {
                response.bitmap = loadOneImage("https://gett.com/uk/wp-content/uploads/sites/6/2015/11/Home_UK.png")
            }
        }

        waitForFirst(listOf(taskId1, taskId2, taskId3))

        return response
    }

}