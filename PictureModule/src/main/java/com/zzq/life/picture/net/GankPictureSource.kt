//package com.zzq.life.picture.net
//
//import androidx.paging.PagingSource
//import com.zzq.life.picture.net.response.GankPictureData
//import com.zzq.life.picture.vm.PictureRepo
//
//class GankPictureSource(private val repo: PictureRepo) :
//    PagingSource<Int, GankPictureData.DataBean>() {
//    /**
//     * Loading API for [PagingSource].
//     *
//     * Implement this method to trigger your async load (e.g. from database or network).
//     */
//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GankPictureData.DataBean> {
//        val pageIndex = params.key ?: 1
//        return repo.getGankPicture(pageIndex, 10)
//    }
//
//}