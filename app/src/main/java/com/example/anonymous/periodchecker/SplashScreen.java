package com.example.anonymous.periodchecker;

/**
 * Created by Annnn on 12/17/2016.
 */

import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.anonymous.periodchecker.common.view.BaseActivity;
import com.example.anonymous.periodchecker.info.model.SettingData;
import com.example.anonymous.periodchecker.info.view.DialogOnClickListener;
import com.example.anonymous.periodchecker.info.view.PasswordDialog;

public class SplashScreen extends BaseActivity {

    private SplashScreen mSplashScreen;

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        mSplashScreen = this;

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        } else {
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

        setContentView(R.layout.spash_screen_layout);
        final TextView productionName = (TextView) findViewById(R.id.textView1);
        productionName.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_production_name));
        final TextView appName = (TextView) findViewById(R.id.textView);
        appName.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_production_name));
        Typeface font = Typeface.createFromAsset(getAssets(), "AustieBostKittenKlub.ttf");
        productionName.setTypeface(font);
        appName.setTypeface(font);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //turn off animation
                productionName.clearAnimation();
                appName.clearAnimation();
                final SettingData settingData = getSettingDataFromPreference();
                if (!settingData.isUsePassword()) {
                    gotoMainActivity();
                } else {
                    // If use pwd then show dialog to user enter pwd
                    PasswordDialog.showDialog(R.string.pwd_dialog_title, new DialogOnClickListener() {
                        @Override
                        public boolean doPositiveClick(String pwd) {
                            if (settingData.getPwd().equals(pwd)) {
                                gotoMainActivity();
                                return true;
                            }
                            return false;
                        }

                        @Override
                        public void doNegativeClick() {

                        }

                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        @Override
                        public void writeToParcel(Parcel dest, int flags) {

                        }
                    }, mSplashScreen.getSupportFragmentManager());
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
