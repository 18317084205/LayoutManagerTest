package com.example.layoutmanagertest

import androidx.databinding.ViewDataBinding
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.TestItemBinding
import com.liang.layoutmanager.BaseAdapter
import com.liang.layoutmanager.ILayoutManager


class TestAdapter(layoutManager: ILayoutManager) : BaseAdapter<TestBean>(layoutManager) {
    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.test_item
    }

    override fun bindViewHolder(viewDataBinding: ViewDataBinding, position: Int) {
        when(viewDataBinding){
            is TestItemBinding-> viewDataBinding.item = getItem(position)
        }
    }
}