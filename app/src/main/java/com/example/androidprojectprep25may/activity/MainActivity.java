package com.example.androidprojectprep25may.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.androidprojectprep25may.R;
import com.example.androidprojectprep25may.helper.SaveSharedPreference;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SaveSharedPreference.getUserName(getApplicationContext()) != "") {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, AuthActivity.class);
            startActivity(intent);
        }
    }

}