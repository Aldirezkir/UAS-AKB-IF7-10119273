package com.example.a10119273_daybook;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7

    Button btnLogout;
    private View vhome;
    FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vhome =  inflater.inflate(R.layout.fragment_home, container, false);
        mAuth = FirebaseAuth.getInstance();
        init();
        return vhome;
    }

    private void init() {

        btnLogout = vhome.findViewById(R.id.btn_logout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                signOutUser();
            }
        });
    }

    private void signOutUser() {
        Intent intent = new Intent((MainActivity) getActivity(), login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        Toast.makeText(getActivity(), "Logout Berhasil", Toast.LENGTH_SHORT).show();
    }
}