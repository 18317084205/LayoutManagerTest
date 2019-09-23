package com.example.layoutmanagertest.adapter

import androidx.databinding.ViewDataBinding
import com.example.layoutmanagertest.R
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.TestItemBinding
import com.liang.layoutmanager.ILayoutManager
import com.liang.layoutmanager.adapter.UniversalAdapter


class TestAdapter(layoutManager: ILayoutManager) : UniversalAdapter<TestBean>(layoutManager) {
    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.test_item
    }

    override fun bindViewHolder(viewDataBinding: ViewDataBinding, position: Int) {
        when(viewDataBinding){
            is TestItemBinding-> viewDataBinding.item = getItem(position)
        }
    }
}