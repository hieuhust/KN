package com.example.anonymous.periodchecker.info.present;

import android.content.Context;

import com.example.anonymous.periodchecker.common.model.BasePresent;
import com.example.anonymous.periodchecker.info.dao.SettingPreferences;
import com.example.anonymous.periodchecker.info.model.SettingData;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public class SettingPresent extends BasePresent<SettingData> {

    private static SettingPresent s_settingPresent;

    private Context mContext;

    private SettingPreferences mSettingPreferences;

    protected SettingPresent(Context context) {
        this.mContext = context;
        mSettingPreferences = SettingPreferences.newInstance(mContext);
    }

    public static SettingPresent newInstance(Context context) {
        if (s_settingPresent == null)
            return new SettingPresent(context);
        else return s_settingPresent;
    }

    @Override
    protected SettingData fetchDataModel() {
        return mSettingPreferences.getSettingData();
    }

    @Override
    public void signalNotifyDataChange(SettingData dataModel) {
        mSettingPreferences.setSettingData(dataModel);
    }


}