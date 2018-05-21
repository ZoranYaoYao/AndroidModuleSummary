package com.zqs.didi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    LoadingWithTextDialog loadingWithTextDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showBidding();
    }

    private void showBidding() {
//        if (!isActivityRunning()) {
//            return;
//        }
        if (loadingWithTextDialog == null) {
            loadingWithTextDialog = new LoadingWithTextDialog(this);
        }

        loadingWithTextDialog.show();
        loadingWithTextDialog.loading();
    }

    private void hideBidding() {
        if (loadingWithTextDialog != null) {
            loadingWithTextDialog.dismiss();
        }
    }
}
