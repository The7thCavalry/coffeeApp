package com.example.coffeeapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.ItemClickListener {
    MainRecyclerViewAdapter adapter;



    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(this, DrinkSettingsActivity.class);
        intent.putExtra("arg", adapter.getItem(position));
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Coffee Party");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.main_rv);


        FloatingActionButton fab = findViewById(R.id.main_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        // data to populate the RecyclerView with
        ArrayList<String> coffeeNames = new ArrayList<>();
        coffeeNames.add("Эспрессо");
        coffeeNames.add("Капучино");
        coffeeNames.add("Американо");
        coffeeNames.add("Латте");
        coffeeNames.add("Фраппе");

        //TODO get data from JSON here



        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainRecyclerViewAdapter(this, coffeeNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
}
