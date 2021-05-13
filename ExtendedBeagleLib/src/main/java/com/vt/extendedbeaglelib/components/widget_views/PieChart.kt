package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieEntry
import com.vt.extendedbeaglelib.data.model.PieChartSlice
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.utils.extensions.updateData
import kotlinx.android.synthetic.main.layout_pie_chart.view.*

class PieChart(context: Context) : LinearLayout(context){

    init {
        View.inflate(context, R.layout.layout_pie_chart, this)
        invalidate()
        requestLayout()
    }

    fun setupPieChart(
            dataset: List<PieChartSlice>,
            valueTextColor: String,
            valueTextSize: Float,
            sliceSpace: Float,
            isHoleNeeded: Boolean,
            holeRadius: Float,
            holeColor: String,
            holeText: String,
            holeTextColor: String,
            holeTextSize: Float
    ) {
        if (dataset.isNotEmpty()) {
            val listData = mutableListOf<PieEntry>()
            val listColor = mutableListOf<Int>()

            for (slice: PieChartSlice in dataset) {
                listData.add(PieEntry(slice.percentage))
                listColor.add(Color.parseColor(slice.color))
            }

            pieChart.apply {
                setUsePercentValues(true)
                description.isEnabled = false
                setExtraOffsets(5f, 10f, 5f, 5f)
                dragDecelerationFrictionCoef = 0.95f
                setDrawEntryLabels(false)
                isRotationEnabled = false
                isHighlightPerTapEnabled = true
                animateY(2000, Easing.EaseInOutQuad)
                legend.isEnabled = false
            }

            if (isHoleNeeded) {
                pieChart.apply {
                    isDrawHoleEnabled = true
                    setHoleColor(Color.parseColor(holeColor))
                    setHoleRadius(holeRadius)
                    setDrawCenterText(true)
                }
            } else {
                pieChart.apply {
                    isDrawHoleEnabled = false
                    setDrawCenterText(false)
                }
            }

            pieChart.apply {
                updateData(
                    listData,
                    listColor,
                    valueTextColor,
                    valueTextSize,
                    sliceSpace,
                    holeText,
                    holeTextColor,
                    holeTextSize
                )
            }
        } else {
            Toast.makeText(context, "Dataset for pie chart is empty!", Toast.LENGTH_SHORT).show()
        }
    }
}