package com.example.user.todolist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
public class ViewLists extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextID;
    private EditText editTextTitle;
    private Button btnPrev;
    private Button btnSave;
    private Button btnDestroy;
    private Button btnNext;
    private Button btnAdd;

//    ListView mListView;


    DatabaseHandler db;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);

        editTextID = (EditText) findViewById(R.id.editTextID);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);

        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDestroy = (Button)findViewById(R.id.btnDestroy);
        btnNext = (Button)findViewById(R.id.btnNext);
        btnAdd = (Button)findViewById(R.id.btnAdd);
//        mListView =(ListView)findViewById(R.id.list_titles);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDestroy.setOnClickListener(this);

        db = ((MainApplication) getApplication()).db;
//        mListView.setAdapter(adapter);



//        c.moveToFirst();
//        showLists();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewLists.this, IndividualList.class);

                startActivity(intent);
            }
        });
    }

//    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringFromArrayList());
//
//    private ArrayList<String> stringFromArrayList() {
//        ArrayList<String> entries = new ArrayList<String>();
//
//        ArrayList<ListTitles> titles = db.getAllLists();
//        for (ListTitles title : titles) {
//            String itemToAdd = title.getTitles();
//
//            entries.add(itemToAdd);
//        }
//        return entries;
//    }

//    protected void openDatabase() {
//        db = openOrCreateDatabase("ListDB", Context.MODE_PRIVATE, null);
//    }

    protected void showLists() {
        String id = c.getString(0);
        String name = c.getString(1);
        editTextID.setText(id);
        editTextTitle.setText(name);
    }

    protected void moveNext() {
        if(!c.isLast())
            c.moveToNext();

        showLists();
    }

    protected void movePrev() {
        if(!c.isFirst())
            c.moveToPrevious();

        showLists();
    }

    protected void saveList() {
        String name = editTextTitle.getText().toString().trim();
        String id = editTextID.getText().toString().trim();

        String inputTitles = editTextTitle.getText().toString();

        ListTitles newListTitle = new ListTitles(inputTitles);

        db.addEntry2(newListTitle);

//        String sql = "UPDATE lists SET name='" + name + "' WHERE id=" + id + ";";
//
//        if (name.equals("")) {
//            Toast.makeText(getApplicationContext(), "OOPS! Try again", Toast.LENGTH_LONG).show();
//            return;
//        }

//        db.execSQL(sql);
//        Toast.makeText(getApplicationContext(), "Sweet", Toast.LENGTH_LONG).show();
//        c = db.rawQuery(SELECT_SQL, null);
//        c.moveToPosition(Integer.parseInt(id));
    }

    private void deleteList() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Careful now!");

        alertDialogBuilder.setPositiveButton("Carry on",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String id = editTextID.getText().toString().trim();

                        String inputTitles = editTextTitle.getText().toString();

                        ListTitles newListTitle = new ListTitles(inputTitles);

                        db.deleteList(newListTitle);

                        Toast.makeText(getApplicationContext(), "Your list has been vanquished", Toast.LENGTH_LONG).show();
//                        c = db.rawQuery(SELECT_SQL, null);
                    }
                });

        alertDialogBuilder.setNegativeButton("Nah",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        if(view == btnNext) {
            moveNext();
        }

        if(view == btnPrev) {
            movePrev();
        }

        if(view == btnSave) {
            saveList();
        }

        if(view == btnDestroy) {
            deleteList();
        }
    }
}





