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
 * @param LayoutManager
 * @param view
 * @return
 */
fun getDecoratedMeasurementHorizontal(LayoutManager: RecyclerView.LayoutManager, view: View): Int {
    val params = view.layoutParams as RecyclerView.LayoutParams
    return (LayoutManager.getDecoratedMeasuredWidth(view) + params.leftMargin
            + params.rightMargin)
}

/**
 * 获取某个childView在竖直方向所占的空间
 * @param LayoutManager
 * @param view
 * @return
 */
fun getDecoratedMeasurementVertical(LayoutManager: RecyclerView.LayoutManager, view: View): Int {
    val params = view.layoutParams as RecyclerView.LayoutParams
    return (LayoutManager.getDecoratedMeasuredHeight(view) + params.topMargin
            + params.bottomMargin)
}

fun getHorizontalSpace(LayoutManager: RecyclerView.LayoutManager): Int {
    return LayoutManager.width - LayoutManager.paddingLeft - LayoutManager.paddingRight
}

fun getVerticalSpace(LayoutManager: RecyclerView.LayoutManager): Int {
    return LayoutManager.height - LayoutManager.paddingTop - LayoutManager.paddingBottom
}