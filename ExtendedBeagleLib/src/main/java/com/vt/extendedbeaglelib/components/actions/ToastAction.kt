package com.vt.extendedbeaglelib.components.actions

import android.view.View
import android.widget.Toast
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.utils.evaluateExpression
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction

@RegisterAction
data class ToastAction(val message: Bind<String>) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val toastMessage = evaluateExpression(rootView, origin, message)
        Toast.makeText(rootView.getContext(), toastMessage, Toast.LENGTH_SHORT).show()
    }
}