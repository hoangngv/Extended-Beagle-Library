package com.vt.extendedbeaglelib.components.actions

import android.Manifest
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.common.Constants.PERMISSION_PHOTO
import com.vt.extendedbeaglelib.common.callback.PickLocalPhotoListener
import com.vt.extendedbeaglelib.ui.AppBeagleActivity
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey
import com.vt.extendedbeaglelib.utils.extensions.checkPermission
import com.vt.extendedbeaglelib.utils.extensions.requestPermission

@RegisterAction
data class PickLocalPhoto(
    val actionOnSuccess: List<Action>,
    val actionOnFailure: List<Action>? = null
): Action {

    override fun execute(rootView: RootView, origin: View) {
        AppBeagleActivity.pickPhotoListener = object : PickLocalPhotoListener {
            override fun onPickPhotoSuccess(uri: Uri) {
                SingleBus.send(SingleBusKey.SET_IMAGE_VIEW_USING_URI, uri)

                this@PickLocalPhoto.handleEvent(
                    rootView = rootView,
                    origin = origin,
                    actions = actionOnSuccess
                )
            }

            override fun onPickPhotoFailure() {
                actionOnFailure?.let {
                    this@PickLocalPhoto.handleEvent(
                        rootView = rootView,
                        origin = origin,
                        actions = actionOnFailure
                    )
                }
            }

        }

        val activity = rootView.getContext() as AppCompatActivity

        if (activity.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            SingleBus.send(SingleBusKey.PICK_LOCAL_IMAGE, true)
        } else {
            activity.requestPermission(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                PERMISSION_PHOTO
            )
        }
    }
}
