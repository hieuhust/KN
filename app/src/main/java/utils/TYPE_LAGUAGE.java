package utils;

/**
 * Created by Huy Hieu on 12/23/2016.
 */

public enum TYPE_LAGUAGE {
    VIET_NAM(0),
    ENGLAND(1);

    private int value;

    TYPE_LAGUAGE(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}
