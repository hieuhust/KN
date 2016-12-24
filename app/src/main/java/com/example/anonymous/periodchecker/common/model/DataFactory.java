package com.example.anonymous.periodchecker.common.model;

import com.example.anonymous.periodchecker.info.model.SettingData;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public class DataFactory {

    public DataModel getData(TYPE_DATA dataType) {
        if (dataType.getValue() == TYPE_DATA.SETTING.getValue()) {
            return new SettingData();
        } else {
            return null;
        }
    }
}
