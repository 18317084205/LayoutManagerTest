package com.liang.layoutmanager.adapter

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.liang.layoutmanager.utils.findDataBinding
import com.liang.layoutmanager.utils.inflate
import kotlin.math.min

abstract class BaseAdapter<Data> : IAdapter<Data, BaseAdapter.BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return BaseViewHolder(
            inflate(
                parent.context,
                getItemLayoutId(viewType),
                parent,
                false
            ).root
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        findDataBinding(holder.itemView)?.let { bindViewHolder(it, position) }
    }

    abstract fun getItemLayoutId(viewType: Int): Int

    abstract fun bindViewHolder(viewDataBinding: ViewDataBinding, position: Int)

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        getItems().addOnListChangedCallback(itemsChangedCallback)
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        getItems().removeOnListChangedCallback(itemsChangedCallback)
    }

    private val itemsChangedCallback = object :
        ObservableList.OnListChangedCallback<ObservableArrayList<Data>>() {
        override fun onChanged(sender: ObservableArrayList<Data>?) {
            notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(
            sender: ObservableArrayList<Data>?,
            positionStart: Int,
            itemCount: Int
        ) {
            Log.d(
                "BaseAdapter",
                "onItemRangeRemoved positionStart: $positionStart, itemCount: $itemCount"
            )
            notifyItemRangeRemoved(positionStart, itemCount)
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeMoved(
            sender: ObservableArrayList<Data>?,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
            notifyItemMoved(fromPosition, toPosition)
            notifyItemRangeChanged(min(fromPosition, toPosition), itemCount)
        }

        override fun onItemRangeInserted(
            sender: ObservableArrayList<Data>?,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeChanged(
            sender: ObservableArrayList<Data>?,
            positionStart: Int,
            itemCount: Int
        ) {
            notifyItemRangeChanged(positionStart, itemCount)
        }
    }

    class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}