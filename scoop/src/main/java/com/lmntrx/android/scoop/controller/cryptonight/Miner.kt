package com.lmntrx.android.scoop.controller.cryptonight

object Miner {

    init {
        System.loadLibrary("CryptoNight")
    }

    external fun fastHash(input: ByteArray, output: ByteArray)

}