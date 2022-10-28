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

public class SignInResActivity extends AppCompatActivity {

    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signinres);

        final EditText un = findViewById(R.id.untxt);
        final EditText pw = findViewById(R.id.txtpassword);
        final Button loginBtn = findViewById(R.id.loginbtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String untxt = un.getText().toString();
                final String pwtxt = pw.getText().toString();

                if(untxt.isEmpty() || pwtxt.isEmpty()){
                    Toast.makeText(SignInResActivity.this , "Please Enter Your Email or Password" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dbref.child("Restaurant").addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            //check email
                            if(snapshot.hasChild(untxt)){
                                //get pw
                                final String getPW = snapshot.child(untxt).child("ResPw").getValue(String.class);

                                if(getPW.equals(pwtxt)){
                                    Toast.makeText(SignInResActivity.this , "Successfully Logged In" , Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SignInResActivity.this , RestaurantDashboardActivity.class));
                                    finish();
                                }
                                else{
                                    Toast.makeText(SignInResActivity.this , "Wrong Passoword" , Toast.LENGTH_SHORT).show();
                                    pw.getText().clear();
                                }
                            }
                            else{
                                Toast.makeText(SignInResActivity.this , "User Not Found" , Toast.LENGTH_SHORT).show();
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
