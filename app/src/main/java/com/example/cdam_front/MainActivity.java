package com.example.cdam_front;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton button = findViewById(R.id.signup_btn);
        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        button.setOnClickListener(v -> {
           this.startActivity(myIntent);
        });
    }
}