package com.example.anonymous.periodchecker;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.periodchecker.common.AppUtility;

import java.util.Locale;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "mainactivity";

    RelativeLayout mRlSelectLanguage, mRLVietnam, mRLEnglish;
    ImageView mImgEnglishSelection, mImgVietnamSelection;
    LinearLayout mLnChoosingLanguage;
    Button mNextButton;
    Animation mAnimSelected;
    TextView mTvLanguageText, mTvChooseLanguage, mLanguage;
    boolean mIsShowChoosingLanguageLayout;
    boolean mDoubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTvLanguageText = (TextView) findViewById(R.id.language_text_showed);
        mTvChooseLanguage = (TextView) findViewById(R.id.choose_language);
        mLanguage = (TextView) findViewById(R.id.language);

        mRlSelectLanguage = (RelativeLayout) findViewById(R.id.select_language);
        mRLVietnam = (RelativeLayout) findViewById(R.id.rlTiengViet);
        mRLEnglish = (RelativeLayout) findViewById(R.id.rlEnglish);

        mImgVietnamSelection = (ImageView) findViewById(R.id.imgTiengVietSelection);
        mImgEnglishSelection = (ImageView) findViewById(R.id.imgEnglishSelection);

        mLnChoosingLanguage = (LinearLayout) findViewById(R.id.lnChoosingLanguage);
        mNextButton = (Button) findViewById(R.id.next_button);

        mAnimSelected = AnimationUtils.loadAnimation(this, R.anim.anim_select);

        mRlSelectLanguage.setOnClickListener(this);
        mRLVietnam.setOnClickListener(this);
        mRLEnglish.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        v.startAnimation(mAnimSelected);
        switch (id) {
            case R.id.select_language:
                if (mIsShowChoosingLanguageLayout) {
                    mIsShowChoosingLanguageLayout = false;
                    mRlSelectLanguage.setBackgroundColor(Color.TRANSPARENT);
                    mLnChoosingLanguage.setVisibility(View.GONE);
                } else {
                    mIsShowChoosingLanguageLayout = true;
                    mRlSelectLanguage.setBackgroundColor(Color.parseColor("#10000000"));
                    mLnChoosingLanguage.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rlTiengViet:
                mImgVietnamSelection.setVisibility(View.VISIBLE);
                mImgEnglishSelection.setVisibility(View.GONE);
                AppUtility.setLocale(getApplicationContext(), "vi");
                mTvLanguageText.setText(getString(R.string.tieng_viet));
                mLanguage.setText(getString(R.string.language));
                mTvChooseLanguage.setText(getString(R.string.choose_language));
                mNextButton.setText(getString(R.string.next));
                break;
            case R.id.rlEnglish:
                mImgVietnamSelection.setVisibility(View.GONE);
                mImgEnglishSelection.setVisibility(View.VISIBLE);
                AppUtility.setLocale(getApplicationContext(), "en");
                mTvLanguageText.setText(getString(R.string.english));
                mLanguage.setText(getString(R.string.language));
                mTvChooseLanguage.setText(getString(R.string.choose_language));
                mNextButton.setText(getString(R.string.next));
                break;
            case R.id.next_button:
                Log.d(TAG, "KienAn: press next button");
                Intent intent = new Intent(MainActivity.this, InformationRegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory( Intent.CATEGORY_HOME );
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            return;
        }

        this.mDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.please_click_back_again_to_exit, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
