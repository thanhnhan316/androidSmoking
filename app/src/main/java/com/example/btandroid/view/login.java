package com.example.btandroid.view;

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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    Button   button;
    TextView tvforgot,tvsignUp;
    EditText edname,edpassword;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        anhXa();

        tvsignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,Sing_Up.class));
            }
        });

        tvforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(login.this,For_Got_Password.class));
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email    = edname.getText().toString().trim();
                String password = edpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(login.this,"Thông tin chưa đầy đủ",Toast.LENGTH_SHORT).show();
                }else{
                    loginUesr(email, password);
                }
            }
        });

    }

    private void loginUesr(String email, String password) {
        auth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(login.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(login.this,Home.class));
                finish();
            }
        });
    }

    private void anhXa() {
        button     = findViewById(R.id.button);
        tvforgot   = findViewById(R.id.forgot);
        tvsignUp   = findViewById(R.id.createAcc);
        edname     = findViewById(R.id.edname);
        edpassword = findViewById(R.id.edpassword);
        auth       = FirebaseAuth.getInstance();
    }

    //LogIn userUID
    public String userUID(){
        return auth.getUid();
    }
}