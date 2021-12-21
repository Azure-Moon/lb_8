package com.example.lb5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import javax.crypto.SecretKey;

public class Auth extends AppCompatActivity {
    DatabaseAdapter dbAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth);
        getSupportActionBar().hide();

        Button btn_auth = findViewById(R.id.auth_btn);
        Button btn_auth_reg = findViewById(R.id.auth_reg_btn);

        EditText login = findViewById(R.id.input_login);
        EditText pass = findViewById(R.id.input_pass);


        dbAdapter = new DatabaseAdapter(this);

        btn_auth.setOnClickListener(v -> {
            try
            {
                dbAdapter.openDBAdapter();

                List<UserInfo> thisusers = dbAdapter.getUser();
                for (UserInfo thisuser : thisusers)
                {
                    if (thisuser.getLogin().equals(login.getText().toString()))
                    {
                        if (thisuser.getPass().equals(PassToCipher.encrypt(pass.getText().toString())))
                        {
                            dbAdapter.closeDBAdapter();
                            Intent intent = new Intent(this, MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(getApplicationContext(), login.getText().toString() + ", приветствую!", Toast.LENGTH_SHORT).show();
                            this.finish();
                        }
                    }
                }
            }
            catch (Exception e)
            {
                Toast.makeText(getApplicationContext(), "При авторизации произошла ошибка: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        btn_auth_reg.setOnClickListener(v -> {
            startActivityForResult(new Intent(this, Registration.class), 1);
        });
    }
}