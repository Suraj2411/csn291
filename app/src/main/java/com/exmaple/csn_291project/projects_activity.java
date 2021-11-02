package com.exmaple.csn_291project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class projects_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        Intent intent = getIntent();


    }

    public void addProject(View view){
        Intent my_addProject_intent = new Intent(this,addProject.class);
        startActivity(my_addProject_intent);
    }
}