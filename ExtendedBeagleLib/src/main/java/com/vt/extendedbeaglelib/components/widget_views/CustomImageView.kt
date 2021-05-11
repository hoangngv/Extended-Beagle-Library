package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.net.Uri
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.ui.AppBeagleActivity
import com.vt.extendedbeaglelib.common.ImageSetListener
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey
import com.vt.extendedbeaglelib.utils.extensions.loadGlide
import kotlinx.android.synthetic.main.layout_image_view.view.*

class CustomImageView(context: Context) : LinearLayout(context) {

    var imageSetListener: ImageSetListener? = null

    init {
        View.inflate(context, R.layout.layout_image_view, this)
    }

    fun setImageView(remoteUrl: String, width: Int, height: Int, radius: Double) {
        initBus(remoteUrl)

        cardView.radius = radius.toFloat().toDp()
        cardView.requestLayout()

        imageView.layoutParams.height = height.toFloat().toDp().toInt()
        imageView.layoutParams.width = width.toFloat().toDp().toInt()
        imageView.requestLayout()

        imageView.loadGlide(url = remoteUrl, isCircleCrop = true, isCached = false)
    }

    private fun initBus(initialUrl: String) {
        AppBeagleActivity.busJobs.apply {
            add(SingleBus.receive(SingleBusKey.SET_IMAGE_VIEW_USING_URI) {
                Log.d("dLog", "Set ImageView with URI = $it")
                imageView.loadGlide(uri = it as Uri, isCircleCrop = true, isCached = false)
                imageSetListener?.onImageSet(it)
            })

            add(SingleBus.receive(SingleBusKey.REVERT_IMAGE_VIEW) {
                Log.d("dLog", "Revert image view with URL = $initialUrl")
                imageView.loadGlide(url = initialUrl, isCircleCrop = true, isCached = false)
            })
        }
    }

    private fun Float.toDp(): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            resources.displayMetrics
        )
    }
}