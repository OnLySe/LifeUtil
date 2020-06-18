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
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.zzq.base.base.BaseFragment
import com.zzq.base.util.DensityUtil
import com.zzq.base.widget.RecycleViewDivider
import com.zzq.lifeutil.R
import com.zzq.lifeutil.adapter.AccountInfoAdapter
import com.zzq.lifeutil.data.AccountInfo
import com.zzq.lifeutil.utils.InjectorUtil
import com.zzq.lifeutil.viewmodels.LifeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : BaseFragment() {

    private val viewModel: LifeViewModel by viewModels {
        InjectorUtil.provideLifeViewModelFactory(this)
    }
    private lateinit var srlMain: SwipeRefreshLayout
    private lateinit var rvMain: RecyclerView
    private val adapter = AccountInfoAdapter()
    private val handler = Handler()
    private var index = 0

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun initView(view: View) {
        srlMain = view.findViewById(R.id.srl_main)
        rvMain = view.findViewById(R.id.rv_main)
        val tvTitle: TextView = view.findViewById(R.id.tv_title)
        tvTitle.text = "Main"
        val fabMain = view.findViewById<FloatingActionButton>(R.id.fab_main)
        fabMain.setOnClickListener {
            index++
            val accountInfo = AccountInfo(
                index.toString(), System.currentTimeMillis(),
                "123456", "Life$index", "QQ", "尝试下"
            )
            GlobalScope.launch { viewModel.addAccountInfo(accountInfo) }
        }

        fabMain.setOnLongClickListener {


            return@setOnLongClickListener true
        }


        srlMain.setOnRefreshListener {

        }

        rvMain.addItemDecoration(
            RecycleViewDivider(
                requireContext(),
                LinearLayoutManager.VERTICAL, DensityUtil.dip2px(requireContext(), 1F),
                ContextCompat.getColor(requireContext(), R.color.colorLine)
            )
        )
        rvMain.layoutManager = LinearLayoutManager(requireContext())
        val defaultItemAnimator = DefaultItemAnimator()
        defaultItemAnimator.addDuration = 300
        defaultItemAnimator.removeDuration = 300
        rvMain.itemAnimator = defaultItemAnimator
        rvMain.adapter = adapter
        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.accounts.observe(this, Observer {
            index = it.size
            adapter.submitList(it)
            rvMain.smoothScrollToPosition(index)
        })
    }
}