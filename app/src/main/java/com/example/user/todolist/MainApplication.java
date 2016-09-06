package com.example.user.todolist;

import android.app.Application;

/**
 * Created by user on 06/09/2016.
 */
public class MainApplication extends Application {

    DatabaseHandler db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = new DatabaseHandler(this);
    }

}
