package com.example.creoissantrouge;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Sign_Up extends AppCompatActivity {
    EditText email, user_name, name, pwd, conf_pwd;
    Button btn_cnx, ButtonSingIn;
    SQLiteDatabase bd;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        email = findViewById(R.id.email);
        user_name = findViewById(R.id.username);
        name = findViewById(R.id.name);
        pwd = findViewById(R.id.pwd);
        conf_pwd = findViewById(R.id.conf_pwd);
        bd = openOrCreateDatabase("CR", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS User(id integer primary key autoincrement, username VARCHAR, name VARCHAR, email_phone VARCHAR, pwd VARCHAR);");
        bd.close();
        ButtonSingIn = findViewById(R.id.button1);

        btn_cnx = findViewById(R.id.button);
        btn_cnx.setOnClickListener(v -> {
            Intent intent;
            if(create_user(v)){
                intent = new Intent(Sign_Up.this, Accueil.class);
                startActivity(intent);
            }

        });
        ButtonSingIn.setOnClickListener(v -> {
            Intent intent = new Intent(Sign_Up.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public boolean create_user(View v) {

        if (email.getText().toString().trim().length() == 0 || user_name.getText().toString().trim().length() == 0 || name.getText().toString().trim().length() == 0 || pwd.getText().toString().trim().length() == 0 || conf_pwd.getText().toString().trim().length() == 0) {
            Toast.makeText(this, "ALL REQUIRED!", Toast.LENGTH_SHORT).show();
        }
        else {
            if ((pwd.getText().toString()).equals(conf_pwd.getText().toString())) {
                bd.execSQL("INSERT INTO User (username, name, email_phone, pwd) VALUES('" + user_name.getText() + "','" + name.getText() + "','" + email.getText() + "','" + pwd.getText() + "');");
                Toast msg = Toast.makeText(this, "SUCCEED", Toast.LENGTH_LONG);
                msg.show();
                vider_Text();
                bd.close();
                return true;
            } else
                Toast.makeText(this, "PASSWORD NOT COMPATIBLE", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
    public void vider_Text()
    {
        user_name.setText("");
        name.setText("");
        email.setText("");
        pwd.setText("");
        conf_pwd.setText("");
    }
}
