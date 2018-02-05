
/*
 * Copyright (c) University of Alberta -All Rights Reserved.
 */

package com.example.imtih.imtihan_subbook;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import static com.example.imtih.imtihan_subbook.R.layout.list_item;

public class SubBookMainActivity extends AppCompatActivity {

    private static final String FILENAME = "datalist.sav";
    private ListView SubList;
    private TextView costVal;
    private ArrayList<Subscription> subscriptionlist;
    private ArrayAdapter<Subscription> adapter;

    private String totalCost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_book_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        costVal = (TextView) findViewById(R.id.costVal);
        SubList = (ListView) findViewById(R.id.SubList);
        totalCost = getTotalCost();
        costVal.setText(totalCost);
        SubList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                openEditSubscription(position);
                Snackbar.make(view, "Changes Saved", Snackbar.LENGTH_LONG).show();
                loadFromFile();
                adapter.notifyDataSetChanged();
                totalCost = getTotalCost();
                costVal.setText(totalCost);
            }
        });
        FloatingActionButton addSubButton = (FloatingActionButton) findViewById(R.id.addSub);
        addSubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddSubscriber();
                Snackbar.make(view, "Subscription Saved", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                loadFromFile();
                adapter.notifyDataSetChanged();
                totalCost = getTotalCost();
                costVal.setText(totalCost);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sub_book_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loadFromFile();
        adapter = new ArrayAdapter<Subscription>(this,android.R.layout.simple_list_item_1, subscriptionlist);
        SubList.setAdapter(adapter);
        costVal = (TextView) findViewById(R.id.costVal);
        totalCost = getTotalCost();
        costVal.setText(totalCost);
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

    private void openAddSubscriber(){
        Intent intent = new Intent(this, addSubscriber.class);
        startActivity(intent);

    }

    private void openEditSubscription(int val){
        Intent intent = new Intent(this, editItem.class);
        Bundle b = new Bundle();
        b.putInt(getString(R.string.index), val);
        intent.putExtras(b);
        startActivity(intent);
    }

    private String getTotalCost(){
        double sum = 0.00;
        try {
            for (int i = 0; i < subscriptionlist.size(); i++) {
                sum += Double.parseDouble(subscriptionlist.get(i).getCharge());
            }
            return Double.toString(sum);
        } catch (Exception e){
            return Double.toString(sum);
        }
    }

}
