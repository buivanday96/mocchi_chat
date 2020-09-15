package com.example.appchatdemo.activity.signup;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpModelImpl implements SignUpContract.Model {

    private FirebaseAuth mAuth;

    public SignUpModelImpl() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void resSignUp(String email, String pwd, ListenerSignUp listenerSignUp) {

        mAuth.createUserWithEmailAndPassword(email,pwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            listenerSignUp.onSuccess();
                            return;
                        }

                        if (task.isCanceled()){
                            listenerSignUp.onFail();
                            return;
                        }
                    }
                });
    }
}
