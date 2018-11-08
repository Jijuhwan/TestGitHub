package com.example.helloworld.myapplication.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.weather.ForeCastManager;
import com.example.helloworld.myapplication.weather.WeatherInfo;
import com.example.helloworld.myapplication.weather.WeatherToHangeul;

import java.util.ArrayList;

public class ClothesFragment extends Fragment {
    MainActivity activity;

    public static final int THREAD_HANDLER_SUCCESS_INFO = 1;
    TextView tv_WeatherInfo;
    TextView tv_DayPrint;

    ForeCastManager mForeCast;

    String lon = "128.3910799"; // 좌표 설정
    String lat = "36.1444292";  // 좌표 설정
    ArrayList<ContentValues> mWeatherData;
    ArrayList<WeatherInfo> mWeatherInfomation;
    ClothesFragment mThis;

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
        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.main_clothes,mainFragmentLayout,false);

        tv_DayPrint = (TextView)view.findViewById(R.id.tvDayData);
        Initialize();

        return view;
    }public void Initialize() {
        mWeatherInfomation = new ArrayList<>();
        mThis = this;
        mForeCast = new ForeCastManager(lon, lat, mThis);
        mForeCast.run();
    }


    public String PrintDay(){
        String mData = "";
        for(int i =0; i < mWeatherInfomation.size(); i++){
            mData =  mWeatherInfomation.get(i).getWeather_Day_Go() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Day_End() + "\r\n";
        }
        return mData;
    }

    public String PrintValue() {
        String mData = "";
        for (int i = 0; i < mWeatherInfomation.size(); i++) {
            mData = mWeatherInfomation.get(i).getWeather_Day_Go() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Day_End() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Name() + "\r\n"
                    + mWeatherInfomation.get(i).getClouds_Sort() + "\r\n"
                    + "구름 양 : " + mWeatherInfomation.get(i).getClouds_Value()
                    + mWeatherInfomation.get(i).getClouds_Per() + "\r\n"
                    + mWeatherInfomation.get(i).getWind_Name() + "\r\n"
                    + "바람 속도 : " + mWeatherInfomation.get(i).getWind_Speed() + " mps" + "\r\n"
                    + "최대 기온 : " + mWeatherInfomation.get(i).getTemp_Max() + "℃" + "\r\n"
                    + "최저 기온: " + mWeatherInfomation.get(i).getTemp_Min() + "℃" + "\r\n"
                    + "습도: " + mWeatherInfomation.get(i).getHumidity() + "%"
                    + "i의 크기 : " + i;

            mData = mData + "\r\n" + "----------------------------------------------" + "\r\n";
        }
        return mData;
    }

    public void DataChangedToHangeul() {
        for (int i = 0; i < mWeatherInfomation.size(); i++) {
            WeatherToHangeul mHangeul = new WeatherToHangeul(mWeatherInfomation.get(i));
            mWeatherInfomation.set(i, mHangeul.getHangeulWeather());
        }
    }


    public void DataToInformation() {
        for (int i = 0; i < mWeatherData.size(); i++) {
            mWeatherInfomation.add(new WeatherInfo(
                    String.valueOf(mWeatherData.get(i).get("weather_Name")), String.valueOf(mWeatherData.get(i).get("weather_Number")), String.valueOf(mWeatherData.get(i).get("weather_Much")),
                    String.valueOf(mWeatherData.get(i).get("weather_Type")), String.valueOf(mWeatherData.get(i).get("wind_Direction")), String.valueOf(mWeatherData.get(i).get("wind_SortNumber")),
                    String.valueOf(mWeatherData.get(i).get("wind_SortCode")), String.valueOf(mWeatherData.get(i).get("wind_Speed")), String.valueOf(mWeatherData.get(i).get("wind_Name")),
                    String.valueOf(mWeatherData.get(i).get("temp_Min")), String.valueOf(mWeatherData.get(i).get("temp_Max")), String.valueOf(mWeatherData.get(i).get("humidity")),
                    String.valueOf(mWeatherData.get(i).get("Clouds_Value")), String.valueOf(mWeatherData.get(i).get("Clouds_Sort")), String.valueOf(mWeatherData.get(i).get("Clouds_Per")),
                    String.valueOf(mWeatherData.get(i).get("weather_Day_Go")), String.valueOf(mWeatherData.get(i).get("weather_Day_End"))
            ));

        }

    }

    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case THREAD_HANDLER_SUCCESS_INFO:
                    mForeCast.getmWeather();
                    mWeatherData = mForeCast.getmWeather();
                    if (mWeatherData.size() == 0)
                        tv_WeatherInfo.setText("데이터가 없습니다");

                    DataToInformation(); // 자료 클래스로 저장,

                    String data = "";
                    String dayData = "";
                    DataChangedToHangeul();
                    data = data + PrintValue();
                    dayData = dayData + PrintDay();

                    tv_DayPrint.setText(dayData);
                    break;
                default:
                    break;
            }
        }
    };
}