package com.vt.extendedbeaglelib.utils.data_format

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class CustomXAxisValueFormatter(private val listTitle : List<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return if(value.toInt() >= listTitle.size) listTitle.last() else listTitle[value.toInt()]
    }
}