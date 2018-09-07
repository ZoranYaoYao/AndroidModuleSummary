package com.zqs.test2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * 多类型的ItemView使用复用机制:
 *   1. 从源码上看 用RecycleBin 的ArrayList<View>[] scrapsView 进行缓存不同类型的view.
 *   2. 在复用时, 用RecycleBin会通过getItemViewType()去判断当前位置的类型, 然后从scrapsView去获取相同类型的视图view (视图相同,绑定数据不同)
 *   3. 根据相同类型的view, 然后在重新绑定当前position的显示值.
 */
public class SecondeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.list_check_reuse);

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) list.add("Title " + i);

        MyAdapter myAdapter = new MyAdapter(list, this);
        listView.setAdapter(myAdapter);
    }


    interface Holder {

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

        //3种类型 0,1,2 类型从0开始
        @Override
        public int getItemViewType(int position) {
            return position % 3;
        }

        //一共3种类型,我只用了3种 (0(默认), 1, 2)
        @Override
        public int getViewTypeCount() {
            return 3;
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
            Holder viewHolder = null;
            if (convertView == null) {
                View view = null;
                if (getItemViewType(position) == 0) {
                    view = inflater.inflate(R.layout.item_check_reuse_1, parent, false);
                    viewHolder = new ViewHolder(view);
                } else if (getItemViewType(position) == 1) {
                    view = inflater.inflate(R.layout.item_check_reuse_2, parent, false);
                    viewHolder = new ViewHolder2(view);
                } else if (getItemViewType(position) == 2) {
                    view = inflater.inflate(R.layout.item_check_reuse_3, parent, false);
                    viewHolder = new ViewHolder3(view);
                }
                view.setTag(viewHolder);
                convertView = view;
            } else {
                viewHolder = (Holder) convertView.getTag();
                Log.e("zqs1", "position=" + position + ", Type=" + getItemViewType(position));
                Log.e("zqs1", "convertView=" + convertView);
                Log.e("zqs1", "--------------------------");
            }


            //进行重新绑定数据
            if (getItemViewType(position) == 0) {
                ((ViewHolder) viewHolder).textView.setText(list.get(position));
            } else if (getItemViewType(position) == 1) {
                ((ViewHolder2) viewHolder).btn.setText(list.get(position));
            } else if (getItemViewType(position) == 2) {
                //                ((ViewHolder3) viewHolder).imgView.setId(1);
            }

            return convertView;
        }

        class ViewHolder implements Holder {
            TextView textView;

            public ViewHolder(View content) {
                this.textView = (TextView) content.findViewById(R.id.tv_cur_location);
            }
        }

        class ViewHolder2 implements Holder {
            Button btn;

            public ViewHolder2(View content) {
                this.btn = (Button) content.findViewById(R.id.btn_cur_location);
            }
        }

        class ViewHolder3 implements Holder {
            ImageView imgView;

            public ViewHolder3(View content) {
                this.imgView = (ImageView) content.findViewById(R.id.im_cur_location);
            }
        }
    }
}
