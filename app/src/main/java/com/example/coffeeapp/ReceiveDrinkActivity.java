package com.example.coffeeapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.IntentCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReceiveDrinkActivity extends AppCompatActivity {
    SqliteHelper sqliteHelper = new SqliteHelper(ReceiveDrinkActivity.this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_drink_activity);

        final String sugarCount = getIntent().getExtras().getString("sugarCount");
        final String cupType = getIntent().getExtras().getString("cupType");
        final String coffeeName = getIntent().getExtras().getString("coffeeName");

        setTitle("Получение напитка");

        FloatingActionButton fab = findViewById(R.id.receive_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        TextView coffeeNameTextView = (TextView)findViewById(R.id.receive_coffee_name_textView);
        coffeeNameTextView.setText(coffeeName);

        TextView cupNameTextView = (TextView)findViewById(R.id.receive_coffee_cup_textView);
        cupNameTextView.setText(cupType);

        TextView sugarNameTextView = (TextView)findViewById(R.id.receive_coffee_sugar_textView);
        sugarNameTextView.setText(sugarCount);

        ImageView image = (ImageView) findViewById(R.id.receive_coffee_image);
        if ("XL".equals(cupType))
        image.setImageResource(R.drawable.coffee2);


        Button btn = findViewById(R.id.receive_save_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] sugarCountArr = sugarCount.split(" ");
                int sugarCount = Integer.parseInt(sugarCountArr[0]);
                sqliteHelper.addOrder(coffeeName, cupType, sugarCount);

                Intent intents = new Intent(ReceiveDrinkActivity.this, MainActivity.class);
                intents.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |  Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intents);
            }
        });

    }
}
