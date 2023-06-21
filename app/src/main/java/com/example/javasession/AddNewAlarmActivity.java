package com.example.javasession;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewAlarmActivity extends AppCompatActivity {
    private static final String TAG = "AddNewAlarmActivity";
    private Button btnAddAlarm;
    private EditText edittxtHour,edittxtMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_alarm);


        btnAddAlarm = (Button) findViewById(R.id.btnAddAlarm);
        edittxtHour = (EditText) findViewById(R.id.edittxtHour);
        edittxtMinute = (EditText) findViewById(R.id.edittxtMinute);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAlarm();
            }
        });
    }

    private void addNewAlarm()
    {
        Log.d(TAG, "addNewAlarm: started");
        int hours = Integer.valueOf(edittxtHour.getText().toString());
        int minutes = Integer.valueOf(edittxtMinute.getText().toString());

        Bundle bundle = new Bundle();
        bundle.putInt(getString(R.string.hours), hours);
        bundle.putInt(getString(R.string.minutes), minutes);

        Intent intent = new Intent(AddNewAlarmActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(getString(R.string.bundle), bundle);
        startActivity(intent);
    }
}