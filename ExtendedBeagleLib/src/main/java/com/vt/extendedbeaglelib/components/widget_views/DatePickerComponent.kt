package com.vt.extendedbeaglelib.components.widget_views

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.appcompat.widget.AppCompatTextView
import java.text.SimpleDateFormat
import java.util.*

class DatePickerComponent constructor(
    context: Context
) : AppCompatTextView(context), DatePickerDialog.OnDateSetListener {

    private val myCalendar: Calendar = Calendar.getInstance()
    var dateSetListener: DateSetListener? = null

    init {
        this.setOnClickListener {
            DatePickerDialog(
                context,
                this,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
    }

    fun setText(text: String) {
        this.text = text
    }

    private fun Date.formatDate(): String{
        val myFormat = "yyyy-MM-dd"
        val simpleDateFormat = SimpleDateFormat(myFormat, Locale.US)
        return simpleDateFormat.format(this).toString()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        myCalendar[Calendar.YEAR] = year
        myCalendar[Calendar.MONTH] = month
        myCalendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        dateSetListener?.onDateSet(myCalendar.time.formatDate())
    }
}

interface DateSetListener{
    fun onDateSet(value: String)
}