package com.vt.extendedbeaglelib.utils.extensions

import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.vt.extendedbeaglelib.R
import java.io.File

fun ImageView.loadGlide(url: String? = null, uri: Uri? = null, file: File? = null,
                        @DrawableRes placeHolderId: Int = R.drawable.img_default_avatar, @DrawableRes errorImageId : Int = R.drawable.img_default_avatar,
                        isCached : Boolean = false, isCircleCrop : Boolean = false, borderRadius : Int = 0,
                        reSize : Pair<Int, Int>? = null,
                        onError : () -> Unit = {}, onSuccess : () -> Unit = {}){

    val from = url ?: (uri ?: file)

    val options: RequestOptions = RequestOptions()
            .fitCenter()
            .placeholder(placeHolderId)
            .error(errorImageId)
            .diskCacheStrategy( if (!isCached) DiskCacheStrategy.NONE else  DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)

    if(borderRadius != 0) options.transform(CenterCrop() , RoundedCorners(borderRadius))
    reSize?.run { options.override(first, second) }
    if (isCircleCrop) options.circleCrop()

    Glide
            .with(context)
            .load(from)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    e?.printStackTrace()
                    onError.invoke()
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    onSuccess.invoke()
                    return false
                }

            }).apply(options).into(this)
}