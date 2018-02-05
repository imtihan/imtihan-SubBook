
/*
 * Copyright (c) University of Alberta -All Rights Reserved.
 */

package com.example.imtih.imtihan_subbook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * This editItem activity opens an activity that displays the current Subscriber object
 * that is selected.
 * It allows for editing the subscription and to delete it if one so prefers
 *
 * @see Subscription
 * @see SubBookMainActivity
 * @see addSubscriber
 */
public class editItem extends AppCompatActivity {
    private static final String FILENAME = "datalist.sav";
    private int index;
    private ArrayList<Subscription> subscriptionlist;
    private Subscription sub = new Subscription();
    private EditText date;
    private EditText charge;
    private EditText name;
    private EditText comment;
    private Calendar mydate;
    private int day;
    private int month;
    private int year;
    private String nameString;
    private String commentString;
    private float chargeFloat;
    private String dateString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        Bundle b = getIntent().getExtras();
        index = b.getInt("index");
        loadFromFile();
        sub = subscriptionlist.get(index);
        Button save = (Button) findViewById(R.id.saveButton);
        Button delete = (Button) findViewById(R.id.deleteButton);
        date = (EditText) findViewById(R.id.Date);
        charge = (EditText) findViewById(R.id.Charge);
        name = (EditText) findViewById(R.id.Name);
        comment = (EditText) findViewById(R.id.Comment);


        date.setText(sub.getDate());
        charge.setText(sub.getCharge());
        name.setText(sub.getName());
        comment.setText(sub.getComment());

        charge.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(5,2)});

        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                DatePickerDialog datePickerDialog = new DatePickerDialog(editItem.this, new DatePickerDialog.OnDateSetListener() {
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
                if (name.getText().toString().equals("")){
                    Toast.makeText(editItem.this, "Name is required", Toast.LENGTH_SHORT).show();
                    //Snackbar.make(v, "Name required", Snackbar.LENGTH_LONG)
                    //      .show();
                } else  if(name.getText().toString().length() > 20){
                    Snackbar.make(v, "Name too long, limit is 20 characters", Snackbar.LENGTH_LONG)
                            .show();
                }  else if (date.getText().toString().equals("")){
                    Toast.makeText(editItem.this, "Date is required", Toast.LENGTH_SHORT).show();
                    //Snackbar.make(v, "Date required", Snackbar.LENGTH_LONG)
                    //      .show();
                } else if (comment.getText().toString().length() > 30){
                    Snackbar.make(v, "Comment too long, limit is 30 characters", Snackbar.LENGTH_LONG)
                            .show();
                } else if (charge.getText().toString().equals("")){
                    Snackbar.make(v, "Monthly charge required", Snackbar.LENGTH_LONG)
                            .show();
                } else{
                    nameString = name.getText().toString();
                    dateString = date.getText().toString();
                    chargeFloat = Float.parseFloat(charge.getText().toString());
                    commentString = comment.getText().toString();
                    Subscription editedSub = new Subscription(nameString, dateString, chargeFloat, commentString);
                    subscriptionlist.set(index, editedSub);
                    saveToFile();
                    finish();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subscriptionlist.remove(index);
                saveToFile();
                finish();
            }
        });

    }
    private void loadFromFile(){
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            //Taken https://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2018-01-31

            Type listType = new TypeToken<ArrayList<Subscription>>(){}.getType();

            subscriptionlist = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            subscriptionlist = new ArrayList<Subscription>();
        }
    }
    private void saveToFile(){
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(subscriptionlist, out);
            out.flush();

        } catch (FileNotFoundException e) {
            subscriptionlist = new ArrayList<Subscription>();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
