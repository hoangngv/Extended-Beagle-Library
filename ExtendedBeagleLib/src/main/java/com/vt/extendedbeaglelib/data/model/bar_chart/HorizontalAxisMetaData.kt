package com.vt.extendedbeaglelib.data.model.bar_chart

import com.github.mikephil.charting.components.XAxis

data class HorizontalAxisMetaData(
    val isXAxisEnabled: Boolean = true,
    val axisPosition: XAxis.XAxisPosition = XAxis.XAxisPosition.BOTTOM,
    val textSize: Float = 12F,
    val textColor: String = "#000000",
    val displayTitleAsLabel: Boolean = false
)

/*
    isXAxisEnabled -> check if the X axis needs to be displayed
    textSize -> size of text displayed under the X axis
    textColor -> color of text
    displayTitleAsLabel -> display title for each bar instead of the x value as default
*/