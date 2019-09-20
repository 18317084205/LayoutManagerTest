package com.liang.layoutmanager

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class ILayoutManager(context: Context, orientation: Int, reverseLayout: Boolean) :
    LinearLayoutManager(context, orientation, reverseLayout) {

    private val layoutHelpers = arrayListOf<LayoutHelper<*>>()

    fun setLayoutHelpers(helpers: Collection<LayoutHelper<*>>) {
        layoutHelpers.clear()
        layoutHelpers.addAll(helpers)
    }

    fun addLayoutHelpers(helpers: Collection<LayoutHelper<*>>) {
        layoutHelpers.addAll(helpers)
    }

    fun addLayoutHelper(helper: LayoutHelper<*>) {
        layoutHelpers.add(helper)
    }

    fun getLayoutHelpers(): ArrayList<LayoutHelper<*>> {
        return layoutHelpers
    }


}