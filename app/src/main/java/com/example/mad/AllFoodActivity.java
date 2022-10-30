package com.example.mad;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllFoodActivity extends AppCompatActivity {
    private ArrayList<Food> foodslist;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allfood);
        recyclerView = findViewById(R.id.res_food);
        foodslist = new ArrayList<>();

        setFoodInfo();
        setAdapter();
    }

    private void setAdapter(){
        FoodAdapter adapter = new FoodAdapter(foodslist);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(lm);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setFoodInfo(){
        foodslist.add(new Food("sa" , "50"));
    }
}
