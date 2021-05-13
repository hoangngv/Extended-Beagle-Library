package com.vt.extendedbeaglelib.components.widgets

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.common.callback.ImageSetListener
import com.vt.extendedbeaglelib.components.widget_views.CustomImageView
import java.io.ByteArrayOutputStream

@RegisterWidget
class ImageView(
    private val url: String,
    private val width: Int = 0,
    private val height: Int = 0,
    private val cornerRadius: Double = 0.0,
    private val actionOnImageSet: List<Action>? = null
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return CustomImageView(rootView.getContext()).apply {
            setImageView(url, this@ImageView.width, this@ImageView.height, cornerRadius)

            imageSetListener = object: ImageSetListener {
                override fun onImageSet(uri: Uri) {
                    val bitmap = MediaStore.Images.Media.getBitmap(rootView.getContext().contentResolver, uri)
                    val outputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                    val byteArray: ByteArray = outputStream.toByteArray()
                    val encodedString: String = Base64.encodeToString(byteArray, Base64.NO_WRAP)

                    actionOnImageSet?.let {
                        this@ImageView.handleEvent(
                            rootView = rootView,
                            origin = this@apply,
                            actions = actionOnImageSet,
                            ContextData(
                                id = "imageDataOnChange",
                                value = encodedString
                            )
                        )
                    }
                }
            }
        }
    }
}