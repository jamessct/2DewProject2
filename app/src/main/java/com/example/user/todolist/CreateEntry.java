package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by user on 03/09/2016.
 */
public class CreateEntry extends AppCompatActivity {

    EditText mNewListInput;
    Button mSubmitButton;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_entry);

        db = ((MainApplication)getApplication()).db;


        mSubmitButton = (Button) findViewById(R.id.submit_list_button);
        mNewListInput = (EditText) findViewById(R.id.new_list_input);

        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                    String listEntries = mNewListInput.getText().toString();

                    ListTitles myNewListItem = new ListTitles(listEntries);

                    db.addEntry2(myNewListItem);

                    showLists();

                }

        });
    }

//    protected void createDatabase() {
//        db=openOrCreateDatabase("ListDB", Context.MODE_PRIVATE, null);
//        db.execSQL("CREATE TABLE IF NOT EXISTS lists(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR);");
//    }
//
//    protected void insertIntoDB(){
//        String name = mNewListInput.getText().toString().trim();
//        if(name.equals("")){
//            Toast.makeText(getApplicationContext(),"Cannot add blank lists", Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        String query = "INSERT INTO lists (name) VALUES('"+name+"');";
//        db.execSQL(query);
//        Toast.makeText(getApplicationContext(),"Got it ;)", Toast.LENGTH_LONG).show();
//    }

    private void showLists() {
        Intent intent = new Intent(this, ViewLists.class);
        startActivity(intent);
        finish();
    }
}