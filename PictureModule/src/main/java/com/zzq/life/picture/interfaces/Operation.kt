package com.zzq.life.picture.interfaces

import androidx.lifecycle.MutableLiveData
import com.zzq.life.picture.net.response.GankPictureData

object Operation {

    interface ViewModel {
        fun getGankPicture(pageIndex: Int, pageSize: Int)
    }

    interface Repo {
        fun getGankPicture(
            pageIndex: Int,
            pageSize: Int,
            liveData: MutableLiveData<GankPictureData>
        )
    }
}