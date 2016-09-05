package com.example.user.todolist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private static final String SELECT_SQL = "SELECT * FROM lists";

    private SQLiteDatabase db;

    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_list);
        openDatabase();

        editTextID = (EditText)findViewById(R.id.editTextID);
        editTextTitle = (EditText)findViewById(R.id.editTextTitle);

        btnPrev = (Button)findViewById(R.id.btnPrev);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnDestroy = (Button)findViewById(R.id.btnDestroy);
        btnNext = (Button)findViewById(R.id.btnNext);

        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);
        btnSave.setOnClickListener(this);
        btnDestroy.setOnClickListener(this);

        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showLists();
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("ListDB", Context.MODE_PRIVATE, null);
    }

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


        String sql = "UPDATE lists SET name='" + name + "' WHERE id=" + id + ";";

        if (name.equals("")) {
            Toast.makeText(getApplicationContext(), "OOPS! Try again", Toast.LENGTH_LONG).show();
            return;
        }

        db.execSQL(sql);
        Toast.makeText(getApplicationContext(), "Sweet", Toast.LENGTH_LONG).show();
        c = db.rawQuery(SELECT_SQL, null);
        c.moveToPosition(Integer.parseInt(id));
    }

    private void deleteList() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Careful now!");

        alertDialogBuilder.setPositiveButton("Carry on",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String id = editTextID.getText().toString().trim();

                        String sql = "DELETE FROM lists WHERE id=" + id + ";";
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Your list has been vanquished", Toast.LENGTH_LONG).show();
                        c = db.rawQuery(SELECT_SQL, null);
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





