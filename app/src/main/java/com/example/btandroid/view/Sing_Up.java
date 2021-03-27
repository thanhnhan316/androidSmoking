package com.example.btandroid.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.btandroid.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sing_Up extends AppCompatActivity {

    Button button;
    EditText edName,edPassword,edEmail,edPhoneNumber;
    TextView tvtextView;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing__up);

        //cố định hướng dọc
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        anhxa();
        tvtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = edEmail.getText().toString().trim();
                String name     = edName.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String phone    = edPhoneNumber.getText().toString().trim();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) ){
                    Toast.makeText(Sing_Up.this, "Fill in incomplete information",Toast.LENGTH_SHORT).show();
                }else if(password.length()<8){
                    Toast.makeText(Sing_Up.this, "short password",Toast.LENGTH_SHORT).show();
                }else{
                    signUpUser(email,password);
                }
            }
        });
    }

    private void signUpUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Sing_Up.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Sing_Up.this,"Account created successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sing_Up.this,login.class));
                    finish();
                }else {
                    Toast.makeText(Sing_Up.this,"failure",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void anhxa() {
        button        = findViewById(R.id.button);
        edEmail       = findViewById(R.id.edemail);
        edPassword    = findViewById(R.id.edpassword);
        edPhoneNumber = findViewById(R.id.edphone);
        edName        = findViewById(R.id.edname);
        tvtextView    = findViewById(R.id.textview);
        auth          = FirebaseAuth.getInstance();
    }
}