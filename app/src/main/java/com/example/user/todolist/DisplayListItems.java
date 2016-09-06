package com.example.user.todolist;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 06/09/2016.
 */
public class DisplayListItems extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_items);

        DatabaseHandler db = new DatabaseHandler(this);

        ListView listView = (ListView)findViewById(R.id.list_data);



//        ListItems[] items = {
//                new ListItems(1, "Milk")
//        };

        ArrayList<ListItems> items = db.getAllEntries();

        for (ListItems cn : items) {
            String log = "Id: " + cn.getID() + ", Entries: " + cn.getEntry();
        }



        ArrayAdapter<ListItems> adapter = new ArrayAdapter<ListItems>(this, android.R.layout.simple_list_item_1, items);

        listView.setAdapter(adapter);
    }
}
