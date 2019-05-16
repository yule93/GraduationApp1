package com.example.graduationapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {

    ImageView mSplash;
    int mScreenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        mSplash = (ImageView) findViewById(R.id.spicy1);
    }


    @Override
    protected void onResume() {
        super.onResume();

        //화면(Windows) 관련 값 획득 위한 코드
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        mScreenHeight = displaymetrics.heightPixels;

        //startFireTweenAnimation(); // 미리선언 파이어 트윈애니메이션, 트윈애니메이션은 anim에서 모두 선언
        startBlinkingAnimation();
    }

    private void startBlinkingAnimation() {
        Animation blink_animation_anim = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        mSplash.startAnimation(blink_animation_anim);
        blink_animation_anim.setAnimationListener(animationListener);

    }

    Animation.AnimationListener animationListener = new Animation.AnimationListener() {
        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {

            finish();
            startActivity(new Intent(getApplicationContext(), Test1.class));
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }
    };
}
