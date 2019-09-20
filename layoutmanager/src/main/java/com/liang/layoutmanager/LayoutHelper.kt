package com.liang.layoutmanager

import androidx.recyclerview.widget.RecyclerView

abstract class LayoutHelper<Data> {

    private val items = arrayListOf<Data>()

    fun setItems(data: Collection<Data>){
        items.clear()
        items.addAll(data)
    }

    fun getItems(): ArrayList<Data>{
        return items
    }

    abstract fun onItemLayout(recycler: RecyclerView.Recycler, state: RecyclerView.State, dy: Int)

}