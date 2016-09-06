package com.example.user.todolist;

/**
 * Created by user on 06/09/2016.
 */
public class ListItems {
    private int id;
    private String entries;

    public ListItems() {
        super();
    }

    public ListItems(int id, String entries) {
        super();
        this.id = id;
        this.entries = entries;
    }

    @Override
    public String toString() {
        return this.id + ". " + this.entries;
    }
}
