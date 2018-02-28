package com.lmntrx.android.scoop.miner.base

import android.content.Context

abstract class BasePresenter {

    var mContext: Context? = null

    open fun attach(context: Context) {
        this.mContext = context
    }

    open fun detach() {
        mContext = null
    }

    abstract fun getLifeObserver(): ILifeObserver?

}