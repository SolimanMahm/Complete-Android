package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.chatapp.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;

    private FirebaseAuth auth;

    private ArrayList<View> viewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewList.add(binding.registerEdtEmail);
        viewList.add(binding.registerEdtPassword);
        viewList.add(binding.btnLogin);
        viewList.add(binding.registerBtn);

        auth = FirebaseAuth.getInstance();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
            }
        });

        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNotEmpty(binding.registerEdtEmail) || Utils.isNotEmpty(binding.registerEdtPassword)) {
                    return;
                }
                registerUser();
            }
        });

    }

    private void registerUser() {
        Utils.setLoading(true, viewList, binding.linearProgressBar);
        auth.createUserWithEmailAndPassword(
                binding.registerEdtEmail.getText().toString().trim(),
                binding.registerEdtPassword.getText().toString().trim()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                Utils.setLoading(false, viewList, binding.linearProgressBar);
                if (task.isSuccessful()) {
                    startActivity(new Intent(getBaseContext(), ChatActivity.class)
                            .putExtra("email", binding.registerEdtEmail.getText().toString().trim()));
                    finish();
                } else {
                    try {
                        Utils.getErrorMessage(RegisterActivity.this, ((FirebaseAuthException) task.getException()).getErrorCode());
                    } catch (Exception e) {
                        Utils.showToast(RegisterActivity.this, R.string.ERROR_NETWORK);
                    }
                }
            }
        });
    }

}