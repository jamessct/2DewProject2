package com.example.user.todolist;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by user on 06/09/2016.
 */
public class DisplayListItems extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView mListEntries;
    private Button mAddNewEntry;

    private ListAdapter mListAdapter;
    private ArrayList<ListEntries> pojoArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_list_items);

        mListEntries = (ListView) findViewById(R.id.list_data);
        mListEntries.setOnItemClickListener(this);

        mAddNewEntry = (Button) findViewById(R.id.another_button);
        mAddNewEntry.setOnClickListener(this);

        pojoArrayList = new ArrayList<ListEntries>();

    }
}





