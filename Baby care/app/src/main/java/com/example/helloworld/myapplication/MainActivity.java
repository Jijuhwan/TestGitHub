package com.example.helloworld.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_clothes:
                    Intent login1 = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(login1);
                    return true;
                case R.id.navigation_daily:
                    Intent login2 = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(login2);
                    return true;
                case R.id.navigation_compare:
                    Intent login3 = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(login3);
                    return true;
                case R.id.navigation_board:
                    Intent login4 = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(login4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
