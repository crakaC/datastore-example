package com.crakac.datastoreexample

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun combineFlows() = runBlocking {
        val timer = flow {
            emit(0)
            repeat(10) {
                delay(100)
                emit(it + 1)
            }
        }
        val f = flow {
            delay(250)
            emit(1)
        }
        var cnt = 0
        combine(timer, f){ now, v ->
            now to v
        }.collect{
            println(it)
            cnt++
        }
        Assert.assertEquals(8, cnt)
    }
}