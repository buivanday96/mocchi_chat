package com.example.appchatdemo.Utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.core.content.ContextCompat;

import com.example.appchatdemo.R;
import com.example.appchatdemo.activity.login.LoginActivity;

public class AnimUtils {

    public static Animation animTop(Context context, View view){
       Animation topAnim = AnimationUtils.loadAnimation(context, R.anim.top_anim);
       view.setAnimation(topAnim);
       return topAnim;
    }

    public static Animation animBot(Context context, View view){
        Animation topAnim = AnimationUtils.loadAnimation(context, R.anim.bot_anim);
        view.setAnimation(topAnim);
        return topAnim;
    }

    public static Animation animHide(Context context, final View view){
        Animation hideAnim = AnimationUtils.loadAnimation(context,R.anim.blink_anim);

        hideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                clearAnim(view);
                view.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        hideAnim.setRepeatMode(Animation.RESTART);
        view.setAnimation(hideAnim);
        return hideAnim;
    }

    public static void animHide(Context context,View view,int repeatMode){
        Animation hideAnim = AnimationUtils.loadAnimation(context,R.anim.blink_anim);
        hideAnim.setRepeatMode(repeatMode);
        view.setAnimation(hideAnim);
    }

    public static void clearAnim(View view){
        view.animate().cancel();
        view.clearAnimation();
    }

    public static void toggle(View view, ViewGroup viewGroup, int idView, boolean show) {
        Transition transition = new Fade();
        transition.setDuration(1600);
        transition.addTarget(idView);

        TransitionManager.beginDelayedTransition(viewGroup, transition);
        view.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    public static Animation shake(Context context,View view){
        Animation shakeAnim = AnimationUtils.loadAnimation(context, R.anim.shake_anim);
        view.setAnimation(shakeAnim);
        return shakeAnim;
    }

    public static void initAnimTransition(Activity activity) {
        Fade fade = new Fade();
        //fade.excludeTarget(decor.findViewById(R.id.action))
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        activity.getWindow().setEnterTransition(fade);
        activity.getWindow().setExitTransition(fade);
    }

    public static void animShake(View view,Activity activity) {
        Animation animation = AnimUtils.shake(activity, view);
        view.setBackground(ContextCompat.getDrawable(activity, R.drawable.invalidated_input_field));
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.startAnimation(animation);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.clearAnimation();
                    }
                }, 2000);
            }
        });
    }

}
