package com.vt.extendedbeaglelib.common.callback

import android.net.Uri

interface PickLocalPhotoListener {
    fun onPickPhotoSuccess(uri: Uri)
    fun onPickPhotoFailure()
}