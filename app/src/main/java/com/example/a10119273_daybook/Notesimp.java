package com.example.a10119273_daybook;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Notesimp extends SQLiteOpenHelper implements NotesInterface{
    public Notesimp(Context context){
        super(context, "db_notes",null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase sql) {
        sql.execSQL("CREATE TABLE tbl_notes(id TEXT,tanggal TEXT, tittle TEXT,kategori TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE tbl_notes");
    }

    @Override
    public Cursor read() {
        SQLiteDatabase sql = getReadableDatabase();
        return sql.rawQuery("SELECT * FROM tbl_notes",null);
    }

    @Override
    public boolean create(Notes notes) {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("INSERT INTO tbl_notes VALUES('"+notes.getId()+"','"+notes.getTanggal()+"','"+notes.getTittle()+"','"+notes.getKategori()+"','"+notes.getDesc()+"')");
        return true;
    }

    @Override
    public boolean update(Notes notes) {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("UPDATE tbl_notes SET tittle='"+notes.getTittle()+"',tanggal='"+notes.getTanggal()+"',kategori='"+notes.getKategori()+"',description='"+notes.getDesc()+"' WHERE id='"+notes.getId()+"'");
        return true;
    }

    @Override
    public boolean delete(String id) {
        SQLiteDatabase sql = getWritableDatabase();
        sql.execSQL("DELETE FROM tbl_notes WHERE id='"+id+"'");
        return true;
    }
}
