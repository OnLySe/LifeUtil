package com.zzq.common.base

import androidx.recyclerview.widget.RecyclerView
import com.zzq.common.interfaces.ClickProxy
import com.zzq.common.interfaces.OnItemClickListener

/**
 * T指显示在列表中的实体类
 * K是ViewHolder类型，可直接使用BaseViewHolder
 */
abstract class BaseAdapter<T , K : BaseViewHolder> : RecyclerView.Adapter<K>() {
    var mOnItemClickListener: OnItemClickListener<T>? = null

    protected val TYPE_EMPTY = 89
    protected val TYPE_DATA = 95

    val dataList: ArrayList<T> = ArrayList<T>()

    /**
     * 获取数据源
     */
    fun getDataSource():ArrayList<T> {
        return dataList
    }

    /**
     * 更新数据源
     */
    fun updateDataSource(newData: ArrayList<T>) {
        dataList.clear()
        dataList.addAll(newData)
        notifyDataSetChanged()
    }

    fun clearData() {
        dataList.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return if (dataList.size == 0) {
            1
        } else {
            dataList.size
        }
    }

    override fun getItemViewType(position: Int): Int {

        return if (dataList.size == 0) {
            TYPE_EMPTY
        } else {
            TYPE_DATA
        }
    }

    override fun onBindViewHolder(holder: K, position: Int) {
        if (dataList.size == 0) {
            return
        }
        val adapterPosition: Int = holder.adapterPosition
        convert(adapterPosition, dataList[adapterPosition], holder)
        holder.itemView.setOnClickListener(ClickProxy {
            if (adapterPosition >= dataList.size) {
                notifyDataSetChanged()
            } else {
                mOnItemClickListener?.onItemClick(holder.itemView, adapterPosition, dataList[adapterPosition])
            }
        })

    }

    /**
     * 移除设备
     */
    fun removeItem(position: Int) {
        if (position < 0 && dataList.size < 1) {
            return
        }
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    abstract fun convert(position: Int, itemData: T, holder: K)
}