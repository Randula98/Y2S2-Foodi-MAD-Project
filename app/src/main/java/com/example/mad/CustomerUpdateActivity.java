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

public class CustomerUpdateActivity extends AppCompatActivity {
    //firebase object
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private EditText cusname;
    private EditText cusphone;
    private EditText cusemail;
    private EditText cuspw;
    private String cname;
    private String cphone;
    private String cemail;
    private String cpw;

    private String sessionUNText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_update);

        cusname = findViewById(R.id.cusname);
        cusphone = findViewById(R.id.cusphone);
        cusemail = findViewById(R.id.cusemail);
        cuspw = findViewById(R.id.cuspw);

        final Button btnSave = findViewById(R.id.savebtn);

        dbref.child("Customer").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(sessionUNText)){
                    Toast.makeText(CustomerUpdateActivity.this , "Snapshot Found" , Toast.LENGTH_SHORT).show();
                    cname = snapshot.child(sessionUNText).child("CusName").getValue(String.class);
                    cphone = snapshot.child(sessionUNText).child("CusPhone").getValue(String.class);
                    cemail = snapshot.child(sessionUNText).child("CusEmail").getValue(String.class);
                    cpw = snapshot.child(sessionUNText).child("CusPw").getValue(String.class);
                    updateViews(cname , cphone , cemail , cpw);
                }
                else
                {
                    Toast.makeText(CustomerUpdateActivity.this , "Snapshot Not Found" , Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        loadData();
        //updateViews("cname" , "cphone" , "cemail" , "cpw");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get data from textedits
                final String cusnametxt = cusname.getText().toString();
                final String cusphonetxt = cusphone.getText().toString();
                final String cusemailtxt = cusemail.getText().toString();
                final String cuspwtxt = cuspw.getText().toString();

                //check is empty
                boolean isCheck = cusnametxt.isEmpty() || cusphonetxt.isEmpty() || cusemailtxt.isEmpty()  || cuspwtxt.isEmpty();
                //boolean isCheck = false;

                if(isCheck){
                    Toast.makeText(CustomerUpdateActivity.this , "Please Fill All The Details" , Toast.LENGTH_SHORT).show();
                }
                else{
                    dbref.child("Customer").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dbref.child("Customer").child(sessionUNText).child("CusName").setValue(cusnametxt);
                            dbref.child("Customer").child(sessionUNText).child("CusPhone").setValue(cusphonetxt);
                            dbref.child("Customer").child(sessionUNText).child("CusEmail").setValue(cusemailtxt);
                            dbref.child("Customer").child(sessionUNText).child("CusPw").setValue(cuspwtxt);

                            Toast.makeText(CustomerUpdateActivity.this , "Profile Updated Successfully" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(CustomerUpdateActivity.this , CustomerDashboardActivity.class));
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

    public void updateViews(String cname , String cphone , String cemail , String cpw){
        //textView.setText(text);
        cusname.setText(cname);
        cusphone.setText(cphone);
        cusemail.setText(cemail);
        cuspw.setText(cpw);
    }

}
