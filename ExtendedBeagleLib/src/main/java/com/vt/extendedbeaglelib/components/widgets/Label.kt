package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.utils.observeBindChanges
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.widget.core.TextAlignment
import com.vt.beagle_ui.common.FontStyle
import com.vt.extendedbeaglelib.components.widget_views.TextView

@RegisterWidget
class Label(
    val text: Bind<String>?,
    val textColor: Bind<String>?,
    val cornerRadius: Double? = null,
    val backgroundColor: String? = null,
    val fontSize: Double = 17.0,
    val fontName: String? = null,
    val fontStyle: FontStyle = FontStyle.NORMAL,
    val numberOfLines: Int = 1,
    val textAlignment: TextAlignment = TextAlignment.LEFT
) : WidgetView() {
    override fun buildView(rootView: RootView): View {
        return TextView(rootView.getContext()).apply {

            setText(
                cornerRadius = cornerRadius,
                backgroundColor = backgroundColor,
                fontSize = fontSize,
                fontName = fontName,
                fontStyle = fontStyle,
                numberOfLines = numberOfLines,
                textAlignment = this@Label.textAlignment)

            text?.let {
                observeBindChanges(rootView, this@apply, it) {  label ->
                    changeText(label ?: "")
                }
            }

            textColor?.let {
                observeBindChanges(rootView, this@apply, it) {  color ->
                    changeColor(color ?: "#000000")
                }
            }
        }
    }
}