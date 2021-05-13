package com.vt.extendedbeaglelib.components.actions

import android.Manifest
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.common.callback.CapturePhotoListener
import com.vt.extendedbeaglelib.common.Constants.PERMISSION_CAMERA
import com.vt.extendedbeaglelib.ui.AppBeagleActivity
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey
import com.vt.extendedbeaglelib.utils.extensions.checkPermission
import com.vt.extendedbeaglelib.utils.extensions.requestPermission

@RegisterAction
data class CapturePhoto(
    val actionOnSuccess: List<Action>,
    val actionOnFailure: List<Action>? = null
): Action {

    override fun execute(rootView: RootView, origin: View) {
        AppBeagleActivity.captureListener = object : CapturePhotoListener {
            override fun onCaptureSuccess(uri: Uri) {
                SingleBus.send(SingleBusKey.SET_IMAGE_VIEW_USING_URI, uri)

                this@CapturePhoto.handleEvent(
                    rootView = rootView,
                    origin = origin,
                    actions = actionOnSuccess
                )
            }

            override fun onCaptureFailure() {
                actionOnFailure?.let {
                    this@CapturePhoto.handleEvent(
                        rootView = rootView,
                        origin = origin,
                        actions = it
                    )
                }
            }
        }

        val activity = rootView.getContext() as AppCompatActivity

        if (activity.checkPermission(Manifest.permission.CAMERA)) {
            SingleBus.send(SingleBusKey.CAPTURE_PHOTO, true)
        } else {
            activity.requestPermission(
                Manifest.permission.CAMERA,
                PERMISSION_CAMERA
            )
        }
    }
}
