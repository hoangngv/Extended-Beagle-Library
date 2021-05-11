package com.vt.extendedbeaglelib.components.widgets

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.components.BeagleRecyclerView
import br.com.zup.beagle.android.components.ListView
import br.com.zup.beagle.android.components.OnInitiableComponent
import br.com.zup.beagle.android.components.OnInitiableComponentImpl

import br.com.zup.beagle.android.context.Bind
import br.com.zup.beagle.android.context.ContextComponent
import br.com.zup.beagle.android.context.ContextData

import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.annotation.RegisterWidget
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.widget.core.ListDirection
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

@RegisterWidget
data class GridView
constructor (
        val spanCount : Int = 2,
        val direction: ListDirection = ListDirection.VERTICAL,
        override val context: ContextData? = null,
        override val onInit: List<Action>? = null,
        val dataSource: Bind<List<Any>>? = null,
        val template: ServerDrivenComponent? = null,
        val onScrollEnd: List<Action>? = null,
        val scrollEndThreshold: Int? = null,
        val iteratorName: String = "item",
        val key: String? = null
) : WidgetView(), ContextComponent, OnInitiableComponent by OnInitiableComponentImpl(onInit) {

    override fun buildView(rootView: RootView): View {
        return buildNewListView(rootView)
    }

    private fun buildNewListView(rootView: RootView): View {
        val listView = ListView(
            direction = direction,
            context = context,
            onInit = onInit,
            dataSource = dataSource,
            template = template,
            onScrollEnd = onScrollEnd,
            scrollEndThreshold = scrollEndThreshold,
            isScrollIndicatorVisible = false,
            iteratorName = iteratorName,
            key = key
        )

        listView.buildView(rootView)

        val field = ListView::class.memberProperties.find { it.name == "recyclerView" }

        field?.let {
            it.isAccessible = true
            val recyclerViewInside = it.get(listView) as BeagleRecyclerView

            val orientation = listDirectionToRecyclerViewOrientation()
            val customLayoutManager = GridLayoutManager(rootView.getContext(), spanCount, orientation, false)
            recyclerViewInside.layoutManager = customLayoutManager
            return recyclerViewInside
        }
        return View(rootView.getContext())
    }

    private fun listDirectionToRecyclerViewOrientation() = if (direction == ListDirection.VERTICAL) {
        RecyclerView.VERTICAL
    } else {
        RecyclerView.HORIZONTAL
    }
}