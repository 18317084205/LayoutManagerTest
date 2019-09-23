package com.example.layoutmanagertest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun jumpToNormalTest(view: View) {
        startActivity(Intent(this, NormalActivity::class.java))
    }

    fun jumpToTest(view: View) {
        startActivity(Intent(this, TestActivity::class.java))
    }
}
