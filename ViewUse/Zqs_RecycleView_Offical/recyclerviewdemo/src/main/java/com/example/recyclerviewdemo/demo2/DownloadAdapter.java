package com.example.recyclerviewdemo.demo2;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.example.recyclerviewdemo.R;

import java.util.List;

/**
 * Created by zqs on 2018/3/7.
 */

public class DownloadAdapter extends BaseAdapter {
    private Context context;
    private List<Job> mData;

    public DownloadAdapter(Context context, List<Job> data) {
        mData =data;
        this.context = context;
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        VH holder = null;
        final Job  job = mData.get(position);
        if(convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_download,parent,false);
            holder = new VH();
            holder.btn = convertView.findViewById(R.id.btn_download);
            holder.progress = convertView.findViewById(R.id.progress);
            convertView.setTag(holder);
        } else {
            holder = (VH) convertView.getTag();
        }

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadTask(context,position).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,job.url);
            }
        });

        if(job.progress == 100) {
            holder.btn.setText("完成");
        } else{
            holder.btn.setText("下载");
        }
        holder.progress.setProgress(job.progress);
        return convertView;
    }

    /**
     * 局部更新API
     */
    public void notifyItemChanged(ListView listView, int position) {
        int firstPos = listView.getFirstVisiblePosition();
        int lastPos = listView.getLastVisiblePosition();
        Job job = mData.get(position);
        if (position >= firstPos && position <= lastPos) {
            View view = listView.getChildAt(position - firstPos);
            VH vh = (VH) view.getTag();
            vh.progress.setProgress((job.progress));
            if(job.progress == 100){
                vh.btn.setText("完成");
            }
        }
    }

    class VH{
        Button btn;
        NumberProgressBar progress;
    }
}
