package com.zqs.snackbar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by zqs on 2018/3/23.
 *
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0714/3186.html
 */

public class SnackbarActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar2);
    }

    public void onClick(View view) {
        View rootView = findViewById(android.R.id.content);
        Snackbar snackbar = Snackbar.make(rootView, "Hello Snackbar", BaseTransientBottomBar.LENGTH_SHORT);
        switch (view.getId()) {
            case R.id.blue_btn:
                ColoredSnacbar.info(snackbar).show();
                break;
            case R.id.green_btn:
                ColoredSnacbar.confirm(snackbar).show();
                break;
            case R.id.orange_btn:
                ColoredSnacbar.warning(snackbar).show();
                break;
            case R.id.red_btn:
                ColoredSnacbar.alert(snackbar).show();
                break;
        }
    }
}
