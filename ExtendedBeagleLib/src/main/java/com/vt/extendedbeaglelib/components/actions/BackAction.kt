package com.vt.extendedbeaglelib.components.actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction

@RegisterAction
data class BackAction(val message : String) : Action {
    override fun execute(rootView: RootView, origin: View) {
        (rootView.getContext() as AppCompatActivity).finish()
    }
}