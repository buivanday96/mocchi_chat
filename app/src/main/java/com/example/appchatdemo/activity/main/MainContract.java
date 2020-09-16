package com.example.appchatdemo.activity.main;

public interface MainContract {

    interface Model{

        interface SignOutListener{
            void success();
            void fail(Exception e);
        }

        void resSignOut(SignOutListener listener);
    }

    interface View{
        void onSignOut();
    }

    interface Presenter{
        void requestSignOut();
    }
}
