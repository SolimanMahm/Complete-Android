package com.example.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.chatapp.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;

    private FirebaseAuth auth;

    private ArrayList<View> viewList = new ArrayList<View>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewList.add(binding.loginEdtEmail);
        viewList.add(binding.loginEdtPassword);
        viewList.add(binding.loginBtn);
        viewList.add(binding.btnRegister);

        auth = FirebaseAuth.getInstance();

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegisterActivity.class));
                finish();
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isNotEmpty(binding.loginEdtEmail) || Utils.isNotEmpty(binding.loginEdtPassword)) {
                    return;
                }
                loginUser();
            }
        });
    }

    private void loginUser() {
        Utils.setLoading(true, viewList, binding.linearProgressBar);
        auth.signInWithEmailAndPassword(binding.loginEdtEmail.getText().toString().trim(),
                        binding.loginEdtPassword.getText().toString().trim())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Utils.setLoading(false, viewList, binding.linearProgressBar);
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getBaseContext(), ChatActivity.class)
                                    .putExtra("email", binding.loginEdtEmail.getText().toString().trim()));
                            finish();
                        } else {
                            try {
                                Utils.getErrorMessage(LoginActivity.this, ((FirebaseAuthException) task.getException()).getErrorCode());
                            } catch (Exception e) {
                                Utils.showToast(LoginActivity.this, R.string.ERROR_NETWORK);
                            }
                        }
                    }
                });
    }
}