//package com.zzq.net
//
//import androidx.paging.PagingSource
//
//open class NetDataSource<Key , Value>: PagingSource<Key, Value>() {
//    /**
//     * Loading API for [PagingSource].
//     *
//     * Implement this method to trigger your async load (e.g. from database or network).
//     */
//    override suspend fun load(params: LoadParams<Key>): LoadResult<Key, Value> {
//
//    }
//
//
//}