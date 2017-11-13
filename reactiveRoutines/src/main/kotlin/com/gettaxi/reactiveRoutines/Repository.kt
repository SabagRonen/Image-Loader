package com.gettaxi.repository

import kotlinx.coroutines.experimental.channels.BroadcastChannel
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.launch

interface IDataStream<T> {
  fun notifyOnChange(block: (T) -> Unit)
}

class DataStream<T> : IDataStream<T> {
  val output = BroadcastChannel<T>(Channel.CONFLATED)

  override fun notifyOnChange(block: (T) -> Unit) {
    launch {
      block(output.openSubscription().receive())
    }
  }
}
class Repository<T> (val runner: Runner) {
  val input = Channel<T>()
  val output = BroadcastChannel<T>(Channel.CONFLATED)
  init {
    connectChannels()
  }

  private fun connectChannels() {
    runner.run {
      output.send(input.receive())
//      while (true)
//      {
//        output.send(input.receive())
//      }
    }
  }
}