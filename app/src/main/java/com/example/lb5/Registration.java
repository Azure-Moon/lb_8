package com.example.lb5;

import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity {
    EditText email, login, pass;
    Button reg;
    DatabaseAdapter dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        dbAdapter = new DatabaseAdapter(this);
        setContentView(R.layout.registration);
        login = findViewById(R.id.reg_login);
        pass = findViewById(R.id.reg_pass);
        email = findViewById(R.id.email_reg);
        reg = findViewById(R.id.reg_btn);
        Button to_login = findViewById(R.id.back_auth_btn);

        to_login.setOnClickListener(v -> {
            super.onBackPressed();
        });

        reg.setOnClickListener(v -> {
            UserInfo users = new UserInfo();
            if (getLogin() == "")
            {
                Toast.makeText(this, "Введите логин", Toast.LENGTH_SHORT).show();
            }
            else if(getPass() == "")
            {
                Toast.makeText(this, "Введите пароль", Toast.LENGTH_SHORT).show();
            }
            else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches())
            {
                Toast.makeText(this, "Проверьте правильность электронной почты!", Toast.LENGTH_SHORT).show();
            }
            else
                {
                try
                {
                    dbAdapter.openDBAdapter();

                    users.setEmail(getEmail());
                    users.setLogin(getLogin());
                    users.setPass(PassToCipher.encrypt(getPass()));
                    dbAdapter.addUsers(users);

                    dbAdapter.closeDBAdapter();

                    MessageToEMail();
                    Toast.makeText(getApplicationContext(),
                            "Вы были успешно зарегистрированы! Ваш логин и пароль будут отправлены на введённую вами электронную почту",
                            Toast.LENGTH_SHORT).show();
                    super.onBackPressed();
                }
                catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), "При регистрации произошла ошибка: " +
                            e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private String getEmail()
    {
        return (email.getText().length() == 0) ? "" : email.getText().toString();
    }

    private String getLogin()
    {
        return (login.getText().length() == 0) ? "" : login.getText().toString();
    }

    private String getPass()
    {
        return (pass.getText().length() == 0) ? "" : pass.getText().toString();
    }

    private void MessageToEMail()
    {
        String email = getEmail();
        String login = getLogin();
        String password = getPass();
        String subject = "Регистрация в лабораторной работе №8";
        String message = "Регистрация прошла успешно.\n" + "Ваши данные для входа:" + "\nЛогин: " + getLogin() + "\nПароль: " + getPass();
        JavaMailAPI mailAPI = new JavaMailAPI(this, email, login, password, subject, message);
        mailAPI.execute();

    }


}
