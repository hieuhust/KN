package com.example.anonymous.periodchecker.info.present;

import com.example.anonymous.periodchecker.common.model.BasePresent;
import com.example.anonymous.periodchecker.common.model.DataFactory;
import com.example.anonymous.periodchecker.common.model.TYPE_DATA;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;
import com.example.anonymous.periodchecker.info.model.SettingData;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public class SettingPresent extends BasePresent<SettingData> {

    private static SettingPresent settingPresent;

    protected SettingPresent() {

    }

    public static SettingPresent newInstance() {
        if (settingPresent == null)
            return new SettingPresent();
        else return settingPresent;
    }

    @Override
    protected SettingData fetchDataModel() {
        SettingData settingData = (SettingData) new DataFactory().getData(TYPE_DATA.SETTING);

        //// TODO: 12/24/2016 get values in here
        settingData.setTypeLaguage(TYPE_LANGUAGE.ENGLAND);
        return settingData;
    }

    @Override
    public void signalNotifyDataChange(SettingData dataModel) {
        //testing
        dataModel.dump();

        // TODO: 12/24/2016 update to db or preferences 
    }


}
