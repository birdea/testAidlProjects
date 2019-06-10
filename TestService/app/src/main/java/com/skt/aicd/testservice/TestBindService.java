package com.skt.aicd.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.skt.aicd.testservice.IAidlService;

public class TestBindService extends Service {

    private static final String TAG = "TestBindService";
    private int mNumber;

    class MyAidlService extends IAidlService.Stub {
        @Override
        public int getValue() throws RemoteException {
            L.i(TAG,"getNumber() mNumber:"+mNumber);
            return mNumber++;
        }
    }

    private MyAidlService myAidlService = new MyAidlService();


    @Override
    public IBinder onBind(Intent intent) {
        L.i(TAG,"onBind() intent:"+intent);
        return myAidlService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i(TAG,"onCreate()");
        mNumber = 0;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.i(TAG,"onStartCommand() intent:"+intent+", flags:"+flags+", startId:"+startId);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        L.i(TAG,"onDestroy()");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        L.i(TAG,"onUnbind() intent:"+intent);
        return super.onUnbind(intent);
    }
}
