package com.example.ronensabag.imageloader.mainActivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap

class MainViewModel : ViewModel() {
    private var internalImage = MutableLiveData<Bitmap>()
    val imageLiveData : LiveData<Bitmap>
        get() = internalImage
    var image : Bitmap? = null
        get() = internalImage.value
        set(value) {
            field = value
            value?.let {
                internalImage.postValue(value)
            }
        }
}