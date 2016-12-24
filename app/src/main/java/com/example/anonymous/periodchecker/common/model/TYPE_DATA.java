package com.example.anonymous.periodchecker.common.model;

/**
 * Created by Huy Hieu on 12/23/2016.
 */

public enum TYPE_DATA {
    SETTING(0);

    private int value;

    TYPE_DATA(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
