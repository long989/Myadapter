package com.qkl.testlifecycle.hiadapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @title:
 * @projectName NewDriverSchool
 * @description:
 * @author qiukailong
 * @date 2021/10/22
 */
class HiAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var mContext: Context
    private var mInflater: LayoutInflater? = null
    private var mDataSets = ArrayList<HiDataItem<*, RecyclerView.ViewHolder>>()
    private var mTypeArrays = SparseArray<HiDataItem<*, RecyclerView.ViewHolder>>()

    init {
        this.mContext = context
        this.mInflater = LayoutInflater.from(context)
    }

    //添加元素
    fun addItem(index: Int, item: HiDataItem<*, RecyclerView.ViewHolder>, notify: Boolean) {
        if (index > 0) {
            mDataSets.add(index, item)
        } else {
            mDataSets.add(item)
        }
        val notifyPos = if (index > 0) index else mDataSets.size - 1
        if (notify) {
            notifyItemChanged(notifyPos)
        }
    }

    //添加元素的集合
    fun addItems(list: ArrayList<HiDataItem<*, RecyclerView.ViewHolder>>, notify: Boolean) {
        val start = mDataSets.size
        for (item in list) {
            mDataSets.add(item)
        }
        if (notify) {
            notifyItemRangeInserted(start, list.size)
        }
    }

    //移除元素
    fun removeItem(index: Int): HiDataItem<*, RecyclerView.ViewHolder>? {
        if (index > 0 && index < mDataSets.size) {
            val removeItem = mDataSets.removeAt(index)
            notifyItemRemoved(index)
            return removeItem
        }
        return null
    }

    fun removeItem(item: HiDataItem<*, RecyclerView.ViewHolder>?) {
        if (item != null) {
            val index = mDataSets.indexOf(item)
            removeItem(index)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return mDataSets.size
    }
}