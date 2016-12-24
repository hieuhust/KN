package com.example.anonymous.periodchecker.common.model;

/**
 * Created by Huy Hieu on 12/23/2016.
 */

public enum TYPE_LANGUAGE {
    VIET_NAM(0),
    ENGLAND(1);

    private int value;

    TYPE_LANGUAGE(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
