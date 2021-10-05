package com.skt.aicd.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

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
                forceStartAgent();
                break;
            case R.id.button6:
                forceStopAgent();
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

    private void forceStopAgent() {
        L.d(TAG, "forceStopAgent()");
        try {
            TestBindService.clientControl.forceStopAgent();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void forceStartAgent() {
        L.d(TAG, "forceStartAgent()");
        try {
            TestBindService.clientControl.forceStartAgent();
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
            if (enable) {
                TestBindService.clientControl.startWakeupDetector();
            } else {
                TestBindService.clientControl.stopWakeupDetector();
            }
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
