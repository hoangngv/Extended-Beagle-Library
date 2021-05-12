package com.vt.extendedbeaglelib.components.actions

import android.view.View
import androidx.core.content.ContextCompat.startActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.networking.HttpMethod
import br.com.zup.beagle.android.utils.newServerDrivenIntent
import br.com.zup.beagle.android.view.ScreenMethod
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.ui.AppBeagleActivity

@RegisterAction
data class NavigateAction(private val destination: String, private val httpMethod: HttpMethod) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val screenRequest = if (httpMethod == HttpMethod.GET) ScreenRequest(url = destination, method = ScreenMethod.GET) else ScreenRequest(url = destination, method = ScreenMethod.POST)
        val intent = rootView.getContext().newServerDrivenIntent<AppBeagleActivity>(screenRequest)
        startActivity(rootView.getContext(), intent, null)
    }
}