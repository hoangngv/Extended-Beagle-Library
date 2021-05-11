package com.vt.extendedbeaglelib.config.cache

import android.util.Log
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.logger.BeagleLogger

private const val BEAGLE_TAG = "BeagleSDK"

@BeagleComponent
class BeagleLoggerDefault : BeagleLogger {

    override fun warning(message: String) {
        Log.w(BEAGLE_TAG, message)
    }

    override fun error(message: String) {
        Log.e(BEAGLE_TAG, message)
    }

    override fun error(message: String, throwable: Throwable) {
        Log.e(BEAGLE_TAG, message, throwable)
    }

    override fun info(message: String) {
        Log.i(BEAGLE_TAG, message)
    }

}