package com.example.creoissantrouge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText user_name, pwd;
    Button btn_cnx,ButtonSignUp;
    SQLiteDatabase bd;
    Cursor c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user_name = findViewById(R.id.user_name);
        pwd = findViewById(R.id.pwd);
        btn_cnx = findViewById(R.id.button);
        ButtonSignUp = findViewById(R.id.button1);
        bd = openOrCreateDatabase("CR", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS User(id integer primary key autoincrement, username VARCHAR, name VARCHAR, email_phone VARCHAR, pwd VARCHAR);");
        bd.close();
        btn_cnx.setOnClickListener(v -> {
            Intent intent;
            if(exist(v))
                intent = new Intent(MainActivity.this, Accueil.class);
            else
                intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);

        });
        ButtonSignUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Sign_Up.class);
            startActivity(intent);
        });
        }

        public boolean exist (View v){
            c = bd.rawQuery("SELECT username, pwd FROM User",null);

            if(c.getString(0).equals(user_name.getText().toString())){
                if(c.getString(1).equals(pwd.getText().toString())){
                    Toast.makeText(this, "SUCCEED", Toast.LENGTH_SHORT).show();
                    return true;
                }else
                    Toast.makeText(this, "ERROR! PASSWORD INCORRECT", Toast.LENGTH_SHORT).show();

            }else
                Toast.makeText(this, "ERROR! USERNAME INCORRECT", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
