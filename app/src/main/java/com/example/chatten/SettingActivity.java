package com.example.chatten;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.chatten.Extras.Help;
import com.example.chatten.Extras.PrivacyPolicy;
import com.example.chatten.databinding.ActivitySettingBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {

    ActivitySettingBinding binding;
    FirebaseAuth auth;
    FirebaseStorage storage;
    FirebaseDatabase database;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dialog=new ProgressDialog(this);
        dialog.setMessage("Updating Please Wait!...");
        dialog.setCancelable(false);


        getSupportActionBar().hide();



        storage=FirebaseStorage.getInstance();
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bio=binding.editBio.getText().toString();
                String name=binding.editName.getText().toString();


                HashMap<String,Object> obj =new HashMap<>();
                obj.put("name",name);
                obj.put("bio",bio);

                database.getReference().child("users").child(auth.getUid()).updateChildren(obj);

                Toast.makeText(SettingActivity.this, "Profile is Updated...", Toast.LENGTH_SHORT).show();

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

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, 28);
            }
        });

        binding.privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SettingActivity.this, PrivacyPolicy.class);
                startActivity(intent);
                finish();
            }
        });

        binding.invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this, "This Feature is coming soon...", Toast.LENGTH_SHORT).show();
            }
        });

        binding.about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this, Help.class));
            }
        });

        binding.notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SettingActivity.this,"This Feature is not available at this time...",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialog.show();
        if(data.getData()!=null){
            Uri sFile=data.getData();
            binding.profile.setImageURI(sFile);

            StorageReference reference=storage.getReference().child("Profiles").child(auth.getUid());
            reference.putFile(sFile).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("users").child(auth.getUid()).child("profileImage").setValue(uri.toString());

                        }
                    });
                    dialog.dismiss();
                    Toast.makeText(SettingActivity.this, "Updated", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }


}