package com.vt.extendedbeaglelib.components.actions

import android.content.Intent
import android.view.View
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction

@RegisterAction
data class CustomOpenNativeRoute(
    val path: String,
) : Action {
    override fun execute(rootView: RootView, origin: View) {
        rootView.getContext().startActivity(Intent(path))
    }
}