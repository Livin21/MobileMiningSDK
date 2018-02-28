
package com.lmntrx.android.scoop.xmrminer

import com.lmntrx.android.miner.util.logPrint
import com.lmntrx.android.scoop.cryptonight.Miner


class JobTest(private val miner: MinerManager,
              private val start: Int,
              private val jobBean: Job.JobBean,
              private val block: () -> Unit) : Runnable {

    override fun run() {
        val startTime = System.currentTimeMillis()
        val blobByte = HexUtil.unhexlify(jobBean.blob)
        val nonce = start
        ByteArray(4)
        val result = ByteArray(32)
        blobByte[39] = nonce.toByte()
        blobByte[40] = (nonce shr 8).toByte()
        blobByte[41] = (nonce shr 16).toByte()
        blobByte[42] = (nonce shr 24).toByte()
        Miner.fastHash(blobByte, result)
        val resultStr = HexUtil.hexlify(result)
        val rs = resultStr.substring(resultStr.length - jobBean.target.length)
        var byteArray = HexUtil.unhexlify(rs)
        byteArray = miner.byteArraySort(byteArray)
        val str = HexUtil.hexlify(byteArray)
        str.toLong(16)
        miner.mSleepTime = System.currentTimeMillis() - startTime
        logPrint("Execution algorithm time-consuming ${miner.mSleepTime}")
        block()
    }
}