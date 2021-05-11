package com.vt.extendedbeaglelib.utils.data_format

import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.formatter.PercentFormatter

class CustomPercentFormatter(pieChart: PieChart, private val threshold: Int = 0) : PercentFormatter(pieChart) {
    override fun getFormattedValue(value: Float): String {
        return if(value > threshold) super.getFormattedValue(value) else ""
    }
}