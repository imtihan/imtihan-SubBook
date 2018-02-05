package com.example.imtih.imtihan_subbook;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class addSubscriber extends AppCompatActivity {
    private static final String FILENAME = "datalist.sav";

    private TextView date;
    private TextView charge;
    private TextView name;
    private TextView comment;
    private Calendar mydate;
    private int day, month, year;
    private String nameString;
    private String commentString;
    private float chargeFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_subscription);

        Button save = (Button) findViewById(R.id.AddSubscription);
        date = (TextView) findViewById(R.id.Date);
        charge = (TextView) findViewById(R.id.Charge);
        name = (TextView) findViewById(R.id.Name);
        comment = (TextView) findViewById(R.id.Comment);

        mydate = Calendar.getInstance();
        day = mydate.get(Calendar.DAY_OF_MONTH);
        month = mydate.get(Calendar.MONTH);
        year = mydate.get(Calendar.YEAR);

        date.setText("Date");
        charge.setText("Monthly Charge");

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                DatePickerDialog datePickerDialog = new DatePickerDialog(addSubscriber.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        date.setText(year+"/"+month+"/"+dayOfMonth);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        // References: https://www.youtube.com/watch?v=5qdnoRHfAYU
        // 2018-02-02

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

    }


}
