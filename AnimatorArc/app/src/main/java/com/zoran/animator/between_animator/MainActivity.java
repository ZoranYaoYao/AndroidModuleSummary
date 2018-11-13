package com.zoran.animator.between_animator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.zoran.animator.R;
import com.zoran.animator.between_animator.interpolator.DecelerateAccelerateInterpolator;
import com.zoran.animator.between_animator.interpolator.TranslateUseIntepolator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_transfer)
    Button btnTransfer;
    @BindView(R.id.btn_rotate)
    Button btnRotate;
    @BindView(R.id.btn_scale)
    Button btnScale;
    @BindView(R.id.img_object)
    ImageView imgObject;
    @BindView(R.id.btn_alpha)
    Button btnAlpha;
    @BindView(R.id.btn_combo)
    Button btnCombo;
    @BindView(R.id.btn_concurrent)
    Button btnConcurrent;
    @BindView(R.id.btn_interpolator)
    Button btnInterpolator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_transfer, R.id.btn_rotate, R.id.btn_scale, R.id.btn_alpha, R.id.btn_combo, R.id.btn_concurrent,R.id.btn_interpolator})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transfer:
                Translate translate = new Translate();
                //                translate.startXML(imgObject);
                //                translate.startCode(imgObject);
                translate.startCodeFixClick(imgObject);
                break;
            case R.id.btn_rotate:
                Rotate rotate = new Rotate();
                //                rotate.startXML(imgObject);
                rotate.startCode(imgObject);
                break;
            case R.id.btn_scale:
                Scale scale = new Scale();
                //                scale.startXML(imgObject);
                scale.startCode(imgObject);
                break;
            case R.id.btn_alpha:
                Alpha alpha = new Alpha();
                //                alpha.startXML(imgObject);
                alpha.startCode(imgObject);
                break;
            case R.id.btn_combo:
                ComboAnimation comboAnimation = new ComboAnimation();
                //                comboAnimation.startXML(imgObject);
                comboAnimation.startCode(imgObject);
                break;
            case R.id.btn_concurrent:
                ConCurrentAnimation conCurrentAnimation = new ConCurrentAnimation();
                //                conCurrentAnimation.startXML(imgObject);
                conCurrentAnimation.startCode(imgObject);
                break;
            case R.id.btn_interpolator:
                new TranslateUseIntepolator().startCode(imgObject);
                break;

        }
    }

    @OnClick({R.id.img_object})
    public void imageViewClick(View view) {
        Toast.makeText(this, "滑动块被点击了", Toast.LENGTH_SHORT).show();
    }
}
