package com.example.anonymous.periodchecker.info.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.common.view.BaseFragment;

import java.util.Calendar;

/**
 * Created by Huy Hieu on 12/23/2016.
 */

public class MainFragment extends BaseFragment {

    private TextView tvDayth, tvNumberDayRemain, tvDate;

    private Button btnTriggerCoKinh;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PeriodInfoForCycleFragment.
     */
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initView(view);
        setDateForTextViewDate();
        return view;
    }

    @Override
    public void initView(View view) {
        tvDayth = (TextView) view.findViewById(R.id.fragment_main_day_th_tv);
        tvNumberDayRemain = (TextView) view.findViewById(R.id.fragment_main_number_day_remain_tv);
        tvDate = (TextView) view.findViewById(R.id.fragment_main_date_tv);
        btnTriggerCoKinh = (Button) view.findViewById(R.id.fragment_main_trigger_co_kinh_btn);
        btnTriggerCoKinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }

    private void setDateForTextViewDate() {
        Calendar c = Calendar.getInstance();
        String date = Integer.toString(c.get(Calendar.DATE));
        String month = Integer.toString(c.get(Calendar.MONTH) + 1);
        String dateOFMonth = String.format("%s/%s", date, month);
        tvDate.setText(dateOFMonth);
    }
}
