package com.zqs.online.popdisable;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.zqs.online.R;


/**
 * Created by zqs on 2018/4/27.
 * 问题一 : dialog 全屏展示
 *  [solution] 自己写一个Style ,替换系统默认的不能全屏展示的sytle
 */
public class MonitorOrderDialogTmp extends Dialog {
    private Context mcontext;
    public MonitorOrderDialogTmp(@NonNull Context context) {
        super(context, R.style.no_mask_dialog);
        mcontext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = LayoutInflater.from(mcontext).inflate(R.layout.layout_bid_pop_dialog,null,false);
        setContentView(rootView);
        //设置界面
//        setContentView(R.layout.layout_bid_pop_dialog);

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        window.setAttributes(params);
    }

    @Override
    public void show() {
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        super.show();
    }
}
