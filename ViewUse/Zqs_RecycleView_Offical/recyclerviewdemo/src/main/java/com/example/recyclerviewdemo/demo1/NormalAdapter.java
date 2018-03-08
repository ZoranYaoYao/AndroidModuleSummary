package com.example.recyclerviewdemo.demo1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by zqs on 2018/3/7.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {
    private List<ObjectModel> mDatas;

    public NormalAdapter(List<ObjectModel> data) {
        this.mDatas = data;

    }


    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1,parent,false);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ObjectModel model = mDatas.get(position);
        holder.number.setText(model.number + "");
        holder.title.setText(model.title);
        /**整个控件的点击*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //item 点击事件
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        public final TextView title;
        public final TextView number;
        public VH(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            number = (TextView) itemView.findViewById(R.id.number);
        }
    }
}
