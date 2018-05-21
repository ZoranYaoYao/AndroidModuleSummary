package com.zqs.customview.imageview;

import android.content.Context;
import android.graphics.Matrix;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

/**
 * Created by zqs on 2018/4/22.
 *问题一: setScaleType : 设置图片和控件之间的规模,比例关系
 */
public class ZoomImageView extends AppCompatImageView implements ViewTreeObserver.OnGlobalLayoutListener{
    private Matrix mMatrix;
    private boolean mOnce;

    public ZoomImageView(Context context) {
        this(context,null);
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //init
        mMatrix = new Matrix();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);

    }


    float mDefaultScale, mMaxScale;
    @Override
    public void onGlobalLayout() {

        if (!mOnce) {
            int width = getWidth(); int height = getHeight();
            if (getDrawable() == null) return;
            int dw = getDrawable().getIntrinsicWidth();int dh = getDrawable().getIntrinsicHeight();
            float scale = 0.0f;
            if(dw > width && dh < height) {
                scale = dw * 1.0f / width;
            }

            if(dw < width && dh > height) {
                scale = dh * 1.0f / height;
            }

            if ((dw > width && dh > height) || (dw < width && dh < height)) {
                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
            }

            mDefaultScale = scale;
            mMaxScale = mDefaultScale * 4;

            float dx = getWidth()*1.0f/2 - dw * 1.0f /2;
            float dy = getHeight()*1.0f/2 - dh * 1.0f/2;

            //平移到手机中心位置
            mMatrix.postTranslate(dx, dy);
            //缩放图片
            mMatrix.postScale(mDefaultScale, mDefaultScale,width/2, height/2);
            setImageMatrix(mMatrix);

            mOnce = true;
        }

    }
}
