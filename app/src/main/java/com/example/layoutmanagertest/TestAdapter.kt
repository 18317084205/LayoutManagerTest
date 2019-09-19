package com.example.layoutmanagertest

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.layoutmanagertest.bean.TestBean
import com.example.layoutmanagertest.databinding.TestItemBinding
import com.liang.layoutmanager.utils.findDataBinding
import com.liang.layoutmanager.utils.inflate

class TestAdapter : RecyclerView.Adapter<TestAdapter.TestHolder>() {
    private val testData = ArrayList<TestBean>()

    fun addData(data: List<TestBean>) {
        testData.addAll(data)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestHolder {
        return TestHolder(inflate(parent.context, R.layout.test_item, parent, false).root)
    }

    override fun getItemCount(): Int {
        return testData.size
    }

    override fun onBindViewHolder(holder: TestHolder, position: Int) {
        (findDataBinding(holder.itemView) as TestItemBinding).item = testData[position]
    }

    class TestHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}