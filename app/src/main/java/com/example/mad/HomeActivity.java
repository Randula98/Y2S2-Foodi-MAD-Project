package com.example.mad;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private Button btndash;
    private Button btnPizza;

    //firebase object
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");
    DatabaseReference dbrefres;

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

        btnPizza = (Button) findViewById(R.id.btnPizza);
        btnPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openItemsActivity();
            }
        });
    }

    public void openCustomerDashActivity(){
        Intent intent = new Intent(this , CustomerDashboardActivity.class);
        startActivity(intent);
    }

    public void openItemsActivity(){
        Intent intent = new Intent(this , FoodListActivity.class);
        startActivity(intent);
    }

}
