package com.skt.aicd.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.skt.vii.service.IViiAgentControl;

public class TestBindService extends Service {

    private static final String TAG = "TestBindService";

    public static IViiAgentControl clientControl;

    public IViiAgentControl myAgentService = new IViiAgentControl.Stub() {

        private int mNumber = 0;

        @Override
        public void startAgent() throws RemoteException {
            L.d(TAG, "startAgent() mNumber:"+mNumber++);
        }

        @Override
        public void stopAgent() throws RemoteException {
            L.d(TAG, "stopAgent() mNumber:"+mNumber++);
        }

        @Override
        public void stopActivity() throws RemoteException {
            L.d(TAG, "stopActivity()");
        }

        @Override
        public void onAgentStatus(int status, String service) throws RemoteException {
            L.i(TAG,"onAgentStatus() status:"+status+", service:"+service);
            L.d(TAG, "onAgentStatus() mNumber:"+mNumber++);
        }

        @Override
        public void setWakeWordDetector(boolean enable) throws RemoteException {
            L.i(TAG,"setWakeWordDetector() enable:"+enable);
        }

        @Override
        public void registerAgentControl(IViiAgentControl agent) throws RemoteException {
            L.i(TAG,"registerAgentControl() agent:"+agent);
            clientControl = agent;
        }

        @Override
        public void unregisterAgentControl(IViiAgentControl agent) throws RemoteException {
            L.i(TAG,"unregisterAgentControl() agent:"+agent);
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        L.i(TAG,"onBind() intent:"+intent);
        return (IBinder) myAgentService;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        L.i(TAG,"onCreate()");
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
