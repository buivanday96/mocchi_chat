package com.example.appchatdemo.Utils;

import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Window;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.example.appchatdemo.R;

import java.util.Objects;

public class DialogUtils {

    public static Dialog createWaitingDialog(Context context) {
        int themes = android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar;
        Dialog dialog = new Dialog(context, themes);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_waiting);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);

        LottieAnimationView animationView = dialog.findViewById(R.id.animationView);
        animationView.playAnimation();
        animationView.setRepeatMode(LottieDrawable.RESTART);
        //animationView.setMinAndMaxFrame(0,123);
        Log.d("zzz",animationView.getDuration()+"");
        animationView.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

            }
        });
        return dialog;
    }

}
