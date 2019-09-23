/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: NormalAdapter
 * Author: Owner
 * Date: 2019/9/23 11:04
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.layoutmanagertest.adapter;

import androidx.databinding.ViewDataBinding
import com.example.layoutmanagertest.R
import com.example.layoutmanagertest.bean.ItemBean
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.ItemHotSearchBinding
import com.example.layoutmanagertest.databinding.ItemLayoutSearchBinding
import com.liang.layoutmanager.adapter.BaseAdapter

/**
 * @ClassName: NormalAdapter
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 11:04
 */
class ItemAdapter : BaseAdapter<TestBean>() {

    override fun getItemLayoutId(viewType: Int): Int {
        return R.layout.item_hot_search

    }

    override fun bindViewHolder(viewDataBinding: ViewDataBinding, position: Int) {
        when (viewDataBinding) {
            is ItemHotSearchBinding -> {
                viewDataBinding.item = getItem(position)
            }
        }
    }

}