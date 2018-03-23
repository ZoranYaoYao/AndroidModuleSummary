package com.zqs.snackbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by zqs on 2018/3/23.
 *
 * https://www.jianshu.com/p/9eb3b17b0e77
 */

public class SnackbarActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
    }

    public void onClickDialog(View view){
        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("This is Message")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .create()
                .show();
    }

    public void onClickSnackbar(View view){
        Snackbar.make(this.findViewById(android.R.id.content), "This is a Snacbar",Snackbar.LENGTH_SHORT)
                .setAction("跳转", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SnackbarActivity.this,SnackbarActivity2.class);
                        startActivity(intent);
                    }
                })
                .show();
    }

    Toast toast;
    public void onClickToast(View v){
        if (toast == null) {
            toast = Toast.makeText(this,"This is a Toast",Toast.LENGTH_SHORT);
        } else {
            toast.setText("This is a Toast");
        }
        toast.show();
    }
}
