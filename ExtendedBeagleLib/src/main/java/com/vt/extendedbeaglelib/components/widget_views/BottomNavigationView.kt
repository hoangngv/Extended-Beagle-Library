package com.vt.extendedbeaglelib.components.widget_views

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.vt.extendedbeaglelib.R
import com.vt.extendedbeaglelib.common.enum_class.TabInfoType
import com.vt.extendedbeaglelib.data.model.BadgeModel
import com.vt.extendedbeaglelib.data.model.TabInfo
import com.vt.extendedbeaglelib.ui.BaseFragment
import com.vt.extendedbeaglelib.utils.bus.SingleBus
import com.vt.extendedbeaglelib.utils.bus.SingleBusKey
import kotlinx.android.synthetic.main.layout_bottom_navigation_view.view.*
import kotlinx.coroutines.Job

class BottomNavigationView(context: Context) : LinearLayout(context) {

    private var notificationMenuItemId = 0
    private var currentTab = ""
    private var tempFragment = BaseFragment()

    init {
        View.inflate(context, R.layout.layout_bottom_navigation_view, this)
        initBus()
        invalidate()
        requestLayout()
    }

    private fun initBus(){
        val busJobs : MutableList<Job> = arrayListOf()
        busJobs.apply {
            add(SingleBus.receive(SingleBusKey.SET_BADGE_NUMBER) {
                val badgeModel = it as BadgeModel
                val notificationBadge = navigationBar.getOrCreateBadge(notificationMenuItemId)
                notificationBadge.number = badgeModel.number
                notificationBadge.backgroundColor = Color.parseColor(badgeModel.badgeBackgroundColor)
                notificationBadge.badgeTextColor = Color.parseColor(badgeModel.badgeTextColor)
            })
        }
    }

    fun setupMenu(
            tabItems: List<TabInfo>,
            selectedColor: String? = "#3596EC",
            unselectedColor: String? = "#788793",
            activity: AppCompatActivity
    ) {
        if (tabItems.isNotEmpty()) {
            val fragmentManager: FragmentManager = activity.supportFragmentManager
            setupMenuItems(tabItems)
            setNavigationTextColor(navigationBar, selectedColor, unselectedColor)
            generateFragment(tabItems, fragmentManager)
            setupListener(tabItems, fragmentManager)
        } else {
            Log.d("dLog", "MenuItems size is zero or null")
        }
    }

    private fun setupMenuItems(tabItems: List<TabInfo>) {
        val menu = navigationBar.menu

        for (i in tabItems.indices) {
            Glide
                .with(this)
                .load(tabItems[i].remoteIconUrl)
                .into(object : SimpleTarget<Drawable?>() {
                    override fun onResourceReady(
                            resource: Drawable,
                            @Nullable transition: Transition<in Drawable?>?
                    ) {
                        if (i == 0) {
                            menu.findItem(R.id.default_page).apply {
                                icon = resource
                                setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                                title = tabItems[i].title
                                currentTab = title.toString()
                            }
                        } else {
                            val menuItemId = ViewCompat.generateViewId()
                            menu.add(Menu.NONE, menuItemId, Menu.NONE, tabItems[i].title).apply {
                                icon = resource
                                setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
                            }

                            if (i == tabItems.size - 1) {
                                notificationMenuItemId = menuItemId
                            }
                        }
                    }
                })
        }
    }

    private fun generateFragment(
            tabItems: List<TabInfo>,
            fragmentManager: FragmentManager)
    {
        val fragmentTransaction = fragmentManager.beginTransaction()

        for (i in tabItems.indices) {
            if (tabItems[i].type == TabInfoType.BEAGLE) {
                tabItems[i].api?.let {
                    val endpoint = "/beagle$it"
                    val homeFragmentInstance = BaseFragment.newInstance(endpoint)

                    fragmentTransaction.add(
                            R.id.server_driven_container,
                            homeFragmentInstance,
                            tabItems[i].title
                    )

                    if (i == 0) {
                        fragmentTransaction.show(homeFragmentInstance)
                        tempFragment = homeFragmentInstance
                    } else {
                        fragmentTransaction.hide(homeFragmentInstance)
                    }
                }
            } else if (tabItems[i].type == TabInfoType.NATIVE) {
                tabItems[i].className?.let {
                    Log.d("dLog", "Native fragment -> $it")
//                    val nativeFragmentClass = Class.forName(it)
//                    val nativeFragmentInstance = nativeFragmentClass.newInstance()
                }
            }
        }

        fragmentTransaction.commit()
    }

    private fun setupListener(
            tabItems: List<TabInfo>,
            fragmentManager: FragmentManager)
    {
        navigationBar.setOnNavigationItemSelectedListener { item ->
            val fragmentTransaction = fragmentManager.beginTransaction()

            if (item.title != currentTab) {
                for (tabItem in tabItems) {
                    if (tabItem.title == item.title) {
                        currentTab = item.title.toString()

                        fragmentTransaction.hide(tempFragment)
                        val shownFragment = fragmentManager.findFragmentByTag(currentTab) as BaseFragment
                        fragmentTransaction.show(shownFragment)
                        tempFragment = shownFragment

                        fragmentTransaction.commit()
                    }
                }
            }
            true
        }
    }

    private fun setNavigationTextColor(
            navigationView: BottomNavigationView,
            selectedColor: String? = "#3596EC",
            unselectedColor: String? = "#788793"
    ) {
        val colors = intArrayOf(
                Color.parseColor(unselectedColor),
                Color.parseColor(selectedColor)
        )
        val states = arrayOf(
                intArrayOf(android.R.attr.state_enabled, -android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_enabled, android.R.attr.state_checked)
        )

        navigationView.itemTextColor = ColorStateList(states, colors)
        navigationView.itemIconTintList = ColorStateList(states, colors)
    }
}