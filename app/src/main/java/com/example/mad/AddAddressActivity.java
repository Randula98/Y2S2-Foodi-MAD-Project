package com.example.mad;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.ExifInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddAddressActivity extends AppCompatActivity {
    //firebase object
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String sessionUNText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addaddress);

        final EditText addno = findViewById(R.id.addno);
        final EditText addroad = findViewById(R.id.addroad);
        final EditText addtown = findViewById(R.id.addtown);
        final EditText addcity = findViewById(R.id.addcity);

        final Button btnadd = findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String addnotxt = addno.getText().toString();
                final String addroadtxt = addroad.getText().toString();
                final String addtowntxt = addtown.getText().toString();
                final String addcitytxt = addcity.getText().toString();
                loadData();

                boolean isCheck = addnotxt.isEmpty() || addcitytxt.isEmpty() || addtowntxt.isEmpty() || addroadtxt.isEmpty();

                if(isCheck){
                    Toast.makeText(AddAddressActivity.this , "Please Fill All The Details" , Toast.LENGTH_SHORT).show();
                }
                else{
                    dbref.child("Address").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dbref.child("Address").child(sessionUNText).child(addnotxt).child("AddNo").setValue(addroadtxt);
                            dbref.child("Address").child(sessionUNText).child(addnotxt).child("AddRoad").setValue(addroadtxt);
                            dbref.child("Address").child(sessionUNText).child(addnotxt).child("AddTown").setValue(addtowntxt);
                            dbref.child("Address").child(sessionUNText).child(addnotxt).child("AddCity").setValue(addcitytxt);
//
                            Toast.makeText(AddAddressActivity.this , "New Address Added Successfully" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddAddressActivity.this , CustomerDashboardActivity.class));
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }

    public void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        sessionUNText = sp.getString(TEXT , "");
    }
}
