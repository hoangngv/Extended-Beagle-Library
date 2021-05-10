package com.vt.extendedbeaglelib.common

import android.net.Uri

interface CapturePhotoListener{
    fun onCaptureSuccess(uri: Uri)
    fun onCaptureFailure()
}