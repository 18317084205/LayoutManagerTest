package com.liang.layoutmanager.adapter

import androidx.databinding.ObservableArrayList
import androidx.recyclerview.widget.RecyclerView

abstract class IAdapter<Data, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val data = ObservableArrayList<Data>()

    override fun getItemCount(): Int {
        return data.size
    }

    fun add(`object`: Data) {
        data.add(`object`)
    }

    fun add(index: Int, `object`: Data) {
        data.add(index, `object`)
    }

    fun addAll(collection: Collection<Data>?) {
        if (collection != null) {
            data.addAll(collection)
        }
    }

    fun addAll(vararg items: Data) {
        addAll(items.asList())
    }

    fun clear() {
        data.clear()
    }

    fun remove(`object`: Data) {
        data.remove(`object`)
    }

    fun remove(index: Int) {
        data.removeAt(index)
    }

    fun reset(collection: Collection<Data>?) {
        clear()
        addAll(collection)
    }

    fun getItems(): ObservableArrayList<Data> {
        return data
    }

    fun getItem(position: Int): Data {
        return data[position]
    }
}