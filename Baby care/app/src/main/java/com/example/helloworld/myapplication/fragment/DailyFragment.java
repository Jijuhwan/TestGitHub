package com.example.helloworld.myapplication.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.PopupWindow;

import com.example.helloworld.myapplication.R;
import com.example.helloworld.myapplication.activity.MainActivity;
import com.example.helloworld.myapplication.util.PopUpActivity;


import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class DailyFragment extends Fragment {
    MainActivity activity;
    int babymonth = 12; //엄마가 입력한 아기 개월 수

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

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.main_daily,mainFragmentLayout,false);
        CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);
        ImageView helpImg = (ImageView)view.findViewById(R.id.help);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String StrDay = year+"."+(month+1)+"."+dayOfMonth;
                if(babymonth < 4)
                {
                    Toast.makeText(getContext(), "오늘의 이유식 : 모유, 분유", Toast.LENGTH_SHORT).show();
                }
                else if(babymonth ==4)
                {
                    if(dayOfMonth % 3 == 1)  //1,4,7,10,13,16,19,22,25,28,31
                    {
                        if(dayOfMonth%2 == 1)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 쌀\n대체 음식 : 찹쌀", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 찹쌀\n대체 음식 : 쌀", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 3 == 2) //2,5,8,11,14,17,20,23,26,29
                    {
                        if(dayOfMonth == 2 || dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 감자\n대체 음식 : 고구마,애호박, 단호박, 오이", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 5 || dayOfMonth == 20)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 고구마\n대체 음식 : 감자,애호박, 단호박, 오이", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 8 || dayOfMonth == 23)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 애호박\n대체 음식 : 감자,고구마, 단호박, 오이", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 11 || dayOfMonth == 26)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 단호박\n대체 음식 : 감자,애호박, 애호박, 오이", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 오이\n대체 음식 : 감자,애호박, 애호박, 단호박", Toast.LENGTH_LONG).show();
                        }
                    }
                    else //3,6,9,12,15,18,21,24,27,30
                    {
                        if(dayOfMonth == 3 || dayOfMonth ==12 || dayOfMonth == 21 || dayOfMonth == 30)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 사과\n대체 음식 : 배, 바나나", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 6 || dayOfMonth == 15 || dayOfMonth == 24)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 배\n대체 음식 : 사과, 바나나", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 바나나\n대체 음식 : 사과,배", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                else if(babymonth == 5)
                {
                    if(dayOfMonth % 3 == 1)  //1,4,7,10,13,16,19,22,25,28,31
                    {
                        if(dayOfMonth%2 == 1)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 쌀\n대체 음식 : 찹쌀", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 찹쌀\n대체 음식 : 쌀", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 3 == 2) //2,5,8,11,14,17,20,23,26,29
                    {
                        if(dayOfMonth == 2 || dayOfMonth == 14 || dayOfMonth == 26)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 무\n대체 음식 : 감자, 고구마, 애호박, 단호박\n오이, 브로콜리, 콜리플라워, 적양배추", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 5 || dayOfMonth == 17 || dayOfMonth == 29)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 브로콜리\n대체 음식 : 감자, 고구마, 애호박, 단호박\n 오이, 무, 콜리플라워, 적양배추", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 8 || dayOfMonth == 20)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 콜리플라워\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 적양배추", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 적양배추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워", Toast.LENGTH_LONG).show();
                        }
                    }
                    else //3,6,9,12,15,18,21,24,27,30
                    {
                        if(dayOfMonth %6 != 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 자두\n대체 음식 : 사과,배,바나나,수박", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 수박\n대체 음식 : 사과,배,바나나,자두", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 6)
                {
                    if(dayOfMonth % 6 == 1)// 1,7,13,19,25,31
                    {
                        if(dayOfMonth == 1 || dayOfMonth == 31)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 차조\n대체 음식 : 쌀, 찹쌀, 현미, 보리, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 7)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 현미\n대체 음식 : 쌀, 찹쌀, 차조, 보리, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 13)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 보리\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 19)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 수수\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 보리, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 25)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 옥수수\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 보리, 수수", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 6 == 2) //2,8,14,20,26
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 소고기 안심\n대체 음식 : 전날 먹은 이유식", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 6 == 3) //3,9,15,21,27
                    {
                        if(dayOfMonth == 3 || dayOfMonth == 21)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 가자미\n대체 음식 : 임연수어, 마른멸치", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 9 || dayOfMonth ==27)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 임연수어\n대체 음식 : 가자미, 마른멸치", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 마른멸치\n대체 음식 : 가자미, 임연수어", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 6 == 4) //4,10,16,22,28
                    {
                        if(dayOfMonth %4 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 김\n대체 음식 : 다시마", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 다시마\n대체 음식 :김", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(dayOfMonth % 6 == 5)  //5,11,17,23,29
                    {
                        if(dayOfMonth == 5)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 참깨 or 검은깨\n대체 음식 :밤, 대두, 검은콩, 완두콩, 강낭콩", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 밤\n대체 음식 :참깨, 검은깨, 대두, 검은콩, 완두콩, 강낭콩", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 대두 or 검은콩 or 완두콩 or강낭콩\n대체 음식 :참깨, 검은깨, 밤", Toast.LENGTH_LONG).show();
                        }
                    }
                    else  //6,12,18,24,30
                    {
                        if (dayOfMonth == 6)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 당근\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 12)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 시금치\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 18)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 대추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 24)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 배추 or 양배추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 양송이버섯 or 새송이버섯 or 표고버섯 or 팽이버섯 or느타리버섯\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 7)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth == 1)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 차조\n대체 음식 : 쌀, 찹쌀, 현미, 보리, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 10)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 현미\n대체 음식 : 쌀, 찹쌀, 차조, 보리, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 19)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 보리\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 수수 or 옥수수\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 보리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 안심\n대체 음식 : 닭 가슴살, 소고기 안심", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 가슴살\n대체 음식 : 닭 안심, 소고기 안심", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 조기\n대체 음식 : 대구, 가자미, 임연수어, 마른멸치", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 대구\n대체 음식 : 조기, 가자미, 임연수어, 마른멸치", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 미역\n대체 음식 : 파래, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 파래\n대체 음식 : 미역, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 노른자\n대체 음식 : 전날 먹인 이유식", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 아기용 치즈\n대체 음식 : 전날 먹인 이유식", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 잣\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 16)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 두부\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 들깨\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 파슬리\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯", Toast.LENGTH_LONG).show();
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 참외\n대체 음식 : 귤", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 귤\n대체 음식 : 참외", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 8)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth == 1)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 차조\n대체 음식 : 쌀, 찹쌀, 현미, 보리, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 10)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 현미\n대체 음식 : 쌀, 찹쌀, 차조, 보리, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 19)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 보리\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 수수, 옥수수", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 수수 or 옥수수\n대체 음식 : 쌀, 찹쌀, 차조, 현미, 보리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 안심\n대체 음식 : 닭 가슴살, 소고기 안심", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 가슴살\n대체 음식 : 닭 안심, 소고기 안심", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 조기\n대체 음식 : 가자미, 임연수어, 마른멸치, 대구", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 대구\n대체 음식 : 가자미, 임연수어, 마른멸치, 조기", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 미역\n대체 음식 : 파래, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 파래\n대체 음식 : 미역, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 노른자\n대체 음식 : 전날 먹인 이유식", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 플레인 요구르트\n대체 음식 : 아기용 치즈", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 잣\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 16)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 두부\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 들깨\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 파슬리\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯", Toast.LENGTH_LONG).show();
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 참외\n대체 음식 : 귤", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 귤\n대체 음식 : 참외", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 9)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 흑미\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 녹두\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 안심\n대체 음식 : 닭 가슴살, 소고기 안심", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 가슴살\n대체 음식 : 닭 안심, 소고기 안심", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 새우\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 굴", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 :굴\n대체 음식 : 가자미, 임연수어, 마른멸치, 조기, 대구, 새우", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 미역\n대체 음식 : 파래, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 파래\n대체 음식 : 미역, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 노른자\n대체 음식 : 전날 먹인 이유식", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 아기용 치즈\n대체 음식 : 전날 먹인 이유식", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 잣\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 16)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 두부\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 들깨\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 숙주\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 콩나물\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 멜론\n대체 음식 : 귤,참외, 건포도", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 건포도\n대체 음식 : 귤, 참외, 멜론", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 10)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 흑미\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n녹두", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 녹두\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                            Toast.makeText(getContext(), "오늘의 이유식 : 돼지고기 안심\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                            Toast.makeText(getContext(), "오늘의 이유식 : 날치알\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 미역\n대체 음식 : 파래, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 파래\n대체 음식 : 미역, 김, 다시마", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 메추리 알\n대체 음식 : 달걀 노른자", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 마가린\n대체 음식 : 아기용 치즈, 플레인 요구르트", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if (dayOfMonth == 7)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 잣\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n두부, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else if (dayOfMonth == 16)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 두부\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 들깨", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 들깨\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 치커리\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 무순\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 가지\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물", Toast.LENGTH_LONG).show();
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 포도,\n대체 음식 : 귤,참외, 멜론, 건포도, 살구", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 살구\n대체 음식 : 귤, 참외, 멜론, 건포도, 포도", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 11)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 밀가루\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미, 녹두", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 돼지고기 안심\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 오징어\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 한천\n대체 음식 : 파래, 김, 다시마, 미역", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 메추리 알\n대체 음식 : 달걀 노른자", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 마가린\n대체 음식 : 아기용 치즈, 플레인 요구르트", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 유부\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 치커리\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 무순, 가지", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 무순\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 가지", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 가지\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순", Toast.LENGTH_LONG).show();
                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 포도,\n대체 음식 : 귤,참외, 멜론, 건포도, 살구", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 살구\n대체 음식 : 귤, 참외, 멜론, 건포도, 포도", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                else if(babymonth == 12)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 팥\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미, 녹두, 밀가루", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth == 2)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 돼지 등심\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 11)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 날개\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 닭 다리\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 게\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 한천\n대체 음식 : 파래, 김, 다시마, 미역", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 흰자\n대체 음식 : 달걀 노른자, 메추리 알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        if(dayOfMonth == 6)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 액상 요구르트\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 생크림, 버터", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 15)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 생크림\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 버터", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 버터\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림", Toast.LENGTH_LONG).show();
                        }

                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 두유\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 파프리카\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 쑥 or 냉이 or 고사리\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 깻잎\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리", Toast.LENGTH_LONG).show();

                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 파인애플\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 복숭아, 단감", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 18)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 복숭아\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 단감", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 단감\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                else if(babymonth == 13 || babymonth == 14)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 율무\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미, 녹두, 밀가루, 팥", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 소고기 양지머리\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 삼겹살\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        if(dayOfMonth == 3)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 고등어 \n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게,모시조개, 홍합, 맛조개, 바지락, 소라, 전복", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 12)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 모시조개 or 홍합\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게, 고등어, 맛조개, 바지락, 소라, 전복", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 21)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 맛조개 or 바지락\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게, 고등어, 모시조개, 홍합, 소라, 전복", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 소라 or 전복\n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게, 고등어, 모시조개, 홍합, 맛조개, 바지락", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 한천\n대체 음식 : 파래, 김, 다시마, 미역", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 흰자\n대체 음식 : 달걀 노른자, 메추리 알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 생우유\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림, 버터, 연유", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 연유\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림, 버터, 생우유", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        if(dayOfMonth == 7)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 피스타치오\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 해바라기 씨앗, 호두, 땅콩", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 16)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 해바라기 씨앗\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 호두, 땅콩", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 호두 or 땅콩\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기 씨앗", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토마토\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토란\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 부추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 망고\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 레몬, 오렌지, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 18)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 레몬 or 오렌지\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 딸기 or 키위\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 레몬, 오렌지", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                else if(babymonth == 15 || babymonth == 16 || babymonth == 17)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 율무\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미, 녹두, 밀가루, 팥", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 소고기 양지머리\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 삼겹살\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 굴비 \n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게, 고등어, 모시조개, 홍합, 맛조개, 바지락, 소라, 전복", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 한천\n대체 음식 : 파래, 김, 다시마, 미역", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 흰자\n대체 음식 : 달걀 노른자, 메추리 알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        if(dayOfMonth %2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 생우유\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림, 버터, 연유", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 연유\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림, 버터, 생우유", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 호박씨\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기 씨앗, 호두, 땅콩", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토마토\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토란\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 부추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 망고\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 레몬, 오렌지, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 18)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 레몬 or 오렌지\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 딸기 or 키위\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 레몬, 오렌지", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                else if(babymonth == 18 || babymonth == 19 || babymonth == 20 || babymonth == 21 || babymonth ==22 || babymonth ==23)
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 율무\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미, 녹두, 밀가루, 팥", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 소고기 양지머리\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 삼겹살\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 굴비 \n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게, 고등어, 모시조개, 홍합, 맛조개, 바지락, 소라, 전복", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 한천\n대체 음식 : 파래, 김, 다시마, 미역", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 흰자\n대체 음식 : 달걀 노른자, 메추리 알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 모차렐라 치즈\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림, 버터, 생우유, 연유", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 호박씨\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기 씨앗, 호두, 땅콩", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토마토\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토란\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 부추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 망고\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 레몬, 오렌지, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 18)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 레몬 or 오렌지\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 딸기 or 키위\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 레몬, 오렌지", Toast.LENGTH_LONG).show();
                        }

                    }
                }
                else
                {
                    if(dayOfMonth % 9 == 1) //1,10,19,28
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 혼합 잡곡\n대체 음식 : 쌀, 찹쌀, 차조,현미, 보리, 수수, 옥수수\n흑미, 녹두, 밀가루, 팥, 율무", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 2) //2,11,20,29
                    {
                        if(dayOfMonth % 2 == 0)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 소고기 양지머리\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 삼겹살\n대체 음식 : 닭 안심, 닭 가슴살, 소고기 안심, 돼지고기 안심, 돼지 등심, 닭 날개, 닭 다리", Toast.LENGTH_LONG).show();
                        }
                    }
                    else if(dayOfMonth % 9 == 3) //3,12,21,30
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 건새우 \n대체 음식 : 가자미, 임연수어, 마른멸치조기, 대구, 새우, 굴\n날치알, 게, 고등어, 모시조개, 홍합, 맛조개, 바지락, 소라, 전복, 굴비", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 4) //4,13,22,31
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 한천\n대체 음식 : 파래, 김, 다시마, 미역", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 5) //5,14,23
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 달걀 흰자\n대체 음식 : 달걀 노른자, 메추리 알", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 6) //6,15,24
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 모차렐라 치즈\n대체 음식 : 아기용 치즈, 플레인 요구르트, 마가린, 액상 요구르트, 생크림, 버터, 생우유, 연유", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 7) //7,16,25
                    {
                        Toast.makeText(getContext(), "오늘의 이유식 : 아몬드\n대체 음식 :참깨, 검은깨, 밤, 대두, 검은콩, 완두콩, 강낭콩\n잣, 두부, 들깨, 유부, 두유, 피스타치오, 해바라기 씨앗, 호두, 땅콩, 호박씨", Toast.LENGTH_LONG).show();
                    }
                    else if(dayOfMonth % 9 == 8) //8,17,26
                    {
                        if(dayOfMonth == 8)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토마토\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else if(dayOfMonth == 17)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 토란\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 부추\n대체 음식 : 감자, 고구마, 애호박, 단호박, 오이, 무, 브로콜리, 콜리플라워, 적양배추\n당근, 시금치, 대추, 배추, 양배추, 양송이버섯, 새송이버섯, 표고버섯, 팽이버섯, 느타리버섯\n 파슬리, 숙주, 콩나물, 치커리, 무순, 가지, 파프리카, 쑥, 냉이, 고사리, 깻잎", Toast.LENGTH_LONG).show();

                        }
                    }
                    else //9,18,27
                    {
                        if(dayOfMonth == 9)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 망고\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 레몬, 오렌지, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else if(dayOfMonth == 18)
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 레몬 or 오렌지\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 딸기, 키위", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getContext(), "오늘의 이유식 : 딸기 or 키위\n대체 음식 : 귤,참외, 멜론, 건포도, 살구, 포도, 파인애플, 복숭아, 단감, 망고, 레몬, 오렌지", Toast.LENGTH_LONG).show();
                        }

                    }
                }
            }
     });

        helpImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context mContext = getContext();
                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.activity_popup, (ViewGroup) v.findViewById(R.id.popup));
                AlertDialog.Builder aDialog = new AlertDialog.Builder(getContext());

                aDialog.setTitle("개월 수 별 먹어도 되는 음식들");
                aDialog.setView(layout);

                AlertDialog ad = aDialog.create();
                ad.show();
            }
        });

        return view;
    }
}