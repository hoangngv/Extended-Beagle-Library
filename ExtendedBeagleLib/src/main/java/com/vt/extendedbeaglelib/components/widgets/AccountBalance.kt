package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import com.vt.extendedbeaglelib.components.widget_views.AccountBalance

@RegisterWidget
class AccountBalance(private val value: Bind<String>, private val currency: String = "VND"): WidgetView() {
    override fun buildView(rootView: RootView): View {
        return AccountBalance(rootView.getContext()).apply {
            observeBindChanges(rootView, this, value) { value ->
                value?.let { balance -> this.setValueBalance(balance, currency) }
            }
        }
    }
}