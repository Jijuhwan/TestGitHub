package com.example.helloworld.myapplication.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.fragment.BoardFragment;
import com.example.helloworld.myapplication.fragment.ClothesFragment;
import com.example.helloworld.myapplication.fragment.CompareFragment;
import com.example.helloworld.myapplication.fragment.DailyFragment;
import com.example.helloworld.myapplication.fragment.MainHomeFragment;
import com.example.helloworld.myapplication.util.LoginActivity;

import java.security.Permission;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;


public class MainActivity extends AppCompatActivity {

    //로그인 정보
    public static int LOGINRECORD = 0;
    private final int PERMISSIONS_REQUEST_RESULT = 1;

    BoardFragment fmBoard;
    ClothesFragment fmClothes;
    CompareFragment fmCompare;
    DailyFragment fmDaily;
    MainHomeFragment fmHome;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            //로그인 기록 정보 확인(비회원)
            if(LOGINRECORD==0) {
                switch (item.getItemId()) {
                    case R.id.navigation_clothes:
                        Intent login1 = new Intent(getApplicationContext(), LoginActivity.class);
                        login1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login1);
                        return true;
                    case R.id.navigation_daily:
                        Intent login2 = new Intent(getApplicationContext(), LoginActivity.class);
                        login2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login2);
                        return true;
                    case R.id.navigation_compare:
                        Intent login3 = new Intent(getApplicationContext(), LoginActivity.class);
                        login3.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login3);
                        return true;
                    case R.id.navigation_board:
                        Intent login4 = new Intent(getApplicationContext(), LoginActivity.class);
                        login4.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(login4);
                        return true;
                }
            }
            else{
                switch (item.getItemId()) {

                    case R.id.navigation_clothes:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout,fmClothes).commit(); // 여러개의 명을 만들어서 쓴다
                        // 꼭 commit 를 해주어야 실행이 된다. 플래그먼트 매니저가 플래그먼트를 담당 한다.
                        // add라고해서 추가를 해주지 않고 replace를 써주는데, 기존에 있던것들을 대체 해 주게 된다.

                        return true;
                    case R.id.navigation_daily:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout,fmDaily).commit();

                        return true;
                    case R.id.navigation_compare:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout,fmCompare).commit();

                        return true;
                    case R.id.navigation_board:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout,fmBoard).commit(); // 여러개의 명을 만들어서 쓴다
                        // 꼭 commit 를 해주어야 실행이 된다. 플래그먼트 매니저가 플래그먼트를 담당 한다.
                        // add라고해서 추가를 해주지 않고 replace를 써주는데, 기존에 있던것들을 대체 해 주게 된다.

                        return true;

                }
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)) {
                //권한을 거절하면 재 요청을 하는 함수
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_RESULT);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_RESULT);
            }
        }

        fmBoard = new BoardFragment();
        fmClothes = new ClothesFragment();
        fmCompare = new CompareFragment();
        fmDaily = new DailyFragment();
        fmHome = new MainHomeFragment();



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout,fmHome).commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (PERMISSIONS_REQUEST_RESULT == requestCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "권한 요청이 됐습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "권한 요청을 해주세요.", Toast.LENGTH_SHORT).show();
                finish();
            }
            return;
        }
    }
}
