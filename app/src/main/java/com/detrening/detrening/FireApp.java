package com.detrening.detrening;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by adibf on 4/6/2018.
 */

public class FireApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
