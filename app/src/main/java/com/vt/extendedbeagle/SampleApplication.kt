package com.vt.extendedbeagle

import android.app.Application
import br.com.zup.beagle.android.widget.WidgetView
import com.vt.extendedbeaglelib.config.BeagleSetup
import com.vt.extendedbeaglelib.config.MyBeagleSetup

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

//        BeagleSetup().init(this)
        MyBeagleSetup(listOf(LabelTwo::class.java as Class<WidgetView>)).init(this)
    }

}
