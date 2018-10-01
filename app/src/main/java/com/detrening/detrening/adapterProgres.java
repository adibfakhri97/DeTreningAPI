package com.detrening.detrening;

/**
 * Created by adibf on 22/04/2018.
 */

public class adapterProgres {
    public String progress;
    public String user;


    public adapterProgres() {

    }

    public adapterProgres(String progress, String user) {

        this.progress = progress;
        this.user = user;
    }

    public String getProgress() {
        return progress;
    }

    public String getUser() {
        return user;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
