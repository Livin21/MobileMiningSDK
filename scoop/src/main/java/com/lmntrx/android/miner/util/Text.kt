package com.lmntrx.android.miner.util

import android.text.Editable
import android.widget.EditText
import com.lmntrx.android.miner.widget.TextChangeListener

fun EditText.addTextChangedListener(block: () -> Unit) {
    addTextChangedListener(object : TextChangeListener() {
        override fun afterTextChanged(s: Editable?) {
            block()
        }
    })
}
