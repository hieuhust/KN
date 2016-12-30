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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Annnn on 12/18/2016.
 */

public class InformationRegisterActivity extends Activity implements View.OnClickListener {

    RelativeLayout mRlSelectDateofbirth, mRlSelectDateofbirth1, mRlBack;
    boolean isShowPickedDate;
    Button nextButton;
    EditText mEtFullName, mEtHeight, mEtWeight;
    TextView mTvBirthday, mTvDate;
    DatePicker mDatePicker;
    long mBirthday;
    boolean mDoubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_register);

        mEtFullName = (EditText) findViewById(R.id.full_name);
        mEtHeight = (EditText) findViewById(R.id.height);
        mEtWeight = (EditText) findViewById(R.id.weight);

        mTvBirthday = (TextView) findViewById(R.id.date_of_birth);
        mTvDate = (TextView) findViewById(R.id.tvDate);

        mDatePicker = (DatePicker) findViewById(R.id.datepicker);

        mRlSelectDateofbirth = (RelativeLayout) findViewById(R.id.select_dateofbirth);
        mRlSelectDateofbirth1 = (RelativeLayout) findViewById(R.id.select_dateofbirth_1);
        mRlBack = (RelativeLayout) findViewById(R.id.rlBack);
        nextButton = (Button) findViewById(R.id.next_button);
        mRlSelectDateofbirth.setOnClickListener(this);
        mRlBack.setOnClickListener(this);
        nextButton.setOnClickListener(this);

        mDatePicker.getCalendarView().setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, year);
                cal.add(Calendar.MONTH, month);
                cal.add(Calendar.DAY_OF_MONTH, dayOfMonth);
                mBirthday = cal.getTimeInMillis();
                mTvDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(year - 1900, month, dayOfMonth)));
            }
        });
    }

    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_select));
        switch (v.getId()) {
            case R.id.select_dateofbirth :
                if (isShowPickedDate) {
                    isShowPickedDate = false;
                    mRlSelectDateofbirth.setBackgroundColor(Color.TRANSPARENT);
                    mRlSelectDateofbirth1.setVisibility(View.GONE);
                } else {
                    isShowPickedDate = true;
                    mRlSelectDateofbirth.setBackgroundColor(Color.parseColor("#10000000"));
                    mRlSelectDateofbirth1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rlBack:
                finish();
                break;
            case R.id.next_button:
                String height = mEtHeight.getText().toString();
                String weight = mEtWeight.getText().toString();
                String name = mEtFullName.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(this, getString(R.string.please_fill_your_full_name), Toast.LENGTH_LONG).show();
                } else if (!isInteger(height) || Integer.parseInt(height) < 100 || Integer.parseInt(height) > 200) {
                    Toast.makeText(this, getString(R.string.please_fill_your_height), Toast.LENGTH_LONG).show();
                } else if (!isInteger(weight) || Integer.parseInt(weight) < 30 || Integer.parseInt(weight) > 200) {
                    Toast.makeText(this, getString(R.string.please_fill_your_weight), Toast.LENGTH_LONG).show();
                } else if (mBirthday == 0) {
                    Toast.makeText(this, getString(R.string.please_fill_your_birthday), Toast.LENGTH_LONG).show();
                } else {
                    saveDataToSharePrefsFile();
                    Intent intent = new Intent(InformationRegisterActivity.this, MenstrualInformationRegisterActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

    private void saveDataToSharePrefsFile () {
        AppUtility.putSharedPrefStringValue(getApplicationContext(), "UserInfoData", "full_name", mEtFullName.getText().toString());
        AppUtility.putSharedPrefStringValue(getApplicationContext(), "UserInfoData", "height", mEtHeight.getText().toString());
        AppUtility.putSharedPrefStringValue(getApplicationContext(), "UserInfoData", "weight", mEtWeight.getText().toString());
        AppUtility.putSharedPrefLongValue(getApplicationContext(), "UserInfoData", "birthday", mBirthday);
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

    private boolean isInteger (String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
