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

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    EditText editEmail, editName, editPassword, editConfirmPassword;
    Button btnRegister,btnLogin;
    ProgressDialog loadingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        editEmail = findViewById(R.id.editEmail);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        editConfirmPassword = findViewById(R.id.editConfirmPassword);

        btnRegister = findViewById(R.id.btn_register);
        btnLogin = findViewById(R.id.btn_login);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString();
                String pass = editPassword.getText().toString();

                RegisterUser(email,pass);
            }
        });
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(register.this,login.class);
            startActivity(intent);
        });
    }

    private void RegisterUser(String email, String pass) {
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Masukan Email anda !!!", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(pass)){
            Toast.makeText(this, "Masukan Password anda !!!", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Sedang Registrasi");
            loadingBar.setMessage("Mohon Tunggu");
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(register.this, "Berhasil menambahkan User !!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(register.this, "Registrasi Gagal !!", Toast.LENGTH_SHORT).show();
                    }
                    loadingBar.dismiss();
                }
            });
        }
    }
}