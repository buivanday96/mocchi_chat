package com.example.appchatdemo.activity.login;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginModelImpl implements LoginContract.Model {

    private FirebaseAuth mAuth;

    public LoginModelImpl(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String userName, String pwd, ListenerLogin listenerLogin) {
       mAuth.signInWithEmailAndPassword(userName,pwd)
               .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                       if (task.isSuccessful()){
                           listenerLogin.resSuccess();
                       }else{
                           listenerLogin.resFail(task.getException());
                       }
                   }
               });
    }
}
