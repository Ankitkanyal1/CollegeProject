package com.example.chatten.Extras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatten.SettingActivity;
import com.example.chatten.databinding.ActivityHelpBinding;


public class Help extends AppCompatActivity {
    ActivityHelpBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHelpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Help.this, SettingActivity.class);
                startActivity(intent);
            }
        });

        binding.textView12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Help.this,PrivacyPolicy.class);
                startActivity(intent);
            }
        });



    }
}