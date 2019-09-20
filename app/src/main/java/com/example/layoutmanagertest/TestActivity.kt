package com.example.layoutmanagertest

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.layoutmanagertest.bean.TestBean
import com.liang.layoutmanager.LayoutHelper
import com.liang.layoutmanager.layout.LinearLayoutHelper
import com.liang.layoutmanager.test.TestLayoutManager
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    val helpers = arrayListOf<LayoutHelper<TestBean>>()

    private val layoutManager by lazy {
        TestLayoutManager(this)
    }
    private val testAdapter by lazy {
        TestAdapter(layoutManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = testAdapter

        recyclerView.addItemDecoration(object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.set(10, 4, 10, 4)
            }
        })

        initData()
    }

    private fun initData() {
        val testData = ArrayList<TestBean>()
        for (i in 0..100) {
            testData.add(TestBean(i, "Test:$i"))
        }
//        testAdapter.addAll(testData)
        helpers.add(LinearLayoutHelper(testData))
        testAdapter.setLayoutHelpers(helpers)

    }
}
