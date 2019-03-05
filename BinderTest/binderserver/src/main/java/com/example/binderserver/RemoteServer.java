package com.example.binderserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.zoran.bindertest.IMyAidlInterface;

/**
 * Created by zqs on 2019/3/1.
 */
public class RemoteServer extends Service {


    private IBinder iBinder = new IMyAidlInterface.Stub() {
        @Override
        public String getServerName() throws RemoteException {
            return "我叫瑶瑶服务器";
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        //Core. 这里返回的是Binder的stub对象！
        return iBinder;
    }
}
