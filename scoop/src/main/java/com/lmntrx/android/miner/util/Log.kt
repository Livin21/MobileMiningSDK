package com.lmntrx.android.miner.util

import android.util.Log
import com.lmntrx.android.scoop.BuildConfig

fun logPrint(any: Any?) {

    if (any != null && BuildConfig.DEBUG) {
        Log.e("APP", any.toString())
    }

}