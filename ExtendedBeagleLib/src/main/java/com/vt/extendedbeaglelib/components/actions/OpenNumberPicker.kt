package com.vt.extendedbeaglelib.components.actions

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.NumberPicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.annotation.RegisterAction
import com.vt.extendedbeaglelib.R

@RegisterAction
data class OpenNumberPicker(
    val title: String?,
    val doneButtonTitle: String,
    val cancelButtonTitle: String,
    val minValue: Int,
    val maxValue: Int,
    val actionOnDone: List<Action>
): Action {
    @SuppressLint("InflateParams")
    override fun execute(rootView: RootView, origin: View) {
        Log.d("dLog", "OpenNumberPicker")
        val context = rootView.getContext()

        val customLayout: View = (context as AppCompatActivity).layoutInflater.inflate(R.layout.layout_number_picker, null)
        val dialogTitle: TextView = customLayout.findViewById(R.id.title)
        val numberPicker: NumberPicker = customLayout.findViewById(R.id.numberPicker) as NumberPicker

        numberPicker.minValue = minValue
        numberPicker.maxValue = maxValue
        numberPicker.value = maxValue

        val dialog = AlertDialog.Builder(context)
            .setView(customLayout)
            .setPositiveButton(doneButtonTitle) { _, _ ->
                this@OpenNumberPicker.handleEvent(
                    rootView = rootView,
                    origin = origin,
                    actions = actionOnDone,
                    ContextData(
                        id = "numberPickerDataOnChange",
                        value = numberPicker.value
                    )
                )
            }
            .setNegativeButton(cancelButtonTitle, null)
            .create()

        title?.let {
            dialogTitle.visibility = View.VISIBLE
            dialogTitle.text = it
        }

        dialog.window?.setBackgroundDrawableResource(R.drawable.bg_dialog)
        dialog.show()
    }
}
