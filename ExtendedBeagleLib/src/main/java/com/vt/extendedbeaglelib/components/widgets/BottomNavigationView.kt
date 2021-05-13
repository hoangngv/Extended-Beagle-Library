package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.data.model.TabInfo
import com.vt.extendedbeaglelib.components.widget_views.BottomNavigationView

@RegisterWidget
class BottomNavigationView(
        private val tabItems: List<TabInfo>,
        private val selectedColor: String? = "#3596EC",
        private val unselectedColor: String? = "#788793"
): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return BottomNavigationView(rootView.getContext()).apply {
            setupMenu(tabItems, selectedColor, unselectedColor, rootView.getContext() as AppCompatActivity)
        }
    }
}