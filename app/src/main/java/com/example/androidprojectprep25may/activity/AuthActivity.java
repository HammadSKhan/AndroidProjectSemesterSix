package com.example.androidprojectprep25may.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidprojectprep25may.R;
import com.example.androidprojectprep25may.fragment.auth.LoginFragment;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentAuthContainer, LoginFragment.class, null)
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}