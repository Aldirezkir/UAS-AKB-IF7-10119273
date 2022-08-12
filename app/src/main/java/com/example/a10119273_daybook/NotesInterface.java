package com.example.a10119273_daybook;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7
import android.database.Cursor;

public interface NotesInterface {
    public Cursor read();
    public boolean create(Notes notes);
    public boolean update(Notes notes);
    public boolean delete(String id);
}
