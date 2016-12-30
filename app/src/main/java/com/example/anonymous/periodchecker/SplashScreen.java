package com.example.anonymous.periodchecker;

/**
 * Created by Annnn on 12/17/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends Activity {

    private final int SPLASH_DISPLAY_LENGTH = 2000;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        if (Build.VERSION.SDK_INT < 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }else{
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

                Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                SplashScreen.this.startActivity(mainIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
