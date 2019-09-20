package com.liang.layoutmanager

import androidx.recyclerview.widget.RecyclerView

abstract class IAdapter<VH : RecyclerView.ViewHolder>(protected val layoutManager: ILayoutManager) :
    RecyclerView.Adapter<VH>()