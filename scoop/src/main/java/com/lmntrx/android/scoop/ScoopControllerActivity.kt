package com.lmntrx.android.scoop

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.SeekBar
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_scoop_controller.*

open class ScoopControllerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoop_controller)

        val seekBarChangeListener = SeekBarChangeListener(this, noThreadsTextView)

        seekBar.setOnSeekBarChangeListener(seekBarChangeListener)

    }

    class SeekBarChangeListener(private val context: Context, private val textView: TextView): SeekBar.OnSeekBarChangeListener{
        override fun onProgressChanged(view: SeekBar?, position: Int, p2: Boolean) {
                textView.text = context.getString(R.string.number_of_threads_update,position.toString())
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {}

        override fun onStopTrackingTouch(p0: SeekBar?) {}

    }
}
