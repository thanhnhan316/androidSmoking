package com.example.btandroid.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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

import com.example.btandroid.model.UserSignUp;
import com.example.btandroid.R;
import com.example.btandroid.databinding.ActivitySingUpBinding;
import com.example.btandroid.viewModel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Sing_Up extends AppCompatActivity {

    Button btButton;
    EditText edName,edPassword,edEmail,edPhoneNumber;
    TextView tvtextView;
    FirebaseAuth auth;

    private UserViewModel UserViewModel;
    private ActivitySingUpBinding binding;

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

        UserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        binding = DataBindingUtil.setContentView(Sing_Up.this, R.layout.activity_sing__up);
        binding.setLifecycleOwner(this);
        binding.setSignViewModel(UserViewModel);

        UserSignUp muserSignUp = null;
        toDataSignUp(muserSignUp);


        UserViewModel.getUserSignUpMutableLiveData().observe(this, new Observer<UserSignUp>() {
            @Override
            public void onChanged(UserSignUp muserSignUp) {
                //Lấy data từ view
                if (TextUtils.isEmpty(muserSignUp.getEmail()) || TextUtils.isEmpty(muserSignUp.getPhoneNumber())
                        || TextUtils.isEmpty(muserSignUp.getPassWord()) || TextUtils.isEmpty(muserSignUp.getUserName())) {
                    Toast.makeText(Sing_Up.this,"Bạn cần điền thông tin đầy đủ thông tin ",Toast.LENGTH_SHORT).show();
                } else if (!muserSignUp.isEmailValid()) {
                    binding.edemail.setError("Email không hợp lệ");
                    binding.edemail.requestFocus();
                } else if (!muserSignUp.isPassword()) {
                    binding.edpassword.setError("Password Không an toàn");
                    binding.edpassword.requestFocus();
                }else{
                    signUpUser(muserSignUp.getEmail().trim(),muserSignUp.getPassWord(),muserSignUp.getUserName(),muserSignUp.getPhoneNumber());
                }
            }
        });

            tvtextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void signUpUser(String email, String passWord,String userName,String phone) {

        auth.createUserWithEmailAndPassword(email,passWord).addOnCompleteListener(Sing_Up.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    Toast.makeText(Sing_Up.this,"Đăng Kí Thành Công",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Sing_Up.this,login.class));
                    finish();
                    pushSignUpToFirebase(email,passWord,userName,phone);
                }else{
                    Toast.makeText(Sing_Up.this,"Đăng kí không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void pushSignUpToFirebase(String email, String passWord, String userName, String phone) {
        HashMap <String ,Object> map = new HashMap<>();
        map.put("Phone : ",phone);
        map.put("Email : ",email);
        map.put("password : ",passWord);
        map.put("userName : ",userName);

        FirebaseDatabase.getInstance().getReference().child("User").child(auth.getUid()).setValue(map);
    }

    private void toDataSignUp(UserSignUp userSignUp) {
        String name     = edName.getText().toString();
        String password = edPassword.getText().toString().trim();
        String email    = edEmail.getText().toString().trim();
        String phone    = edPhoneNumber.getText().toString().trim();

        userSignUp = new UserSignUp(name,password,email,phone);
    }

    private void anhxa() {
        btButton      = findViewById(R.id.button);
        edEmail       = findViewById(R.id.edemail);
        edPassword    = findViewById(R.id.edpassword);
        edPhoneNumber = findViewById(R.id.edphone);
        edName        = findViewById(R.id.edname);
        tvtextView    = findViewById(R.id.textview);
        auth          = FirebaseAuth.getInstance();
    }
}

//                                UP DATA SIGN UP LEN FIREBASE
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email    = edEmail.getText().toString().trim();
//                String name     = edName.getText().toString().trim();
//                String password = edPassword.getText().toString().trim();
//                String phone    = edPhoneNumber.getText().toString().trim();
//
//                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(name) ){
//                    Toast.makeText(Sing_Up.this, "Fill in incomplete information",Toast.LENGTH_SHORT).show();
//                }else if(password.length()<8){
//                    Toast.makeText(Sing_Up.this, "short password",Toast.LENGTH_SHORT).show();
//                }else{
//                    signUpUser(email,password);
//                }
//            }
//        });
//    }
//
//    private void signUpUser(String email, String password) {
//        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Sing_Up.this,new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    Toast.makeText(Sing_Up.this,"Account created successfully",Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(Sing_Up.this,login.class));
//                    finish();
//                }else {
//                    Toast.makeText(Sing_Up.this,"failure",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });