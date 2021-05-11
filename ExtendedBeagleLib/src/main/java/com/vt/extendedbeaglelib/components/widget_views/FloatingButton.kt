package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.content.res.ColorStateList
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.vt.extendedbeaglelib.R
import kotlinx.android.synthetic.main.layout_floating_button.view.*

class FloatingButton(context: Context) : LinearLayout(context) {

    init {
        View.inflate(context, R.layout.layout_floating_button, this)
    }

    fun setupFloatingButton(image: String, backgroundColor: String) {
        floatingActionButton.apply {
            backgroundTintList = ColorStateList.valueOf(Color.parseColor(backgroundColor))
            setImageDrawable(getDrawableByName(image))
        }
    }

    private fun getDrawableByName(name: String): Drawable? {
        val resources: Resources = context.resources
        val resourceId: Int = resources.getIdentifier(name, "drawable", context.packageName)
        return ContextCompat.getDrawable(context, resourceId)
    }
}