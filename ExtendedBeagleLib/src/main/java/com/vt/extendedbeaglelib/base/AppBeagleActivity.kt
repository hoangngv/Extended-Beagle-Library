package com.vt.extendedbeaglelib.base

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import br.com.zup.beagle.android.annotation.RegisterController
import br.com.zup.beagle.android.view.BeagleActivity
import br.com.zup.beagle.android.view.ServerDrivenState
import com.vt.extendedbeaglelib.R
import kotlinx.android.synthetic.main.activity_beagle.*

@RegisterController(id = "ACTIVITY_IDENTIFIER")
class AppBeagleActivity : BeagleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beagle)
    }

    override fun getServerDrivenContainerId(): Int = R.id.server_driven_container

    override fun getToolbar(): Toolbar = findViewById(R.id.toolbar)

    override fun onServerDrivenContainerStateChanged(state: ServerDrivenState) {
        when (state) {
            is ServerDrivenState.Started -> {
                progress_bar.visibility = View.VISIBLE
            }

            is ServerDrivenState.Finished -> {
                progress_bar.visibility = View.GONE
            }
            else -> Log.d("dLog", "State -> $state")
        }
    }
}