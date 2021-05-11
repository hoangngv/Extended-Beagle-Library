package com.vt.extendedbeaglelib.utils.data_format

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter

class CustomTimeDataFormatBarChart(private val listTimes : List<String>) : ValueFormatter() {
    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
        if(value.toInt() >= listTimes.size) return listTimes.last() else return listTimes[value.toInt()]
    }
}