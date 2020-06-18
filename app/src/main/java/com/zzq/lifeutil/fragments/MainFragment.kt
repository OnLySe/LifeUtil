package com.zzq.lifeutil.fragments

import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.zzq.base.base.BaseFragment
import com.zzq.base.util.DensityUtil
import com.zzq.base.widget.RecycleViewDivider
import com.zzq.lifeutil.R
import com.zzq.lifeutil.adapter.AccountInfoAdapter
import com.zzq.lifeutil.databinding.FragmentMainBinding
import com.zzq.lifeutil.utils.InjectorUtil
import com.zzq.lifeutil.viewmodels.LifeViewModel
import kotlinx.android.synthetic.main.include_toolbar.*

class MainFragment : BaseFragment() {

    private val viewModel: LifeViewModel by viewModels {
        InjectorUtil.provideLifeViewModelFactory(this)
    }
    private lateinit var srlMain: SwipeRefreshLayout
    private lateinit var rvMain: RecyclerView
    private val adapter = AccountInfoAdapter()
    private val handler = Handler()

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(view: View) {
        srlMain = view.findViewById(R.id.srl_main)
        rvMain = view.findViewById(R.id.rv_main)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        tvTitle.text = "Main"


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
        rvMain.adapter = adapter
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.accounts.observe(this, Observer {
            adapter.submitList(it)
        })
    }
}