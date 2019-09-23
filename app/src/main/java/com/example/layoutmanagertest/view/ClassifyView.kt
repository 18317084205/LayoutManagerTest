/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: HotSearchView
 * Author: Owner
 * Date: 2019/9/23 14:35
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.layoutmanagertest.view;

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.layoutmanagertest.R
import com.example.layoutmanagertest.adapter.ItemAdapter
import com.example.layoutmanagertest.bean.ItemBean
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.ClassifyViewBinding
import com.example.layoutmanagertest.databinding.ItemHotSearchBinding
import com.example.layoutmanagertest.databinding.ItemLayoutDefBinding

/**
 * @ClassName: HotSearchView
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 14:35
 */
class ClassifyView : ArrayItemView<ClassifyViewBinding> {

    override fun setContentView() = R.layout.classify_view

    override fun getRecyclerView() = dataBinding.recyclerView!!

    override fun getLayoutManager() = GridLayoutManager(
        context,
        5
    )

    override fun getItemViewResId(viewType: Int) = R.layout.item_layout_def

    override fun bindViewHolder(viewDataBinding: ViewDataBinding, item: TestBean) {
        when (viewDataBinding) {
            is ItemLayoutDefBinding -> {
                viewDataBinding.root.layoutParams = ConstraintLayout.LayoutParams(-2,-2)
                viewDataBinding.item = item
            }
        }
    }

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {


        getRecyclerView().addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(5, 5, 5, 5)
            }
        })

    }

}

