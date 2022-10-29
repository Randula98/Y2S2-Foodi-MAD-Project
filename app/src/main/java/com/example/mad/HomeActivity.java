package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    private Button btndash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btndash = (Button) findViewById(R.id.btnup);
        btndash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerDashActivity();
            }
        });
    }

    public void openCustomerDashActivity(){
        Intent intent = new Intent(this , CustomerDashboardActivity.class);
        startActivity(intent);
    }
}
