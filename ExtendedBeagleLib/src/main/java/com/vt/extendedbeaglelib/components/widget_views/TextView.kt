package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import br.com.zup.beagle.widget.core.TextAlignment
import com.vt.extendedbeaglelib.common.enum_class.FontStyle
import com.vt.extendedbeaglelib.R
import kotlinx.android.synthetic.main.layout_custom_text_view.view.*

class TextView constructor(
    context: Context
): LinearLayout(context) {

    init {
        View.inflate(context, R.layout.layout_custom_text_view, this)
    }

    fun setText(
            text: String? = "",
            textColor: String = "#000000",
            cornerRadius: Double? = null,
            backgroundColor: String? = null,
            fontSize: Double = 17.0,
            fontName: String? = null,
            fontStyle: FontStyle = FontStyle.NORMAL,
            numberOfLines: Int = 1,
            textAlignment: TextAlignment = TextAlignment.LEFT
    ) {
        if (text.equals("")) {
            parentBackground.visibility = View.GONE
        } else {
            parentBackground.visibility = View.VISIBLE
        }

        textView.text = text
        try {
            textView.setTextColor(Color.parseColor(textColor))
        } catch (throwable : Throwable) {
            throwable.printStackTrace()
            textView.setTextColor(Color.parseColor("#000000"))
        }

        textView.textSize = fontSize.toFloat()
        textView.maxLines = numberOfLines
        textView.ellipsize = TextUtils.TruncateAt.END

        when (fontStyle) {
            FontStyle.BOLD -> textView.setTypeface(null, Typeface.BOLD)
            FontStyle.ITALIC -> textView.setTypeface(null, Typeface.ITALIC)
            FontStyle.NORMAL -> textView.setTypeface(null, Typeface.NORMAL)
            FontStyle.UNDERLINE -> textView.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            else -> textView.setTypeface(null, Typeface.NORMAL)
        }

        when (textAlignment) {
            TextAlignment.LEFT -> textView.gravity = Gravity.START
            TextAlignment.RIGHT -> textView.gravity = Gravity.END
            TextAlignment.CENTER -> textView.gravity = Gravity.CENTER
        }

        cornerRadius?.let {
            parentBackground.radius = (it*2).toInt().dpToPixels(context)
        }

        backgroundColor?.let {
            parentBackground.setCardBackgroundColor(Color.parseColor(it))
        }
    }

    fun changeText(text : String) {
        if (text == "") {
            parentBackground.visibility = View.GONE
        } else {
            parentBackground.visibility = View.VISIBLE
        }
        textView.text = text
    }

    fun changeColor(color : String) {
        try {
            textView.setTextColor(Color.parseColor(color))
        } catch (throwable : Throwable) {
            throwable.printStackTrace()
            textView.setTextColor(Color.parseColor("#000000"))
        }
    }

    private fun Int.dpToPixels(context: Context): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,this.toFloat(), context.resources.displayMetrics
    )
}