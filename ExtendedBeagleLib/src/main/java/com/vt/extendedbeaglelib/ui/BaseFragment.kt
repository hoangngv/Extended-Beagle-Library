package com.vt.extendedbeaglelib.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenMethod
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.extendedbeaglelib.R
import kotlinx.android.synthetic.main.fragment_home.*

class BaseFragment : Fragment() {

    private var endpoint = ""

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("dLog", "onDestroyView: $tag")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            endpoint = it.getString(ENDPOINT).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderServerDrivenUI()
    }

    private fun renderServerDrivenUI() {
        fragment.loadView(
            this,
            ScreenRequest(
                url = endpoint,
                method = ScreenMethod.POST
            ),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                }
            }
        )
    }

    companion object {
        private const val ENDPOINT = "endpoint"

        @JvmStatic
        fun newInstance(destination: String) =
            BaseFragment().apply {
                arguments = Bundle().apply {
                    putString(ENDPOINT, destination)
                }
            }
    }
}