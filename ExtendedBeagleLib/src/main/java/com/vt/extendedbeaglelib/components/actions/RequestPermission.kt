package com.vt.extendedbeaglelib.components.actions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.common.Constants.PERMISSION_CAMERA
import com.vt.extendedbeaglelib.common.Constants.PERMISSION_PHOTO
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey

@RegisterAction
data class RequestPermission(
    val listPermission: List<Int>
): Action {

    override fun execute(rootView: RootView, origin: View) {
        val context = rootView.getContext()

        if (listPermission.size == 1) {

            if (listPermission[0] == PERMISSION_CAMERA) {

                if (checkPermission(context, Manifest.permission.CAMERA)) {
                    SingleBus.send(SingleBusKey.CAPTURE_PHOTO, true)
                } else {
                    requestPermission(
                        context as Activity,
                        Manifest.permission.CAMERA,
                        PERMISSION_CAMERA
                    )
                }

            } else if (listPermission[0] == PERMISSION_PHOTO) {

                if (checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    SingleBus.send(SingleBusKey.PICK_LOCAL_IMAGE, true)
                } else {
                    requestPermission(
                        context as Activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        PERMISSION_PHOTO
                    )
                }
            }
        } else if (listPermission.size > 1) {
            // handle here
            Log.d("dLog", "Permission array size = ${listPermission.size}")
        }
    }

    private fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(activity: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(permission),
            requestCode
        )
    }
}