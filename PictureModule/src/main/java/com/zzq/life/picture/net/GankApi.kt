package com.zzq.life.picture.net

import com.zzq.life.picture.net.response.GankPictureData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GankApi {

    @GET("data/category/Girl/type/Girl/page/{pageIndex}/count/{pageAccount}")
    fun getGankPicture(
        @Path("pageIndex") index: Int,
        @Path("pageAccount") account: Int
    ): Call<GankPictureData>
}