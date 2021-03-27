package com.example.btandroid.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.btandroid.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Add_Target extends AppCompatActivity {

    Button    button;
    ImageView imageView;
    EditText edYouTarget,edStartDay,edEndDate,edCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__target);

        //cố định hướng dọc
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        Anhxa();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               String target    = edYouTarget.getText().toString().trim();
               String StartDate = edStartDay.getText().toString().trim();
               String EndDate   = edEndDate.getText().toString().trim();
               String Count     = edCount.getText().toString().trim();

               if(target.isEmpty() || StartDate.isEmpty() || EndDate.isEmpty() || Count.isEmpty()){
                   Toast.makeText(Add_Target.this,"lack of information",Toast.LENGTH_SHORT).show();
               }else{
                   HashMap<String , Object> map = new HashMap<>();
                   map.put("Count",Count);
                   map.put("End Date",EndDate);
                   map.put("Start Date",StartDate);
                   map.put("Target",target);
                   FirebaseDatabase.getInstance().getReference().child("Add_Target").push().setValue(map);
                   FirebaseAuth.getInstance().signOut();
                   Toast.makeText(Add_Target.this,"success",Toast.LENGTH_SHORT).show();
               }
            }
        });
    }

    private void Anhxa() {
        button     = findViewById(R.id.btSumit);
        imageView  = findViewById(R.id.image);
        edCount    = findViewById(R.id.edCount);
        edEndDate  = findViewById(R.id.edEnd);
        edStartDay = findViewById(R.id.edStart);
        edYouTarget= findViewById(R.id.edTargetYou);
    }
}