package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PracticalTest01Var05MainActivity extends AppCompatActivity {

    private Button west;
    private Button north;
    private Button east;
    private Button south;
    private Button navigateButton;
    private StringBuilder secventa = new StringBuilder();
    private TextView secvAfisare;
    private ButtonClickListener btn = new ButtonClickListener();
    private int contor = 0;
    public static final int STOP = 1;
    public static final int START = 0;
    private IntentFilter intentFilter = new IntentFilter();
    private int serviceStatus = STOP;

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.west:
                    secventa.append("West ");
                    secvAfisare.setText(secventa.toString());
                    break;
                case R.id.north:
                    secventa.append("North ");
                    secvAfisare.setText(secventa.toString());
                    break;
                case R.id.east:
                    secventa.append("East ");
                    secvAfisare.setText(secventa.toString());
                    break;
                case R.id.south:
                    secventa.append("South ");

                    secvAfisare.setText(secventa.toString());
                    break;
                case R.id.navigate:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05SecondaryActivity.class);

                    intent.putExtra("contor", contor);
                    intent.putExtra("secventa", secvAfisare.getText().toString());
                    startActivityForResult(intent, 1);
                    break;
            }
            if (contor == 1) {
                Intent intent = new Intent(getApplicationContext(), PracticalTest01Var05Service.class);
                intent.putExtra("secventa", secvAfisare.getText().toString());

                getApplicationContext().startService(intent);
                serviceStatus = START; //???CEV A
            }
        }
    }

    @Override
    protected void onResume() {
        Log.d("[OnResume]", "OnResume");
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        Log.d("[OnPause]", "OnPause");
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // if(savedInstanceState == null) {
            this.west = (Button) findViewById(R.id.west);
            this.east = (Button) findViewById(R.id.east);
            this.south = (Button) findViewById(R.id.south);
            this.north = (Button) findViewById(R.id.north);
            this.navigateButton = (Button) findViewById(R.id.navigate);
            this.secvAfisare = (TextView) findViewById(R.id.secventa);
            secvAfisare.setText(" ");

            this.west.setOnClickListener(btn);
            this.east.setOnClickListener(btn);
            this.north.setOnClickListener(btn);
            this.south.setOnClickListener(btn);
        this.navigateButton.setOnClickListener(btn);
        //} else {
            //onRestoreInstanceState(savedInstanceState);
        //}
        for (int index = 0; index < Constants.actionTypes.length; index++) {
            intentFilter.addAction(Constants.actionTypes[index]);
        }

    }

    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String msg =  intent.getStringExtra("message");
            Log.d("[MessageService]", intent.getStringExtra("message"));

        }
    }

    @Override
    protected void onDestroy() {
        Log.d("[onDestroy()]", "On onDestroy comand");
        Intent intent = new Intent(this, PracticalTest01Var05Service.class);
        stopService(intent);
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            Toast.makeText(this, "Activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
            if(resultCode == -1) {
                contor++;
                Toast.makeText(this, "Contor este " + contor, Toast.LENGTH_LONG).show();

            } else
                Toast.makeText(this, "Contor este " + contor, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.d("[SaveeInsctaceState]", "On SaveeInsctaceState comand");
        savedInstanceState.putInt("contor", contor);
        savedInstanceState.putString("secventa", secvAfisare.getText().toString());

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.d("[RestoreInsctaceState]", "On RestoreInsctaceState comand");
        if (savedInstanceState.containsKey("contor")) {
            contor = (int)savedInstanceState.getInt("contor");
        } else {
            contor = 0;
        }
        if (savedInstanceState.containsKey("secventa")) {
            secvAfisare.setText(savedInstanceState.getString("secventa"));
        } else {
            secvAfisare.setText(" ");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_practical_test01_var05_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
