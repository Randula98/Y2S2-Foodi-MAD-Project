package com.example.mad;

import android.content.Intent;
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

public class RegisterCusActivity extends AppCompatActivity{
    //firebase object
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registercus);

        final EditText cusname = findViewById(R.id.cusname);
        final EditText cusphone = findViewById(R.id.cusphone);
        final EditText cusemail = findViewById(R.id.cusemail);
        final EditText cusun = findViewById(R.id.cusuname);
        final EditText cuspw = findViewById(R.id.cuspw);
        final EditText cusrepw = findViewById(R.id.cusrepw);

        final Button btnSignUp = findViewById(R.id.btnsign);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from textedits
                final String cusnametxt = cusname.getText().toString();
                final String cusphonetxt = cusphone.getText().toString();
                final String cusemailtxt = cusemail.getText().toString();
                final String cusuntxt = cusun.getText().toString();
                final String cuspwtxt = cuspw.getText().toString();
                final String cusrepwtxt = cusrepw.getText().toString();

                //check is empty
                boolean isCheck = cusnametxt.isEmpty() || cusphonetxt.isEmpty() || cusemailtxt.isEmpty() || cusuntxt.isEmpty() || cuspwtxt.isEmpty() || cusrepwtxt.isEmpty();

                if(isCheck){
                    Toast.makeText(RegisterCusActivity.this , "Please Fill All The Details" , Toast.LENGTH_SHORT).show();
                    cuspw.getText().toString();
                    cusrepw.getText().toString();
                }
                else if (!cuspwtxt.equals(cusrepwtxt)){
                    Toast.makeText(RegisterCusActivity.this , "Passwords Are Not Maching" , Toast.LENGTH_SHORT).show();
                    cuspw.getText().toString();
                    cusrepw.getText().toString();
                }
                else{
                    dbref.child("Customer").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(cusemailtxt)){
                                Toast.makeText(RegisterCusActivity.this , "Email is Already Registered" , Toast.LENGTH_SHORT).show();
                                cusemail.getText().clear();
                            }
                            else if(snapshot.hasChild(cusphonetxt)){
                                Toast.makeText(RegisterCusActivity.this , "Phone Number is Already Registered" , Toast.LENGTH_SHORT).show();
                                cusphone.getText().clear();
                            }
                            else if(snapshot.hasChild(cusuntxt)){
                                Toast.makeText(RegisterCusActivity.this , "Username is Already Registered" , Toast.LENGTH_SHORT).show();
                                cusun.getText().clear();
                            }
                            else{
                                dbref.child("Customer").child(cusuntxt).child("CusName").setValue(cusnametxt);
                                dbref.child("Customer").child(cusuntxt).child("CusPhone").setValue(cusphonetxt);
                                dbref.child("Customer").child(cusuntxt).child("CusEmail").setValue(cusemailtxt);
                                dbref.child("Customer").child(cusuntxt).child("CusPw").setValue(cuspwtxt);

                                Toast.makeText(RegisterCusActivity.this , "New Customer Registered Successfully" , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterCusActivity.this , SignInCusActivity.class));
                                finish();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

    }
}