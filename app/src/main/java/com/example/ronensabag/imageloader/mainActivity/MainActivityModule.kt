package com.example.ronensabag.imageloader.mainActivity

import android.arch.lifecycle.ViewModelProviders
import com.example.ronensabag.imageloader.bitmapRepository.BitmapEntity
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class MainActivityModule {
    @Provides
    internal fun provideMainViewModel(featureActivity: MainActivity): MainViewModel {
        return ViewModelProviders.of(featureActivity).get(MainViewModel::class.java)
    }

    @Provides
    internal fun provideMainLifecycleObserver(featureActivity: MainActivity): MainLifecycleObserver {
        val mainLifecycleObserver = MainLifecycleObserver()
        featureActivity.lifecycle.addObserver(mainLifecycleObserver)
        return mainLifecycleObserver
    }
}