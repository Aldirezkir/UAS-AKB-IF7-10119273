package com.example.a10119273_daybook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class InputNotes extends AppCompatActivity {
    //    10119273
//    ALDI REZEKI RAMDANI
//    IF7
    private NotesInterface notesInterface;
    private EditText et_tanggal, et_tittle, et_kategori, et_desc;
    private Button btn_save;
    private Intent intent;
    private boolean TAG = true;
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_notes);
        init();

        intent = getIntent();

        if(intent.getStringExtra("id") != null){
            et_tanggal.setText(intent.getStringExtra("tanggal"));
            et_tittle.setText(intent.getStringExtra("tittle"));
            et_kategori.setText(intent.getStringExtra("kategori"));
            et_desc.setText(intent.getStringExtra("desc"));
            TAG = false;
        } else{
            TAG = true;
        }
        btn_save.setOnClickListener(view -> {
            save();
        });
    }
    private void init(){
        et_tanggal = findViewById(R.id.et_tanggal);
        et_tanggal.setText(formatter.format(date).toString());
        et_tittle = findViewById(R.id.et_tittle);
        et_kategori = findViewById(R.id.et_kategori);
        et_desc = findViewById(R.id.et_desc);
        btn_save = findViewById(R.id.btn_save2);

    }

    private void tes123(){
        System.out.println("ini 123");
        notesInterface = new Notesimp(this);
        Notes notes1 = new Notes(
                generateTextRandom(),
                et_tanggal.getText().toString(),
                et_tittle.getText().toString(),
                et_kategori.getText().toString(),
                et_desc.getText().toString()
        );
        if(notesInterface.create(notes1)){
            System.out.println("success");
            Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                finish();
        }else{
            System.out.println("gagal");
        }

    }

    private void save(){
        notesInterface = new Notesimp(this);
        System.out.println("savenya");
        if (TAG){
            System.out.println("coba");
            Notes notes1 = new Notes(
                    generateTextRandom(),
                    et_tanggal.getText().toString(),
                    et_tittle.getText().toString(),
                    et_kategori.getText().toString(),
                    et_desc.getText().toString()
            );
            if(notesInterface.create(notes1)){
                Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Notes notes2 = new Notes(
                    intent.getStringExtra("id"),
                    et_tittle.getText().toString(),
                    et_tanggal.getText().toString(),
                    et_kategori.getText().toString(),
                    et_desc.getText().toString()
            );

            if(notesInterface.update(notes2)){
                Toast.makeText(this, "Berhasil Diubah", Toast.LENGTH_SHORT).show();
//                finish();
            }
        }
    }
    private static String generateTextRandom(){
        byte[] array = new byte[5];
        new Random().nextBytes(array);
        return new String(array, Charset.forName("UTF-8"));
    }
}