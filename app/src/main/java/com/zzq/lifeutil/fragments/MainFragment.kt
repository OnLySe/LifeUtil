package com.zzq.lifeutil.fragments

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zzq.base.base.BaseFragment
import com.zzq.base.util.DensityUtil
import com.zzq.base.widget.RecycleViewDivider
import com.zzq.lifeutil.R

class MainFragment : BaseFragment() {
    private lateinit var srlMain :SwipeRefreshLayout
    private lateinit var rvMain:RecyclerView
    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(view: View) {
        srlMain = view.findViewById(R.id.srl_main)
        rvMain = view.findViewById(R.id.rv_main)

        srlMain.setOnRefreshListener {

        }

        rvMain.addItemDecoration(
            RecycleViewDivider(
                requireContext(),
                LinearLayoutManager.VERTICAL, DensityUtil.dip2px(requireContext(), 1F),
                ContextCompat.getColor(requireContext(), R.color.colorLine)
            )
        )
        val defaultItemAnimator = DefaultItemAnimator()
        defaultItemAnimator.addDuration = 1000
        defaultItemAnimator.removeDuration = 1000
        rvMain.itemAnimator = defaultItemAnimator

    }
}