package com.example.zoran.recycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.zip.Inflater;

/**
 * Created by zqs on 2018/3/6.
 */

public class CustomAdapter extends RecyclerView.Adapter {
    private String[] datas;
    CustomAdapter(String[] datas){
        this.datas = datas;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       /**
        *  E/AndroidRuntime( 3791): java.lang.IllegalStateException:
         ViewHolder views must not be attached when created.
         Ensure that you are not passing 'true' to the attachToRoot parameter of LayoutInflater.inflate(..., boolean attachToRoot)
        inflate(R.layout.text_row_item,parent,true);  改为false
        */
       Log.d("zqs","onCreateViewHolder ");
        /**Log日志可以看出 onCreateViewHolder 默认创建12个
         * (Depends 屏幕展示的个数) [eg] 显示9个, 创建12个,显示2个,创建4个*/
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_row_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Log.d("zqs","onBindViewHolder position = " +position);
        /**Log日志可以看出 onBindViewHolder滑进界面就会重新绑定*/
        ((ViewHolder)holder).getTextview().setText(datas[position]);
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textview;

        public ViewHolder(View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.textView);
        }

        public TextView getTextview() {
            return textview;
        }
    }
}
