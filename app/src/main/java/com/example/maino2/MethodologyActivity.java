package com.example.maino2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.ViewFlipper;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.maino2.databinding.ActivityMethodologyBinding;

public class MethodologyActivity extends drawerBaseActivity {

    ActivityMethodologyBinding activityMethodologyBinding;
    ViewFlipper viewFlipper;
    ImageButton buttonleft,buttonright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMethodologyBinding = ActivityMethodologyBinding.inflate(getLayoutInflater());


        setContentView(activityMethodologyBinding.getRoot());
        allocateActitvityTitle("Methodology");

        viewFlipper =findViewById(R.id.viewflipper);
        buttonleft =findViewById(R.id.buttonleft);
        buttonright = findViewById(R.id.buttonright);




    }

    public void buttonLeftClicked(View view) {
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.fullScroll(View.FOCUS_DOWN);

        Animation outAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_to_left);
        viewFlipper.setAnimation(outAnimation);

        // Move to the previous view (slide left)
        viewFlipper.showPrevious();


    }

    public void buttonRightClicked(View view) {
        ScrollView scrollView = findViewById(R.id.scrollView);
        scrollView.fullScroll(View.FOCUS_DOWN);


        Animation outAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_to_right);
        viewFlipper.setAnimation(outAnimation);

        // Move to the next view (slide right)
        viewFlipper.showNext();


    }
}