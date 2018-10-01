package com.detrening.detrening.Workout;

/**
 * Created by adibf on 20/04/2018.
 */

public class methodWorkout {
    private int id, gambar;
    private String nama, deskripsi;

    public methodWorkout(int id, String nama, int gambar, String deskripsi){
        this.id = id;
        this.nama = nama;
        this.gambar = gambar;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public int getId(){
        return id;
    }


    public String getNama() {
        return nama;
    }

    public int getGambar() {
        return gambar;
    }

}
