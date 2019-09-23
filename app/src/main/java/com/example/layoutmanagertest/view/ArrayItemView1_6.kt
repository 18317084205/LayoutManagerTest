/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ArrayItemView
 * Author: Owner
 * Date: 2019/9/23 16:48
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.layoutmanagertest.view;

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.layoutmanagertest.R
import com.example.layoutmanagertest.bean.ItemBean
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.ItemLayoutDefBinding

import com.liang.layoutmanager.adapter.BaseAdapter

/**
 * @ClassName: ArrayItemView
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 16:48
 */
class ArrayItemView1_6 : FrameLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val itemWidth = width / 5

        for (i in 0..childCount) {
            val child: View? = getChildAt(i)
            child?.let {
                when (i) {
                    0 -> it.layoutParams = FrameLayout.LayoutParams(itemWidth * 2, height)
                    else -> it.layoutParams = FrameLayout.LayoutParams(itemWidth, height / 2)
                }
            }
        }


    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val itemWidth = width / 5

        for (i in 0..childCount) {
            val child: View? = getChildAt(i)
            child?.let {
                when (i) {
                    0 -> it.layout(0, 0, itemWidth * 2, height)
                    in 1..3 -> it.layout(itemWidth * (i + 1), 0, itemWidth * (i + 2), height / 2)
                    else -> it.layout(itemWidth * (i - 2), height / 2, itemWidth * (i + 3), height)
                }
            }
        }

    }

    open fun setViewData(data: ItemBean?) {
        data?.items?.forEach {
            if (childCount < 7) {
                val viewDataBinding = com.liang.layoutmanager.utils.inflate(
                    context,
                    R.layout.item_layout_def,
                    this,
                    false
                ) as ItemLayoutDefBinding
                viewDataBinding.item = it
                addView(viewDataBinding.root)
            }
        }
    }


}