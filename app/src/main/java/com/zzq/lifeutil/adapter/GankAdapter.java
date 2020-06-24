package com.zzq.lifeutil.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.zzq.lifeutil.R;
import com.zzq.lifeutil.net.response.FuliDataBean;
import com.zzq.lifeutil.widget.RatioImageView;

public class GankAdapter extends PagedListAdapter<FuliDataBean.ResultsBean, GankAdapter.GankViewHolder> {

    private Context mContext;

    public GankAdapter(Context context) {
        super(diffCallback);
        mContext = context;
    }

    static DiffUtil.ItemCallback<FuliDataBean.ResultsBean> diffCallback = new DiffUtil.ItemCallback<FuliDataBean.ResultsBean>() {
        @Override
        public boolean areItemsTheSame(FuliDataBean.ResultsBean oldItem, FuliDataBean.ResultsBean newItem) {
            // User properties may have changed if reloaded from the DB, but ID is fixed
            return oldItem.get_id().equals(newItem.get_id());
        }

        @Override
        public boolean areContentsTheSame(FuliDataBean.ResultsBean oldItem, FuliDataBean.ResultsBean newItem) {
            // NOTE: if you use equals, your object must properly override Object#equals()
            // Incorrectly returning false here will result in too many animations.
            return oldItem.equals(newItem);
        }
    };

    @Override
    public GankViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GankViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_gank, parent, false));
    }

    @Override
    public void onBindViewHolder(final GankViewHolder holder, int position) {
        holder.setDataAndListener(getItem(position));
    }

    public static class GankViewHolder extends RecyclerView.ViewHolder {
        RatioImageView mImageView;
        View card;

        GankViewHolder(View view) {
            super(view);
            card = view;
            mImageView = view.findViewById(R.id.iv_item_gank);
            mImageView.setOriginalSize(50, 50);
        }

        public void setDataAndListener(final FuliDataBean.ResultsBean resultsBean) {
            Glide.with(itemView.getContext())
                    .load(resultsBean.getUrl())
                    .centerCrop()
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(mImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
