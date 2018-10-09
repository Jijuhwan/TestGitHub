package Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    board fmBoard;
    clothes fmClothes;
    compare fmCompare;
    daily fmDaily;
    mainHome fmHome;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            //로그인 기록 정보 확인
            int loginRecord = 1;
            if(loginRecord==0) {
                switch (item.getItemId()) {
                    case R.id.navigation_clothes:
                        Intent login1 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login1);
                        return true;
                    case R.id.navigation_daily:
                        Intent login2 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login2);
                        return true;
                    case R.id.navigation_compare:
                        Intent login3 = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(login3);
                        return true;
                    case R.id.navigation_board:
                        Intent login4 = new Intent(getApplicationContext(), LoginActivity.class);
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
        setContentView(R.layout.activity_main);

        fmBoard = new board();
        fmClothes = new clothes();
        fmCompare = new compare();
        fmDaily = new daily();
        fmHome = new mainHome();


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.mainFragmentLayout,fmHome).commit();
    }

}
