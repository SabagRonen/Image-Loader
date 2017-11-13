package com.gettaxi.repository

import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryTests {
  @Test fun test() {
    val runner = TestRunner()
    val repository = Repository<Int>(runner)

    runBlocking {
      repository.input.send(5)
      val value = repository.output.openSubscription().receive()
      assertEquals(5, value)
    }
  }
}