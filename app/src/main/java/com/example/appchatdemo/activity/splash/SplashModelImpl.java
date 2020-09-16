package com.example.appchatdemo.activity.splash;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashModelImpl implements SplashContract.Model {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public SplashModelImpl() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Override
    public void resCheckLoginAccount(LoginFirebaseListener listener) {
        if (mUser != null){
            listener.login();
        }else{
            listener.logout();
        }
    }
}
