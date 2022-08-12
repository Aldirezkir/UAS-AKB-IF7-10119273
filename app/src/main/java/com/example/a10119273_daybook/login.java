package com.example.a10119273_daybook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7

public class login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText editEmail, editPassword;
    Button btnLogin, btnRegister;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);

        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(login.this, MainActivity.class);
//                startActivity(intent);
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                Login(email,password);
            }
        });

        btnRegister.setOnClickListener(view -> {
            Intent intent = new Intent(login.this,register.class);
            startActivity(intent);
        });
    }

    private void Login(String email, String password) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Masukan Email anda !!!", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Masukan Password anda !!!", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Sedang Login");
            loadingBar.setMessage("Mohon Tunggu....");
            loadingBar.show();

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        loadingBar.dismiss();
                        Intent mainActivity = new Intent(login.this, MainActivity.class);
                        startActivity(mainActivity);
                        Toast.makeText(login.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    else {
                        Toast.makeText(login.this, "Gagal Login !!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}