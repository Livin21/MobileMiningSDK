package com.lmntrx.android.miner.util

import android.content.Context
import android.content.SharedPreferences

/**
 * SharedPreferences
 */
fun getSharedPref(context: Context): SharedPreferences =
        context.getSharedPreferences("APP", Context.MODE_PRIVATE)
