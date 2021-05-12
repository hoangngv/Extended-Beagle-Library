package com.vt.extendedbeaglelib.config

import br.com.zup.beagle.android.annotation.BeagleComponent
import br.com.zup.beagle.android.setup.DesignSystem
import com.vt.extendedbeaglelib.R

@BeagleComponent
class AppDesignSystem: DesignSystem() {
    override fun textStyle(id: String): Int {
        return when(id) {
            "NormalText" -> R.style.NormalText
            "SmallText" -> R.style.SmallText
            "TinyText" -> R.style.TinyText
            "WhiteNormalText" -> R.style.WhiteNormalText
            "NormalBoldText" -> R.style.NormalBoldText
            "SmallBoldText" -> R.style.SmallBoldText
            "BottomNavigationText" -> R.style.BottomNavigationText
            "TextStyle" -> R.style.TextStyle
            "TextAccessProfile" -> R.style.TextAccessProfile
            "TextTitleProfile" -> R.style.TextTitleProfile
            "h1" -> R.style.TextTemplateCard
            else -> R.style.TextDefault
        }
    }

    override fun buttonStyle(id: String): Int {
        return when(id) {
            "button" -> R.style.DesignSystem_Button_Default
            "ButtonWithIcon" -> R.style.DesignSystem_ButtonWithIcon
            else -> R.style.DesignSystem_Button_Default
        }
    }

    override fun toolbarStyle(id: String): Int {
        return when(id){
            "toolbar" -> R.style.DesignSystem_Toolbar_Center
            else -> R.style.DesignSystem_Toolbar_Center
        }
    }

    override fun inputTextStyle(id: String): Int {
        return when(id) {
            "TextInput" -> R.style.TextInput
            "HintText" -> R.style.HintText
            else -> R.style.TextInput
        }
    }

    override fun image(id: String): Int {
        return when(id) {
            "ic_notification" -> R.drawable.ic_notification
            "ic_search" -> R.drawable.ic_search
            else -> android.R.drawable.ic_menu_help
        }
    }

    override fun tabViewStyle(id: String): Int = R.style.DesignSystem_TabView_Default
}