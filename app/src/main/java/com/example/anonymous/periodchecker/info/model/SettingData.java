package com.example.anonymous.periodchecker.info.model;

import com.example.anonymous.periodchecker.common.model.DataModel;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;

import utils.Logger;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public class SettingData extends DataModel {

    private int numberDayOfaCycle;
    private int numverDayHanhKinh;
    private boolean isUsePassword;
    private TYPE_LANGUAGE typeLaguage;

    public void setNumberDayOfaCycle(int numberDayOfaCycle) {
        this.numberDayOfaCycle = numberDayOfaCycle;
        notifyAllObservers();
    }

    public void setNumverDayHanhKinh(int numverDayHanhKinh) {
        this.numverDayHanhKinh = numverDayHanhKinh;
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

    public int getNumberDayOfaCycle() {
        return numberDayOfaCycle;
    }

    public int getNumberDayHanhKinh() {
        return numverDayHanhKinh;
    }

    public boolean isUsePassword() {
        return isUsePassword;
    }

    public TYPE_LANGUAGE getTypeLaguage() {
        return typeLaguage;
    }

    @Override
    public void dump() {
        Logger.i(TAG, "TypeLanguage = " + typeLaguage.name() + ", numberDayOfaCycle" + numberDayOfaCycle + ", numverDayHanhKinh" + numverDayHanhKinh + " ,isUsePassword" + isUsePassword);
    }
}
