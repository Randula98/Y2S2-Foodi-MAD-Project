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

public class AddFeedbackActivity extends AppCompatActivity {

    DatabaseReference dbref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://foodi-5f9d1-default-rtdb.firebaseio.com/");
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private String sessionUNText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfeedback);

        final EditText fdover = findViewById(R.id.fdover);
        final EditText fdtype = findViewById(R.id.fdtype);
        final EditText fddesc = findViewById(R.id.fddesc);
        final EditText fdrating = findViewById(R.id.fdrating);

        final Button btnadd = findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fdovertxt = fdover.getText().toString();
                final String fdtypetxt = fdtype.getText().toString();
                final String fddesctxt = fddesc.getText().toString();
                final String fdratingtxt = fdrating.getText().toString();
                loadData();

                boolean isCheck = fdovertxt.isEmpty() || fdtypetxt.isEmpty() || fddesctxt.isEmpty() || fdratingtxt.isEmpty();

                if(isCheck){
                    Toast.makeText(AddFeedbackActivity.this , "Please Fill All The Details" , Toast.LENGTH_SHORT).show();
                }
                else{
                    dbref.child("Feedback").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            dbref.child("Feedback").child(sessionUNText).child(fdovertxt).child("FDOver").setValue(fdovertxt);
                            dbref.child("Feedback").child(sessionUNText).child(fdovertxt).child("FDType").setValue(fdovertxt);
                            dbref.child("Feedback").child(sessionUNText).child(fdovertxt).child("FDDesc").setValue(fdovertxt);
                            dbref.child("Feedback").child(sessionUNText).child(fdovertxt).child("FDRating").setValue(fdovertxt);

                            Toast.makeText(AddFeedbackActivity.this , "New Address Added Successfully" , Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddFeedbackActivity.this , CustomerDashboardActivity.class));
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
