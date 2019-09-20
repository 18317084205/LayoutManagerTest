package com.liang.layoutmanager.adapter

import android.util.Log
import com.liang.layoutmanager.ILayoutManager
import com.liang.layoutmanager.LayoutHelper

abstract class UniversalAdapter<Data>(private val layoutManager: ILayoutManager) :
    BaseAdapter<Data>() {

    fun setLayoutHelpers(helpers: Collection<LayoutHelper<Data>>) {
        layoutManager.setLayoutHelpers(helpers)
        val data = arrayListOf<Data>()
        helpers.forEach {
            data.addAll(it.getItems())
        }
        Log.e("UniversalAdapter","data: ${data.size}")
        reset(data)
    }




}