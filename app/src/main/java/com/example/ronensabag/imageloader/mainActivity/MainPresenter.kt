package com.example.ronensabag.imageloader.mainActivity

import javax.inject.Inject

class MainPresenter @Inject constructor(private val viewModel: MainViewModel, private val mainModel: MainModel) {
    fun init() {
        viewModel.stateText = "State = None"
    }

    fun loadImageClicked() {
        viewModel.stateText = "State = load images"
        mainModel.loadImages() { dataStream ->
            dataStream.notifyOnChange { bitmap ->
                viewModel.image = bitmap
            }
        }
    }

    fun loadFirstImageClicked() {
        viewModel.stateText = "State = load one images"
        mainModel.loadOneImage { viewModel.image = it }
    }
}