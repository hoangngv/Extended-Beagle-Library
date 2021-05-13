package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.data.model.PieChartSlice
import com.vt.extendedbeaglelib.components.widget_views.PieChart

@RegisterWidget
class PieChart(
        private val dataset: List<PieChartSlice>,
        private val valueTextColor: String = "#ffffff",
        private val valueTextSize: Float = 12F,
        private val sliceSpace: Float = 3F,
        private val isHoleNeeded: Boolean = false,
        private val holeRadius: Float = 0f,
        private val holeColor: String = "#ffffff",
        private val holeText: String = "",
        private val holeTextColor: String = "#000000",
        private val holeTextSize: Float = 12F,
        private val width: Int = 100,
        private val height: Int = 100
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return PieChart(rootView.getContext()).apply {
            setupPieChart(dataset, valueTextColor, valueTextSize, sliceSpace, isHoleNeeded, holeRadius, holeColor, holeText, holeTextColor, holeTextSize)
        }
    }
}