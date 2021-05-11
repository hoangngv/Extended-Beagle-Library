package com.vt.extendedbeaglelib.utils.data_format

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class CustomBarChartValueFormatter : ValueFormatter() {
    private var mFormat: DecimalFormat = DecimalFormat("###,###,###,##0.0")

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return mFormat.format(value).toString() + " %"
    }
}