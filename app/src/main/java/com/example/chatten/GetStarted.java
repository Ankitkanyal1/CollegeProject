package com.example.chatten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.chatten.databinding.ActivityGetStartedBinding;
import com.google.firebase.auth.FirebaseAuth;

public class GetStarted extends AppCompatActivity {
    FirebaseAuth auth;
    ActivityGetStartedBinding binding;
    private static int SPLASH_SCREEN_TIME_OUT=5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGetStartedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        getSupportActionBar().hide();

     /*   if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(GetStarted.this, MainActivity.class);
            startActivity(intent);
            finish();
        }*/


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(GetStarted.this, SlideActivity.class);
                startActivity(intent);
                finish();
                }
            }, SPLASH_SCREEN_TIME_OUT);

        Animation animation= AnimationUtils.loadAnimation(GetStarted.this,R.anim.animation2);
        binding.textView4.startAnimation(animation);

        Animation animation2= AnimationUtils.loadAnimation(GetStarted.this,R.anim.animation1);
        binding.textView5.startAnimation(animation2);
        }


        //binding.button.setOnClickListener(new View.OnClickListener() {
          //  @Override
          //  public void onClick(View view) {


          //  }
     //   });
}

