package com.example.user.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06/09/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "listDB";
    public static final String TABLE_ENTRIES = "entries";
    public static final String KEY_ID = "id";
    public static final String KEY_ITEMS = "items";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_ENTRIES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_ITEMS + " TEXT"
                 + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENTRIES);

        onCreate(db);
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


}
