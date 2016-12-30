package com.example.anonymous.periodchecker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.periodchecker.common.AppUtility;
import com.example.anonymous.periodchecker.info.view.MainInfoActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Annnn on 12/18/2016.
 */

public class MenstrualInformationRegisterActivity extends Activity implements View.OnClickListener {

    RelativeLayout mRlSelectFirstPeriodDay, mRlSelectFirstPeriodDay1, mRlBack;
    boolean isShowPickedDate;
    Button nextButton;
    EditText mEtMenstrualCycleMax, mEtMenstrualCycleMin, mPeriodLength;
    TextView mTvDate;
    DatePicker mDatePicker;
    long mFirstPeriodDay;
    boolean mDoubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menstrual_information_register);

        mEtMenstrualCycleMax = (EditText) findViewById(R.id.menstrual_cycle_max);
        mEtMenstrualCycleMin = (EditText) findViewById(R.id.menstrual_cycle_min);
        mPeriodLength = (EditText) findViewById(R.id.period_length);

        mTvDate = (TextView) findViewById(R.id.tvDate);
        mDatePicker = (DatePicker) findViewById(R.id.datepicker);

        mRlSelectFirstPeriodDay = (RelativeLayout) findViewById(R.id.select_first_period_day);
        mRlSelectFirstPeriodDay1 = (RelativeLayout) findViewById(R.id.select_first_period_day_1);
        mRlBack = (RelativeLayout) findViewById(R.id.rlBack);
        nextButton = (Button) findViewById(R.id.next_button);
        mRlSelectFirstPeriodDay.setOnClickListener(this);
        mRlBack.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        mDatePicker.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, year);
                cal.add(Calendar.MONTH, month);
                cal.add(Calendar.DAY_OF_MONTH, dayOfMonth);
                mFirstPeriodDay = cal.getTimeInMillis();
                mTvDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(year - 1900, month, dayOfMonth)));
            }
        });
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
                String menstrualCycleMax = mEtMenstrualCycleMax.getText().toString();
                String menstrualCycleMin = mEtMenstrualCycleMin.getText().toString();
                String periodLength = mPeriodLength.getText().toString();
                if (TextUtils.isEmpty(menstrualCycleMax)) {
                    Toast.makeText(this, getString(R.string.please_fill_your_menstrual_cycle_max), Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(menstrualCycleMin)) {
                    Toast.makeText(this, getString(R.string.please_fill_your_menstrual_cycle_min), Toast.LENGTH_LONG).show();
                } else if (TextUtils.isEmpty(periodLength)) {
                    Toast.makeText(this, getString(R.string.please_fill_your_period_length), Toast.LENGTH_LONG).show();
                } else if (mFirstPeriodDay == 0) {
                    Toast.makeText(this, getString(R.string.please_fill_your_first_period_day), Toast.LENGTH_LONG).show();
                } else {
                    saveDataToSharePrefsFile();
                    Intent intent = new Intent(MenstrualInformationRegisterActivity.this, MainInfoActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private void saveDataToSharePrefsFile () {
        AppUtility.putSharedPrefStringValue(getApplicationContext(), "MenstrualInfoData", "full_name", mEtMenstrualCycleMax.getText().toString());
        AppUtility.putSharedPrefStringValue(getApplicationContext(), "MenstrualInfoData", "height", mEtMenstrualCycleMin.getText().toString());
        AppUtility.putSharedPrefStringValue(getApplicationContext(), "MenstrualInfoData", "weight", mPeriodLength.getText().toString());
        AppUtility.putSharedPrefLongValue(getApplicationContext(), "MenstrualInfoData", "first_period_day", mFirstPeriodDay);
    }

    @Override
    public void onBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            return;
        }

        this.mDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.please_click_back_again_to_exit, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
