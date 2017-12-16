package com.example.ronensabag.imageloader

import android.content.Context
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = arrayOf(AndroidSupportInjectionModule::class,
        AppModule::class, ActivitiesInjectorFactory::class))
interface AppComponent {
    fun inject(app: ImageLoaderApplication)
    fun getContext() : Context
}