package com.example.user.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by user on 06/09/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "listDB";

    public static final String TABLE_ENTRIES = "entries";
    public static final String ENTRIES_ID = "id";
    public static final String KEY_ITEMS = "items";

    public static final String TABLE_LISTS = "lists";
    public static final String LISTS_ID = "id";
    public static final String KEY_TITLES = "title";

    private Cursor c;


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_ENTRIES_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
                + ENTRIES_ID + " INTEGER PRIMARY KEY," + KEY_ITEMS + " TEXT"
                + ")";
        String CREATE_LISTS_TABLE = "CREATE TABLE " + TABLE_LISTS + "("
                + LISTS_ID + " INTEGER PRIMARY KEY," + KEY_TITLES + " TEXT"
                + ")";
        db.execSQL(CREATE_ENTRIES_TABLE);
        db.execSQL(CREATE_LISTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LISTS);

        onCreate(db);
    }

    public void addEntry(ListItems listItems) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ITEMS, listItems.getItems());

        db.insert(TABLE_ENTRIES, null, values);
        db.close();
    }

    public void addEntry2(ListTitles listTitles) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLES, listTitles.getTitles());

        db.insert(TABLE_LISTS, null, values);
        db.close();
    }

    public ListItems getListItems(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ENTRIES, new String[] { ENTRIES_ID,
                        KEY_ITEMS }, ENTRIES_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        ListItems listItems = new ListItems(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return listItems;
    }

    public ListTitles getListTitles(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LISTS, new String[] { LISTS_ID,
                        KEY_TITLES }, LISTS_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        ListTitles listTitles = new ListTitles(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1));
        return listTitles;
    }

    public void deleteList(ListTitles listTitle) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LISTS, LISTS_ID + " = ?",
                new String[] { String.valueOf(listTitle.getID())});
        db.close();
    }

    public ArrayList<ListItems> getAllEntries() {
        ArrayList<ListItems> itemsList = new ArrayList<ListItems>();

        String selectQuery = "SELECT * FROM " + TABLE_ENTRIES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                ListItems listitems = new ListItems();
                listitems.setID(Integer.parseInt(cursor.getString(0)));
                listitems.setEntry(cursor.getString(1));
                itemsList.add(listitems);
            }
            while (cursor.moveToNext());
        }
    return itemsList;
    }

    public ArrayList<ListTitles> getAllLists() {
        ArrayList<ListTitles> listTitle = new ArrayList<ListTitles>();

        String selectQuery = "SELECT * FROM " + TABLE_LISTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()) {
            do {
                ListTitles listTitles = new ListTitles();
                Log.d("null_check - ListTitles", "" + listTitles);
                listTitles.setID(Integer.parseInt(cursor.getString(0)));
                String hopefullyTheTitle = cursor.getString(1);
                Log.d("null_check", hopefullyTheTitle);
                listTitles.setTitle(cursor.getString(1));
                listTitle.add(listTitles);
            }
            while (cursor.moveToNext());
        }
        return listTitle;
    }


}
