package com.zqs.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.zqs.customview.imageview.ZoomImageView;

/**
 * Created by zqs on 2018/4/22.
 */

public class ImageViewAcitivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去除title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_imageview);
        ZoomImageView zoomImageView = findViewById(R.id.zoom_image_view);

    }


}
