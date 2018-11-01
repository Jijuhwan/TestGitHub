package com.example.helloworld.myapplication.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.helloworld.myapplication.R;

public class CompareActivity extends Fragment{
    MainActivity activity;
    int imageIndex = 0;
    Button compareBtn;
    ImageView questionsignimg = null, yellowlightimg = null, greenlightimg = null, redlightimg =null,
            questionmarkimg = null, lessgrowimg1= null, normalgrowimg1 = null, moregrowimg1=null;
    TextView touchbtntxt = null, lesstxt = null, normaltxt = null, moretxt = null;

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

        compareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeImage();
            }

        });
        return view;
    }
    private void changeImage() {
        if(imageIndex == 0)
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

            imageIndex = 1;
        }
        else if(imageIndex == 1)
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

            imageIndex = 2;
        }
        else if(imageIndex == 2)
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

            imageIndex = 3;
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

            imageIndex = 0;
        }
    }
}

