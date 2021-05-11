package com.vt.extendedbeaglelib.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.vt.extendedbeaglelib.R
import kotlinx.android.synthetic.main.layout_bottom_sheet_dialog.*

class BaseBottomSheetDialogFragment: BottomSheetDialogFragment() {

    private var destinationUrl: String = ""

    companion object {
        fun newInstance(destinationUrl: String) : BaseBottomSheetDialogFragment {
            val bundle = bundleOf("destination" to destinationUrl)
            return BaseBottomSheetDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            destinationUrl = it.getString("destination").toString()
        }
        setStyle(STYLE_NORMAL, R.style.TransparentBottomSheetStyle);
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.layout_bottom_sheet_dialog, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottom_sheet_container.loadView(
            this,
            ScreenRequest(destinationUrl),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                    when (serverState) {
                        is ServerDrivenState.Started -> {
                            layout_loading.visibility = View.VISIBLE
                        }
                        is ServerDrivenState.Finished -> {
                            layout_loading.visibility = View.GONE
                        }
                        is ServerDrivenState.Error -> {
                            serverState.throwable.printStackTrace()
                        }
                        ServerDrivenState.Canceled -> {
                        }
                        is ServerDrivenState.Loading -> {

                        }
                        ServerDrivenState.Success -> {

                        }
                    }
                }
            }
        )
    }
}