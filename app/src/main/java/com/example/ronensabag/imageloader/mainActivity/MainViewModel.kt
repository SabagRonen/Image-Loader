package com.example.ronensabag.imageloader.mainActivity

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.Bitmap

class MainViewModel : ViewModel() {
    private var internalImage = MutableLiveData<Bitmap>()
    private var internalStateText = MutableLiveData<String>()

    val imageLiveData : LiveData<Bitmap>
        get() = internalImage

    val stateTextLiveData : LiveData<String>
        get() = internalStateText

    var image : Bitmap? = null
        get() = internalImage.value
        set(value) {
            field = value
            value?.let {
                internalImage.postValue(value)
            }
        }

    var stateText : String? = null
        get() = internalStateText.value
        set(value) {
            field = value
            value?.let {
                internalStateText.postValue(value)
            }
        }
}