package com.example.admin.callardar;

import android.content.Intent;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class AccountSetting extends AppCompatActivity
{
    private ConstraintLayout mainLayout;

    private TextView accountName;
    private TextView id;
    private TextView email;

    private void Initialize()
    {
        accountName = findViewById(R.id.setting_AccoutName);
        id = findViewById(R.id.setting_ID);
        email = findViewById(R.id.setting_Email);

        accountName.setText("UserName : " + MainActivity.user.getName());
        id.setText("id : " + MainActivity.user.getID() + "");
        email.setText("Email : " + MainActivity.user.getEmail());

        Button b = findViewById(R.id.Logout);
        b.setBackgroundColor(Color.RED);

        b.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(AccountSetting.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        Initialize();
    }
}
