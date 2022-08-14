package com.crakac.datastoreexample

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class StateFlowTest {
    @Test(expected = CancellationException::class)
    fun observers() = runTest {
        val stateFlow = MutableStateFlow(0)
        val job = launch {
            repeat(10) {
                delay(10)
                stateFlow.value++
            }
        }
        launch {
            stateFlow.collect {
                println("collector1: $it")
            }
        }
        launch {
            stateFlow.collect {
                println("collector2: $it")
            }
        }
        job.join()
        println("finish")
        cancel()
    }
}