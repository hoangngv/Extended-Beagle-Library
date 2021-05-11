package com.vt.extendedbeaglelib.components.actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.common.Gravity
import com.vt.extendedbeaglelib.ui.FragmentDrawer

@RegisterAction
data class OpenSideMenuViewController(val url : String, val gravity: Gravity = Gravity.START) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val fragmentManager = (rootView.getContext() as AppCompatActivity).supportFragmentManager
        val fragment = FragmentDrawer.newInstance(url, gravity)

        fragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, fragment, url)
            .addToBackStack(fragment.javaClass.simpleName)
            .show(fragment)
            .commitAllowingStateLoss()
    }
}