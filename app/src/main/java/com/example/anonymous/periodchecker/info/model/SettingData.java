package com.example.anonymous.periodchecker.info.model;

import com.example.anonymous.periodchecker.common.model.DataModel;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;
import com.google.gson.annotations.SerializedName;

import utils.Logger;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public class SettingData extends DataModel {

    private int numberDayMaxCycle;

    private int numberDayHanhKinh;

    private boolean isUsePassword;

    private int numberDayMinCycle;

    private TYPE_LANGUAGE typeLaguage;

    private String pwd;


    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
        notifyAllObservers();
    }

    public void setNumberDayMaxCycle(int numberDayMaxCycle) {
        this.numberDayMaxCycle = numberDayMaxCycle;
        notifyAllObservers();
    }

    public void setNumberDayHanhKinh(int numberDayHanhKinh) {
        this.numberDayHanhKinh = numberDayHanhKinh;
        notifyAllObservers();
    }

    public void setNumberDayMinCycle(int numberDayMinCycle) {
        this.numberDayMinCycle = numberDayMinCycle;
        notifyAllObservers();
    }

    public void setUsePassword(boolean usePassword) {
        isUsePassword = usePassword;
        notifyAllObservers();
    }

    public void setTypeLaguage(TYPE_LANGUAGE typeLaguage) {
        this.typeLaguage = typeLaguage;
        notifyAllObservers();
    }

    public int getNumberDayMaxCycle() {
        return numberDayMaxCycle;
    }

    public int getNumberDayHanhKinh() {
        return numberDayHanhKinh;
    }

    public boolean isUsePassword() {
        return isUsePassword;
    }

    public TYPE_LANGUAGE getTypeLaguage() {
        return typeLaguage;
    }

    public int getNumberDayMinCycle() {
        return numberDayMinCycle;
    }

    @Override
    public void dump() {
        Logger.i(TAG, "TypeLanguage = " + typeLaguage.name() + ", numberDayMaxCycle" + numberDayMaxCycle + ", numberDayHanhKinh" + numberDayHanhKinh + " ,getSettingDataFromPreference" + isUsePassword);
    }
}
