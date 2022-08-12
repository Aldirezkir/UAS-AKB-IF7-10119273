package com.example.a10119273_daybook;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment implements NotesAdapter.NotesOnClickListener {
    private RecyclerView rv_notes;
    private List<Notes> notesList;
    private NotesInterface notesInterface;
    private NotesAdapter notesAdapter;
    private View sample;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        sample = inflater.inflate(R.layout.fragment_notes, container, false);
        init();
        return sample;

    }

    private void init() {
        rv_notes = sample.findViewById(R.id.rv_notes);
        rv_notes.setLayoutManager(new LinearLayoutManager(getContext()));
        System.out.println(rv_notes.findViewById(R.id.fab));
        Intent intent = new Intent((MainActivity) getActivity(), InputNotes.class);
        sample.findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        read();
    }

    private void  read(){
        notesList = new ArrayList<>();
        notesInterface = new Notesimp(getContext());
        Cursor cursor = notesInterface.read();
        if(cursor.moveToFirst()){
            do {
                Notes notes = new Notes(
                        cursor.getString(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4)

                );

                notesList.add(notes);
            } while (cursor.moveToNext());
        }
        notesAdapter = new NotesAdapter(this, notesList);
        rv_notes.setAdapter(notesAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        read();
    }

    @Override
    public void onClickItem(int position) {
        final Notes notes = notesList.get(position);
        System.out.println("cek");
        String[] pilihan = {"Ubah", "Hapus"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Pilihan");
        builder.setItems(pilihan, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:
                        Intent intent = new Intent(getActivity(), InputNotes.class);
                        intent.putExtra("id", notes.getId());
                        intent.putExtra("tanggal", notes.getTanggal());
                        intent.putExtra("tittle", notes.getTittle());
                        intent.putExtra("kategori", notes.getKategori());
                        intent.putExtra("desc", notes.getDesc());
                        startActivity(intent);
                        break;
                    case 1:
                        AlertDialog.Builder builderDelete = new AlertDialog.Builder(getContext());
                        builderDelete.setMessage("Yakin Ingin Dihapus ?");
                        builderDelete.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(notesInterface.delete(notes.getId())){
                                    Toast.makeText(getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                    read();
                                }
                            }
                        });
                        builderDelete.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builderDelete.show();
                        break;
                }
            }
        });
        builder.show();
    }
}
