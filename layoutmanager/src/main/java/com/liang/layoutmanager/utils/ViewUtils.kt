package com.liang.layoutmanager.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun inflate(
    context: Context, layoutId: Int, parent: ViewGroup?,
    attachToParent: Boolean
): ViewDataBinding {
    return DataBindingUtil.inflate(LayoutInflater.from(context), layoutId, parent, attachToParent)
}

fun findDataBinding(view: View): ViewDataBinding? {
    return DataBindingUtil.findBinding<ViewDataBinding>(view)
}