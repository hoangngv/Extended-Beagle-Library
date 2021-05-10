package com.vt.extendedbeaglelib.common

import android.net.Uri

interface PickLocalPhotoListener {
    fun onPickPhotoSuccess(uri: Uri)
    fun onPickPhotoFailure()
}