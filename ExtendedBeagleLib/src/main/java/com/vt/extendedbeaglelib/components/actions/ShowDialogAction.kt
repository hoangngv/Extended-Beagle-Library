package com.vt.extendedbeaglelib.components.actions

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.ui.BaseDialogFragment

@RegisterAction
data class ShowDialogAction(
    val endpoint: String,
    val numberOfItems: Int?
) : Action {
    override fun execute(rootView: RootView, origin: View) {
        val fragmentManager = (rootView.getContext() as AppCompatActivity).supportFragmentManager
        val previousFragment = fragmentManager.findFragmentByTag(endpoint)

        if (previousFragment != null) {
            fragmentManager.beginTransaction().remove(previousFragment).addToBackStack(null).commit()
        }

        val dialogFragment = BaseDialogFragment.newInstance(endpoint)
        dialogFragment.show(fragmentManager, endpoint)

//        val isBeagleActivity = (rootView.getContext() as AppCompatActivity) is AppBeagleActivity
//
//        if (isBeagleActivity) {
//            fragmentManager
//                .beginTransaction()
//                .add(R.id.server_driven_container, dialogFragment, endpoint)
//                .addToBackStack(dialogFragment.javaClass.simpleName)
//                .show(dialogFragment)
//                .commitAllowingStateLoss()
//        } else {
//            dialogFragment.show(fragmentManager, endpoint)
//        }
    }
}