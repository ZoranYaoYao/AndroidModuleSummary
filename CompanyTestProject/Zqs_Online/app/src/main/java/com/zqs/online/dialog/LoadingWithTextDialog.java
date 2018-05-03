package com.zqs.online.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.zqs.online.DisplayUtil;
import com.zqs.online.R;


/**
 * Created by herr.wang on 2018/1/25.
 */

public class LoadingWithTextDialog extends Dialog {
    ImageView ivLoading;
    TextView tvText;
    RotateAnimation rotateAnimation;
    private TextView tvContent;

    public LoadingWithTextDialog(Context context) {
        this(context, R.style.LoadingDialog);
    }

    public LoadingWithTextDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View contentView = getLayoutInflater().inflate(R.layout.layout_loading_with_text, null, false);
        setContentView(contentView);
        setCancelable(false);
        setCanceledOnTouchOutside(false);

        ivLoading = (ImageView) contentView.findViewById(R.id.iv_loading);
        tvText = (TextView) contentView.findViewById(R.id.tv_text);
        tvContent = (TextView) contentView.findViewById(R.id.tv_content);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        //lp.height = DisplayUtil.dip2px(getContext(), 160);
        lp.width = DisplayUtil.dip2px(getContext(), 230);
        window.setAttributes(lp);
    }

    /**
     * call after #show().
     */
    public void loading(){
        ivLoading.setImageResource(R.drawable.icon_loading);
        tvText.setText("正在抢单…");
        tvContent.setText("系统将根据接驾距离和服务水平分配订单");
        checkAnimation();
        ivLoading.startAnimation(rotateAnimation);
    }

    public void Displayresult(boolean success, String content) {
        tvText.setText(success ? "抢单成功" : "抢单失败");
        tvContent.setText(content);
    }

    private void checkAnimation() {
        if (rotateAnimation == null) {
            rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(1500);
            rotateAnimation.setInterpolator(new LinearInterpolator());
            rotateAnimation.setRepeatCount(-1);
        }
    }

    public void dismiss() {
        super.dismiss();
        if (ivLoading.getAnimation() != null) {
            ivLoading.getAnimation().cancel();
            ivLoading.clearAnimation();
        }
    }
}
