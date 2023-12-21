package com.example.creoissantrouge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_Up extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    setContentView(R.layout.sign_up);
    Button ButtonCnx;

    ButtonCnx = findViewById(R.id.button1);
            ButtonCnx.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(Sign_Up.this, MainActivity.class);
            startActivity(intent);
        }
    });
}
}
