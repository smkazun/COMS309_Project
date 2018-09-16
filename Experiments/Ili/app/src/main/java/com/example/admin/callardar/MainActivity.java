package com.example.admin.callardar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected EditText account;
    private EditText password;

    protected User user;

    private Button LOGIN;

    /**
     *  get from database to determine the current account
     *  and passWord if is match
     * @param account
     * @param passWord
     * @return
     *  return true if match
     */
    private boolean ifExist(final String account, final String passWord)
    {
        //toDO
        user = null;

        return false;
    }

    private class OnClick implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            if(ifExist( account.getText().toString(), password.getText().toString()))
            {
                startActivity(new Intent(MainActivity.this,CalendarList.class));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toDO
        LOGIN = findViewById(0);
        account = findViewById(0);
        password = findViewById(0);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        LOGIN.setOnClickListener(new OnClick());
    }
}
