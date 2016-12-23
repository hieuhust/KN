package com.example.anonymous.periodchecker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.anonymous.periodchecker.info.view.MainInfoActivity;

/**
 * Created by Annnn on 12/18/2016.
 */

public class MenstrualInformationRegisterActivity extends Activity implements View.OnClickListener {

    RelativeLayout mRlSelectFirstPeriodDay, mRlSelectFirstPeriodDay1, mRlBack;
    boolean isShowPickedDate;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menstrual_information_register);

        mRlSelectFirstPeriodDay = (RelativeLayout) findViewById(R.id.select_first_period_day);
        mRlSelectFirstPeriodDay1 = (RelativeLayout) findViewById(R.id.select_first_period_day_1);
        mRlBack = (RelativeLayout) findViewById(R.id.rlBack);
        nextButton = (Button) findViewById(R.id.next_button);
        mRlSelectFirstPeriodDay.setOnClickListener(this);
        mRlBack.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_select));
        switch (v.getId()) {
            case R.id.select_first_period_day :
                if (isShowPickedDate) {
                    isShowPickedDate = false;
                    mRlSelectFirstPeriodDay.setBackgroundColor(Color.TRANSPARENT);
                    mRlSelectFirstPeriodDay1.setVisibility(View.GONE);
                } else {
                    isShowPickedDate = true;
                    mRlSelectFirstPeriodDay.setBackgroundColor(Color.parseColor("#10000000"));
                    mRlSelectFirstPeriodDay1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rlBack:
                finish();
                break;
            case R.id.next_button:
                Intent intent = new Intent(MenstrualInformationRegisterActivity.this, MainInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
