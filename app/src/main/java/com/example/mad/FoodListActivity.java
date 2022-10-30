package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class FoodListActivity extends AppCompatActivity {

    private Button btnPizza;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodl_ist);

        btnPizza = (Button) findViewById(R.id.btnPizza);
        btnPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemsActivity();
            }
        });
    }

    public void openItemsActivity(){
        Intent intent = new Intent(this , CartActivity.class);
        startActivity(intent);
    }
}
