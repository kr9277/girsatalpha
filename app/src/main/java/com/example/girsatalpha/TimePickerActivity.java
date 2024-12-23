package com.example.girsatalpha;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class TimePickerActivity extends AppCompatActivity {
    Button btnOpenTime;
    TextView titel6;
    TextView tvTime;
    private CountDownTimer cDT;
    private static final long DURATION = 10*1000;
    private long seconds, centiSeconds;
    private  String formattedTime;
    private AlarmManager alarmMgr;
    private PendingIntent alarmIntent;
    private int ALARM_RQST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time_picker);

        titel6 = findViewById(R.id.titel6);
        btnOpenTime = findViewById(R.id.btnOpenTime);
        tvTime = findViewById(R.id.tvTime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cDT = new CountDownTimer(DURATION, 10) {
            // countDownInterval for centi-seconds = 10
            // countDownInterval for seconds = 1000
            @Override
            public void onTick(long mSecToFinish) {
                updateDidplay(mSecToFinish);
            }

            @Override
            public void onFinish() {
                Toast.makeText(TimePickerActivity.this, "Passing to the first Activity", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(TimePickerActivity.this, MainActivity.class);
                startActivity(intent);
            }
        };
    }

//    public void setTime(View view) {
//        Calendar calendar = Calendar.getInstance();
//        int hours = calendar.get(Calendar.HOUR_OF_DAY);
//        int mins = calendar.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(TimePickerActivity.this, com.google.android.material.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
//            @Override
//            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                Calendar c = Calendar.getInstance();
//                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//                c.set(Calendar.MINUTE, minute);
//                c.setTimeZone(TimeZone.getDefault());
//                SimpleDateFormat format = new SimpleDateFormat("k:mm a");
//                String time = format.format(c.getTime());
//            }
//        }, hours, mins, false);
//        timePickerDialog.show();
//        Toast.makeText(TimePickerActivity.this, "Time is Over!", Toast.LENGTH_SHORT).show();
//    }
    public void setTime(View view) {
        cDT.start();
        if(tvTime.getText().toString().equals("00:00")){

        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cDT != null) {
            cDT.cancel();
        }
    }
    private void updateDidplay(long mSecToFinish) {
        seconds = mSecToFinish / 1000;
        centiSeconds = (mSecToFinish / 10) % 100;
        formattedTime = String.format("%02d:%02d", seconds, centiSeconds);
        tvTime.setText(formattedTime);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.tafrit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@Nullable MenuItem item){
        String st = item.getTitle().toString();
        if(st.equals("Enter Data Activity")){
            Intent intent = new Intent(this, EnterDataActivity.class);
            startActivity(intent);
        }
        else if(st.equals("GPS Activity")){
            Intent intent = new Intent(this, GPSActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Show Data")){
            Intent intent = new Intent(this, ShowData.class);
            startActivity(intent);
        }
        else if(st.equals("Sound Activity")){
            Intent intent = new Intent(this, SoundActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Main Activity")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}