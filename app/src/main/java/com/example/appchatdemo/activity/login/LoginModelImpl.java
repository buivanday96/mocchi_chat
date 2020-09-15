package com.example.appchatdemo.activity.login;

public class LoginModelImpl implements LoginContract.Model {


    @Override
    public void login(String userName, String pwd, ListenerLogin listenerLogin) {
        if (userName.equals("bvd") && pwd.equals("123")){
            listenerLogin.resSuccess();
            return;
        }
        listenerLogin.resFail();
    }
}
