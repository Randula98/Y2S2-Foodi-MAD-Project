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
    private Button btnadditems;
    private Button btnviewitems;

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

        btnadditems = (Button) findViewById(R.id.additems);
        btnadditems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddItemsActivity();
            }
        });

        btnviewitems = (Button) findViewById(R.id.viewitems);
        btnviewitems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openViewItemsActivity();
            }
        });
    }

    public void openViewItemsActivity(){
        Intent intent = new Intent(this , AllFoodActivity.class);
        startActivity(intent);
    }

    public void openAddItemsActivity(){
        Intent intent = new Intent(this , AddItemActivity.class);
        startActivity(intent);
    }

    public void openResUpdate(){
//        Intent intent = new Intent(this , RestaurantUpdateActivity.class);
//        startActivity(intent);
        Intent intent = new Intent(this , RestaurantUpdateActivity.class);
        startActivity(intent);

    }

    public void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        text = sp.getString(TEXT , "");
    }

    public void updateViews(){
    }
}
