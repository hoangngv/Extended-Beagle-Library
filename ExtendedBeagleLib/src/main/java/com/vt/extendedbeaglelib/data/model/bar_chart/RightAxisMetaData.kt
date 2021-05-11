package com.vt.extendedbeaglelib.data.model.bar_chart

data class RightAxisMetaData(
    val isRightAxisEnabled: Boolean = true,
    val textSize: Float = 12F,
    val textColor: String = "#000000",
    val displayUnit: Boolean = false
)

/*
    isRightAxisEnabled -> check if the right axis needs to be displayed
    textSize -> size of text displayed on the left axis
    textColor -> color of text
    displayUnit -> display y value with unit (for ex: y=30, unit="%" -> 30%)
*/