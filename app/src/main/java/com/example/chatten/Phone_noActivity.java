package com.example.chatten;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatten.databinding.ActivityPhoneNoBinding;
import com.google.firebase.auth.FirebaseAuth;

public class Phone_noActivity extends AppCompatActivity {
   ActivityPhoneNoBinding binding;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPhoneNoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

      /* if(auth.getCurrentUser() != null) {
            Intent intent = new Intent(Phone_noActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }*/
        getSupportActionBar().hide();
        binding.phoneBox.requestFocus();
        binding.continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Phone_noActivity.this,OtpActivity.class);
                intent.putExtra("phoneNumber",binding.phoneBox.getText().toString());
                startActivity(intent);
            }
        });

    }
}