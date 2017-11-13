package com.gettaxi.repository

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking


interface Runner {
  fun run(block: suspend () -> Unit)
}

class LaunchRunner : Runner {
  override fun run(block: suspend () -> Unit) {
    launch {
      block();
    }
  }
}

class AsyncRunner : Runner {
  override fun run(block: suspend () -> Unit) {
    launch {
      async {
        block();
      }.await()
    }
  }
}

class RunBLockingRunner : Runner {
  override fun run(block: suspend () -> Unit) {
    runBlocking {
      block();
    }
  }
}

class TestRunner : Runner {
  override fun run(block: suspend () -> Unit) {
    val job = launch {
      block();
    }
  }
}