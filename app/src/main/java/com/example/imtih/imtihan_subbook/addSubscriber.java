package com.example.imtih.imtihan_subbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class addSubscriber extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_subscription);
    }

    public void onAddSubscriberClick(View v){
        if(v.getId() == R.id.AddSubscription){
            EditText name = (EditText) findViewById(R.id.Name);
            EditText date = (EditText) findViewById(R.id.Date);
            EditText charge = (EditText) findViewById(R.id.Charge);
            EditText comment = (EditText) findViewById(R.id.Comment);

            String namestr = name.getText().toString();

        }
    }
}
