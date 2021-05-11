package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.components.widget_views.FloatingButton

@RegisterWidget
data class MyFloatingButton(
    private val image: String,
    private val backgroundColor: String
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return FloatingButton(rootView.getContext()).apply {
            setupFloatingButton(image, backgroundColor)
        }
    }
}