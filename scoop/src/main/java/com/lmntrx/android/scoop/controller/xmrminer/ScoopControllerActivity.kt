package com.lmntrx.android.scoop.controller.xmrminer

import android.text.method.ScrollingMovementMethod
import com.cpf.baseproject.R
import com.lmntrx.android.scoop.miner.base.BaseActivity
import com.lmntrx.android.scoop.miner.util.setOnSeekBarChangeListener
import com.lmntrx.android.scoop.miner.util.ui
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileFilter
import java.util.*
import java.util.regex.Pattern

open class ScoopControllerActivity : BaseActivity() {


    // Config
    private val MINING_POOL_URL: String = "xmr.pool.minergate.com:45560"
    private val WALLET_ADDRESS: String = "livinmathew99@gmail.com"
    private val WALLET_PASSWORD: String = "x"

    private var miner = MinerManager()
    private var maxThread = 1
    private var timer: Timer? = null

    override fun canSwipeBack(): Boolean = false

    override fun initActionBarLayout(): Int = 0

    override fun initLayout(): Int = R.layout.activity_main

    override fun initView() {

    }

    override fun initEvent() {
        miner.setMinerEventListener(object : MinerEventListener {
            override fun start() {
                ui {
                    run.text = stopStr
                    startTimer()
                }
            }

            override fun stop() {
                ui {
                    run.text = runStr
                    stopTimer()
                }
            }

            override fun error() {

            }

        })
        content.movementMethod = ScrollingMovementMethod.getInstance()
        miner.setMinerMsgListener(object : MinerMsgListener {
            override fun message(msg: String) {
                ui {
                    refreshLogView(msg + "\n")
                }
            }
        })

        run.setOnClickListener {
            changeStatus()
        }
        speedSeekBar.setOnSeekBarChangeListener({
            speedText.text = ((it + 1) * 10).toString().plus("%")
        }, {
            miner.mSpeed = (it + 1) / 10f
        })
        threadSeekBar.setOnSeekBarChangeListener({
            threadText.text = (it + 1).toString()
        }, {
            miner.mThreadNum = it + 1
        })
    }

    fun refreshLogView(msg: String) {
        content.append(msg)
        val offset = content.lineCount * content.lineHeight
        if (offset > content.height) {
            content.scrollTo(0, offset - content.height)
        }
    }

    private var runStr: String? = null
    private var stopStr: String? = null

    private fun changeStatus() {
        if (runStr == null) {
            runStr = getString(R.string.run)
        }
        if (stopStr == null) {
            stopStr = getString(R.string.stop)
        }
        if (run.text == runStr) {
            miner.mThreadNum = threadText.text.toString().toInt()
            miner.mSpeed = (speedSeekBar.progress + 1) / 10f
            miner.startMiner(MINING_POOL_URL, WALLET_ADDRESS, WALLET_PASSWORD)
        } else {
            miner.stopMiner()
        }
    }

    private fun startTimer() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
        timer = Timer()
        timer!!.scheduleAtFixedRate(object : TimerTask() {
            var tempHash = 0L
            override fun run() {
                ui {
                    hash.text = formatHash((miner.mHashCount - tempHash) / 3)
                    total.text = formatHash(miner.mHashCount)
                    accepted.text = miner.mShareCount.toString()
                    tempHash = miner.mHashCount
                }
            }

        }, 1000, 3000)
    }

    private fun formatHash(count: Long): String {
        return when {
            count < 1000f -> {
                count.toString()
            }
            count >= 1000f -> {
                (count / 1000f).toString() + "K"
            }
            count >= 1000f * 1000f -> {
                (count / 1000f * 1000f).toString() + "M"
            }
            count >= 1000f * 1000f * 1000f -> {
                (count / 1000f * 1000f * 1000f).toString() + "G"
            }
            else -> {
                (count / 1000f * 1000f * 1000f).toString() + "G"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        miner.stopMiner()
    }

    private fun stopTimer() {
        timer?.cancel()
        timer = null
    }

    override fun initData() {
        maxThread = getNumCores()
        threadSeekBar.max = maxThread - 1
        threadSeekBar.progress = maxThread - 1
        threadText.text = maxThread.toString()
    }

    private fun getNumCores(): Int {
        class CpuFilter : FileFilter {
            override fun accept(pathname: File): Boolean = Pattern.matches(getString(R.string.cpu_match), pathname.name)
        }
        return try {
            val dir = File("/sys/devices/system/cpu/")
            val files = dir.listFiles(CpuFilter())
            files.size
        } catch (e: Exception) {
            1
        }
    }
}
