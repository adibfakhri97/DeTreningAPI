package com.detrening.detrening.Profil;

/**
 * Created by adibf on 4/6/2018.
 */

public class AdapterProfile {
    private static final AdapterProfile ourInstance = new AdapterProfile();

    public String nama;
    public String tinggi;
    public String berat;

    public String imageURL;
    public String user;

    public AdapterProfile() {

    }

    public AdapterProfile(String name, String tinggi, String berat, String user, String imageURL) {

        this.nama = name;
        this.tinggi = tinggi;
        this.berat = berat;
        this.imageURL= imageURL;
        this.user = user;
    }

}
