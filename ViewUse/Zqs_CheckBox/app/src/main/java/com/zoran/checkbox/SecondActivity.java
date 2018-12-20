package com.zoran.checkbox;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zqs on 2018/12/20.
 * 改进用RadioGroup实现
 *
 * 1.
 *  单选按钮，同时点击会触发先后2个点击事件
 * 2018-12-20 14:25:14.543 15305-15305/com.zoran.checkbox E/zqs: checkBoxFriend isChecked = true
 * 2018-12-20 14:25:14.544 15305-15305/com.zoran.checkbox E/zqs: checkBoxFamily isChecked = false
 * 2018-12-20 14:25:14.547 15305-15305/com.zoran.checkbox E/zqs: checkBoxFamily isChecked = true
 * 2018-12-20 14:25:14.547 15305-15305/com.zoran.checkbox E/zqs: checkBoxFriend isChecked = false
 *
 * 2.
 *  核心方法是：setCheckWithoutNotif() 将上一个选中的按钮进行设置setChecked(false);
 *  (id == mCheckedId) ，跳出sechecked 与 onCheckedChanged()的循环
 */
public class SecondActivity extends AppCompatActivity {


    @BindView(R.id.checkBox_family)
    RadioButton checkBoxFamily;
    @BindView(R.id.checkBox_friend)
    RadioButton checkBoxFriend;
    @BindView(R.id.rg)
    MultiRadioGroup rg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        ButterKnife.bind(this);
        checkBoxFamily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("zqs","checkBoxFamily isChecked = " + isChecked);
                rg.setCheckWithoutNotif(buttonView.getId());
            }
        });
        checkBoxFriend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("zqs","checkBoxFriend isChecked = " + isChecked);
                rg.setCheckWithoutNotif(buttonView.getId());
            }
        });

    }


}
