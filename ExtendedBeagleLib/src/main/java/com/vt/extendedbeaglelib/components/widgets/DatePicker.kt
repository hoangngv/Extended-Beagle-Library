package com.vt.extendedbeaglelib.components.widgets

import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.components.widget_views.DatePickerComponent
import com.vt.extendedbeaglelib.components.widget_views.DateSetListener

@RegisterWidget
class DatePicker(
    val date: Bind<String>,
    val actions: List<Action>
) : WidgetView() {

    override fun buildView(rootView: RootView) = DatePickerComponent(rootView.getContext()).apply {
        observeBindChanges(rootView, this, date) { text ->
            text?.let { setText(it) }
        }

        dateSetListener = object : DateSetListener {
            override fun onDateSet(value: String) {
                this@DatePicker.handleEvent(
                    rootView,
                    this@apply,
                    actions,
                    ContextData(
                        id = "onChange",
                        value = value
                    )
                )
            }
        }
    }
}