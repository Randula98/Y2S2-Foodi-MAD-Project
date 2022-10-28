package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRes;
    private Button btnCus;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRes = (Button) findViewById(R.id.btnres);
        btnCus = (Button) findViewById(R.id.btncus);

        btnRes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openResActivity();
            }
        });

        btnCus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openCusActivity();
            }
        });
    }

    public void openResActivity(){
        Intent intent = new Intent(this , RegisterResActivity.class);
        startActivity(intent);
    }

    public void openCusActivity(){
        Intent intent = new Intent(this , RegisterCusActivity.class);
        startActivity(intent);
    }
}
