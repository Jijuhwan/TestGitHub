package com.example.helloworld.myapplication.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CompareFragment extends Fragment{
    MainActivity activity;
    Button compareBtn;
    ImageView questionsignimg = null, yellowlightimg = null, greenlightimg = null, redlightimg =null,
            questionmarkimg = null, lessgrowimg1= null, normalgrowimg1 = null, moregrowimg1=null;
    TextView touchbtntxt = null, lesstxt = null, normaltxt = null, moretxt = null;

    //유아 비교
    //우리 아이 <  평균 아이 = 0,1
    //우리 아이 = 평균 아이 = 2,3,4
    //우리 아이 > 평균 아이 = 5,6
    int imageIndex = 0; // else 값

    //아이 개월 수
    String Month;

    public static String babyHeight;
    public static String babyWeight;
    public static String babyHead;
    public static String babyMonth;
    public static String babyGender;

    String savgHeight;
    String savgWeight;
    String savgHead;

    int Height;
    int avgHeight;

    avg task;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        activity = null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup mainFragmentLayout, @Nullable Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.main_compare,mainFragmentLayout,false);

        compareBtn = (Button)view.findViewById(R.id.compareBtn);
        questionsignimg = (ImageView)view.findViewById(R.id.questionsignimg);
        yellowlightimg = (ImageView)view.findViewById(R.id.yellowlightimg);
        greenlightimg = (ImageView)view.findViewById(R.id.greenlightimg);
        redlightimg = (ImageView)view.findViewById(R.id.redlightimg);
        questionmarkimg =  (ImageView)view.findViewById(R.id.questionmarkimg);
        lessgrowimg1 = (ImageView)view.findViewById(R.id.lessgrowimg1);
        normalgrowimg1= (ImageView)view.findViewById(R.id.normalgrowimg1);
        moregrowimg1 = (ImageView)view.findViewById(R.id.moregrowimg1);
        touchbtntxt = (TextView)view.findViewById(R.id.touchbtntxt);
        lesstxt = (TextView)view.findViewById(R.id.lesstxt);
        normaltxt = (TextView)view.findViewById(R.id.normaltxt);
        moretxt = (TextView)view.findViewById(R.id.moretxt);

        //시간 불러오는 메소드
        getTime();

            task = new avg();
            task.execute(Month);

        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //유아비교 메소드
                compare();

                changeImage();

            }

        });

        return view;
    }

    public void compare()
    {
        imageIndex = 0;
        //평균보다 작다
        int Height = Integer.parseInt(babyHeight);
        int avgHeight = Integer.parseInt(savgHeight);
        if(Height < avgHeight)
        {
            imageIndex += 0;
        }
        else if(Height == avgHeight)
        {
            imageIndex += 1;
        }else
        {
            imageIndex += 2;
        }
        //평균이다.
        int Weight = Integer.parseInt(babyWeight);
        int avgWeight = Integer.parseInt(savgWeight);
        if(Weight < avgWeight)
        {
            imageIndex += 0;
        }
        else if(Weight == avgWeight)
        {
            imageIndex += 1;
        }else
        {
            imageIndex += 2;
        }
        //평균보다 크다
        int Head = Integer.parseInt(babyHead);
        int avgHead = Integer.parseInt(savgHead);
        if(Head < avgHead)
        {
            imageIndex += 0;
        }
        else if(Head == avgHead)
        {
            imageIndex += 1;
        }else
        {
            imageIndex += 2;
        }
    }

    //현재 시간 메소드
    public void getTime(){
        long mNow = System.currentTimeMillis();
        SimpleDateFormat mFormat = new SimpleDateFormat("yy MM");
        Date mDate = new Date(mNow);
        String nTime = mFormat.format(mDate);

        String sday = nTime.split(" ")[0];
        String stime = nTime.split(" ")[1];

        int day = Integer.parseInt(sday);
        int time = Integer.parseInt(stime);

        String sYear = babyMonth.substring(0,2); //입력한 년
        String sMonth = babyMonth.substring(2,4);//입력한 달

        int iyear = Integer.parseInt(sYear); //입력한 년 int
        int imonth = Integer.parseInt(sMonth); //입력한 달 int

        int year = (day - iyear);
        int month = (year*12) + time -imonth;
        Month = String.valueOf(month);

    }
    private void changeImage() {
        if(imageIndex == 0 || imageIndex == 1)
        {
            //우리 아이 <  평균 아이
            questionsignimg.setVisibility(View.INVISIBLE);
            yellowlightimg.setVisibility(View.VISIBLE);
            greenlightimg.setVisibility(View.INVISIBLE);
            redlightimg.setVisibility(View.INVISIBLE);

            questionmarkimg.setVisibility(View.INVISIBLE);
            lessgrowimg1.setVisibility(View.VISIBLE);
            normalgrowimg1.setVisibility(View.INVISIBLE);
            moregrowimg1.setVisibility(View.INVISIBLE);

            touchbtntxt.setVisibility(View.INVISIBLE);
            lesstxt.setVisibility(View.VISIBLE);
            normaltxt.setVisibility(View.INVISIBLE);
            moretxt.setVisibility(View.INVISIBLE);

        }
        else if(imageIndex == 2 || imageIndex == 3 || imageIndex == 4)
        {
            //우리 아이 = 평균 아이
            questionsignimg.setVisibility(View.INVISIBLE);
            yellowlightimg.setVisibility(View.INVISIBLE);
            greenlightimg.setVisibility(View.VISIBLE);
            redlightimg.setVisibility(View.INVISIBLE);

            questionmarkimg.setVisibility(View.INVISIBLE);
            lessgrowimg1.setVisibility(View.INVISIBLE);
            normalgrowimg1.setVisibility(View.VISIBLE);
            moregrowimg1.setVisibility(View.INVISIBLE);

            touchbtntxt.setVisibility(View.INVISIBLE);
            lesstxt.setVisibility(View.INVISIBLE);
            normaltxt.setVisibility(View.VISIBLE);
            moretxt.setVisibility(View.INVISIBLE);

        }
        else if(imageIndex == 5 || imageIndex == 6)
        {
            //우리 아이 > 평균 아이
            questionsignimg.setVisibility(View.INVISIBLE);
            yellowlightimg.setVisibility(View.INVISIBLE);
            greenlightimg.setVisibility(View.INVISIBLE);
            redlightimg.setVisibility(View.VISIBLE);

            questionmarkimg.setVisibility(View.INVISIBLE);
            lessgrowimg1.setVisibility(View.INVISIBLE);
            normalgrowimg1.setVisibility(View.INVISIBLE);
            moregrowimg1.setVisibility(View.VISIBLE);

            touchbtntxt.setVisibility(View.INVISIBLE);
            lesstxt.setVisibility(View.INVISIBLE);
            normaltxt.setVisibility(View.INVISIBLE);
            moretxt.setVisibility(View.VISIBLE);

        }
        else
        {
            //기본 상태
            questionsignimg.setVisibility(View.VISIBLE);
            yellowlightimg.setVisibility(View.INVISIBLE);
            greenlightimg.setVisibility(View.INVISIBLE);
            redlightimg.setVisibility(View.INVISIBLE);

            questionmarkimg.setVisibility(View.VISIBLE);
            lessgrowimg1.setVisibility(View.INVISIBLE);
            normalgrowimg1.setVisibility(View.INVISIBLE);
            moregrowimg1.setVisibility(View.INVISIBLE);

            touchbtntxt.setVisibility(View.VISIBLE);
            lesstxt.setVisibility(View.INVISIBLE);
            normaltxt.setVisibility(View.INVISIBLE);
            moretxt.setVisibility(View.INVISIBLE);

        }
    }

    private class avg extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
                String month =  arg0[0];
                String link;

                if(babyGender.equals("1"))
                {
                    link = "http://otl9882.codns.com:443/manmodel.php?Month=" + month;
                }
                else {
                    link = "http://otl9882.codns.com:443/girlmodel.php?Month=" + month;
                }

                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer sb = new StringBuffer("");
                String line = "";

                while ((line = in.readLine()) != null) {
                    sb.append(line);
                    break;
                }
                in.close();
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }

        }

        @Override
        protected void onPostExecute(String result){
            savgHeight = result.split(",")[0];    //키
            savgWeight = result.split(",")[1];    //몸무게
            savgHead = result.split(",")[2];    //머리이이이잇
        }
    }
}

