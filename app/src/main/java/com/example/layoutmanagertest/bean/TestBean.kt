package com.example.layoutmanagertest.bean

import android.view.View
import android.widget.Toast

data class TestBean(val id: Int, val name: String, val url: String) {
    fun onClick(view: View) {
        Toast.makeText(view.context, "onClick: id=$id, name=$name", Toast.LENGTH_SHORT).show()
    }
}