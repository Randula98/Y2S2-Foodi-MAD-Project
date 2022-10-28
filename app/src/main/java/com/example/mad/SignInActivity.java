package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private Button btnCus;
    private Button btnRes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        btnRes = (Button) findViewById(R.id.btnres);
        btnCus = (Button) findViewById(R.id.btncus);

        btnRes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openResActivity();
            }
        });

        btnCus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCusActivity();
            }
        });
    }

    public void openResActivity(){
        Intent intent = new Intent(this , SignInResActivity.class);
        startActivity(intent);
    }

    public void openCusActivity(){
        Intent intent = new Intent(this , SignInCusActivity.class);
        startActivity(intent);
    }
}

