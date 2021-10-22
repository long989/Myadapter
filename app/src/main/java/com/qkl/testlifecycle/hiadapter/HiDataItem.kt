package com.qkl.testlifecycle.hiadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @title:
 * @projectName NewDriverSchool
 * @description:
 * @author qiukailong
 * @date 2021/10/22
 */
abstract class HiDataItem<DATA, VH : RecyclerView.ViewHolder>(data: DATA) {
    var mData: DATA? = null
    private var mAdapter: HiAdapter? = null

    init {
        this.mData = data
    }

    abstract fun bindData(holder: RecyclerView.ViewHolder, position: Int)

    open fun getLayoutRes(): Int {
        return -1
    }

    open fun getItemView(view: ViewGroup): View? {
        return null
    }

    fun setAdapter(adapter: HiAdapter) {
        this.mAdapter = adapter
    }

    fun refreshItem() {
        mAdapter!!.refreshItem(this)
    }

    fun removeItem() {
        mAdapter!!.removeItem(this)
    }

    fun getSpanSize(): Int {
        return 0
    }

}