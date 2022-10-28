package com.example.mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnReg;
    private Button btnSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnReg = (Button) findViewById(R.id.reg1);
        btnSign = (Button) findViewById(R.id.sign1);

        btnReg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openRegActivity();
            }
        });

        btnSign.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                openSignActivity();
            }
        });

    }

    public void openRegActivity(){
        Intent intent = new Intent(this , RegisterActivity.class);
        startActivity(intent);
    }

    public void openSignActivity(){
        Intent intent = new Intent(this , SignInActivity.class);
        startActivity(intent);
    }
}