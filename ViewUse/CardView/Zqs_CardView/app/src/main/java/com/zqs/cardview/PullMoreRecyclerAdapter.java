package com.zqs.cardview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zqs on 2018/3/14.
 */

public class PullMoreRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private static final int TYPE_NORMAL_ITEM = 0;  //普通Item
    private static final int TYPE_FOOTER_ITEM = 1;  //底部FooterView

    //上拉加载更多
    public static final int PULLUP_LOAD_MORE = 1;
    //正在加载中
    public static final int LOADING_MORE = 2;
    //默认为0
    private int load_more_status = 1;

    public List<CardInfo> list;
    private OnItemClickListener mClickListener;

    public PullMoreRecyclerAdapter(List<CardInfo> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_NORMAL_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_recyclerview_item
                    ,parent, false);
            final NormalItemViewHolder vh = new NormalItemViewHolder(view);
            if (mClickListener != null) {
                vh.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onItemClick(vh.itemView, vh.getLayoutPosition());
                    }
                });
            }
            return vh;
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_footer_view,
                    parent, false);
            FooterViewHolder vh = new FooterViewHolder(view);
            return vh;
        }


    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof NormalItemViewHolder) {
            ((NormalItemViewHolder) viewHolder).titleTv.setText(list.get(position).getTitle());
            ((NormalItemViewHolder) viewHolder).contentTv.setText(list.get(position).getContent());
        } else if (viewHolder instanceof FooterViewHolder) {
            FooterViewHolder footViewHolder = (FooterViewHolder) viewHolder;
            switch (load_more_status) {
                case PULLUP_LOAD_MORE:
                    footViewHolder.foot_view_item_tv.setVisibility(View.VISIBLE);
                    footViewHolder.foot_view_item_tv.setText("上拉加载更多");
                    footViewHolder.pb.setVisibility(View.GONE);
                    break;
                case LOADING_MORE:
                    footViewHolder.foot_view_item_tv.setVisibility(View.GONE);
                    footViewHolder.pb.setVisibility(View.VISIBLE);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        //+1是加入底部的加载布局项
        return list.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1 == getItemCount()) {
            return TYPE_FOOTER_ITEM;
        } else {
            return TYPE_NORMAL_ITEM;
        }
    }


    public void setMoreStatus(int status){
        load_more_status=status;
        notifyDataSetChanged();
    }

    public static class NormalItemViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTv,contentTv;
        public ImageView iv;

        public NormalItemViewHolder(View itemView) {
            super(itemView);
            titleTv =itemView.findViewById(R.id.item_content_tv);
            contentTv = itemView.findViewById(R.id.item_content_tv);
            iv = itemView.findViewById(R.id.item_iv);
        }
    }


    /**
     * 底部FooterView布局
     */
    public static class FooterViewHolder extends RecyclerView.ViewHolder {
        public TextView foot_view_item_tv;
        public ProgressBar pb;

        public FooterViewHolder(View view) {
            super(view);
            pb = (ProgressBar) view.findViewById(R.id.progress_view);
            foot_view_item_tv = (TextView) view.findViewById(R.id.tv_content);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View itemView, int pos);
    }
}
