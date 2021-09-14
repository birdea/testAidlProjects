package com.skt.aicd.testservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.skt.vii.service.IViiAgentControl;

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
                stopTestService();
                break;
            case R.id.button3:
                bindTestService();
                break;
            case R.id.button4:
                unbindTestService();
                break;
            case R.id.button5:
                startAgent();
                break;
            case R.id.button6:
                stopAgent();
                break;
            case R.id.button7:
                stopActivity();
                break;
            case R.id.button8:
                setWakeWordDetector(true);
                break;
            case R.id.button9:
                setWakeWordDetector(false);
                break;
            case R.id.button10:
                notifyAgentStatus();
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

    private void stopAgent() {
        L.d(TAG, "stopAgent()");
        try {
            TestBindService.clientControl.stopAgent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAgent() {
        L.d(TAG, "startAgent()");
        try {
            TestBindService.clientControl.startAgent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopActivity() {
        L.d(TAG, "stopActivity()");
        try {
            TestBindService.clientControl.stopActivity();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setWakeWordDetector(boolean enable) {
        L.d(TAG, "setWakeWordDetector() enable:"+enable);
        try {
            TestBindService.clientControl.setWakeWordDetector(enable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void notifyAgentStatus() {
        L.d(TAG, "notifyAgentStatus()");
        try {
            int status = 0;
            String service = "media";
            TestBindService.clientControl.onAgentStatus(status, service);
        } catch (Exception e) {
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
       // boolean result = bindService(getServiceIntent(), serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindTestService() {
        L.d(TAG, "unbindTestService()");
        /*try {
            unbindService(serviceConnection);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }*/
    }

    /*IViiAgentControl iAidlService = TestBindService.clientControl;

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            L.d(TAG, "onServiceConnected() name:"+name+", IBinder:"+service);
            iAidlService = IViiAgentControl.Stub.asInterface(service);
            //L.d(TAG, "getTestService() instance:"+ iAidlService);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            L.d(TAG, "onServiceDisconnected() name:"+name);
            iAidlService = null;
        }
    };*/
}
