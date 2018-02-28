package com.lmntrx.android.miner.base

interface ILifeObserver {

    fun onCreate()

    fun onStart()

    fun onResume()

    fun onPause()

    fun onStop()

    fun onDestroy()
}