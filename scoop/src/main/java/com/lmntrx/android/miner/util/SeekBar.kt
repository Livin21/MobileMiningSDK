package com.lmntrx.android.miner.util

import android.widget.SeekBar

fun SeekBar.setOnSeekBarChangeListener(change: (progress: Int) -> Unit, stop: (progress: Int) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                change(progress)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            stop(seekBar!!.progress)
        }

    })
}

fun SeekBar.setOnSeekBarChangeListener(change: (progress: Int) -> Unit, start: (progress: Int) -> Unit, stop: (progress: Int) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            if (fromUser) {
                change(progress)
            }
        }

        override fun onStartTrackingTouch(seekBar: SeekBar?) {
            start(seekBar!!.progress)
        }

        override fun onStopTrackingTouch(seekBar: SeekBar?) {
            stop(seekBar!!.progress)
        }

    })
}