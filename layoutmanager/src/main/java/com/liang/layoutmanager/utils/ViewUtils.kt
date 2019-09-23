package com.liang.layoutmanager.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

fun inflate(
    context: Context, layoutId: Int, parent: ViewGroup?,
    attachToParent: Boolean
): ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, attachToParent)
}

fun findDataBinding(view: View): ViewDataBinding? {
    return DataBindingUtil.findBinding(view)
}


/**
 * 获取某个childView在水平方向所占的空间
 * @param layoutManager
 * @param view
 * @return
 */
fun getDecoratedMeasurementHorizontal(layoutManager: RecyclerView.LayoutManager, view: View): Int {
    val params = view.layoutParams as RecyclerView.LayoutParams
    return (layoutManager.getDecoratedMeasuredWidth(view) + params.leftMargin
            + params.rightMargin)
}

/**
 * 获取某个childView在竖直方向所占的空间
 * @param layoutManager
 * @param view
 * @return
 */
fun getDecoratedMeasurementVertical(layoutManager: RecyclerView.LayoutManager, view: View): Int {
    val params = view.layoutParams as RecyclerView.LayoutParams
    return (layoutManager.getDecoratedMeasuredHeight(view) + params.topMargin
            + params.bottomMargin)
}

fun getHorizontalSpace(layoutManager: RecyclerView.LayoutManager): Int {
    return layoutManager.width - layoutManager.paddingLeft - layoutManager.paddingRight
}

fun getVerticalSpace(layoutManager: RecyclerView.LayoutManager): Int {
    return layoutManager.height - layoutManager.paddingTop - layoutManager.paddingBottom
}