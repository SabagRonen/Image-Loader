package com.example.ronensabag.imageloader.mainActivity

import android.graphics.Bitmap
import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import com.gettaxi.repository.UseCase
import com.gettaxi.repository.UseCaseRequest
import com.gettaxi.repository.UseCaseResponse

class LoadImageRequest : UseCaseRequest(){
    lateinit var url2 : String
    lateinit var bitmapEntity : BitmapEntity
}

class LoadImageResponse : UseCaseResponse() {
    lateinit var bitmap : Bitmap
}

class LoadImageUseCase : UseCase<LoadImageRequest, LoadImageResponse>() {
    override fun handleUseCase() {
        val request1 : LoadImageRequest = request
        val response = LoadImageResponse()

        request1.bitmapEntity {
            bitmapUrl = request1.url2
            response.bitmap = loadBitmap()
            suceess(response)
        }
    }
}

class MainModel(val bitmapEntity: BitmapEntity) {
    fun loadImage(url: String, block: (Bitmap) -> Unit) {
        val loadImageUseCase = LoadImageUseCase()
        loadImageUseCase {
            request  = LoadImageRequest()
            request.url2 = url
            request.bitmapEntity = bitmapEntity
            onResponse { response : UseCaseResponse ->
                if (response is LoadImageResponse)
                    block(response.bitmap)
            }
        }
    }

}