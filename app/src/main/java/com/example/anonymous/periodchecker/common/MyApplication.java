package com.example.anonymous.periodchecker.common;

import android.app.Application;

/**
 * Created by Huy Hieu on 1/3/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "MyFontAsset.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "AustieBostKittenKlub.ttf");
    }
}
