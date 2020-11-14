package com.example.coffeeapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainRecyclerViewAdapter.ItemClickListener {
    MainRecyclerViewAdapter adapter;
    static HttpURLConnection connection;



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

        final List<String> coffeeNames = Collections.synchronizedList(new ArrayList<String>());

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                BufferedReader bufferedReader;
                String line;
                StringBuffer responseContent = new StringBuffer();
                boolean requestIsOk = true;


                try {
                    URL url = new URL("http://testapi.servertest.pro/api/objects/");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setRequestProperty("Authorization", "Token d23d2a771294e285257976bc1e5a4040fa6c14c8");
                    connection.connect();

                    int status = connection.getResponseCode();

                    if (status > 299) {
                        requestIsOk = false;
                        bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                        while ((line = bufferedReader.readLine()) != null) {
                            responseContent.append(line);
                        }
                        bufferedReader.close();
                    }
                    else {
                        bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        while ((line = bufferedReader.readLine()) != null) {
                            responseContent.append(line);
                        }
                        bufferedReader.close();
                    }

                    System.out.println(responseContent.toString());

                    if (requestIsOk) {
                        JSONObject entireJsonObject = new JSONObject(responseContent.toString());
                        JSONArray jsonCoffeeNames = entireJsonObject.getJSONArray("results");
                        for (int i = 0; i < jsonCoffeeNames.length(); i++) {
                            JSONObject jsonObject = jsonCoffeeNames.getJSONObject(i);
                            String coffeeName = jsonObject.getString("title");
                            coffeeNames.add(coffeeName);
                        }
                    }


                    connection.disconnect();
                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // set up the RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MainRecyclerViewAdapter(this, coffeeNames);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }
}
