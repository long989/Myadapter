package com.qkl.testlifecycle.hiadapter

import android.content.Context
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.ParameterizedType

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

    fun removeItem(item: HiDataItem<*, *>?) {
        if (item != null) {
            val index = mDataSets.indexOf(item)
            removeItem(index)
        }
    }

    override fun getItemViewType(position: Int): Int {
        val hiDataItem = mDataSets[position]
        val type = hiDataItem.javaClass.hashCode()
        if (mTypeArrays.indexOfKey(type) < 0) {
            mTypeArrays.put(type, hiDataItem)
        }
        return type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dataItem = mTypeArrays[viewType]
        var itemView = dataItem.getItemView(parent)
        if (itemView == null) {
            val layoutRes = dataItem.getLayoutRes()
            if (layoutRes < 0) {
                RuntimeException("dataItem:" + dataItem.javaClass.name + "must override getItemView or getLayoutRes")
            }
            itemView = mInflater!!.inflate(layoutRes, parent)
        }
        return createViewHolderInteral(dataItem.javaClass, itemView)
    }

    private fun createViewHolderInteral(
        javaClass: Class<HiDataItem<*, RecyclerView.ViewHolder>>,
        itemView: View?
    ): RecyclerView.ViewHolder {
        //获取该类的泛型
        val genericSuperclass = javaClass.genericSuperclass
        //如果是参数化类型
        if (genericSuperclass is ParameterizedType) {
            //获取泛型，这是一个集合
            val actualTypeArguments = genericSuperclass.actualTypeArguments
            //遍历该集合
            for (argument in actualTypeArguments) {
                //isAssignableFrom判断是否是某个类的父类
                if (argument is Class<*> && RecyclerView.ViewHolder::class.java.isAssignableFrom(
                        argument
                    )
                ) {
                    return argument.getConstructor(View::class.java)
                        .newInstance(itemView) as RecyclerView.ViewHolder
                }
            }
        }
        //返回默认一个ViewHolder对象
        return object : RecyclerView.ViewHolder(itemView!!) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val dataItem = mDataSets[position]
        dataItem.bindData(holder, position)
    }

    //设置recyclerView每列的个数
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is GridLayoutManager) {
            val spanCount = layoutManager.spanCount
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    if (position < mDataSets.size) {
                        val hiDataItem = mDataSets[position]
                        val spanSize = hiDataItem.getSpanSize()
                        return if (spanSize <= 0) spanCount else spanSize
                    }
                    return spanCount
                }

            }
        }
    }

    override fun getItemCount(): Int {
        return mDataSets.size
    }

    fun refreshItem(hiDataItem: HiDataItem<*, *>) {
        val index = mDataSets.indexOf(hiDataItem)
        notifyItemChanged(index)
    }
}