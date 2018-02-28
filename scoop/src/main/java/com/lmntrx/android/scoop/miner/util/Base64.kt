package com.lmntrx.android.scoop.miner.util

import android.util.Base64

fun getBase64(str: String): String {
    var result = ""
    try {
        result = String(Base64.encode(str.toByteArray(), Base64.DEFAULT))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}

fun getFromBase64(str: String): String {
    var result = ""
    try {
        result = String(Base64.decode(str, Base64.DEFAULT))
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return result
}