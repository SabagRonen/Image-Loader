package com.example.ronensabag.imageloader

import android.content.Context
import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import com.example.ronensabag.imageloader.mainActivity.MainActivityComponent
import com.example.ronensabag.imageloader.mainActivity.MainModel
import dagger.Module
import dagger.Provides

@Module (subcomponents = arrayOf(MainActivityComponent::class))
class AppModule (val context: Context) {
    @Provides fun provideBitmapEntity() = BitmapEntity(context)
    @Provides fun provideMainModel(bitmapEntity: BitmapEntity) = MainModel(bitmapEntity)
}