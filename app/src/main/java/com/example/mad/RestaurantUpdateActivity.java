package com.example.mad;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RestaurantUpdateActivity extends AppCompatActivity {

    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    private EditText resname;
    private EditText restype;
    private EditText resloc;
    private EditText resphone;
    private EditText resemail;

    private String rname;
    private String rtype;
    private String rloc;
    private String rphone;
    private String remail;

    private String sessionUNText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_update);

        resname = findViewById(R.id.resname);
        restype = findViewById(R.id.restype);
        resloc = findViewById(R.id.resloc);
        resphone = findViewById(R.id.resphone);
        resemail = findViewById(R.id.resemail);

        final Button btnSave = findViewById(R.id.btnsave);



        dbref.child("Restaurant").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(sessionUNText)) {
                    Toast.makeText(RestaurantUpdateActivity.this, "Snapshot Found", Toast.LENGTH_SHORT).show();
                    rname = snapshot.child(sessionUNText).child("ResName").getValue(String.class);
                    rtype = snapshot.child(sessionUNText).child("ResType").getValue(String.class);
                    rloc = snapshot.child(sessionUNText).child("ResLoc").getValue(String.class);
                    rphone = snapshot.child(sessionUNText).child("ResPhone").getValue(String.class);
                    remail = snapshot.child(sessionUNText).child("ResEmail").getValue(String.class);
                    updateViews(rname, rtype, rloc, rphone, remail);
                } else {
                    Toast.makeText(RestaurantUpdateActivity.this, "Snapshot Found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadData();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String nametxt = resname.getText().toString();
                final String typetxt = restype.getText().toString();
                final String loctxt = resloc.getText().toString();
                final String phontxt = resphone.getText().toString();
                final String emailtxt = resemail.getText().toString();

                dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        dbref.child("Restaurant").child(sessionUNText).child("ResName").setValue(nametxt);
                        dbref.child("Restaurant").child(sessionUNText).child("ResType").setValue(typetxt);
                        dbref.child("Restaurant").child(sessionUNText).child("ResLoc").setValue(loctxt);
                        dbref.child("Restaurant").child(sessionUNText).child("ResPhone").setValue(phontxt);
                        dbref.child("Restaurant").child(sessionUNText).child("ResEmail").setValue(emailtxt);

                        Toast.makeText(RestaurantUpdateActivity.this , "Profile Updated Successfully" , Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RestaurantUpdateActivity.this , RestaurantDashboardActivity.class));
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }


    public void loadData(){
        SharedPreferences sp = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        sessionUNText = sp.getString(TEXT , "");
    }

    public void updateViews(String name , String type , String loc , String phone , String email){
        resname.setText(name);
        restype.setText(type);
        resloc.setText(loc);
        resphone.setText(phone);
        resemail.setText(email);
    }
}
