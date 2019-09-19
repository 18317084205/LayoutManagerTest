package com.example.layoutmanagertest

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.layoutmanagertest.bean.TestBean
import com.liang.layoutmanager.test.TestLayoutManager
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    private val testAdapter: TestAdapter by lazy {
        TestAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        recyclerView.layoutManager = TestLayoutManager(this)
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
        testAdapter.addData(testData)
    }
}
