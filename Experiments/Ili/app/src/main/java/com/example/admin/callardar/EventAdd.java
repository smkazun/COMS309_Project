package com.example.admin.callardar;

import android.app.AppComponentFactory;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.admin.callardar.MainActivity;
import com.example.admin.callardar.R;

public class EventAdd extends AppCompatActivity {
    private Button select;
    private EditText title, date, time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);
        select = findViewById(R.id.SelectAdd);
        title = findViewById(R.id.EventAdd);
        date = findViewById(R.id.DateAdd);
        time = findViewById((R.id.TimeAdd));


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.user.getCalender()[CalendarList.iem].Event(title.getText().toString(), date.getText().toString(), time.getText().toString());

                Intent i = new Intent(EventAdd.this, EventActivity.class);
                startActivity(i);
            }

        });
    }
}

