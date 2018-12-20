package com.zoran.animator.property_animator.ValueAnimator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.zoran.animator.R;
import com.zoran.animator.property_animator.AnimatorSet.AnimatorSetTest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqs on 2018/11/9.
 */
public class VauleAnimatorActivity extends AppCompatActivity {

    @BindView(R.id.btn_offint)
    Button btnOffint;
    @BindView(R.id.btn_offloat)
    Button btnOffloat;
    @BindView(R.id.btn_ofObject)
    Button btnOfObject;
    @BindView(R.id.img_object)
    ImageView imgObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_valueanimator);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_offint, R.id.btn_offloat,R.id.btn_animatorSet})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_offint:
//                new OfInt().startCode(imgObject);
                new OfInt().startXML(imgObject);
                break;
            case R.id.btn_offloat:
//                new ofFloat().startCode(imgObject);
                new ofFloat().startXML(imgObject);
                break;
            case R.id.btn_animatorSet:
                AnimatorSetTest.startCode(imgObject);
                break;
        }

    }
}
