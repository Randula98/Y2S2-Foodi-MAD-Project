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

public class RegisterResActivity extends AppCompatActivity {

    //firebase object
    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerres);

        final EditText resname = findViewById(R.id.resname);
        final EditText restype = findViewById(R.id.restype);
        final EditText resloc = findViewById(R.id.resloc);
        final EditText resphone = findViewById(R.id.resphone);
        final EditText resemail = findViewById(R.id.resemail);
        final EditText resun = findViewById(R.id.resun);
        final EditText respw = findViewById(R.id.respw);
        final EditText resrepw = findViewById(R.id.resrepw);

        final Button btnregsign = findViewById(R.id.btnregsign);

        btnregsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //get data from textedits
                final String resnametxt = resname.getText().toString();
                final String restypetxt = restype.getText().toString();
                final String resloctxt = resloc.getText().toString();
                final String resphonetxt = resphone.getText().toString();
                final String resemailtxt = resemail.getText().toString();
                final String resuntxt = resun.getText().toString();
                final String respwtxt = respw.getText().toString();
                final String resrepwtxt = resrepw.getText().toString();

                //check is empty
                boolean isCheck = resnametxt.isEmpty() || restypetxt.isEmpty() || resloctxt.isEmpty() || resphonetxt.isEmpty() || resemailtxt.isEmpty() || resuntxt.isEmpty() || respwtxt.isEmpty() || resrepwtxt.isEmpty();

                if(isCheck){
                    Toast.makeText(RegisterResActivity.this , "Please Fill All The Details" , Toast.LENGTH_SHORT).show();
                    respw.getText().clear();
                    resrepw.getText().clear();
                }
                else if (!respwtxt.equals(resrepwtxt)){
                    Toast.makeText(RegisterResActivity.this , "Passwords Are Not Maching" , Toast.LENGTH_SHORT).show();
                    respw.getText().clear();
                    resrepw.getText().clear();
                }
                else{

                    dbref.child("Restaurant").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            if(snapshot.hasChild(resemailtxt)){
                                Toast.makeText(RegisterResActivity.this , "Email is Already Registered" , Toast.LENGTH_SHORT).show();
                                resemail.getText().clear();
                            }
                            else if(snapshot.hasChild(resphonetxt)){
                                Toast.makeText(RegisterResActivity.this , "Phone Number is Already Registered" , Toast.LENGTH_SHORT).show();
                                resphone.getText().clear();
                            }
                            else if(snapshot.hasChild(resuntxt)){
                                Toast.makeText(RegisterResActivity.this , "Username is Already Registered" , Toast.LENGTH_SHORT).show();
                                resun.getText().clear();
                            }
                            else {
                                //sending to firebase
                                dbref.child("Restaurant").child(resuntxt).child("ResName").setValue(resnametxt);
                                dbref.child("Restaurant").child(resuntxt).child("ResType").setValue(restypetxt);
                                dbref.child("Restaurant").child(resuntxt).child("ResLoc").setValue(resloctxt);
                                dbref.child("Restaurant").child(resuntxt).child("ResPhone").setValue(resphonetxt);
                                dbref.child("Restaurant").child(resuntxt).child("ResEmail").setValue(resemailtxt);
                                dbref.child("Restaurant").child(resuntxt).child("ResPw").setValue(respwtxt);

                                Toast.makeText(RegisterResActivity.this , "New Restaurant Registered Successfully" , Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterResActivity.this , SignInResActivity.class));
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

