package com.example.helloworld.myapplication.util;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.helloworld.myapplication.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

public class RegistByeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_registbye);




    }

    private class avg extends AsyncTask<String,Void,String> {

        protected void onPreExecute(){

        }
        @Override
        protected String doInBackground(String... arg0) {

            try {
                String id =  arg0[0];

                String link = "http://otl9882.codns.com:443/delete.php?ID=" + id;

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
            Toast.makeText(RegistByeActivity.this, "아이디가 삭제되었습니다.",Toast.LENGTH_SHORT).show();
        }
    }
}
