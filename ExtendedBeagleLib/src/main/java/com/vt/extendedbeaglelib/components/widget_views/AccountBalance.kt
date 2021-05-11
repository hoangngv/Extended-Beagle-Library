package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.utils.extensions.applyCurrencyFormat
import kotlinx.android.synthetic.main.layout_account_balance.view.*

class AccountBalance constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 1
): ConstraintLayout(context, attrs, defStyleAttr){

    var isVisible = true

    init {
        View.inflate(context, R.layout.layout_account_balance, this)
        initialComponent()
        invalidate()
        requestLayout()
    }

    fun setValueBalance(value: String, currency: String) {
        balance.text = value.applyCurrencyFormat(currency)
    }
    private fun actionBalanceHidden() {
        isVisible = !isVisible
    }

    private fun setImage() : Int {
        return if (isVisible) R.drawable.ic_visible  else R.drawable.ic_invisible
    }

    private fun initialComponent() {
        visible.setOnClickListener {
            actionBalanceHidden()
            visible.setImageResource(setImage())
            if (isVisible) {
                balance.visibility = View.GONE
                hidden_balance.visibility = View.VISIBLE
            } else {
                balance.visibility = View.VISIBLE
                hidden_balance.visibility = View.GONE
            }
        }
    }
}