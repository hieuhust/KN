package com.example.anonymous.periodchecker.common.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Huy Hieu on 12/24/2016.
 */

public abstract class DataModel {

    public String TAG = this.getClass().getSimpleName();

    private List<BasePresent> observers = new ArrayList<BasePresent>();

    public void attach(BasePresent observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (BasePresent observer : observers) {
            observer.signalNotifyDataChange(this);
        }
    }

    public abstract void dump();

}
