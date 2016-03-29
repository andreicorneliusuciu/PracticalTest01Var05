package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;
import java.util.Random;

/**
 * Created by Andrei S on 3/28/2016.
 */
public class ProcessingThread extends Thread {

    private Context context;
    private boolean isRunning = true;

    private Random random = new Random();

    private String secventa;


    public ProcessingThread(Context context, String secv) {
        this.context = context;

        secventa = secv;

    }



    @Override
    public void run() {
        Log.d("[ProcessingThread]", "Thread has started!");
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d("[ProcessingThread]", "Thread has stopped!");
    }

    private void sleep() {
        try {
            Thread.sleep(10000);

        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.actionTypes[random.nextInt(Constants.actionTypes.length)]);
        intent.putExtra("message", new Date(System.currentTimeMillis()) + " " + secventa);
        context.sendBroadcast(intent);
    }

    public void stopThread() {
        isRunning = false;
    }
}
