package com.example.ronensabag.imageloader

import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import com.example.ronensabag.imageloader.mainActivity.MainModel
import dagger.Component
import javax.inject.Singleton
import dagger.android.support.AndroidSupportInjectionModule



@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class, ActivitiesInjectorFactory::class))
interface AppComponent {
    fun inject(app: ImageLoaderApplication)
    fun getBitmapEntity() : BitmapEntity
    fun getMainModel() : MainModel
}