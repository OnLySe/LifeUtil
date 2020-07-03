package com.zzq.life.picture.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zzq.common.base.BaseActivity
import com.zzq.library.arounter.RouterConstant
import com.zzq.life.picture.R

@Route(path = RouterConstant.Picture_Activity_Gank)
class GankPictureActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank_picture)
    }
}
