package com.example.ronensabag.imageloader.mainActivity

class MainPresenter(val viewModel: MainViewModel, val mainModel: MainModel) {
    fun loadImageClicked() {
        mainModel.loadImage("https://gett.com/uk/wp-content/uploads/sites/6/2015/11/Home_UK.png") {
            viewModel.image = it
        }
    }
}