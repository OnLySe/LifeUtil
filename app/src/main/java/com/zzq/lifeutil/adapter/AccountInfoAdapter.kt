package com.zzq.lifeutil.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zzq.lifeutil.data.AccountInfo
import com.zzq.lifeutil.databinding.ItemAccountInfoBinding

class AccountInfoAdapter :
    ListAdapter<AccountInfo, AccountInfoAdapter.AccountInfoHolder>(AccountInfoDiffCallback()) {

    class AccountInfoHolder(private val binding: ItemAccountInfoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: AccountInfo) {
            binding.ivItemAccount.text = data.toString()
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