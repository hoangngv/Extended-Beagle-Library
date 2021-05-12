package com.vt.extendedbeaglelib.ui

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.Toolbar
import androidx.core.content.FileProvider
import br.com.zup.beagle.android.annotation.RegisterController
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ServerDrivenState
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.common.CapturePhotoListener
import com.vt.extendedbeaglelib.common.Constants.PERMISSION_CAMERA
import com.vt.extendedbeaglelib.common.Constants.PERMISSION_PHOTO
import com.vt.extendedbeaglelib.common.PickLocalPhotoListener
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey
import com.vt.extendedbeaglelib.utils.extensions.toast
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.activity_beagle.*
import kotlinx.coroutines.Job
import java.io.File

@RegisterController(id = "ACTIVITY_IDENTIFIER")
class AppBeagleActivity : BeagleActivity() {

    private lateinit var uri: Uri

    private val takePicture = registerForActivityResult(ActivityResultContracts.TakePicture()) { isSaved ->
        if (isSaved) {
            Log.d("dLog", uri.toString())
            captureListener?.onCaptureSuccess(uri)
        } else {
            Log.d("dLog", "Image isn't saved")
            captureListener?.onCaptureFailure()
        }
    }

    private val pickLocalImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        if (uri != null) {
            pickPhotoListener?.onPickPhotoSuccess(uri)
        } else {
            pickPhotoListener?.onPickPhotoFailure()
        }
    }

    companion object {
        var captureListener: CapturePhotoListener? = null
        var pickPhotoListener: PickLocalPhotoListener? = null
        val busJobs : MutableList<Job> = arrayListOf()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beagle)
        initBus(this)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun getServerDrivenContainerId(): Int = R.id.server_driven_container

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {
        when (state) {
            is ServerDrivenState.Started -> {
                (loading_indicator as AVLoadingIndicatorView).visibility = View.VISIBLE
            }

            is ServerDrivenState.Finished -> {
                (loading_indicator as AVLoadingIndicatorView).visibility = View.GONE
            }
            else -> Log.d("dLog", "State -> $state")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toast(resources.getString(R.string.camera_granted))
                capturePhoto(this)
            } else {
                toast(resources.getString(R.string.camera_denied))
            }
        } else if (requestCode == PERMISSION_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toast(resources.getString(R.string.storage_granted))
                pickImage()
            } else {
                toast(resources.getString(R.string.storage_denied))
            }
        }
    }

    private fun initBus(context: Context) {
        busJobs.apply {
            add(SingleBus.receive(SingleBusKey.CAPTURE_PHOTO) {
                capturePhoto(context)
            })

            add(SingleBus.receive(SingleBusKey.PICK_LOCAL_IMAGE) {
                pickImage()
            })
        }
    }

    private fun capturePhoto(context: Context) {
        val photoFile = File.createTempFile(
            "IMG_",
            ".jpg",
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

        uri = FileProvider.getUriForFile(
            context,
            "${context.packageName}.provider",
            photoFile
        )

        takePicture.launch(uri)
    }

    private fun pickImage() {
        // pass in the mime type
        pickLocalImage.launch("image/*")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("dLog", "onDestroy --- busJobs = ${busJobs.size}")
        busJobs.forEach {
            it.cancel()
        }
    }
}