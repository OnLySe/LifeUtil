package com.zzq.base.interfaces;

import androidx.annotation.Nullable;

public abstract class OnSelectCallback {

    public abstract void onPositive(@Nullable String msg);

    public void onNegative(){}
}
