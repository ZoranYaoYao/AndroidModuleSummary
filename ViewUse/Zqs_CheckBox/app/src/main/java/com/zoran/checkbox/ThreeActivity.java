package com.zoran.checkbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zqs on 2018/12/20.
 * 自己实现RadioButton 互斥点击事件
 * [原理]
 * 1.RadioButton 状态切换时，会进行回调OnCheckedChangeListener
 * 2.只有当回调是isChecked=true，的情况下才进行状态切换
 *
 * 注意：
 * 如果在OnCheckedChangeListener()进行设置setChecked()，会出现循环调用，应当避免！！
 * setChecked() -> OnCheckedChangeListener()
 */
public class ThreeActivity extends AppCompatActivity {


    @BindView(R.id.checkBox_family)
    RadioButton checkBoxFamily;
    @BindView(R.id.checkBox_friend)
    RadioButton checkBoxFriend;
    @BindView(R.id.rg)
    RadioGroup rg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        ButterKnife.bind(this);
        checkBoxFamily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("zqs","checkBoxFamily isChecked = " + isChecked);
                singleSelected(buttonView, isChecked);
//                rg.setCheckWithoutNotif(buttonView.getId());
            }
        });
        checkBoxFriend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("zqs","checkBoxFriend isChecked = " + isChecked);
                singleSelected(buttonView,isChecked);
//                rg.setCheckWithoutNotif(buttonView.getId());
            }
        });

    }

    public void singleSelected(CompoundButton view, boolean isChecked) {

        if(isChecked) {
            if (view == checkBoxFamily) {
                checkBoxFamily.setChecked(true);
                checkBoxFriend.setChecked(false);
            } else {
                checkBoxFamily.setChecked(false);
                checkBoxFriend.setChecked(true);
            }
        }

    }

}
