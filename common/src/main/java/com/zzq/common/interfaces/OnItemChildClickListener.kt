package com.zzq.common.interfaces

import android.view.View

interface OnItemChildClickListener<T> {
    fun onItemChildClick(t: T, view: View?, position: Int)
}