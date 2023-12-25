package com.example.aplikasipencatatpelanggaran;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityLogin extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        username = findViewById(R.id.edtText_username);
        password = findViewById(R.id.edtText_password);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        // register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(registerIntent);
                finish();
            }
        });

        // login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                Boolean masuk = db.checkLogin(strUsername, strPassword);
                if (masuk) {
                    Boolean updateSession = db.upgradeSession("ada", 1);
                    if (updateSession) {
                        Toast.makeText(getApplicationContext(), "Berhasil Masuk", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(ActivityLogin.this, ActivityUtama.class);
                        startActivity(mainIntent);
                        finish();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Masuk Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
