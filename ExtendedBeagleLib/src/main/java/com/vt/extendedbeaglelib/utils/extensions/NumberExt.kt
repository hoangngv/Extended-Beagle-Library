package com.vt.extendedbeaglelib.utils.extensions

import android.content.Context
import android.util.DisplayMetrics
import kotlin.math.roundToInt

fun Number.pxToDp(context: Context) : Int{
    val resources = context.resources
    val metrics = resources.displayMetrics
    return (this.toInt() / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
}

fun Number.dpToPx(context : Context) : Int {
    val displayMetrics = context.resources.displayMetrics
    return (this.toInt() * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun Number.pxToSp(context: Context) : Float {
    val scaledDensity = context.resources.displayMetrics.scaledDensity
    return this.toFloat() / scaledDensity
}

fun Number.spToPx(context: Context) : Float {
    val scaledDensity = context.resources.displayMetrics.scaledDensity
    return this.toFloat() * scaledDensity
}

fun Number?.isNotValue() : Boolean = this == null