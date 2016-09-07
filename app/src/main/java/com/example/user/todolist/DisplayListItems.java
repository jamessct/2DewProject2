package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by user on 06/09/2016.
 */
public class DisplayListItems extends AppCompatActivity {

    Button mAddNewItem;
    ListView mListView;

    DatabaseHandler db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MyApp:", "back from super.onCreate()");
        setContentView(R.layout.display_list_items);

        mAddNewItem = (Button)findViewById(R.id.another_button);
        mListView = (ListView)findViewById(R.id.list_data);

        db = ((MainApplication)getApplication()).db;

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringFromArrayList());

        mListView.setAdapter(adapter);

        mAddNewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayListItems.this, IndividualList.class);

                startActivity(intent);
            }
        });
    }

    private ArrayList<String> stringFromArrayList() {
        ArrayList<String> entries = new ArrayList<String>();

        ArrayList<ListItems> items = db.getAllEntries();
        for (ListItems item : items) {
            String itemToAdd = item.getItems();

            entries.add(itemToAdd);
        }
        return entries;
    }
}
