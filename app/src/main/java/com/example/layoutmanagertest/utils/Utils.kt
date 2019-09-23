/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: Utils
 * Author: Owner
 * Date: 2019/9/23 10:38
 * Description:
 * History:
 * <author> <time> <version> <desc>
 * 作者姓名 修改时间 版本号 描述
 */
package com.example.layoutmanagertest.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.layoutmanagertest.R
import com.example.layoutmanagertest.bean.ItemBean
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.view.ArrayItemView
import com.example.layoutmanagertest.view.ArrayItemView1_6
import com.example.layoutmanagertest.view.HotSearchView
import java.io.InputStream
import java.lang.Exception
import java.util.*
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.JsonArray
import com.google.gson.Gson


/**
 * @ClassName: Utils
 * @Description: java类作用描述
 * @Author: Owner
 * @Date: 2019/9/23 10:38
 */
fun getDataFromAssets(context: Context, filePath: String): String {
    var s = ""
    var inputStream: InputStream? = null
    try {
        inputStream = context.assets.open(filePath)

        val scanner = Scanner(inputStream, "UTF-8").useDelimiter("\\A")
        if (scanner.hasNext()) {
            s = scanner.next()
        }

    } catch (e: Exception) {
        e.printStackTrace()
    } finally {
        inputStream?.close()
    }

    return s

}

fun <T> getDataBean(json: String, cls: Class<T>): T {
    return Gson().fromJson(json, cls)
}

fun <T> getDataBeanArray(json: String, cls: Class<T>): List<T> {
    val list = ArrayList<T>()
    try {
        val gson = Gson()
        JsonParser().parse(json).asJsonArray.forEach {
            list.add(gson.fromJson(it, cls))
        }

    } catch (e: Exception) {
        e.printStackTrace()
    }

    return list
}

@BindingAdapter("app:imageUrl")
fun loadImage(view: View, url: String) {
    if (view is ImageView) Glide.with(view).load(url).apply(RequestOptions().placeholder(R.mipmap.ic_launcher)).into(
        view
    )
}

@BindingAdapter("setViewData")
fun setViewData(view: View, data: ItemBean?) {
    if (view is ArrayItemView<*>) view.setViewData(data)
    if (view is ArrayItemView1_6) view.setViewData(data)
}