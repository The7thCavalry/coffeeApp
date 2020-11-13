package com.example.coffeeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class HistoryActivity extends AppCompatActivity {
    HistoryRecyclerViewAdapter adapter;
    SqliteHelper sqliteHelper = new SqliteHelper(HistoryActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);
        setTitle("История");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.history_rv);


        FloatingActionButton fab = findViewById(R.id.history_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();

        Cursor cursor = sqliteHelper.readAllData();
        while (cursor.moveToNext()) {
            String coffeeName = cursor.getString(1);
            String cupType = cursor.getString(2);
            String sugarCount = cursor.getString(3);
            String sugarCountModified = sugarCount + "(куска/ов сахара)";

            ArrayList<String> tmpList = new ArrayList<>();
            tmpList.add(coffeeName);
            tmpList.add(cupType);
            tmpList.add(sugarCountModified);
            orders.add(tmpList);
        }


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryRecyclerViewAdapter(this, orders);
        recyclerView.setAdapter(adapter);



        Button btn = findViewById(R.id.history_to_main);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
