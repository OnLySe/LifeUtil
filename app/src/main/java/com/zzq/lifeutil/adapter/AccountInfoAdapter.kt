package com.zzq.lifeutil.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.launcher.ARouter
import com.zzq.library.arounter.RouterConstant
import com.zzq.lifeutil.R
import com.zzq.lifeutil.data.AccountInfo
import com.zzq.lifeutil.databinding.ItemAccountInfoBinding

class AccountInfoAdapter :
    ListAdapter<AccountInfo, AccountInfoAdapter.AccountInfoHolder>(AccountInfoDiffCallback()) {

    class AccountInfoHolder(private val binding: ItemAccountInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setOnClickListener { view ->
                binding.accountInfo?.let { navigateToDetail(it, view, adapterPosition) }
            }
        }

        fun bindData(data: AccountInfo) {
            binding.accountInfo = data
            binding.executePendingBindings()
        }

        private fun navigateToDetail(data: AccountInfo, view: View, position: Int) {
            if (position % 2 == 0) {
                ARouter.getInstance().build(RouterConstant.Picture_Activity_Main).navigation()
            } else {
                ARouter.getInstance().build(RouterConstant.News_Activity_Main).navigation()
            }
//            view.findNavController().navigate(R.id.action_mainFragment_to_appInfoFragment)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountInfoHolder {
        return AccountInfoHolder(
            ItemAccountInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AccountInfoHolder, position: Int) {
        holder.bindData(getItem(position))
    }
}

private class AccountInfoDiffCallback : DiffUtil.ItemCallback<AccountInfo>() {
    override fun areItemsTheSame(oldItem: AccountInfo, newItem: AccountInfo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AccountInfo, newItem: AccountInfo): Boolean {
        return oldItem.toString() == newItem.toString()
    }

}