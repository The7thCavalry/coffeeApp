package com.example.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DrinkSettingsActivity extends AppCompatActivity {
    ArrayList<String> transferList = new ArrayList<>(3);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drink_settings_activity);
        String passedArg = getIntent().getExtras().getString("arg");

        setTitle(passedArg);

        FloatingActionButton fab = findViewById(R.id.settings_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        final String[] sugarArr = {"0 кубиков", "1 кубик", "2 кубика", "3 кубика", "4 кубика",
                "5 кубиков", "6 кубиков", "7 кубиков", "8 кубиков", "9 кубиков", "10 кубиков"};

        ArrayAdapter<String> sugarAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sugarArr);
        sugarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner sugarSpinner = (Spinner) findViewById(R.id.settings_sugar_spinner);
        sugarSpinner.setAdapter(sugarAdapter);
        sugarSpinner.setPrompt("Сахар");
        sugarSpinner.setSelection(0);

        sugarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                transferList.set(0, sugarArr[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });


        final String[] cupArr = {"Стандарт", "XL"};

        ArrayAdapter<String> cupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cupArr);
        cupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner cupSpinner = (Spinner) findViewById(R.id.settings_cup_spinner);
        cupSpinner.setAdapter(cupAdapter);
        cupSpinner.setPrompt("Сахар");
        cupSpinner.setSelection(0);

        cupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                transferList.set(1, cupArr[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        transferList.add(sugarArr[0]);
        transferList.add(cupArr[0]);
        transferList.add(passedArg);


        Button btn = findViewById(R.id.settings_to_receive);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DrinkSettingsActivity.this, ReceiveDrinkActivity.class);
                intent.putExtra("sugarCount", transferList.get(0));
                intent.putExtra("cupType", transferList.get(1));
                intent.putExtra("coffeeName", transferList.get(2));
                startActivity(intent);
            }
        });
    }
}
