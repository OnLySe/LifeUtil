package com.zzq.lifeutil.fragments

import android.view.View
import android.widget.TextView
import com.zzq.common.base.BaseFragment
import com.zzq.lifeutil.R
import com.zzq.lifeutil.utils.AppUtil

class AppInfoFragment : BaseFragment() {
    override fun getLayoutId(): Int {
        return R.layout.fragment_app_info
    }

    override fun initView(view: View) {
        view.findViewById<TextView>(R.id.tv_title).text = getString(R.string.title_app_info)
        setToolbarReturnFun()

        AppUtil.getVersionName(requireContext())
        view.findViewById<TextView>(R.id.tv_app_id).text = AppUtil.getVersionName(requireContext())
        view.findViewById<TextView>(R.id.tv_app_versionCode).text =
            AppUtil.getPackageName(requireContext())
    }

}