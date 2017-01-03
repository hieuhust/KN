package com.example.anonymous.periodchecker.info.dao;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.anonymous.periodchecker.common.model.DataFactory;
import com.example.anonymous.periodchecker.common.model.DataModel;
import com.example.anonymous.periodchecker.common.model.TYPE_DATA;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;
import com.example.anonymous.periodchecker.info.model.SettingData;
import com.google.gson.Gson;

/**
 * Created by Huy Hieu on 12/26/2016.
 */

public class PreferencesUtil {

    public String TAG = this.getClass().getSimpleName();

//    private final static String NAME = "PreferencesUtil";

    /*private final static String NUMBER_DAY_OF_A_CYCLE = "number_day_of_a_cycle";

    private final static String NUMBER_DAY_HANH_KINH = "number_day_hanh_kinh";

    private final static String IS_USE_PASSWORD = "is_use_password";

    private final static String TYPE_LANGUAGE_STRING = "type_language";
    private final static String PASS_WORD = "password";*/

    private final static boolean IS_USE_PASSWORD_DEFAULT_VALUE = false;

    private final static int NUMBER_DAY_OF_A_CYCLE_DEFAULT_VALUE = 30;

    private final static int NUMBER_DAY_HANH_KINH_DEFAULT_VALUE = 30;

    private final static TYPE_LANGUAGE TYPE_LANGUAGE_DEFAULT_VALUE = TYPE_LANGUAGE.ENGLAND;

    private Context mContext;

    private SharedPreferences mSharedPreferences;

    private static PreferencesUtil s_PreferencesUtil;

    private PreferencesUtil(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(TAG, Context.MODE_PRIVATE);
    }

    public static PreferencesUtil newInstance(Context context) {
        if (s_PreferencesUtil == null) {
            s_PreferencesUtil = new PreferencesUtil(context);
        }
        return s_PreferencesUtil;
    }

    public SettingData initSettingDataDefault() {
        SettingData settingData = (SettingData) new DataFactory().getData(TYPE_DATA.SETTING);

        settingData.setNumberDayHanhKinh(NUMBER_DAY_HANH_KINH_DEFAULT_VALUE);
        settingData.setNumberDayMaxCycle(NUMBER_DAY_OF_A_CYCLE_DEFAULT_VALUE);
        settingData.setUsePassword(IS_USE_PASSWORD_DEFAULT_VALUE);
        settingData.setTypeLaguage(TYPE_LANGUAGE_DEFAULT_VALUE);
        settingData.setPwd("");
        return settingData;
    }

    public DataModel getDataModel(TYPE_DATA typeData) {
        Gson gson = new Gson();
        String json = mSharedPreferences.getString(typeData.name(), "");
        if (typeData == TYPE_DATA.SETTING) {
            if (!json.equals("")) {
                SettingData settingData = gson.fromJson(json, SettingData.class);
                return settingData;
            } else {
                return initSettingDataDefault();
            }
        }
        return new DataFactory().getData(TYPE_DATA.SETTING);
    }

    public void setDataModel(TYPE_DATA typeData, DataModel dataModel) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();

        //Convert object to json
        Gson gson = new Gson();
        String json = gson.toJson(dataModel);
        editor.putString(typeData.name(), json);
        editor.commit();
    }

}

