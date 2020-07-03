package com.zzq.life.picture.view.fragments

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zzq.common.base.BaseFragment
import com.zzq.life.picture.R
import com.zzq.life.picture.adapter.GankAdapter
import com.zzq.life.picture.net.response.GankPictureData
import com.zzq.life.picture.vm.PictureViewModel

class GankPictureFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var srlGank: SwipeRefreshLayout
    private lateinit var rvGank: RecyclerView
    private lateinit var adapter: GankAdapter
    private lateinit var viewModel: PictureViewModel

    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_picture
    }

    override fun initView(view: View) {

        viewModel = initViewModel(PictureViewModel::class.java)
        srlGank = view.findViewById(R.id.srl_gank_list)
        rvGank = view.findViewById(R.id.rv_gank_list)

        srlGank.setOnRefreshListener(this)
        adapter = GankAdapter(requireContext())
        rvGank.adapter = adapter

        initData()

        initLiveData()
    }

    private fun initLiveData() {
        viewModel.getGankPictureLiveData().observe(this, Observer<GankPictureData> { t ->
            Log.e("tetetetete", "size: ${t.data.size}")
        })
    }

    private fun initData() {
        viewModel.getGankPicture(1, 10)
    }

    override fun onRefresh() {
        viewModel.getGankPicture(1, 10)
    }
}