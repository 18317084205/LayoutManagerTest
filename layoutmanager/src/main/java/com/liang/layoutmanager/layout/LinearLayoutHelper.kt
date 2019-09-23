/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: LinearLayoutHelper
 * Author: Owner
 * Date: 2019/9/20 15:35
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.liang.layoutmanager.layout

import androidx.recyclerview.widget.RecyclerView
import com.liang.layoutmanager.LayoutHelper

/**
 * @ClassName: LinearLayoutHelper
 * @Description: 类似LinearLayoutManager
 * @Author: Owner
 * @Date: 2019/9/20 15:35
 */
class LinearLayoutHelper<Data> : LayoutHelper<Data> {

    constructor(data: Collection<Data>){
        setItems(data)
    }

    override fun onItemLayout(recycler: RecyclerView.Recycler, state: RecyclerView.State, dy: Int): Int {
        return 0
    }


}