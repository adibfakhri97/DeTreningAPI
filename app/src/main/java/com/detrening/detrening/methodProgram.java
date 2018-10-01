package com.detrening.detrening;

import com.detrening.detrening.Profil.ProfilInfo;

/**
 * Created by adibf on 25/04/2018.
 */

public class methodProgram {
    private int mGif;
    private String namaGerakan;
    private String deskripsiGerakan;
    private String jumlah;

    public methodProgram(String namaGerakan,int mGif,  String deskripsiGerakan, String jumlah){
        this.namaGerakan = namaGerakan;
        this.mGif = mGif;
        this.deskripsiGerakan = deskripsiGerakan;
        this.jumlah = jumlah;
    }

    public String getJumlah() {
        return jumlah;
    }

    public int getmGif() {
        return mGif;
    }

    public String getNamaGerakan() {
        return namaGerakan;
    }

    public String getDeskripsiGerakan() {
        return deskripsiGerakan;
    }
}
