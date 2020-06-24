package com.zzq.lifeutil.fragments

import android.os.Handler
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zzq.common.base.BaseFragment
import com.zzq.lifeutil.R
import com.zzq.lifeutil.adapter.AccountInfoAdapter
import com.zzq.lifeutil.adapter.GankAdapter

class GankPictureFragment : BaseFragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var srlPicture: SwipeRefreshLayout
    private lateinit var rvPicture: RecyclerView
    private lateinit var adapter: GankAdapter
    private val handler = Handler()
    override fun getLayoutId(): Int {
        return R.layout.fragment_gank_picture
    }

    override fun initView(view: View) {
        srlPicture = view.findViewById(R.id.srl_gank_pic_list)
        rvPicture = view.findViewById(R.id.rv_gank_pic_list)

        srlPicture.setOnRefreshListener(this)
        rvPicture.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        adapter = GankAdapter(requireContext())
        rvPicture.adapter = adapter
    }

    override fun onRefresh() {

    }
}