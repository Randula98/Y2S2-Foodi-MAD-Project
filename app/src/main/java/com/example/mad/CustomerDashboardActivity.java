package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerDashboardActivity extends AppCompatActivity {

    private Button btnup;
    private Button btnaddfd;
    private Button btnaddadd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_dashboard);

        btnup = (Button) findViewById(R.id.btnup);
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomerUpdateActivity();
            }
        });

        btnaddfd = (Button) findViewById(R.id.btnaddfeedback);
        btnaddfd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddFeedbackActivity();
            }
        });

        btnaddadd = (Button) findViewById(R.id.btnaddaddress);
        btnaddadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddAddressActivity();
            }
        });

    }

    public void openAddAddressActivity(){
        Intent intent = new Intent(this , AddAddressActivity.class);
        startActivity(intent);
    }

    public void openCustomerUpdateActivity(){
        Intent intent = new Intent(this , CustomerUpdateActivity.class);
        startActivity(intent);
    }

    public void openAddFeedbackActivity(){
        Intent intent = new Intent(this , AddFeedbackActivity.class);
        startActivity(intent);
    }
}
