package com.example.helloapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;

import java.io.Serializable;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button registerButton = findViewById(R.id.RegistreButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText prenomEditText = findViewById(R.id.prenomInput);
                EditText nomEditText = findViewById(R.id.nomInput);
                EditText emailEditText = findViewById(R.id.emailInput);
                EditText passwordEditText = findViewById(R.id.passInput);
                EditText phoneNumberEditText = findViewById(R.id.phoneInput);

                String prenom = prenomEditText.getText().toString();
                String nom = nomEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();

                if (!(prenom.isEmpty()||nom.isEmpty()||email.isEmpty()||password.isEmpty()||phoneNumber.isEmpty()))
                {
                UserInfo userInfo = new UserInfo(prenom, nom, email, password, phoneNumber);
                Intent intent = new Intent(RegisterActivity.this, Userinfo.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("UserInfo", userInfo);
                startActivity(intent);
                }
            }
        });
    }

    public static class UserInfo implements Serializable {
        private String prenom;
        private String nom;
        private String email;
        private String password;
        private String phoneNumber;

        // Constructor
        public UserInfo(String prenom, String nom, String email, String password, String phoneNumber) {
            this.prenom = prenom;
            this.nom = nom;
            this.email = email;
            this.password = password;
            this.phoneNumber = phoneNumber;
        }

        // Getters and setters
        public String getPrenom() {
            return prenom;
        }
        public String getNom() {
            return nom;
        }
        public String getEmail() {
            return email;
        }
        public String getPassword() {
            return password;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }
    }
}
