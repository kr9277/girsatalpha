package com.example.girsatalpha;

import static com.example.girsatalpha.FBref.refUser;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.girsatalpha.databinding.ActivityShowDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ShowData extends AppCompatActivity {
    TextView tvDFB, titel3;
    ActivityShowDataBinding binding;
    Button btnShow;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_show_data);
        binding = ActivityShowDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tvDFB = findViewById(R.id.tvDFB);
        titel3 = findViewById(R.id.titel3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        binding.btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference("User");
                reference.child("1").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ShowData.this, "Data Found", Toast.LENGTH_SHORT).show();
                            DataSnapshot dataSnapshot = task.getResult();
                            String something = dataSnapshot.child("something").getValue(String.class);
                            binding.tvDFB.setText(something);
                        }
                        else{
                            Toast.makeText(ShowData.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
//    protected void onResume() {
//        super.onResume();
//        ValueEventListener user2Listener = new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        };
//
//        refUser.addValueEventListener(user2Listener);
//    }
//
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
        else if(st.equals("Main Activity")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Sound Activity")){
            Intent intent = new Intent(this, SoundActivity.class);
            startActivity(intent);
        }
        else if(st.equals("Time Picker Activity")){
            Intent intent = new Intent(this, TimePickerActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}