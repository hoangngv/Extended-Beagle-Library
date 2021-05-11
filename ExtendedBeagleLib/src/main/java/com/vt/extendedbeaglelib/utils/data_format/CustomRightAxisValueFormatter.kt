package com.vt.extendedbeaglelib.utils.data_format

import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.formatter.ValueFormatter
import java.text.DecimalFormat

class CustomRightAxisValueFormatter : ValueFormatter() {
    private var mFormat: DecimalFormat = DecimalFormat("###,###,###,###")

    override fun getFormattedValue(value: Float, axis: AxisBase?): String {
        return mFormat.format(value).toString() + "ahihi"
//        return value.toString()
    }
}