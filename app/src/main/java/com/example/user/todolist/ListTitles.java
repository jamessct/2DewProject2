package com.example.user.todolist;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 07/09/2016.
 */
public class ListTitles {
    private int id;
    private String titles;

    public ListTitles() {

    }

    public ListTitles(int id, String titles) {
        this.id = id;
        this.titles = titles;
    }

    public ListTitles(String titles) {

        this.titles = titles;
    }

    public String toString() {
        return this.id + ". " + this.titles;
    }

    public int getID() {
        return this.id;
    }

    public String getTitles() {
        return this.titles;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setEntry(String entries) {
        this.titles = titles;
    }
}

