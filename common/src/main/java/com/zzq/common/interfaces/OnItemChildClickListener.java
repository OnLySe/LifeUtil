package com.zzq.common.interfaces;

import android.view.View;

public interface OnItemChildClickListener<T> {
    void onItemChildClick(T t, View view, int position);
}
