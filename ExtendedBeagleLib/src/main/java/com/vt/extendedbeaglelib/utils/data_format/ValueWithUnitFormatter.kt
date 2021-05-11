package com.vt.extendedbeaglelib.utils.data_format

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class ValueWithUnitFormatter(private val unit: String) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        return "$value $unit"
    }
}