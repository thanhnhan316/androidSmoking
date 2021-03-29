package com.example.btandroid.model;

import android.util.Log;
import android.util.Patterns;

public class UserSignUp {

    private String userName;
    private String passWord;
    private String email;
    private String phoneNumber;

    public UserSignUp(String userName, String passWord, String email, String phoneNumber) {
        this.userName = userName;
        this.passWord = passWord;
        this.email    = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //Kiem tra data co dung khong
    public boolean isEmailValid(){
        boolean flag =  Patterns.EMAIL_ADDRESS.matcher(getEmail().trim()).matches();
        String text = email;
        Log.d("Email",text);
        return flag;
    }
    public boolean isPassword(){
       return getPassWord().length()>=8;
    }
}
