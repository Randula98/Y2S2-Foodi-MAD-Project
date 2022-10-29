package com.example.mad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RestaurantDashboardActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String text;
    private Button btnup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_dashboard);

        loadData();
        updateViews();

        btnup = (Button) findViewById(R.id.btnup);
        btnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResUpdate();
            }
        });
    }

    public void openResUpdate(){
        // Intent intent = new Intent((this , Res)
    }

    public void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text = sp.getString(TEXT , "");
    }

    public void updateViews(){
    }
}
