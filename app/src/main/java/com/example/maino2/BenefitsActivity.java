package com.example.maino2;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maino2.databinding.ActivityBenefitsBinding;

public class BenefitsActivity extends drawerBaseActivity {

    ActivityBenefitsBinding activityBenefitsBinding;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityBenefitsBinding = ActivityBenefitsBinding.inflate(getLayoutInflater());
        setContentView(activityBenefitsBinding.getRoot());
        allocateActitvityTitle("Benefits");

        textView1=findViewById(R.id.number1);
        textView2=findViewById(R.id.number2);
        textView3=findViewById(R.id.number3);
        textView4=findViewById(R.id.number4);
        textView5=findViewById(R.id.number5);
        textView6=findViewById(R.id.number6);
        startCountAnimation1();
        startCountAnimation2();
        startCountAnimation3();
        startCountAnimation4();
        startCountAnimation5();
        startCountAnimation6();

    }

    private void startCountAnimation1(){
        ValueAnimator valueanimator1=ValueAnimator.ofInt(0,70);

        valueanimator1.setDuration(8000);
        valueanimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animatedValue = (int) valueanimator1.getAnimatedValue();
                textView1.setTextColor(R.color.gradiantpurlpe);
                textView1.setText(animatedValue+"%");
            }
        });
        valueanimator1.start();
    }

    private void startCountAnimation2() {
        ValueAnimator valueanimator2 = ValueAnimator.ofInt(0, 85);

        valueanimator2.setDuration(8000);
        valueanimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animvalue = (int) valueanimator2.getAnimatedValue();
                textView2.setText(animvalue+"%");
            }
        });
        valueanimator2.start();
    }


    private void startCountAnimation3() {
        ValueAnimator valueanimator3 = ValueAnimator.ofInt(0, 38);

        valueanimator3.setDuration(8000);
        valueanimator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animvalue = (int) valueanimator3.getAnimatedValue();
                textView3.setText(animvalue+"%");
            }
        });
        valueanimator3.start();
    }

    private void startCountAnimation4() {
        ValueAnimator valueanimator4 = ValueAnimator.ofInt(0, 3);

        valueanimator4.setDuration(10000);
        valueanimator4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animvalue = (int) valueanimator4.getAnimatedValue();
                textView4.setText(animvalue+"X");

            }
        });
        valueanimator4.start();
    }

    private void startCountAnimation5() {
        ValueAnimator valueanimator5 = ValueAnimator.ofInt(0, 8);

        valueanimator5.setDuration(8000);
        valueanimator5.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animvalue = (int) valueanimator5.getAnimatedValue();
                textView5.setText(animvalue+"+");
            }
        });
        valueanimator5.start();
    }


    private void startCountAnimation6() {
        ValueAnimator valueanimator6 = ValueAnimator.ofInt(0,46);

        valueanimator6.setDuration(8000);
        valueanimator6.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(@NonNull ValueAnimator animation) {
                int animvalue = (int) valueanimator6.getAnimatedValue();
                textView6.setText(animvalue+"%");
            }
        });
        valueanimator6.start();
    }







}