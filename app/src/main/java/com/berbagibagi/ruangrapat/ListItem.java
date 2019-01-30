package com.berbagibagi.ruangrapat;
import java.io.Serializable;

public class ListItem implements Serializable{

    private String ruang;
    private String nama;
    private String tanggal;
    private String waktu;
    private String hasil;
    private String perihal;
    private String notulis;
    private String tampungHasil;

    public ListItem( String ruang, String nama, String tanggal, String waktu, String hasil, String perihal, String notulis, String tampungHasil)
    {
        this.ruang = ruang;
        this.nama = nama;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.hasil = hasil;
        this.perihal = perihal;
        this.notulis = notulis;
        this.tampungHasil = tampungHasil;
    }

    public String getRuang(){
        return ruang;
    }
    public String getNama(){
        return nama;
    }
    public String getTanggal(){
        return tanggal;
    }
    public String getWaktu(){
        return waktu;
    }
    public String getHasil(){
        return hasil;
    }
    public String getPerihal(){
        return perihal;
    }
    public String getNotulis(){
        return notulis;
    }
    public String getTampungHasil(){
        return tampungHasil;
    }
}