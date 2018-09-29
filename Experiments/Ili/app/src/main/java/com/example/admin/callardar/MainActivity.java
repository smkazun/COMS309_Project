package com.example.admin.callardar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    protected EditText account;
    private EditText password;

    private TextView wrongMessage;

    protected static User user;

    private Button LOGIN;

    /**
     *  get from database to determine the current account
     *  and passWord if is match, if it is true, add the
     *  calendar to the user by calling user.addCalender()
     *
     * @param account
     * @param passWord
     * @return
     *  return true if match
     */
    private boolean ifExist(final String account, final String passWord)
    {
        if(account.equals("") && passWord.equals(""))
        {
            user = new User("test","@");

            User[] arr = new User[15];

            for(int i = 0 ; i < 15 ; i += 1)
            {
                arr[i] = new User("illiand" + i, "!");
            }

            user.addFriends(arr);

            return true;
        }

        //toDO
        user = null;
        //user.addFriend...

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
            else
            {
                System.out.println(account.getText().toString() + " + " +password.getText().toString());
                wrongMessage.setVisibility(View.VISIBLE);
                password.setText(new char[]{},0,0);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LOGIN = findViewById(R.id.button_Login);
        account = findViewById(R.id.acc);
        password = findViewById(R.id.pass);

        wrongMessage = findViewById(R.id.WrongMessage);
        wrongMessage.setVisibility(View.INVISIBLE);

        password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        LOGIN.setOnClickListener(new OnClick());
    }
}
