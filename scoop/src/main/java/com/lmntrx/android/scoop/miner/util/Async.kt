
package com.lmntrx.android.scoop.miner.util

import android.os.Handler
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

var threadService: ExecutorService = Executors.newCachedThreadPool()

val handler = Handler()

fun async(block: () -> Unit) {
    threadService.execute {
        block()
    }
}

fun ui(block: () -> Unit) {
    handler.post {
        block()
    }
}




