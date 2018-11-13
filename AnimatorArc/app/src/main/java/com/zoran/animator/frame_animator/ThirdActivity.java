package com.zoran.animator.frame_animator;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zoran.animator.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zqs on 2018/11/7.
 */
public class ThirdActivity extends AppCompatActivity {

    @BindView(R.id.img_frame)
    ImageView imgFrame;
    @BindView(R.id.btn_start_anim)
    Button btnStartAnim;
    @BindView(R.id.btn_stop_anim)
    Button btnStopAnim;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ButterKnife.bind(this);
        btnStartAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //1. view设置动画
                imgFrame.setImageResource(R.drawable.frame_anim);
                //2. 获取AnimationDrawable
                AnimationDrawable drawable = (AnimationDrawable) imgFrame.getDrawable();
                //3. 开始动画
                drawable.start();
            }
        });
        btnStopAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //停止动画
                ((AnimationDrawable) imgFrame.getDrawable()).stop();
            }
        });
    }

}
