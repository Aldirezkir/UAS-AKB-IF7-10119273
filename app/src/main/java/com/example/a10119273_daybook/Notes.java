package com.example.a10119273_daybook;
//    10119273
//    ALDI REZEKI RAMDANI
//    IF7
public class Notes {
    private String id;
    private String tanggal;
    private String tittle;
    private String kategori;
    private String desc;

    public Notes(String id,String tanggal, String tittle,String kategori, String desc) {
        this.id = id;
        this.tanggal = tanggal;
        this.tittle = tittle;
        this.kategori = kategori;
        this.desc = desc;
    }
    public String getId() {
        return id;
    }

    public String getTanggal(){return tanggal;}

    public String getTittle() {
        return tittle;
    }

    public String getKategori(){ return kategori; }

    public String getDesc() {
        return desc;
    }

}
