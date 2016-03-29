package ro.pub.cs.systems.eim.practicaltest01var05;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Andrei S on 3/29/2016.
 */
public class PracticalTest01Var05Service extends Service {
    private ProcessingThread processingThread = null;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("[Service]", "On start comand");
        String secventa = intent.getStringExtra("secventa");

        processingThread = new ProcessingThread(this, secventa);
        processingThread.start();
        return Service.START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        processingThread.stopThread();
    }
}
