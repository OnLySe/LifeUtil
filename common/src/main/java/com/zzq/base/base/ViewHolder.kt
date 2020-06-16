package com.zzq.base.base

import android.util.SparseArray
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private lateinit var viewIdArray: SparseArray<View>

    init {
        viewIdArray = SparseArray<View>()

    }

    protected fun <T : View> getView(@IdRes viewId: Int): T {
        val view = itemView.findViewById<T>(viewId)
        viewIdArray.put(viewId, view)
        return view
    }

    open fun <T> bindData(itemData: T) {

    }

}

class EmptyScanListHolder(itemView: View) : BaseViewHolder(itemView)