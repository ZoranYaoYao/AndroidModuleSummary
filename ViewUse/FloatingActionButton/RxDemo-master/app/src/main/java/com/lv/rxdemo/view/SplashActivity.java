package com.lv.rxdemo.view;

import android.content.Intent;
import android.os.Bundle;

import com.lv.rxdemo.R;
import com.lv.rxdemo.app.RxApplication;
import com.lv.rxdemo.utils.AppUtils;
import com.lv.rxdemo.view.base.BaseActivity;
import com.lv.rxdemo.widget.ParticleView;

public class SplashActivity extends BaseActivity {

    private ParticleView particleViewLaunch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!RxApplication.isFirstInstall) {//说明不是第一次安装
            setContentView(R.layout.activity_splash);
            particleViewLaunch = (ParticleView) findViewById(R.id.particle_view_launch);
            particleViewLaunch.startAnim();
            particleViewLaunch.setOnParticleAnimListener(new ParticleView.ParticleAnimListener() {
                @Override
                public void onAnimationEnd() {
                    AppUtils.checkLogin(SplashActivity.this);
                }
            });
        } else {
            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
            finish();
            overridePendingTransition(R.anim.slide_in, R.anim.slide_out_back);
        }
    }

}
