package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.github.mikephil.charting.data.BarEntry
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.data.model.bar_chart.BarModel
import com.vt.extendedbeaglelib.data.model.bar_chart.HorizontalAxisMetaData
import com.vt.extendedbeaglelib.data.model.bar_chart.LeftAxisMetadata
import com.vt.extendedbeaglelib.data.model.bar_chart.RightAxisMetaData
import com.vt.extendedbeaglelib.utils.extensions.setupBarChart
import com.vt.extendedbeaglelib.utils.extensions.updateData
import kotlinx.android.synthetic.main.layout_bar_chart.view.*

class BarChart(context: Context) : LinearLayout(context){

    init {
        View.inflate(context, R.layout.layout_bar_chart, this)
        invalidate()
        requestLayout()
    }

    fun setupBarChart(
        isHorizontalBarChart: Boolean,
        dataset: List<BarModel>,
        horizontalAxisMetaData: HorizontalAxisMetaData,
        leftAxisMetadata: LeftAxisMetadata,
        rightAxisMetaData: RightAxisMetaData,
        unit: String,
        barWidth: Float
    ) {
        if (dataset.isNotEmpty()) {
            val listData = mutableListOf<BarEntry>()
            val listTitle = mutableListOf<String>()
            val listColor = mutableListOf<Int>()

            for (color: String in dataset[0].color) {
                listColor.add(Color.parseColor(color))
            }

            for (bar: BarModel in dataset) {
                listData.add(BarEntry(bar.x, bar.y))
                listTitle.add(bar.title)
            }

            if (isHorizontalBarChart) {
                barChart.visibility = View.GONE
                horizontalBarChart.visibility = View.VISIBLE

                horizontalBarChart.apply {
                    setupBarChart(horizontalAxisMetaData, leftAxisMetadata, rightAxisMetaData, unit, listTitle)
                    updateData(listData, listColor, unit, barWidth)
                }
            } else {
                barChart.apply {
                    setupBarChart(horizontalAxisMetaData, leftAxisMetadata, rightAxisMetaData, unit, listTitle)
                    updateData(listData, listColor, unit, barWidth)
                }
            }
        } else {
            Toast.makeText(context, "Dataset for bar chart is empty!", Toast.LENGTH_SHORT).show()
        }
    }
}