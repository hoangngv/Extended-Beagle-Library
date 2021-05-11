package com.vt.extendedbeaglelib.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.extendedbeaglelib.R
import kotlinx.android.synthetic.main.layout_dialog.*

class BaseDialogFragment: DialogFragment() {

    private var destinationUrl: String = ""

    companion object {
        fun newInstance(destinationUrl: String) : BaseDialogFragment {
            val bundle = bundleOf("destination" to destinationUrl)
            return BaseDialogFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            destinationUrl = it.getString("destination").toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return inflater.inflate(R.layout.layout_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_container.loadView(
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