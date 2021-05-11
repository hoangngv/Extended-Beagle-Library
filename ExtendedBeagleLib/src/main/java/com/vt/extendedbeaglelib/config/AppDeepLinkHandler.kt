package com.vt.extendedbeaglelib.config

import android.content.Intent
import android.content.pm.PackageManager
import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.navigation.DeepLinkHandler
import br.com.zup.beagle.android.widget.RootView
import com.vt.extendedbeaglelib.ui.AppBeagleActivity

@BeagleComponent
class AppDeepLinkHandler : DeepLinkHandler {
    override fun getDeepLinkIntent(
        rootView: RootView,
        path: String,
        data: Map<String, String>?,
        shouldResetApplication: Boolean,
    ): Intent {
        val intent = Intent(path)
        val pm: PackageManager = rootView.getContext().packageManager
        val resolveInfos = pm.queryIntentActivities(intent, PackageManager.GET_RESOLVED_FILTER)
        return if (resolveInfos.isEmpty()) {
            // Default activity
            Intent(rootView.getContext(), AppBeagleActivity::class.java)
        } else {
            // Default mapped intent
            Intent(path)
        }
    }
}

//return if (path == "login") {
//    Intent(rootView.getContext(), LoginActivity::class.java)
//} else if (path == "home") {
//    Intent(rootView.getContext(), HomeActivity::class.java)
//} else if (path == "anotherScreen") {
//    Intent(rootView.getContext(), AnotherScreen::class.java)
//} else {
//    Intent(rootView.getContext(), MainActivity::class.java)
//}