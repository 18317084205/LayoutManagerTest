/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: TestViewMode
 * Author: Owner
 * Date: 2019/9/23 10:41
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.layoutmanagertest.viewmodel;

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.layoutmanagertest.bean.ItemBean
import com.example.layoutmanagertest.utils.getDataBeanArray
import com.example.layoutmanagertest.utils.getDataFromAssets
import com.google.gson.Gson

/**
 * @ClassName: TestViewMode
 * @Description: 类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 10:41
 */
class TestViewMode(application: Application) : AndroidViewModel(application) {

    val dataList = MutableLiveData<List<ItemBean>>()

    fun getData() {
        Thread {
            val dataFromAssets = getDataFromAssets(getApplication(), "test_data.json")
            Log.d("TestViewMode", dataFromAssets)
            val dataBeanArray = getDataBeanArray(dataFromAssets, ItemBean::class.java)
            Log.d("TestViewMode", "dataBeanArray: ${dataBeanArray.size}")
            dataList.postValue(dataBeanArray)
        }.start()

    }
}