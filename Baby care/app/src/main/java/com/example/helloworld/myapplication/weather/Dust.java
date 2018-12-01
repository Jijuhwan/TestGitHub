package com.example.helloworld.myapplication.weather;

import android.os.AsyncTask;
import android.util.Log;

import com.example.helloworld.myapplication.fragment.ClothesFragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Dust extends AsyncTask<String, Void, String> {

    private String str, receiveMsg, aqi;

    public static String city;

    @Override
    protected String doInBackground(String... params) {
        URL url = null;
        String lon = ClothesFragment.Slon;
        String lat = ClothesFragment.Slat;

        if ( lat == null&& lon == null){
            lon = "126.969630"; // 경도 설정
            lat =  "37.553794"; // 위도 설정
        }
        try {
            url = new URL("https://api.waqi.info/feed/geo:" + lat + ";" + lon + "/?token=41da8ebfbc9cc68442af347291689e8cbeb5a9b1");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();

                JSONObject jObject = new JSONObject(receiveMsg);

                JSONObject obj1 = jObject.getJSONObject("data");

                JSONObject obj2 = obj1.getJSONObject("city");

                aqi = obj1.getString("aqi");

                int iaqi = Integer.parseInt(aqi);

                if(iaqi >= 0 && iaqi <= 50)
                {
                    aqi = aqi + "\n미세먼지 상태 : 좋음";
                }
                else if(iaqi >50 && iaqi <= 100){
                    aqi = aqi + "\n미세먼지 상태 : 보통";
                }
                else if(iaqi >101 && iaqi <= 150){
                    aqi = aqi + "\n미세먼지 상태 : 살짝나쁨";
                }
                else if(iaqi >151 && iaqi <= 200){
                    aqi = aqi + "\n미세먼지 상태 : 나쁨";
                }
                else if(iaqi >201 && iaqi <= 300){
                    aqi = aqi + "\n미세먼지 상태 : 매우나쁨";
                }
                else{
                    aqi = aqi + "\n미세먼지 상태 : 위험";
                }

                String scity = obj2.getString("name");
                city = scity.split(",")[1];


                reader.close();
            } else {
                Log.i("통신 결과", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "미세먼지 지수 : " + aqi;
    }

}

