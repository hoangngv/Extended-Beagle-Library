package com.vt.extendedbeaglelib.utils.extensions

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.MPPointF
import com.vt.extendedbeaglelib.data.model.bar_chart.HorizontalAxisMetaData
import com.vt.extendedbeaglelib.data.model.bar_chart.LeftAxisMetadata
import com.vt.extendedbeaglelib.data.model.bar_chart.RightAxisMetaData
import com.vt.extendedbeaglelib.utils.data_format.CustomPercentFormatter
import com.vt.extendedbeaglelib.utils.data_format.CustomXAxisValueFormatter
import com.vt.extendedbeaglelib.utils.data_format.ValueWithUnitFormatter
import java.util.*

fun BarChart.setupBarChart(
    horizontalAxisMetaData: HorizontalAxisMetaData,
    leftAxisMetadata: LeftAxisMetadata,
    rightAxisMetaData: RightAxisMetaData,
    unit: String,
    listTitle: List<String>
): BarChart {
    requestLayout()

    description.isEnabled = false
    isScaleXEnabled = false
    setTouchEnabled(false)
    setPinchZoom(false)
    isDoubleTapToZoomEnabled = false
    setDrawGridBackground(false)
    setDrawBarShadow(false)
    legend.isEnabled = false
    setFitBars(true)
    axisLeft.axisMinimum = 0f
    axisRight.axisMinimum = 0f
    animateY(2000)

    if (leftAxisMetadata.isLeftAxisEnabled) {
        axisLeft.textSize = leftAxisMetadata.textSize
        axisLeft.textColor = Color.parseColor(leftAxisMetadata.textColor)
        if (leftAxisMetadata.displayUnit) axisLeft.valueFormatter = ValueWithUnitFormatter(unit)
    } else {
        axisLeft.isEnabled = false
        axisLeft.setDrawAxisLine(false)
    }

    if (rightAxisMetaData.isRightAxisEnabled) {
        axisRight.textSize = rightAxisMetaData.textSize
        axisRight.textColor = Color.parseColor(rightAxisMetaData.textColor)
        if (rightAxisMetaData.displayUnit) axisRight.valueFormatter = ValueWithUnitFormatter(unit)
    } else {
        axisRight.isEnabled = false
        axisRight.setDrawAxisLine(false)
    }

    if (horizontalAxisMetaData.isXAxisEnabled) {
        xAxis.apply {
            textSize = horizontalAxisMetaData.textSize
            textColor = Color.parseColor(horizontalAxisMetaData.textColor)

            labelCount = listTitle.size
            position = horizontalAxisMetaData.axisPosition
            setDrawAxisLine(true)
            setDrawGridLines(false)
            setDrawLabels(true)
            granularity = 1f
        }
        if (horizontalAxisMetaData.displayTitleAsLabel){
            xAxis.valueFormatter = CustomXAxisValueFormatter(listTitle)
        }
    } else {
        xAxis.isEnabled = false
        xAxis.setDrawAxisLine(false)
    }

    return this
}

fun BarChart.updateData(
    values: MutableList<BarEntry>,
    colors: List<Int>,
    unit: String,
    barWidth: Float = 1F
){
    val set1: BarDataSet

    if (data != null && data.dataSetCount > 0) {
        try {
            set1 = data.getDataSetByIndex(0) as BarDataSet
            set1.values = values
            data.notifyDataChanged()
            notifyDataSetChanged()
        } catch (throwable : Throwable) {
            throwable.printStackTrace()
        }
    } else {
        try{
            set1 = BarDataSet(values, "Bar Chart").apply {
                setDrawIcons(false)
                setDrawValues(false)
                //valueFormatter = ValueWithUnitFormatter(unit)
                setColors(*colors.toIntArray())
            }
            val dataSets = ArrayList<IBarDataSet>()
            dataSets.add(set1)

            val data = BarData(dataSets).apply {
                this.barWidth = barWidth
            }
            setData(data)

        } catch (throwable : Throwable) {
            throwable.printStackTrace()
        }
    }

    setFitBars(true)
    invalidate()
}

fun PieChart.updateData(
    values: List<PieEntry>,
    colors: List<Int>,
    valueTextColor: String,
    valueTextSize: Float,
    slideSpace: Float,
    textCenter: CharSequence = "",
    centerTextColor: String,
    centerTextSize: Float
) {
    val dataSet = PieDataSet(values, "Dataset Label")

    dataSet.setDrawValues(true) //draw number value
    dataSet.setDrawIcons(false) //draw icon

    dataSet.sliceSpace = slideSpace
    dataSet.iconsOffset = MPPointF(0f, 40f)
    dataSet.selectionShift = 5f
    dataSet.colors = colors

    val data = PieData(dataSet)
    data.setValueFormatter(CustomPercentFormatter(this, 5))
    data.setValueTextColor(Color.parseColor(valueTextColor))
    data.setValueTextSize(valueTextSize)
    //data.setValueTextSize(context.resources.getDimension(R.dimen._10sdp).pxToDp(context).toFloat())

    this.centerText = textCenter
    this.setCenterTextSize(centerTextSize)
    this.setCenterTextColor(Color.parseColor(centerTextColor))
    this.data = data
}