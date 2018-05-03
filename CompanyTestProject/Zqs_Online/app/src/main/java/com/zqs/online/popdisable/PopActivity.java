package com.zqs.online.popdisable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zqs on 2018/4/27.
 */

public class PopActivity extends AppCompatActivity {
    MonitorOrderDialogTmp monitorOrderDialogTmp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        monitorOrderDialogTmp = new MonitorOrderDialogTmp(this);
        monitorOrderDialogTmp.show();
    }


}
