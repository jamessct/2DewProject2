package com.example.user.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by user on 05/09/2016.
 */
public class IndividualList extends AppCompatActivity {

    EditText mNewEntryInput;
    Button mSubmitEntryButton;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.individual_list);

        createDatabase();

        mSubmitEntryButton = (Button)findViewById(R.id.submit_list_button);
        mNewEntryInput = (EditText)findViewById(R.id.new_entry_input);

        mSubmitEntryButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (view == mSubmitEntryButton) {
                    insertIntoDB();
                }
            }
        });
    }

    protected void createDatabase() {
        db=openOrCreateDatabase("ListDB", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS entries(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, item VARCHAR);");
    }

    protected void insertIntoDB(){
        String item = mNewEntryInput.getText().toString().trim();
        if(item.equals("")){
            Toast.makeText(getApplicationContext(), "Cannot add blank entries", Toast.LENGTH_LONG).show();
            return;
        }

        String query = "INSERT INTO entries (item) VALUES('" + item +"');";
        db.execSQL(query);
        Toast.makeText(getApplicationContext(),"Got it ;)", Toast.LENGTH_LONG).show();
    }


}
