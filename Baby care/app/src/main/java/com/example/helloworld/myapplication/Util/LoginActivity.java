package com.example.helloworld.myapplication.util;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;

public class LoginActivity extends AppCompatActivity {

    ViewFlipper Vf;
    HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_login);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView tv = (TextView)findViewById(R.id.tv);
        ImageView imgSignUp = (ImageView)findViewById(R.id.imgRegist);
        ImageView imgSignEnd = (ImageView)findViewById(R.id.imgCancel);
        ImageView imgSignout = (ImageView)findViewById(R.id.imgNo);

        //로그인 시작
        ImageView imgSignIn = (ImageView)findViewById(R.id.imgOk);
        imgSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(LoginActivity.this, "",
                        "Validating user...", true);
                new Thread(new Runnable() {
                    public void run() {
                        login();
                    }
                }).start();
            }
        });
    }

    void login() {
        try {
            httpclient = new DefaultHttpClient();
            httppost = new HttpPost("http://192.168.1.48/login.php");
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
                    Toast.makeText(LoginActivity.this,"Response from PHP : " + response,Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            });

            if (response.equalsIgnoreCase("User Found")) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    }
                });

                startActivity((new Intent(LoginActivity.this, MainActivity.class)));
                finish();
            } else {
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e)
        {
            dialog.dismiss();
            System.out.println("Exception : " + e.getMessage());
        }
    }

    public void signUp(View view)
    {
        Intent intent = new Intent(this, RegistActivity.class);
        startActivity(intent);
    }

    public void signEnd(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void signNo(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}