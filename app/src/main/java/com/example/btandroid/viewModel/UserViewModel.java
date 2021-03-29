package com.example.btandroid.viewModel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.btandroid.model.UserSignUp;

public class UserViewModel extends ViewModel {

    public MutableLiveData<String> email    = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();
    public MutableLiveData<String> name     = new MutableLiveData<>();
    public MutableLiveData<String> phone    = new MutableLiveData<>();

    private MutableLiveData<UserSignUp> userSignUpMutableLiveData;

    public MutableLiveData<UserSignUp> getUserSignUpMutableLiveData() {
        if(userSignUpMutableLiveData == null){
            userSignUpMutableLiveData = new MutableLiveData<>();
        }
        return userSignUpMutableLiveData;
    }

    public void onClick(View view){
        UserSignUp userSignUp = new UserSignUp(name.getValue(),password.getValue(),email.getValue(),phone.getValue());
        userSignUpMutableLiveData.setValue(userSignUp);
    }
}
