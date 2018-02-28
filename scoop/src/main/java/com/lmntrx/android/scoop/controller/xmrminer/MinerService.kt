package com.lmntrx.android.scoop.controller.xmrminer

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MinerService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return MinerBinder()
    }

    inner class MinerBinder : Binder() {
        fun getService(): MinerService {
            return this@MinerService
        }
    }

    fun startMiner(){

    }

}
