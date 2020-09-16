package com.example.appchatdemo.activity.main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainModel implements MainContract.Model {

    private FirebaseAuth mAuth;
    private FirebaseUser mUser;

    public MainModel() {
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
    }

    @Override
    public void resSignOut(SignOutListener listener) {
        if (mUser != null){
            mAuth.signOut();
            listener.success();
        }
    }
}
