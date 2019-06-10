package com.skt.aicd.testservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skt.aicd.testservice.lib.IAidlService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
   //private TestBindService mTestBindService;
    private TextView textView;
    private static final String servicePackage = "com.skt.aicd.testservice";
    private static final String serviceClass = servicePackage+".TestBindService";
    private ComponentName componentName = new ComponentName(servicePackage, serviceClass);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void onClickView(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startTestService();
                break;
            case R.id.button2:
                bindTestService();
                break;
            case R.id.button3:
                unbindTestService();
                break;
            case R.id.button4:
                getValueOnBindTestService();
                break;
            case R.id.button5:
                stopTestService();
                break;
            default:
                break;
        }
    }

    private void startTestService() {
        L.d(TAG, "startTestService()");
        startService(getServiceIntent());
    }

    private void stopTestService() {
        L.d(TAG, "stopTestService()");
        stopService(getServiceIntent());
    }

    private void getValueOnBindTestService() {
        L.d(TAG, "getValueOnBindTestService()");
        if (iAidlService == null) {
            textView.setText("n/a");
            return;
        }

        try {
            final int value = iAidlService.getValue();
            L.d(TAG, "getValueOnBindTestService() num:"+value);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText(String.valueOf(value));
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private Intent getServiceIntent() {
        Intent intent = new Intent();
        intent.setComponent(componentName);
        return intent;
    }

    private void bindTestService() {
        L.d(TAG, "bindTestService()");
        boolean result = bindService(getServiceIntent(), serviceConnection, Context.BIND_AUTO_CREATE);
        L.d(TAG, "bindTestService() result:"+result);
    }

    private void unbindTestService() {
        L.d(TAG, "unbindTestService()");
        try {
            unbindService(serviceConnection);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    IAidlService iAidlService = null;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.d(TAG, "onServiceConnected() name:"+name+", IBinder:"+service);
            iAidlService = IAidlService.Stub.asInterface(service);
            //mTestBindService = ((TestBindService.MyBinder) service).getTestService();
            L.d(TAG, "getTestService() instance:"+ iAidlService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.d(TAG, "onServiceDisconnected() name:"+name);
        }
    };
}
