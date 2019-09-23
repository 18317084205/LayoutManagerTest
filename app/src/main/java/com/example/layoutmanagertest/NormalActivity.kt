package com.example.layoutmanagertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.layoutmanagertest.adapter.NormalAdapter
import com.example.layoutmanagertest.databinding.ActivityNormalBinding
import com.example.layoutmanagertest.viewmodel.TestViewMode
import kotlinx.android.synthetic.main.activity_normal.*

class NormalActivity : AppCompatActivity() {

    private val viewMode: TestViewMode by lazy {
        ViewModelProviders.of(this).get(TestViewMode::class.java)
    }

    private val adapter: NormalAdapter by lazy {
        NormalAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityNormalBinding>(this, R.layout.activity_normal)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewMode.dataList.observe(this, Observer {
            adapter.addAll(it)
        })

        viewMode.getData()
    }
}
