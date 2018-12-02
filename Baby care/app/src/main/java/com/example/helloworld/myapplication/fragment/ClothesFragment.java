package com.example.helloworld.myapplication.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.weather.Dust;
import com.example.helloworld.myapplication.weather.ForeCastManager;
import com.example.helloworld.myapplication.weather.WeatherInfo;
import com.example.helloworld.myapplication.weather.WeatherToHangeul;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class ClothesFragment extends Fragment {
    public static final int THREAD_HANDLER_SUCCESS_INFO = 1;
    MainActivity activity;
    ForeCastManager mForeCast;

    TextView tvLocal;
    TextView tvTemp;
    TextView tvCloud;
    ImageView ivCloud;
    ImageView ivClothesData;
    TextView tvClothes;
    TextView tvDustData;
    Button btnSetGPS;
    //상태바
    ProgressDialog dialog;

    //날짜 변수
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("dd HH");
    String nTime;
    String day;
    String time;
    //현재 시간의 i 값
    int ni = 1;

    //설정할 GPS
    public static String Slat;
    public static String Slon;

    //위치정보를 공급하는 근원
    String locationProvider;
    //위치 정보 매니져 객체
    LocationManager locationManager;
    //먼지 값
    String sDust;

    //기본 GPS설정
    String lon = "126.969630"; // 경도 설정
    String lat =  "37.553794"; // 위도 설정
    ArrayList<ContentValues> mWeatherData;
    ArrayList<WeatherInfo> mWeatherInfomation;
    ClothesFragment mThis;

    @Override
    public void onAttach(Context context) {
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
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.main_clothes, mainFragmentLayout, false);

        //지역
        tvLocal = (TextView) view.findViewById(R.id.tvLocal);
        //온도
        tvTemp = (TextView) view.findViewById(R.id.tvTemp);
        //구름량
        tvCloud = (TextView) view.findViewById(R.id.tvCloud);
        //구름 이미지
        ivCloud = (ImageView) view.findViewById(R.id.ivCloud);
        //미세먼지
        tvDustData = (TextView) view.findViewById(R.id.tvDustData);
        //외출복
        ivClothesData = (ImageView) view.findViewById(R.id.ivClothesData);
        tvClothes = (TextView)view.findViewById(R.id.tvCloethesText);

        btnSetGPS = (Button) view.findViewById(R.id.btnSetGPS);
        Button btnLogout = (Button) view.findViewById(R.id.btnLogout);
        Button btnInformation = (Button) view.findViewById(R.id.btnInformation);

        sDust = "미세먼지 정보가 없습니다.";

        try{
            sDust = new Dust().execute().get();
        }catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        tvDustData.setText(sDust);

        // LocationManager 객체를 얻어온다
        final LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        //자신의 위치 GPS 값 받기
        btnSetGPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( Build.VERSION.SDK_INT >= 23 &&
                        ContextCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                        ContextCompat.checkSelfPermission(
                                getContext(), Manifest.permission.ACCESS_COARSE_LOCATION)
                                != PackageManager.PERMISSION_GRANTED){
                    return;
                }

                // GPS 제공자의 정보가 바뀌면 콜백하도록 리스너 등록하기~!!!
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, // 등록할 위치제공자
                        1000, // 통지사이의 최소 시간간격 (miliSecond)
                        0, // 통지사이의 최소 변경거리 (m)
                        mLocationListener);

                dialog = ProgressDialog.show(getContext(), "위치 찾는 중",
                        "현재 위치를 찾고 있습니다.", true);

                //위치정보 받을 대기시간
                Handler mHandler = new Handler();
                mHandler.postDelayed(new Runnable()  {
                    public void run() {

                        Toast.makeText(getContext(),"위치 정보를 갱신하였습니다.",Toast.LENGTH_SHORT).show();

                        lon = Slon;
                        lat = Slat;

                        try{
                            sDust = new Dust().execute().get();
                        }catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(mThis).attach(mThis).commit();
                        dialog.dismiss();
                        lm.removeUpdates(mLocationListener);  //  미수신할때는 반드시 자원해체를 해주어야 한다.

                    }
                }, 10000);
            }
        });

        //로그아웃 버튼
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.LOGINRECORD == 1)
                {
                    Toast.makeText(getActivity(), "로그아웃이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    MainActivity.LOGINRECORD = 0;
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            }
        });

        //앱 정보 버튼
        btnInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_information, (ViewGroup) v.findViewById(R.id.Information));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(getContext());

                aDialog.setTitle("오픈소스 라이선스");
                aDialog.setView(layout);

                AlertDialog ad = aDialog.create();
                ad.show();
            }
        });

        //초기화 메소드
        Initialize();
        //시간 받는 메소드
        getTime();

        return view;
    }

    //초기화
    public void Initialize() {

        mWeatherInfomation = new ArrayList<>();
        mThis = this;
        mForeCast = new ForeCastManager(lon, lat, mThis);
        mForeCast.run();
    }
    //현재 시간 메소드
    public void getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        nTime = mFormat.format(mDate);

        day = nTime.split(" ")[0];
        time = nTime.split(" ")[1];

    }
    //지역 출력 메소드
    public String LocalPrint(){
        String LocalData = "";

        /*Bundle bundle = getArguments();
        if(bundle != null )
        {
            city = bundle.getString("city");
            LocalData = city;
        }*/

        if(Dust.city ==  null){
            LocalData = "Seoul";
        }else{
            LocalData = Dust.city;
        }

        return LocalData;
    }
    /* 1 : 00 ~ 03 / 2 : 03 ~ 06 / 3 : 06 ~ 09 / 4 : 09 ~ 12
       5 : 12 ~ 15 / 6 : 15 ~ 18 / 7 : 18 ~ 21 / 8 : 21 ~ 24 */
    //온도 출력 메소드
    public String TempPrint(){
        String TempData = "";
        TempData =  "최대 기온 : " + mWeatherInfomation.get(ni).getTemp_Max() + "\n"+
                    "최저 기온 : " + mWeatherInfomation.get(ni).getTemp_Min();
        return TempData;
    }
    //현재 날씨 출력 메소드
    public String CloudPrint(){
        String CloudData = "";

        CloudData =   mWeatherInfomation.get(ni).getWeather_Name();

        return CloudData;
    }

    //평균 온도 출력 메소드
    public double AvgTempPrint(){

        Double AvgTemp = 0.0;
        AvgTemp =   (Double.parseDouble(mWeatherInfomation.get(ni).getTemp_Max()) + Double.parseDouble(mWeatherInfomation.get(ni).getTemp_Min()))/2;
        return AvgTemp;
    }

    //날씨 정보 보여주는 메소드(삭제X)
    public String PrintValue() {
        String mData = "";
        for (int i = 0; i < mWeatherInfomation.size(); i++) {
            mData = mData + mWeatherInfomation.get(i).getWeather_Day_Go() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Day_End() + "\r\n"
                    + mWeatherInfomation.get(i).getWeather_Name() + "\r\n"
                    + "구름 양 : " + mWeatherInfomation.get(i).getClouds_Value()
                    + mWeatherInfomation.get(i).getClouds_Per() + "\r\n"
                    + mWeatherInfomation.get(i).getWind_Name() + "\r\n"
                    + "바람 속도 : " + mWeatherInfomation.get(i).getWind_Speed() + " mps" + "\r\n"
                    + "최대 기온 : " + mWeatherInfomation.get(i).getTemp_Max() + "℃" + "\r\n"
                    + "최저 기온: " + mWeatherInfomation.get(i).getTemp_Min() + "℃" + "\r\n"
                    + "습도: " + mWeatherInfomation.get(i).getHumidity() + "%" + "\r\n"
                    + "지역: " + Dust.city
                    + "i의 크기 : " + i ;

            mData = mData + "\r\n" + "----------------------------------------------" + "\r\n";
        }
        return mData;
    }

    public String Now(){
        String now = "";
        //오늘 요일
        String nday = "";
        //오늘 시간
        String temp = "";
        String ntime1 = "";
        String ntime2 = "";
        int ptime = 0;
        int ntime = 0;
        int a = 0;
        int b = 0;

        for(int i =0; i <mWeatherInfomation.size(); i++){
            //now = 요일이 나온다.
            temp = mWeatherInfomation.get(i).getWeather_Day_End().split("T")[0];
            nday = temp.split("-")[2];
            ntime1 = mWeatherInfomation.get(i).getWeather_Day_End().split("T")[1];
            ntime2 = ntime1.split(":")[0];
            //파싱 time
            ptime = Integer.parseInt(ntime2);
            //현재 time
            ntime = Integer.parseInt(time);

            if(nday.contains(day) && b == 0)
            {
                //i값마다 nday가 하나씩 증가
                //now = now + mWeatherInfomation.get(i).getWeather_Day_End() + "\r\n";

                a = 1;

                //now = now + "\r\n" + "----------------------------------------------" + "\r\n";
            }
            //0,1,2 - 3,4,5 - 6,7,8 - 9,10,11 - 12,13,14 - 15,16,17 - 18,19,20 - 21,22,23
            //0~3시
            if(ntime >= 0 && ptime == 3 && a == 1 && ntime <= ptime)
            {
                //현재의 i 값을 ni에 저장
                ni = i;
                a = 0;
                b =  1;
            }
            //3~6시
            else if(ntime >= 3 && ptime == 6 && a == 1 && ntime <= ptime)
            {
                ni = i;
                a = 0;
                b =  1;
            }
            //6~9시
            else if(ntime >= 6 && ptime == 9 && a == 1 && ntime <= ptime)
            {
                ni = i;
                a = 0;
                b = 1;
            }
            //9~12시
            else if(ntime >= 9 && ptime == 12 && a == 1 && ntime <= ptime)
            {
                ni = i;
                a = 0;
                b =  1;
            }
            //12~15시
            else if(ntime >= 12 && ptime == 15 && a == 1 && ntime <= ptime)
            {
                ni = i;
                a = 0;
                b =  1;
            }
            //15~18시
            else if(ntime >= 15 && ptime == 18 && a == 1 && ntime <= ptime)
            {
                ni = i;
                a = 0;
                b = 1;
            }
            //18~21시
            else if(ntime >= 18 && ptime == 21 && a == 1 && ntime <= ptime)
            {
                ni = i;
                a = 0;
                b =  1;
            }
            //21~24시
            else if(ntime >= 21 && ptime == 0 && a == 1)
            {
                ni = i;
                a = 0;
                b =  1;
            }


        }
        now =  now + mWeatherInfomation.get(ni).getWeather_Day_End() + "\r\n";
        return now;
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
                    String.valueOf(mWeatherData.get(i).get("weather_Day_Go")), String.valueOf(mWeatherData.get(i).get("weather_Day_End")), String.valueOf(mWeatherData.get(i).get("city"))
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
                        tvLocal.setText("데이터가 없습니다");
                       tvTemp.setText("데이터가 없습니다");
                       tvCloud.setText("데이터가 없습니다");
                       tvClothes.setText("데이터가 없습니다");

                    DataToInformation(); // 자료 클래스로 저장,

                    String localData = "";
                    String tempData = "";
                    String cloudData = "";
                    Double avgtempData = 0.0;
                    String now="";

                    DataChangedToHangeul();

                    localData = localData + LocalPrint();
                    tempData = tempData + TempPrint();
                    cloudData = cloudData + CloudPrint();
                    avgtempData = avgtempData + AvgTempPrint();
                    now = now + Now();

                    tvLocal.setText(localData);
                    tvTemp.setText(tempData);
                    tvCloud.setText(cloudData);

                    if(avgtempData >=26.0)
                    {
                        ivClothesData.setImageResource(R.drawable.tog);
                        tvClothes.setText("다리없는 바디수트");
                    }
                    else if(avgtempData >=24.0)
                    {
                        ivClothesData.setImageResource(R.drawable.temp24);
                        tvClothes.setText("다리없는 바디수트+보온성 겉옷");
                    }
                    else if(avgtempData >=22.0)
                    {
                        ivClothesData.setImageResource(R.drawable.temp22);
                        tvClothes.setText("전신 바디수트+보온성 겉옷");
                    }
                    else if(avgtempData >=20.0)
                    {
                        ivClothesData.setImageResource(R.drawable.temp20);
                        tvClothes.setText("다리없는 바디수트+전신 바디수트+보온성 겉옷");
                    }
                    else if(avgtempData >=18.0)
                    {
                        ivClothesData.setImageResource(R.drawable.temp18);
                        tvClothes.setText("보온 내복+전신 바디수트+보온성 겉옷");
                    }
                    else if(avgtempData >= 16.0)
                    {
                        ivClothesData.setImageResource(R.drawable.temp16);
                        tvClothes.setText("보온 내복+전신 바디수트+보온성 겉옷+양말");
                    }
                    else
                    {
                        ivClothesData.setImageResource(R.drawable.temp14);
                        tvClothes.setText("보온 내복+전신 바디수트+보온성 겉옷+양말+장갑+모자");
                    }

                    if(cloudData.contains("하늘")) {
                        ivCloud.setImageResource(R.drawable.sun);
                    }else if(cloudData.contains("비") || !cloudData.contains("번개")){
                        ivCloud.setImageResource(R.drawable.rain);
                    }else if(cloudData.contains("소나기")) {
                        ivCloud.setImageResource(R.drawable.rain);
                    }else if(cloudData.contains("흐림")) {
                        ivCloud.setImageResource(R.drawable.cloud);
                    }else if(cloudData.contains("번개") || cloudData.contains("천둥")) {
                        ivCloud.setImageResource(R.drawable.flash);
                    }else if(cloudData.contains("눈")) {
                        ivCloud.setImageResource(R.drawable.snow);
                    }else{
                        ivCloud.setImageResource(R.drawable.questionsign);
                    }
                    break;
                default:
                    break;
            }
        }
    };

    //GPS 값 전달
    private LocationListener mLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            //여기서 위치값이 갱신되면 이벤트가 발생한다.
            //값은 Location 형태로 리턴되며 좌표 출력 방법은 다음과 같다.

            double updateLon = location.getLongitude(); //경도
            double updateLat = location.getLatitude();   //위도

            Slat = String.valueOf(updateLat);
            Slon = String.valueOf(updateLon);

        }

        public void onStatusChanged(String provider, int i, Bundle bundle) {
            alterStatus(provider);
        }

        public void onProviderEnabled(String provider) {
            alterProvider(provider);
        }


        public void onProviderDisabled(String provider) {
            checkProvider(provider);
        }
    };

    public void checkProvider(String provider){
        Toast.makeText(getContext(),provider + "를 켜주세요.",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    public void alterProvider(String provider)
    {
        Toast.makeText(getContext(),provider + "가 켜졌습니다.",Toast.LENGTH_SHORT).show();
    }

    public void alterStatus(String provider)
    {
        Toast.makeText(getContext(),"위치서비스가 "+provider+"로 변경되었습니다.",Toast.LENGTH_SHORT).show();
    }

}