package com.example.ronensabag.imageloader

import dagger.Module
import android.app.Activity
import com.example.ronensabag.imageloader.mainActivity.MainActivity
import com.example.ronensabag.imageloader.mainActivity.MainActivityComponent
import dagger.android.AndroidInjector
import dagger.android.ActivityKey
import dagger.multibindings.IntoMap
import dagger.Binds
import javax.inject.Singleton


@Singleton @Module (subcomponents = arrayOf(MainActivityComponent::class))
abstract class ActivitiesInjectorFactory {
    @Binds
    @IntoMap
    @ActivityKey(MainActivity::class)
    internal abstract fun activityInjectorFactory(builder: MainActivityComponent.Builder) :
            AndroidInjector.Factory<out Activity>
}