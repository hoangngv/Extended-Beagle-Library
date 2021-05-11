package com.vt.extendedbeaglelib.components.actions

import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.ui.BaseBottomSheetDialogFragment
import com.vt.extendedbeaglelib.ui.BaseDialogFragment

@RegisterAction
data class DismissDialogAction(val endpoint: String): Action {
    override fun execute(rootView: RootView, origin: View) {
        val fragmentManager = (rootView.getContext() as AppCompatActivity).supportFragmentManager
        val dialogFragment = fragmentManager.findFragmentByTag(endpoint)
//        val isBeagleActivity = (rootView.getContext() as AppCompatActivity) is AppBeagleActivity
//        if (isBeagleActivity) {
//            if (dialogFragment != null) {
//                fragmentManager.beginTransaction().remove(dialogFragment).addToBackStack(null).commit()
//            }
//        } else {
//            if (dialogFragment is BaseDialogFragment) {
//                dialogFragment.dismiss()
//                Log.d("dLog", "Dismiss a BaseDialogFragment with endpoint $endpoint")
//            } else if (dialogFragment is BaseBottomSheetDialogFragment) {
//                dialogFragment.dismiss()
//                Log.d("dLog", "Dismiss a BaseBottomSheetDialogFragment with endpoint $endpoint")
//            }
//        }

        if (dialogFragment is BaseDialogFragment) {
            dialogFragment.dismiss()
            Log.d("dLog", "Dismiss a BaseDialogFragment with endpoint $endpoint")
        } else if (dialogFragment is BaseBottomSheetDialogFragment) {
            dialogFragment.dismiss()
            Log.d("dLog", "Dismiss a BaseBottomSheetDialogFragment with endpoint $endpoint")
        }
    }
}