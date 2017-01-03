package com.example.anonymous.periodchecker.info.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcel;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.Toast;

import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.common.AppUtility;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;
import com.example.anonymous.periodchecker.common.view.BaseActivity;
import com.example.anonymous.periodchecker.info.model.ResourceData;
import com.example.anonymous.periodchecker.info.model.SettingData;
import com.example.anonymous.periodchecker.period.OnFragmentInteractionListener;
import com.example.anonymous.periodchecker.period.PeriodInfoForCycleFragment;

public class MainInfoActivity extends BaseActivity implements OnFragmentInteractionListener, OnSettingFragmentInteractionListener {

    public static final String LANGUAGE = "language";

    private ViewPager mViewPager;
    private boolean mDoubleBackToExitPressedOnce = false;
    private boolean isChangeLang = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_info);

        mViewPager = (ViewPager) findViewById(R.id.activity_main_info_viewpager);
        if (mViewPager != null) {
            setupViewPager(mViewPager);
        }

        TabLayout tabLayout = (TabLayout) findViewById(R.id.activity_main_info_tabs);
        tabLayout.setupWithViewPager(mViewPager);
        setIconForTabLayout(tabLayout);

        isChangeLang = getIntent().getBooleanExtra(LANGUAGE,false);

        // If activity is called by change language then go to setting fragment
        if (isChangeLang) {
            mViewPager.setCurrentItem(4);
            tabLayout.getTabAt(4).select();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MainFragment.newInstance(), "Category 2");
        adapter.addFragment(new CalendarFragment(), "Category 3");
        adapter.addFragment(new PeriodInfoForCycleFragment(), "Category 3");
        adapter.addFragment(new PeriodInfoForCycleFragment(), "Category 3");
        adapter.addFragment(SettingFragment.newInstance(), "Category 1");
        viewPager.setAdapter(adapter);
    }

    private void setIconForTabLayout(TabLayout tabLayout) {
        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(ResourceData.tabUnSelectedIcons[i]);
            tabLayout.getTabAt(i).setText("");
        }

        // Set the first tab to selected
        tabLayout.getTabAt(0).setIcon(ResourceData.tabSelectedIcons[0]);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.setIcon(ResourceData.tabSelectedIcons[tab.getPosition()]);
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.setIcon(ResourceData.tabUnSelectedIcons[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onChangeLanguage(TYPE_LANGUAGE typeLanguage) {
        isChangeLang = true;
        if (typeLanguage.equals(TYPE_LANGUAGE.VIET_NAM)) {
            AppUtility.setLocale(getApplicationContext(), AppUtility.LANGUAGE_VN);
        } else {
            AppUtility.setLocale(getApplicationContext(), AppUtility.LANGUAGE_ENG);
        }

        Intent refresh = new Intent(this, MainInfoActivity.class);
        refresh.putExtra(LANGUAGE, isChangeLang);
        startActivity(refresh);
        finish();

    }

    @Override
    public void onBackPressed() {
        if (mDoubleBackToExitPressedOnce) {
            Intent homeIntent = new Intent(Intent.ACTION_MAIN);
            homeIntent.addCategory(Intent.CATEGORY_HOME);
            homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(homeIntent);
            return;
        }

        this.mDoubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.please_click_back_again_to_exit, Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                mDoubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }
}
