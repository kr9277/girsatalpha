package com.example.girsatalpha;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SoundActivity extends AppCompatActivity {
    TextView titel5;
    Button btnS1, btnS2, btnS3, btnSS1, btnSS2, btnSS3;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sound);
        titel5 = findViewById(R.id.titel5);
        btnS1 = findViewById(R.id.btnS1);
        btnS2 = findViewById(R.id.btnS2);
        btnS3 = findViewById(R.id.btnS3);
        btnSS1 = findViewById(R.id.btnSS1);
        btnSS2 = findViewById(R.id.btnSS2);
        btnSS3 = findViewById(R.id.btnSS3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player==null){
                    player = MediaPlayer.create(SoundActivity.this, R.raw.dog);
                }
                player.start();
            }
        });
        btnSS1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player!=null){
                    player.release();
                    player = null;
                }
            }
        });
        btnS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player==null){
                    player = MediaPlayer.create(SoundActivity.this, R.raw.omeradam);
                }
                player.start();
            }
        });
        btnSS2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player!=null){
                    player.release();
                    player = null;
                }
            }
        });
        btnS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player==null){
                    player = MediaPlayer.create(SoundActivity.this, R.raw.cat);
                }
                player.start();
            }
        });
        btnSS3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player!=null){
                    player.release();
                    player = null;
                }
            }
        });


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
        else if(st.equals("Main Activity")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Time Picker Activity")){
            Intent intent = new Intent(this, TimePickerActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}