package com.example.chatten.Extras;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatten.MainActivity;
import com.example.chatten.R;
import com.example.chatten.User;
import com.example.chatten.databinding.ActivityProfile2Binding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class Profile extends AppCompatActivity {
    ActivityProfile2Binding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityProfile2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();


        storage= FirebaseStorage.getInstance();
        auth= FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
            }
        });

        database.getReference().child("users").child(auth.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user= snapshot.getValue(User.class);
                Picasso.get().load(user.getProfileImage()).placeholder(R.drawable.avatar).into(binding.profile);
                binding.editBio.setText(user.getBio());
                binding.editName.setText(user.getName());

                HashMap<String,Object> obj =new HashMap<>();
                obj.put("profileImage",user.getProfileImage());
                database.getReference().child("users").child(auth.getUid()).updateChildren(obj);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}