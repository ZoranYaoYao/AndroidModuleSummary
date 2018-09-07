package com.zqs.test2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list_check_reuse);

        List<String> list = new ArrayList<>();
        for(int i=1; i<=100; i++) list.add("Title " + i);

        MyAdapter myAdapter = new MyAdapter(list,this);
        listView.setAdapter(myAdapter);
    }

    class MyAdapter extends BaseAdapter {
        private List<String> list;
        private LayoutInflater inflater;
        private Context context;

        public MyAdapter(List<String> list, Context context) {
            this.list = list;
            this.inflater = LayoutInflater.from(context);
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //用复用机制,但是不重新绑定数据, 是否会数据错乱
            ViewHolder viewHolder;
            if (convertView == null) {
                View view = inflater.inflate(R.layout.item_check_reuse_1, parent, false);
                viewHolder = new ViewHolder(view);
                viewHolder.textView.setText(list.get(position));
                view.setTag(viewHolder);
                convertView = view;
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //不进行重新绑定数据的话, 使用的是以前的view和上面的数据
//            viewHolder = (ViewHolder) convertView.getTag();
//            Log.e("zqs1", "position=" +position+", 内容:" + viewHolder.textView.getText());

            //进行重新绑定数据
            viewHolder.textView.setText(list.get(position));
            Log.e("zqs1", "convertView=" + convertView);
            return convertView;
        }

        class ViewHolder {
            TextView textView;

            public ViewHolder(View content) {
                this.textView = (TextView) content.findViewById(R.id.tv_cur_location);
            }
        }
    }


}
