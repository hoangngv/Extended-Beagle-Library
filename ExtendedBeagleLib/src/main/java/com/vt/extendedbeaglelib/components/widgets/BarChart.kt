package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.components.widget_views.BarChart
import com.vt.extendedbeaglelib.data.model.bar_chart.BarModel
import com.vt.extendedbeaglelib.data.model.bar_chart.HorizontalAxisMetaData
import com.vt.extendedbeaglelib.data.model.bar_chart.LeftAxisMetadata
import com.vt.extendedbeaglelib.data.model.bar_chart.RightAxisMetaData

@RegisterWidget
class BarChart(
    private val isHorizontalBarChart: Boolean = false,
    private val dataset: List<BarModel>,
    private val horizontalAxisMetadata: HorizontalAxisMetaData = HorizontalAxisMetaData(),
    private val leftAxisMetadata: LeftAxisMetadata = LeftAxisMetadata(),
    private val rightAxisMetadata: RightAxisMetaData = RightAxisMetaData(),
    private val unit: String,
    private val barWidth: Float = 1F,
    private val width: Int = 100,
    private val height: Int = 100
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return BarChart(rootView.getContext()).apply {
            setupBarChart(isHorizontalBarChart, dataset, horizontalAxisMetadata, leftAxisMetadata, rightAxisMetadata, unit, barWidth)
        }
    }
}