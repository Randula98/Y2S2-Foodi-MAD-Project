package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        button = (Button) findViewById(R.id.btncheck);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartActivity.this , "Order Placed" , Toast.LENGTH_SHORT).show();
                openActivity2();
            }
        });
    }
    public void openActivity2() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
