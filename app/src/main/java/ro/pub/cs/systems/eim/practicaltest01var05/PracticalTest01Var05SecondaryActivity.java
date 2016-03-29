package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PracticalTest01Var05SecondaryActivity extends AppCompatActivity {

    private EditText contorView;
    private EditText secventaView;
    private Button register = null;
    private Button cancelButton = null;

    private ButtonClickListener btn = new ButtonClickListener();

    private class ButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.register:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.cancel:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var05_secondary);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        contorView = (EditText)findViewById(R.id.editCounter);
        secventaView = (EditText)findViewById(R.id.editSecventa);
        register = (Button)findViewById(R.id.register);
        cancelButton = (Button)findViewById(R.id.cancel);

        register.setOnClickListener(btn);
        cancelButton.setOnClickListener(btn);


        Intent intent = getIntent();
        if(intent != null) {
            if(intent.getExtras().containsKey("contor")) {
                int cont = intent.getIntExtra("contor", -1);
                contorView.setText(String.valueOf(cont));
            }
            if(intent.getExtras().containsKey("secventa")) {
                String secv = intent.getStringExtra("secventa");
                secventaView.setText(secv);
            }
        }


    }
}
