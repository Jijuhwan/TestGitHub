package com.example.helloworld.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.helloworld.myapplication.R;

public class CancelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_login);

        Button btnRegist = (Button)findViewById(R.id.btnRegist);
        //Button btnCancel = (Button)findViewById(R.id.btnCancel);



    }
}
