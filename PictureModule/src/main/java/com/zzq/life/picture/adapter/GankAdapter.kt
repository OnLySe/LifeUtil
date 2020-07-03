package com.zzq.life.picture.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.zzq.life.picture.R
import com.zzq.life.picture.adapter.GankAdapter.GankViewHolder
import com.zzq.life.picture.net.response.GankPictureData.DataBean

class GankAdapter(private val mContext: Context) :
    PagingDataAdapter<DataBean, GankViewHolder>(diffCallback) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GankViewHolder {
        return GankViewHolder(
            LayoutInflater.from(mContext)
                .inflate(R.layout.item_show_picture, parent, false)
        )
    }

    override fun onBindViewHolder(holder: GankViewHolder, position: Int) {
        holder.setDataAndListener(getItem(position))
    }

    class GankViewHolder internal constructor(view: View) :
        RecyclerView.ViewHolder(view) {
        var mImageView: ImageView
        fun setDataAndListener(resultsBean: DataBean?) {
            Glide.with(itemView.context)
                .load(resultsBean!!.url) //                    .asBitmap()
                .centerCrop()
                .thumbnail(0.1f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mImageView)
        }

        init {
            mImageView =
                view.findViewById(R.id.iv_item_picture)
        }
    }

    companion object {
        var diffCallback: DiffUtil.ItemCallback<DataBean> =
            object : DiffUtil.ItemCallback<DataBean>() {
                override fun areItemsTheSame(
                    oldItem: DataBean,
                    newItem: DataBean
                ): Boolean {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return oldItem._id == newItem._id
                }

                override fun areContentsTheSame(
                    oldItem: DataBean,
                    newItem: DataBean
                ): Boolean {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldItem == newItem
                }
            }
    }

}