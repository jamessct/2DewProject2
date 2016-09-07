package com.example.user.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by user on 03/09/2016.
 */
public class MainActivity extends AppCompatActivity {

    Button mCreateButton;
    Button mViewButton;
    Button mAboutButton;
    Button mMagicButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCreateButton = (Button)findViewById(R.id.create_entry);
        mViewButton = (Button)findViewById(R.id.view_lists);
        mAboutButton = (Button)findViewById(R.id.about);
        mMagicButton = (Button)findViewById(R.id.magic_button);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateEntry.class);

                startActivity(intent);
            }
        });

        mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewLists.class);

                startActivity(intent);
            }
        });

        mAboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, About.class);

                startActivity(intent);
            }
        });

        mMagicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, IndividualList.class);

                startActivity(intent);
            }
        });
    }
}
