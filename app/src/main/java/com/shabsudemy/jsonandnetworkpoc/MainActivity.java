package com.shabsudemy.jsonandnetworkpoc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.shabsudemy.jsonandnetworkpoc.JSONToRV.JSONToRVActivity;
import com.shabsudemy.jsonandnetworkpoc.retrofitMVVM.RetrofitMVVMActivity;
import com.shabsudemy.jsonandnetworkpoc.retrofitsample.RetrofitPOCActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button jTRVButton1;
    Button jTRVButton2;
    Button jTRVButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JSONSample.simpleJSONParser();
        JSONSample.simpleJSONParserFromURLAsyncTask("https://run.mocky.io/v3/3a38e2b5-5b4f-43e0-a464-98f0f1f41003");

        jTRVButton1 = (Button) findViewById(R.id.recyclerviewButton1);
        jTRVButton2 = (Button) findViewById(R.id.recyclerviewButton2);
        jTRVButton3 = (Button) findViewById(R.id.recyclerviewButton3);

        jTRVButton1.setOnClickListener(this);
        jTRVButton2.setOnClickListener(this);
        jTRVButton3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.recyclerviewButton1:
                i = new Intent(this, JSONToRVActivity.class);
                startActivity(i);
                break;
            case R.id.recyclerviewButton2:
                i = new Intent(this, RetrofitPOCActivity.class);
                startActivity(i);
                break;
            case R.id.recyclerviewButton3:
                i = new Intent(this, RetrofitMVVMActivity.class);
                startActivity(i);
                break;
            default:
                break;
        }
    }
}