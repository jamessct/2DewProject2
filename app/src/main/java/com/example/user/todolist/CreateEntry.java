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


    private void showLists() {
        Intent intent = new Intent(this, ViewLists.class);
        startActivity(intent);
        finish();
    }
}