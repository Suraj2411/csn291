package com.exmaple.csn_291project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import com.exmaple.csn_291project.Adapter.RecyclerViewAdapter;
import com.exmaple.csn_291project.data.MyListDBHandler;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.exmaple.csn_291project.model.lists;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private DrawerLayout drawerlayout;
    private ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private  ArrayList<lists> Array_of_Listnames;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        navController = Navigation.findNavController(this,R.id.main_fragment);
        drawerlayout = findViewById(R.id.my_drawer);
        navigationView = findViewById(R.id.navigation_view);

        toggle = new ActionBarDrawerToggle(this,drawerlayout,R.string.open,R.string.close);
        drawerlayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
        NavigationUI.setupWithNavController(bottomNavigationView,navController);

        //Recyclerview initialization
        recyclerView = findViewById(R.id.listrecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyListDBHandler db = new MyListDBHandler(MainActivity.this);



        Array_of_Listnames = new ArrayList<>();


        // Get all contacts
        List<lists> Array_List = db.getAllLists();
        for(lists List: Array_List){
            Array_of_Listnames.add(List);
        }

//        Use your recyclerView
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, Array_of_Listnames);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    public void addTask(View view){
        Intent my_addTask_intent = new Intent(this,addTask.class);
        startActivity(my_addTask_intent);
    }

    public void addList(View view){
        Intent my_addList_intent = new Intent(this,addList.class);
        startActivity(my_addList_intent);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.projects:
                Intent my_projects_intent = new Intent(this,projects_activity.class);
                startActivity(my_projects_intent);
                break;
            case R.id.completedtasks:
                Intent my_completed_tasks_intent = new Intent(this,completed_tasks.class);
                startActivity(my_completed_tasks_intent);
                break;
            case R.id.performance:
                Intent my_performance_intent = new Intent(this,performance.class);
                startActivity(my_performance_intent);
                break;
        }
        return true;
    }



}