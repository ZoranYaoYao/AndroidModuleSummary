package com.zoran.animator.property_animator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zoran.animator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zqs on 2018/11/7.
 */
public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.btn_transfer)
    Button btnTransfer;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;
    @BindView(R.id.btn_combo)
    Button btnCombo;
    @BindView(R.id.btn_concurrent)
    Button btnConcurrent;
    @BindView(R.id.img_object)
    ImageView imgObject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_transfer, R.id.btn_rotate, R.id.btn_scale, R.id.btn_alpha, R.id.btn_combo, R.id.btn_concurrent})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                new Translate().startCode(imgObject);
                break;
            case R.id.btn_rotate:
                new Rotate().startCode(imgObject);
                break;
            case R.id.btn_scale:
                new Scale().startCode(imgObject);
                break;
            case R.id.btn_alpha:
                new Alpha().startCode(imgObject);
                break;
            case R.id.btn_combo:
                new ComboAnimation().startCode(imgObject);
                break;
            case R.id.btn_concurrent:
                new ConCurrentAnimation().startCode(imgObject);
                break;
        }
    }

    @OnClick({R.id.img_object})
    public void imageViewClick(View view) {
        Toast.makeText(this, "滑动块被点击了", Toast.LENGTH_SHORT).show();
    }
}
