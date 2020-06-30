package com.zzq.life.picture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.zzq.library.arounter.RouterConstant

@Route(path = RouterConstant.Picture_Activity_Main)
class PictureMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picture_main)
    }
}
