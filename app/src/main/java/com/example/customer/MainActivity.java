package com.example.customer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    private String WIFFLE = "miFU2Mn2ZrUcSgyhmAxLMjgDJAr2";
    private String HODDUK = "jqG9AZCtJBfm8wCsPTgnf332dWB3";
    private ImageView wiffle_open;
    private ImageView hodduk_open;
    private static final String TAG = "MainActivity";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference Ref_wiffle = db.collection("open").document(WIFFLE);
    DocumentReference Ref_hodduk = db.collection("open").document(HODDUK);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wiffle_open = (ImageView)findViewById(R.id.wiffle);
        hodduk_open = (ImageView)findViewById(R.id.hodduk);

        Ref_wiffle.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Boolean open =(Boolean) document.get("open");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(open){
                            wiffle_open.setImageResource(R.drawable.open_wiffle);
                            wiffle_open.setBackgroundColor(Color.rgb(240,227,182));
                        }
                        else {
                            wiffle_open.setImageResource(R.drawable.close_wiffle);
                            wiffle_open.setBackgroundColor(Color.rgb(212,231,241));
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        Ref_hodduk.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Boolean open =(Boolean) document.get("open");
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        if(open){
                            hodduk_open.setImageResource(R.drawable.open_hodduk);
                            hodduk_open.setBackgroundColor(Color.rgb(246,214,127));
                        }
                        else {
                            hodduk_open.setImageResource(R.drawable.close_hodduk);
                            hodduk_open.setBackgroundColor(Color.rgb(212,231,241));
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
