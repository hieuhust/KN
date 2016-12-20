package com.example.anonymous.periodchecker.period;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.example.anonymous.periodchecker.R;

public class PeriodInfoActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private ImageView imvBack;
    private SwitchCompat swPeriod;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period_info);
        initView();
    }

    private void initView() {
        swPeriod = (SwitchCompat) findViewById(R.id.activity_period_info_sw);
        swPeriod.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                switchFragment(isChecked);
            }
        });

        imvBack = (ImageView) findViewById(R.id.activity_period_info_back_imv);
        imvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnNext = (Button) findViewById(R.id.activity_period_info_next_button);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        swPeriod.setChecked(true);
        // Display cycle period when firstly start
        switchFragment(true);
    }

    private void switchFragment(boolean isCycle) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isCycle) {
            PeriodInfoForCycleFragment periodInfoForCycleFragment = PeriodInfoForCycleFragment.newInstance();
            fragmentTransaction.replace(R.id.activity_period_info_container, periodInfoForCycleFragment).commit();
        } else {
            PeriodInfoForCycleNoneFragment periodInfoForCycleNoneFragment = PeriodInfoForCycleNoneFragment.newInstance();
            fragmentTransaction.replace(R.id.activity_period_info_container, periodInfoForCycleNoneFragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
