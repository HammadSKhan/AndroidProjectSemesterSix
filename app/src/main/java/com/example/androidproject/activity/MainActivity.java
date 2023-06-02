package com.example.androidproject.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidproject.R;
import com.example.androidproject.helper.SaveSharedPreference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Thread background = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for 5 seconds
                    sleep(5*1000);

                    // After 5 seconds redirect to another intent
                    if (!SaveSharedPreference.getUserName(getApplicationContext()).equals("")) {
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getBaseContext(), AuthActivity.class);
                        startActivity(intent);
                    }

                    //Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();

//        if (SaveSharedPreference.getUserName(getApplicationContext()) != "") {
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//        } else {
//            Intent intent = new Intent(this, AuthActivity.class);
//            startActivity(intent);
//        }
    }

}