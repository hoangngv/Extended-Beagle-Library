package com.vt.extendedbeaglelib.components.actions

import android.view.View
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey

@RegisterAction
class RevertImageView: Action {
    override fun execute(rootView: RootView, origin: View) {
        SingleBus.send(SingleBusKey.REVERT_IMAGE_VIEW, true)
    }
}