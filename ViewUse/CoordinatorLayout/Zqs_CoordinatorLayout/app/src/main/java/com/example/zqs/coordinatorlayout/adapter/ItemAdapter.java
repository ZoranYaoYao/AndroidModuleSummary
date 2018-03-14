package com.example.zqs.coordinatorlayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.zqs.coordinatorlayout.R;
import com.example.zqs.coordinatorlayout.recyclerView.BaseRecyclerHolder;

import java.util.List;

/**
 * @ explain:
 * @ author：xujun on 2016/10/18 16:42
 * @ email：gdutxiaoxu@163.com
 */
public class ItemAdapter extends BaseRecyclerAdapter<String> {

    public ItemAdapter(Context context, List<String> datas) {
        super(context, R.layout.item_string, datas);
    }

    @Override
    public void convert(BaseRecyclerHolder holder, String item, int position) {
        TextView  tv=holder.getView(R.id.tv);
        tv.setText(item);

    }
    
}
