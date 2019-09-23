/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: ViewPagerAdapter
 * Author: Owner
 * Date: 2019/9/23 15:43
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.layoutmanagertest.adapter;

import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.example.layoutmanagertest.R
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.ItemLayoutDefBinding
import com.liang.layoutmanager.utils.inflate

/**
 * @ClassName: ViewPagerAdapter
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 15:43
 */
class ViewPagerAdapter : PagerAdapter() {

    private val testBeans = arrayListOf<TestBean>()

    fun setData(data: Collection<TestBean>) {
        testBeans.addAll(data)
        notifyDataSetChanged()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val dataBinding = inflate(
            container.context,
            R.layout.item_layout_def,
            container,
            false
        ) as ItemLayoutDefBinding
        dataBinding.root.setPadding(10, 10, 10, 10)
        dataBinding.item = testBeans[position]
        container.addView(dataBinding.root)
        return dataBinding.root
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return testBeans.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }
}