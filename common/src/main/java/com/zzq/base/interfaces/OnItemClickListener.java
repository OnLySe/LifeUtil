package com.zzq.base.interfaces;

import android.view.View;

public interface OnItemClickListener<T> {
    void onItemClick(View view, int position, T data);
}
