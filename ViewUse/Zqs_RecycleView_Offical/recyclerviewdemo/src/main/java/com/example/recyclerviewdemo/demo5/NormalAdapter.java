package com.example.recyclerviewdemo.demo5;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by zqs on 2018/3/8.
 */

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH>{
    private List<String> mData;
    public NormalAdapter(List<String> data) {
        this.mData = data;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_5,parent,false);
        return  new VH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.title.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class VH extends RecyclerView.ViewHolder{
        public final TextView title;

        public VH(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.text);
        }
    }
}
