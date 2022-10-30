package com.example.mad;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>{

    private ArrayList<Food> foodlist;

    public FoodAdapter(ArrayList<Food> foodlist) {
        this.foodlist = foodlist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView foodnametxt;
        private TextView foodpricetxt;

        public MyViewHolder(final View view) {
            super(view);
            foodnametxt = view.findViewById(R.id.txt_name);
            foodpricetxt = view.findViewById(R.id.txt_price);
        }
    }

    @NonNull
    @Override
    public FoodAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_allfood , parent , false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.MyViewHolder holder, int position) {
        String name = foodlist.get(position).getName();
        holder.foodnametxt.setText(name);
        String price = foodlist.get(position).getPrice();
        holder.foodpricetxt.setText(price);
    }

    @Override
    public int getItemCount() {
        return foodlist.size();
    }
}