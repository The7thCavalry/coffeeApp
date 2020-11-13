package com.example.coffeeapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    private List<ArrayList<String>> mData;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    HistoryRecyclerViewAdapter(Context context, List<ArrayList<String>> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_history_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<String> coffee = mData.get(position);
        holder.coffeeTextView.setText(coffee.get(0));
        holder.cupTextView.setText(coffee.get(1));
        holder.sugarTextView.setText(coffee.get(2));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView coffeeTextView;
        TextView cupTextView;
        TextView sugarTextView;

        ViewHolder(View itemView) {
            super(itemView);
            coffeeTextView = itemView.findViewById(R.id.history_coffee_name);
            cupTextView = itemView.findViewById(R.id.history_cup_size);
            sugarTextView = itemView.findViewById(R.id.history_sugar_count);
        }
    }
}
