package com.example.anonymous.periodchecker;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by Annnn on 12/18/2016.
 */

public class InformationRegisterActivity extends Activity implements View.OnClickListener {

    RelativeLayout mRlSelectDateofbirth, mRlSelectDateofbirth1, mRlBack;
    boolean isShowPickedDate;
    Button nextButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_register);

        mRlSelectDateofbirth = (RelativeLayout) findViewById(R.id.select_dateofbirth);
        mRlSelectDateofbirth1 = (RelativeLayout) findViewById(R.id.select_dateofbirth_1);
        mRlBack = (RelativeLayout) findViewById(R.id.rlBack);
        nextButton = (Button) findViewById(R.id.next_button);
        mRlSelectDateofbirth.setOnClickListener(this);
        mRlBack.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_select));
        switch (v.getId()) {
            case R.id.select_dateofbirth :
                if (isShowPickedDate) {
                    isShowPickedDate = false;
                    mRlSelectDateofbirth.setBackgroundColor(Color.TRANSPARENT);
                    mRlSelectDateofbirth1.setVisibility(View.GONE);
                } else {
                    isShowPickedDate = true;
                    mRlSelectDateofbirth.setBackgroundColor(Color.parseColor("#10000000"));
                    mRlSelectDateofbirth1.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.rlBack:
                finish();
                break;
            case R.id.next_button:
                Intent intent = new Intent(InformationRegisterActivity.this, MenstrualInformationRegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
