package com.example.ronensabag.imageloader.mainActivity

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.gettaxi.kotlinReactiveRoutines.UseCaseCanceler

class MainLifecycleObserver : LifecycleObserver, UseCaseCanceler {
    val blocks = mutableListOf<() -> Unit>()

    override fun notifyToCancel(block: () -> Unit) {
        blocks.add(block)
    }

    fun notifyOnStop(block: () -> Unit) {
        blocks.add(block)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun disconnectListener() {
        blocks.forEach{ it() }
    }

}