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
import com.example.layoutmanagertest.databinding.*
import com.liang.layoutmanager.adapter.BaseAdapter

/**
 * @ClassName: NormalAdapter
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 11:04
 */
class NormalAdapter : BaseAdapter<ItemBean>() {

    override fun getItemLayoutId(viewType: Int): Int {
        return when (viewType) {
            0 -> R.layout.item_layout_search
            1 -> R.layout.item_layout_search_hot
            2 -> R.layout.item_layout_banner
            3 -> R.layout.item_layout_classify
            4 -> R.layout.item_layout_lager
            5 -> R.layout.item_layout_1plus6
            else -> R.layout.item_layout_empty
        }
    }

    override fun bindViewHolder(viewDataBinding: ViewDataBinding, position: Int) {
        when (viewDataBinding) {
            is ItemLayoutSearchBinding -> viewDataBinding.item = getItem(position)
            is ItemLayoutSearchHotBinding -> viewDataBinding.item = getItem(position)
            is ItemLayoutBannerBinding -> bindBannerLayout(viewDataBinding, getItem(position))
            is ItemLayoutClassifyBinding -> viewDataBinding.item = getItem(position)
            is ItemLayoutLagerBinding -> viewDataBinding.item = getItem(position)
            is ItemLayout1plus6Binding -> viewDataBinding.item = getItem(position)
        }
    }

    private fun bindBannerLayout(dataBinding: ItemLayoutBannerBinding, item: ItemBean) {
        val pagerAdapter = ViewPagerAdapter()
        dataBinding.viewPager.adapter = pagerAdapter
        pagerAdapter.setData(item.items)
    }


    override fun getItemViewType(position: Int): Int {
        return getItem(position).type
    }
}