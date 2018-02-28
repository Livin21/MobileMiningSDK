package com.lmntrx.android.scoop.miner.util

import android.content.Context
import com.google.gson.Gson

val gson = Gson()

fun <T> get(context: Context, key: String, clazz: Class<T>): T? {
    return try {
        gson.fromJson(getFromBase64(getSharedPref(context).getString(key, "")), clazz)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}