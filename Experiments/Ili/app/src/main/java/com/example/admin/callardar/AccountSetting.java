package com.example.admin.callardar;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class AccountSetting extends AppCompatActivity
{
    private ConstraintLayout mainLayout;

    private ImageView pic;
    private EditText name;
    private TextView accountName;
    private TextView id;
    private TextView email;

    private void Initialize()
    {
        mainLayout = findViewById(R.id.setting_mainLayout);
        mainLayout.setBackgroundColor(Color.BLUE);

        pic = findViewById(R.id.setting_PICofUSER);
        name = findViewById(R.id.setting_Name);
        accountName = findViewById(R.id.setting_AccoutName);
        id = findViewById(R.id.setting_ID);
        email = findViewById(R.id.setting_Email);

        Random r = new Random();
        pic.setBackgroundColor(Color.rgb(r.nextInt(255), r.nextInt(255), r.nextInt(255)));

        //toDO get the real name
        name.setText("DEFAULT");

        accountName.setText(MainActivity.user.getName());
        id.setText((MainActivity.user.getID() + ""));
        email.setText(MainActivity.user.getEmail());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);

        Initialize();
    }
}
