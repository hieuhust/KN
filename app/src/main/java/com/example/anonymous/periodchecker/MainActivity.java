package com.example.anonymous.periodchecker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "mainactivity";

    RelativeLayout mRlSelectLanguage, mRLVietnam, mRLEnglish;
    ImageView mImgEnglishSelection, mImgVietnamSelection;
    LinearLayout mLnChoosingLanguage;
    Button mNextButton;
    boolean mIsShowChoosingLanguageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRlSelectLanguage = (RelativeLayout) findViewById(R.id.select_language);
        mRLVietnam  = (RelativeLayout) findViewById(R.id.rlTiengViet);
        mRLEnglish  = (RelativeLayout) findViewById(R.id.rlEnglish);

        mImgVietnamSelection = (ImageView) findViewById(R.id.imgTiengVietSelection);
        mImgEnglishSelection = (ImageView) findViewById(R.id.imgEnglishSelection);

        mLnChoosingLanguage = (LinearLayout) findViewById(R.id.lnChoosingLanguage);
        mNextButton = (Button) findViewById(R.id.next_button);

        mRlSelectLanguage.setOnClickListener(this);
        mRLVietnam.setOnClickListener(this);
        mRLEnglish.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
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
                break;
            case R.id.rlEnglish:
                mImgVietnamSelection.setVisibility(View.GONE);
                mImgEnglishSelection.setVisibility(View.VISIBLE);
                break;
            case R.id.next_button:
                Log.d(TAG, "KienAn: press next button");
                Intent intent = new Intent(MainActivity.this, InformationRegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
