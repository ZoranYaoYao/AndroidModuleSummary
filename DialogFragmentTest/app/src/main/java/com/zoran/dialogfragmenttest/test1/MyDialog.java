package com.zoran.dialogfragmenttest.test1;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.zoran.dialogfragmenttest.R;

/**
 * Created by zqs on 2018/10/18.
 */
public class MyDialog extends Dialog {
     Context mContext;

    public MyDialog(@NonNull Context context) {
        this(context, 0);
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View root = View.inflate(mContext, R.layout.dialog_my, null);
        setContentView(root);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
