package com.vt.extendedbeagle

import android.view.View
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.core.TextAlignment
import com.vt.extendedbeaglelib.common.enum_class.FontStyle
import com.vt.extendedbeaglelib.components.widget_views.TextView

class LabelTwo() : WidgetView() {
    override fun buildView(rootView: RootView): View = View(rootView.getContext())
}