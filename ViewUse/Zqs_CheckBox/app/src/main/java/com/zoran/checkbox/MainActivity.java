package com.zoran.checkbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * https://github.com/pheng/android_radiogroup_MutilRadioGroup
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.checkBox_family)
    CheckBox checkBoxFamily;
    @BindView(R.id.checkBox_friend)
    CheckBox checkBoxFriend;
    int type = 0; // 0选中家人 1选中朋友

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        checkBoxFamily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (type == 0) return;
//
//                if (isChecked){
//                    Log.e("zqs", "选中家人");
//                    type = 0;
//                    checkBoxFriend.setChecked(false);
//                }
//            }
//        });
//        checkBoxFriend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (type == 1) return;
//
//                if(isChecked) {
//                    Log.e("zqs", "选中朋友");
//                    type = 1;
//                    checkBoxFamily.setChecked(false);
//                }
//            }
//        });

        checkBoxFamily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("zqs", "checkBoxFamily + isChecked = " + isChecked);
            }
        });

    }

    /**onclick， 同时点击，会出现问题
     *
     */
        @OnClick({R.id.checkBox_family, R.id.checkBox_friend})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkBox_family://家人
                Log.e("zqs", "checkBoxFamily.isChecked() = " + checkBoxFamily.isChecked());
                if (checkBoxFamily.isChecked() && !checkBoxFriend.isChecked()) {
                    return;
                } else {
                    checkBoxFamily.setChecked(true);
                    checkBoxFriend.setChecked(false);
//                    Toast.makeText(MainActivity.this, "选中家人", 1000).show();
                    Log.e("zqs", "选中家人");
                }
                break;
            case R.id.checkBox_friend://朋友
                Log.e("zqs", "checkBoxFriend.isChecked() = " + checkBoxFriend.isChecked());
                if (checkBoxFriend.isChecked() && !checkBoxFamily.isChecked()) {
                    return;
                } else {
                    checkBoxFriend.setChecked(true);
                    checkBoxFamily.setChecked(false);
//                    Toast.makeText(MainActivity.this, "选中朋友", 1000).show();
                    Log.e("zqs", "选中朋友");

                }
                break;
        }
    }
}
