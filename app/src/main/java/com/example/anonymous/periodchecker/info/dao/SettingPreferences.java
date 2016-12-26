package com.example.anonymous.periodchecker.info.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anonymous.periodchecker.common.model.DataFactory;
import com.example.anonymous.periodchecker.common.model.TYPE_DATA;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;
import com.example.anonymous.periodchecker.info.model.SettingData;

/**
 * Created by Huy Hieu on 12/26/2016.
 */

public class SettingPreferences {

    private final static String NAME = "SettingPreferences";

    private final static String NUMBER_DAY_OF_A_CYCLE = "number_day_of_a_cycle";

    private final static String NUMBER_DAY_HANH_KINH = "number_day_hanh_kinh";

    private final static String IS_USE_PASSWORD = "is_use_password";

    private final static String TYPE_LANGUAGE_STRING = "type_language";

    private final static boolean IS_USE_PASSWORD_DEFAULT_VALUE = false;

    private final static int NUMBER_DAY_OF_A_CYCLE_DEFAULT_VALUE = 30;

    private final static int NUMBER_DAY_HANH_KINH_DEFAULT_VALUE = 30;

    private final static TYPE_LANGUAGE TYPE_LANGUAGE_DEFAULT_VALUE = TYPE_LANGUAGE.ENGLAND;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    private static SettingPreferences s_settingPreferences;

    private SettingPreferences(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public static SettingPreferences newInstance(Context context) {
        if (s_settingPreferences == null) {
            s_settingPreferences = new SettingPreferences(context);
        }
        return s_settingPreferences;
    }

    public SettingData getSettingData() {
        SettingData settingData = (SettingData) new DataFactory().getData(TYPE_DATA.SETTING);

        settingData.setNumverDayHanhKinh(mSharedPreferences.getInt(NUMBER_DAY_HANH_KINH, NUMBER_DAY_HANH_KINH_DEFAULT_VALUE));
        settingData.setNumberDayOfaCycle(mSharedPreferences.getInt(NUMBER_DAY_OF_A_CYCLE, NUMBER_DAY_OF_A_CYCLE_DEFAULT_VALUE));
        settingData.setUsePassword(mSharedPreferences.getBoolean(IS_USE_PASSWORD, IS_USE_PASSWORD_DEFAULT_VALUE));
        settingData.setTypeLaguage(TYPE_LANGUAGE.convertIntToTypeLanguage(mSharedPreferences.getInt(TYPE_LANGUAGE_STRING, TYPE_LANGUAGE_DEFAULT_VALUE.getValue())));

        return settingData;
    }

    public void setSettingData(SettingData settingData) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(NUMBER_DAY_HANH_KINH, settingData.getNumberDayHanhKinh());
        editor.putInt(NUMBER_DAY_OF_A_CYCLE, settingData.getNumberDayOfaCycle());
        editor.putInt(TYPE_LANGUAGE_STRING, settingData.getTypeLaguage().getValue());
        editor.putBoolean(IS_USE_PASSWORD, settingData.isUsePassword());
        editor.commit();
    }

}
