package com.example.helloworld.myapplication.util;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.fragment.BoardFragment;
import com.example.helloworld.myapplication.fragment.ClothesFragment;
import com.example.helloworld.myapplication.fragment.CompareFragment;
import com.example.helloworld.myapplication.fragment.DailyFragment;

public class LoginActivity extends AppCompatActivity {

    ViewFlipper Vf;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;
    user utask;
    delete dtask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_login);

        TextView tv = (TextView)findViewById(R.id.tv);
        ImageView imgSignUp = (ImageView)findViewById(R.id.imgRegist);
        ImageView imgSignout = (ImageView)findViewById(R.id.imgNo);

        //자동 로그인
        //SharedPreferences auto = getSharedPreferences("auto", Activity.MODE_PRIVATE);
        //뭐징...
        //StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        //StrictMode.setThreadPolicy(policy);

        //로그인 시작
        ImageView imgSignIn = (ImageView)findViewById(R.id.imgOk);
        imgSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(LoginActivity.this, "로그인 중",
                        "회원정보를 찾고 있습니다...", true);
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();
            }
        });
        ImageView imgSignEnd = (ImageView)findViewById(R.id.imgCancel);
        imgSignEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inID = (EditText) findViewById(R.id.etID);
                EditText inPW = (EditText) findViewById(R.id.etPW);
                String Id = inID.getText().toString();
                String Pw = inPW.getText().toString();

                if (Id.equals("")) {
                    Toast.makeText(LoginActivity.this, "삭제하실 ID가 공백입니다.", Toast.LENGTH_SHORT).show();
                } else if (Pw.equals("")) {
                    Toast.makeText(LoginActivity.this, "삭제하실 ID의 비밀번호가 공백입니다.", Toast.LENGTH_SHORT).show();
                } else {
                    dialog = ProgressDialog.show(LoginActivity.this, "삭제 요청중",
                            "회원정보를 찾고 있습니다...", true);
                    new Thread(new Runnable() {
                        public void run() {
                            sDelete();
                        }
                    }).start();
                }
            }
        });
    }

    void login() {
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://otl9882.codns.com:443/login.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);

            EditText inputID = (EditText)findViewById(R.id.etID);
            EditText inputPW = (EditText)findViewById(R.id.etPW);

            nameValuePairs.add(new BasicNameValuePair("username", inputID.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", inputPW.getText().toString()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"" + response,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            if (response.equalsIgnoreCase("로그인 성공")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        MainActivity.LOGINRECORD = 1;
                        EditText inID = (EditText)findViewById(R.id.etID);
                        String Id = inID.getText().toString();

                        utask = new user();
                        utask.execute(Id);

                    }
                });

                startActivity((new Intent(LoginActivity.this, MainActivity.class)));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "로그인에 실패하셨습니다.", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }


    //아이디 삭제
    void sDelete() {
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://otl9882.codns.com:443/sDelete.php");
            nameValuePairs = new ArrayList<NameValuePair>(2);

            EditText inputID = (EditText)findViewById(R.id.etID);
            EditText inputPW = (EditText)findViewById(R.id.etPW);

            nameValuePairs.add(new BasicNameValuePair("username", inputID.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", inputPW.getText().toString()));

            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpclient.execute(httppost);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            final String response = httpclient.execute(httppost, responseHandler);
            System.out.println("Response : " + response);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(LoginActivity.this,"" + response,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            inputID.setText("");
            inputPW.setText("");

            if (response.equalsIgnoreCase("삭제 중...")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        EditText inID = (EditText)findViewById(R.id.etID);
                        String Id = inID.getText().toString();

                        dtask = new delete();
                        dtask.execute(Id);

                    }
                });

            } else {
                Toast.makeText(LoginActivity.this, "삭제 실패", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    private class user extends AsyncTask<String,Void,String> {

        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... arg0) {

            try {
                String id = arg0[0];

                String link = "http://otl9882.codns.com:443/month.php?Id=" + id;

                URL url = new URL(link);
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
        protected void onPostExecute(String result) {
            DailyFragment.sbabymonth = result.split(",")[3];
            CompareFragment.babyHeight = result.split(",")[0];
            CompareFragment.babyWeight = result.split(",")[1];
            CompareFragment.babyHead = result.split(",")[2];
            CompareFragment.babyMonth = result.split(",")[3];
            CompareFragment.babyGender = result.split(",")[4];


        }
    }
        private class delete extends AsyncTask<String, Void, String> {

            protected void onPreExecute() {

            }

            @Override
            protected String doInBackground(String... arg0) {

                try {
                    String id = arg0[0];

                    String link = "http://otl9882.codns.com:443/delete.php?Id=" + id;

                    URL url = new URL(link);
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
            protected void onPostExecute(String result) {

                Toast.makeText(LoginActivity.this, "삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();

            }

        }

    public void signUp(View view)
    {
        Intent intent = new Intent(this, RegistActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void signNo(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}