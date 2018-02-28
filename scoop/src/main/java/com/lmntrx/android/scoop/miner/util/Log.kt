package com.lmntrx.android.scoop.miner.util

import android.util.Log
import com.cpf.baseproject.BuildConfig

fun logPrint(any: Any?) {

    if (any != null && BuildConfig.DEBUG) {
        Log.e("APP", any.toString())
    }

}