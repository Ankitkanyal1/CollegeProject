
package com.example.chatten;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.inputmethod.InputMethodManager;
        import android.widget.Toast;

        import com.example.chatten.databinding.ActivityOtpBinding;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.FirebaseException;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
        import com.google.firebase.auth.PhoneAuthCredential;
        import com.google.firebase.auth.PhoneAuthOptions;
        import com.google.firebase.auth.PhoneAuthProvider;
        import com.mukesh.OnOtpCompletionListener;

        import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {

    ActivityOtpBinding binding;
    FirebaseAuth auth;

    String verificationId;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        dialog = new ProgressDialog(this);
        dialog.setMessage("Sending OTP..");
        dialog.setCancelable(false);
        dialog.show();

        auth = FirebaseAuth.getInstance();

        getSupportActionBar().hide();



        String phoneNumber = getIntent().getStringExtra("phoneNumber");

        binding.phoneLbl.setText("Verify "+phoneNumber);

        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(OtpActivity.this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }

                    @Override
                    public void onCodeSent(@NonNull String verifyId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(verifyId, forceResendingToken);
                        dialog.dismiss();

                        verificationId = verifyId;

                        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                        binding.OtpView.requestFocus();
                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);

        binding.OtpView.setOtpCompletionListener(new OnOtpCompletionListener() {
            @Override
            public void onOtpCompleted(String otp) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, otp);

                auth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(OtpActivity.this, "LoggedIn.", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(OtpActivity.this, ProfileActivity.class);
                            startActivity(intent);
                            finishAffinity();
                        } else {
                            Toast.makeText(OtpActivity.this, "Failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
}
