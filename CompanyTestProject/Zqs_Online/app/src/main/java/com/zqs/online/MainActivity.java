package com.zqs.online;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zqs.online.dialog.LoadingWithTextDialog;
import com.zqs.online.popdisable.PopActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动抢单 dialog提示UI
 *
 * https://blog.csdn.net/lcq5211314123/article/details/46722737
 */
public class MainActivity extends AppCompatActivity {

    public static List<String> list = new ArrayList<>();
    static {
        list.add("Popup,禁用back键");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listview);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                switch (position) {
                    case 0:
                        intent.setClass(MainActivity.this,PopActivity.class);
                        break;
                }
                startActivity(intent);
            }
        });
    }
}
