package com.vt.extendedbeaglelib.common.callback

import android.net.Uri

interface CapturePhotoListener{
    fun onCaptureSuccess(uri: Uri)
    fun onCaptureFailure()
}