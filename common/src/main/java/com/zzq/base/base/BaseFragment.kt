package com.zzq.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gyf.immersionbar.ImmersionBar
import com.gyf.immersionbar.components.SimpleImmersionFragment
import com.zzq.base.R

/**
 * androidx
 * ViewModel
 * ImmersionBar
 * Navigation
 */
abstract class BaseFragment : SimpleImmersionFragment() {

    protected abstract fun getLayoutId(): Int
    protected var defaultToolbar: Toolbar? = null

    protected fun <T : ViewModel> initViewModel(modelClass: Class<T>): T {
        return ViewModelProvider(requireActivity()).get(modelClass)
    }

    protected open fun initView(view: View) {}

    @IdRes
    @Nullable
    protected open fun setToolbarId(): Int? = R.id.toolbar

    /**
     * {@link HasDefaultViewModelProviderFactory#getDefaultViewModelProviderFactory() default factory}
     *  不应使onCreateView变成可重写，需要用final做限制，如有必要，可使用apply！
     *
     */
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutId(), container, false).apply {
            val toolbarId = setToolbarId()
            toolbarId?.let {
                defaultToolbar  = findViewById(it)
                setToolbar(defaultToolbar!!)

            }
            initView(this)
        }
    }

    /**
     * 默认使用这种方式来设置Toolbar，即返回上一层，支持设置Toolbar左边icon，不设置可不传
     */
    protected open fun setToolbarReturnFun(@DrawableRes iconId: Int? = null) {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
            if (iconId != null) {
                it.setHomeAsUpIndicator(iconId)
            }
            defaultToolbar?.setNavigationOnClickListener { navigateUp() }
        }
    }

    protected open fun setToolbarReturnFun(function: () -> Unit, @DrawableRes iconId: Int? = null) {
        (activity as AppCompatActivity).supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(false)
            if (iconId != null) {
                it.setHomeAsUpIndicator(iconId)
            }
            defaultToolbar?.setNavigationOnClickListener { function() }
        }
    }

    private fun setToolbar(toolbar: Toolbar) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        ImmersionBar.setTitleBar(this, toolbar)
    }

    protected fun navigate(@IdRes resId: Int, args: Bundle? = null) {
        findNavController().navigate(resId, args)
    }

    @CallSuper
    protected open fun navigateUp() {
        findNavController().navigateUp()
    }

    override fun initImmersionBar() {
        val immersionBar = ImmersionBar.with(this)
        if (ImmersionBar.isSupportStatusBarDarkFont()) {
            immersionBar.statusBarDarkFont(true)
        }
        immersionBar.init();
    }
}