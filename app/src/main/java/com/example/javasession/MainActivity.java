package com.example.javasession;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btnAddNewButton;
    private RecyclerView recyclerView;
    private RecyclerViewAdapater adapater;

    private static ArrayList<Alarm> allAlarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapater = new RecyclerViewAdapater();

        if (null == allAlarms)
        {
            allAlarms = new ArrayList<>();
        }
        btnAddNewButton = (Button) findViewById(R.id.btnAddNewAlarm);
        recyclerView    = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setAdapter(adapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapater.setAlarms(allAlarms);

        btnAddNewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddNewAlarmActivity.class);
                startActivity(intent);
            }
        });
        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra(getString(R.string.bundle));
            if(null != bundle)
            {
                int hours = bundle.getInt(getString(R.string.hours),-1);
                int minutes = bundle.getInt(getString(R.string.minutes), -1);

                if (hours != -1)
                {
                    if (minutes != -1)
                    {
                        Intent alarmintent = new Intent(AlarmClock.ACTION_SET_ALARM);
                        alarmintent.putExtra(AlarmClock.EXTRA_HOUR, hours);
                        alarmintent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                        alarmintent.putExtra(AlarmClock.EXTRA_MESSAGE,"Hello from the other side");
                        Alarm alarm = new Alarm(hours,minutes, "Hello from the other side");
                        allAlarms.add(alarm);
                        adapater.setAlarms(allAlarms);
                        startActivity(alarmintent);
                    }
                }
            }
            else {
                Log.d(TAG, "onCreate: bundle is null");
            }
        }catch (Exception e)
        {
            Log.d(TAG, "onCreate: something happened");
            e.printStackTrace();
        }
    }
}