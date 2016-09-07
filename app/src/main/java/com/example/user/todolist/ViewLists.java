package com.example.user.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by user on 03/09/2016.
 */
public class ViewLists extends AppCompatActivity {
    //    private EditText editTextID;
//    private EditText editTextTitle;
//    private Button btnPrev;
//    private Button btnSave;
//    private Button btnDestroy;
//    private Button btnNext;
    private Button btnAdd;
    ListView mListView;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        db = ((MainApplication) getApplication()).db;

        btnAdd = (Button) findViewById(R.id.btnAdd);
        mListView = (ListView) findViewById(R.id.list_titles);

        ArrayList<String> titles = stringFromArrayList();

        for (int i = 0; i < titles.size(); i++){
            Log.d("null_check", "" + titles.get(i) );
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.single_row_item, R.id.list_entry, stringFromArrayList());

        mListView.setAdapter(adapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewLists.this, IndividualList.class);

                startActivity(intent);
            }
        });
    }


    private ArrayList<String> stringFromArrayList() {
        ArrayList<String> entries = new ArrayList<String>();

        ArrayList<ListTitles> titles = db.getAllLists();
        for (ListTitles title : titles) {
            String itemToAdd = title.getTitles();

            entries.add(itemToAdd);
        }
        return entries;
    }
}