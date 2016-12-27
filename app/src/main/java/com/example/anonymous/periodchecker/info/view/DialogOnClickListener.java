package com.example.anonymous.periodchecker.info.view;

import android.os.Parcelable;

/**
 * Created by Huy Hieu on 12/27/2016.
 */

public interface DialogOnClickListener extends Parcelable {
    void doPositiveClick(String pwd);

    void doNegativeClick();
}
