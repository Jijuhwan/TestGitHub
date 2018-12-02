package com.example.helloworld.myapplication.util;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;


public class RegistActivity extends AppCompatActivity {

    private EditText editTextId;
    private EditText editTextPw;
    private EditText editTextHead;
    private EditText editTextHeight;
    private EditText editTextMonth;
    private EditText editTextWeight;
    private RadioGroup rg;
    private String gender = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.util_regist);

        editTextId = (EditText) findViewById(R.id.etID);
        editTextPw = (EditText) findViewById(R.id.etPW);
        editTextHead = (EditText)findViewById(R.id.etHead);
        editTextHeight = (EditText)findViewById(R.id.etHeight);
        editTextMonth = (EditText)findViewById(R.id.etMonth);
        editTextWeight = (EditText)findViewById(R.id.etWeight);

        rg = (RadioGroup) findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(radioGroupButtonChangeListener);

    }

    RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(i == R.id.rbm){
                gender = "1";
            }
            else if(i == R.id.rbg)
            {
                gender = "2";
            }
        }
    };

    public void insert(View view) {
        String Id = editTextId.getText().toString();
        String Pw = editTextPw.getText().toString();
        String Head = editTextHead.getText().toString();
        String Height = editTextHeight.getText().toString();
        String Month = editTextMonth.getText().toString();
        String Weight = editTextWeight.getText().toString();
        String Gender = gender;

        if(Pw.equals(""))
        {
            Toast.makeText(this,"비밀번호가 공백입니다.", Toast.LENGTH_SHORT).show();
        }else if(Head.equals(""))
        {
            Toast.makeText(this,"아이 머리둘레가 공백입니다.", Toast.LENGTH_SHORT).show();
        }
        else if(Height.equals(""))
        {
            Toast.makeText(this,"아이 키가 공백입니다.", Toast.LENGTH_SHORT).show();
        }
        else if(Month.equals(""))
        {
            Toast.makeText(this,"아이 개월 수가 공백입니다.", Toast.LENGTH_SHORT).show();
        }
        else if(Weight.equals(""))
        {
            Toast.makeText(this,"아이 몸무게가 공백입니다.", Toast.LENGTH_SHORT).show();
        }
        else if(gender.equals("0"))
        {
            Toast.makeText(this,"아이의 성별을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else
            {
            insertoToDatabase(Id, Pw, Head, Height, Month, Weight, Gender);
            }
    }
    private void insertoToDatabase(String Id, String Pw, String Head, String Height,
                                   String Month, String Weight, String Gender) {

        class InsertData extends AsyncTask<String, Void, String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegistActivity.this, "잠시만 기다려주세요.", null, true, true);
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }
            @Override
            protected String doInBackground(String... params) {

                try {
                    String Id = (String) params[0];
                    String Pw = (String) params[1];
                    String Head = (String) params[2];
                    String Height = (String) params[3];
                    String Month = (String) params[4];
                    String Weight = (String) params[5];
                    String Gender = (String) params[6];

                    String link = "http://otl9882.codns.com:443/regist.php";
                    String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(Id, "UTF-8");
                    data += "&" + URLEncoder.encode("Pw", "UTF-8") + "=" + URLEncoder.encode(Pw, "UTF-8");
                    data += "&" + URLEncoder.encode("Head", "UTF-8") + "=" + URLEncoder.encode(Head, "UTF-8");
                    data += "&" + URLEncoder.encode("Height", "UTF-8") + "=" + URLEncoder.encode(Height, "UTF-8");
                    data += "&" + URLEncoder.encode("Month", "UTF-8") + "=" + URLEncoder.encode(Month, "UTF-8");
                    data += "&" + URLEncoder.encode("Weight", "UTF-8") + "=" + URLEncoder.encode(Weight, "UTF-8");
                    data += "&" + URLEncoder.encode("Gender", "UTF-8") + "=" + URLEncoder.encode(Gender, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }

            InsertData task = new InsertData();
            task.execute(Id, Pw, Head, Height, Month, Weight, Gender);

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);

    }

    public void signNo(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}