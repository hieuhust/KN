package com.example.anonymous.periodchecker.info.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.example.anonymous.periodchecker.BaseActivity;
import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.info.model.ResourceData;
import com.example.anonymous.periodchecker.period.OnFragmentInteractionListener;
import com.example.anonymous.periodchecker.period.PeriodInfoForCycleFragment;

public class MainInfoActivity extends BaseActivity implements OnFragmentInteractionListener {

    private ViewPager mViewPager;

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

    }

    private void setupViewPager(ViewPager viewPager) {
        ScreenSlidePagerAdapter adapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MainFragment.newInstance(), "Category 2");
        adapter.addFragment(new PeriodInfoForCycleFragment(), "Category 3");
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
}
