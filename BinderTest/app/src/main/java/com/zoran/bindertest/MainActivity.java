package com.zoran.bindertest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bindAidl();
            }
        });
    }

    private void bindAidl() {
        Intent intent = new Intent();
        /**Core, 绑定service时，包名一定不能写错！！*/
        intent.setPackage("com.example.binderserver");
        intent.setAction("com.example.binderserver.remote");
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                IMyAidlInterface proxy = IMyAidlInterface.Stub.asInterface(service);
                try {
                    String tip = proxy.getServerName();
                    Toast.makeText(MainActivity.this, tip, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Toast.makeText(MainActivity.this, "链接失败！", Toast.LENGTH_SHORT).show();
            }
        }, BIND_AUTO_CREATE);
    }
}
