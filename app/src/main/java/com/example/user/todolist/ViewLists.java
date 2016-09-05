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
public class ViewLists extends AppCompatActivity {
    private EditText editTextID;
    private EditText editTextTitle;
    private Button btnPrev;
    private Button btnSave;
    private Button btnDestroy;
    private Button btnNext;
    private Button btnAdd;

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
        btnAdd = (Button)findViewById(R.id.btnAdd);

        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showLists();
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("ListDB", Context.MODE_PRIVATE, null);
    }

    protected void showLists() {
        String id = c.getString(0);
        String title = c.getString(1);
        editTextID.setText(id);
        editTextTitle.setText(title);
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

    protected void saveRecord() {
        String title = editTextTitle.getText().toString().trim();
        String id = editTextID.getText().toString().trim();


        String sql = "UPDATE lists SET title='" + title + "' WHERE id=" + id + ";";

        if (title.equals("")) {
            Toast.makeText(getApplicationContext(), "OOPS! Try again");
            return;
        }

        db.execSQL(sql);
        Toast.makeText(getApplicationContext(), "Sweet");
        c = db.rawQuery(SELECT_SQL, null);
        c.moveToPosition(Integer.parseInt(id));
    }

    private void deleteRecord() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("You totally sure about that?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String id = editTextID.getText().toString().trim();

                        String sql = "DELETE FROM lists WHERE id=" + id + ";";
                        db.execSQL(sql);
                        Toast.makeText(getApplicationContext(), "Your list is no more", Toast.LENGTH_LONG).show();

                    }
                }
    }
}





