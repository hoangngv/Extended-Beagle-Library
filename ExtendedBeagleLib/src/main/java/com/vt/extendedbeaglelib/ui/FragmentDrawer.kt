package com.vt.extendedbeaglelib.ui

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import br.com.zup.beagle.android.utils.loadView
import br.com.zup.beagle.android.view.ScreenRequest
import br.com.zup.beagle.android.view.ServerDrivenState
import br.com.zup.beagle.android.view.custom.OnServerStateChanged
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.common.Gravity
import kotlinx.android.synthetic.main.fragment_drawer_navigation.*

class FragmentDrawer : Fragment() {

    companion object {
        const val KEY_URL = "KEY_URL"
        const val KEY_GRAVITY = "KEY_GRAVITY"

        fun newInstance(url: String, gravity: Gravity = Gravity.START) : FragmentDrawer {
            return FragmentDrawer().apply {
                arguments = bundleOf(
                    KEY_URL to url,
                    KEY_GRAVITY to gravity
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_drawer_navigation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            addChildView()
        }, 100)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("dLog", "FragmentDrawer onDestroyView ")
    }

    override fun onStop() {
        super.onStop()
        Log.d("dLog", "FragmentDrawer onStop ")
    }

    override fun onPause() {
        super.onPause()
        Log.d("dLog", "FragmentDrawer onPause ")
    }

    private fun addChildView(){
        val viewContent = layoutInflater.inflate(R.layout.view_content_drawer, null) as FrameLayout

        val animation = AnimationUtils.loadAnimation(
            requireContext(),
            if (arguments?.getSerializable(KEY_GRAVITY) == Gravity.END) R.anim.slide_right_to_center
            else R.anim.slide_left_to_center
        )

        val parentWidth = view?.width ?: 0

        val contentChildWidth = parentWidth / 10 * 8

        val params: FrameLayout.LayoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.MATCH_PARENT
        ).apply {
            gravity = if(arguments?.getSerializable(KEY_GRAVITY) == Gravity.END) android.view.Gravity.END else android.view.Gravity.START
            width = contentChildWidth
        }

        layoutDrawer.addView(viewContent, params)
        viewContent.startAnimation(animation)

        layoutDrawer.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        viewContent.loadView(
            requireActivity() as AppCompatActivity,
            ScreenRequest(arguments?.getString(KEY_URL, "") ?: ""),
            object : OnServerStateChanged {
                override fun invoke(serverState: ServerDrivenState) {
                    Log.d("dLog", serverState.toString())
                }
            }
        )
    }
}