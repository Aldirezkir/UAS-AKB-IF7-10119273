package com.example.a10119273_daybook;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    private List<Notes> notesList;
    private TextView tv_tanggal,tv_tittle,tv_kategori,tv_desc;
    private NotesOnClickListener listener;

    public NotesAdapter(NotesFragment notesFragment, List<Notes> notesList){
        this.notesList = notesList;
        try {
            this.listener = ((NotesOnClickListener) notesFragment);
        } catch (ClassCastException e) {
            throw new ClassCastException("fragment must implement AdapterCallback.");
        }
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((parent.getContext()));
        View view = inflater.inflate(R.layout.fragment_item, null,false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        holder.tv_tanggal.setText(notesList.get(position).getTanggal());
        holder.tv_tittle.setText(notesList.get(position).getTittle());
        holder.tv_kategori.setText(notesList.get(position).getKategori());
        holder.tv_desc.setText(notesList.get(position).getDesc());
        holder.itemView.setOnClickListener(view -> {
            listener.onClickItem(position);
        });
    }

    @Override
    public int getItemCount() {

        return notesList.size();
    }

    public interface NotesOnClickListener{
        void onClickItem(int position);
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_tanggal,tv_tittle,tv_kategori,tv_desc;
        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_tanggal = itemView.findViewById(R.id.tv_tanggal);
            tv_tittle = itemView.findViewById(R.id.tv_tittle);
            tv_kategori = itemView.findViewById(R.id.tv_kategori);
            tv_desc = itemView.findViewById(R.id.tv_desc);
        }
    }
}

