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

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.layoutmanagertest.bean.ItemBean
import com.example.layoutmanagertest.bean.TestBean

import com.liang.layoutmanager.adapter.BaseAdapter

/**
 * @ClassName: ArrayItemView
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 16:48
 */
@Suppress("UNCHECKED_CAST")
abstract class ArrayItemView<DataBinding : ViewDataBinding> : FrameLayout {

    protected val dataBinding: DataBinding by lazy {
        val viewBinding =
            com.liang.layoutmanager.utils.inflate(context, setContentView(), this, false)
        viewBinding as DataBinding
    }

    abstract fun setContentView(): Int

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        addView(dataBinding.root)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = getRecyclerView()
        recyclerView.layoutManager = getLayoutManager()
        recyclerView.adapter = itemAdapter
    }

    open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context)

    abstract fun getRecyclerView(): RecyclerView

    abstract fun getItemViewResId(viewType: Int): Int
    abstract fun bindViewHolder(viewDataBinding: ViewDataBinding, item: TestBean)


    fun getItemType(position: Int): Int {
        return 0
    }

    open fun setViewData(data: ItemBean?) {
        data.let {
            itemAdapter.reset(it?.items)
        }
    }

    private val itemAdapter = object : BaseAdapter<TestBean>() {
        override fun getItemLayoutId(viewType: Int) = getItemViewResId(viewType)

        override fun bindViewHolder(viewDataBinding: ViewDataBinding, position: Int) {
            bindViewHolder(viewDataBinding, getItem(position))
        }

        override fun getItemViewType(position: Int): Int {
            return getItemType(position)
        }
    }

}