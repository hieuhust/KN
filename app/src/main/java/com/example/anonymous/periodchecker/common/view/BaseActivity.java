package com.example.anonymous.periodchecker.common.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.anonymous.periodchecker.MainActivity;
import com.example.anonymous.periodchecker.common.model.TYPE_DATA;
import com.example.anonymous.periodchecker.info.dao.PreferencesUtil;
import com.example.anonymous.periodchecker.info.model.SettingData;
import com.example.anonymous.periodchecker.info.view.MainInfoActivity;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class BaseActivity extends AppCompatActivity {

    public String TAG = this.getClass().getSimpleName();

    public void gotoMainActivity() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        this.startActivity(mainIntent);
        this.finish();
    }
    public void gotoMainInfoActivity() {
        Intent mainIntent = new Intent(this, MainInfoActivity.class);
        this.startActivity(mainIntent);
        this.finish();
    }
    public SettingData getSettingDataFromPreference() {
        PreferencesUtil preferencesUtil = PreferencesUtil.newInstance(getApplicationContext());
        return (SettingData) preferencesUtil.getDataModel(TYPE_DATA.SETTING);
    }
}

