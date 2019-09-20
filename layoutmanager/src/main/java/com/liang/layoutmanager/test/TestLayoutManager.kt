package com.liang.layoutmanager.test

import android.content.Context
import com.liang.layoutmanager.ILayoutManager

class TestLayoutManager : ILayoutManager {

    constructor(context: Context) : this(context, VERTICAL, false)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

}