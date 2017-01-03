package com.example.anonymous.periodchecker.info.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.common.view.BaseFragment;
import com.example.anonymous.periodchecker.period.OnFragmentInteractionListener;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

import java.util.Calendar;

/**
 * Created by sev_user on 12/23/2016.
 */
public class CalendarFragment extends BaseFragment {
    private OnFragmentInteractionListener mListener;
    private MaterialCalendarView mCalendarView;
    private TextView tvCurrentSelecteDate;
    private Button mBtAddNote;
    public CalendarFragment() {
        // Required empty public constructor
    }

    public static CalendarFragment newInstance() {
        CalendarFragment fragment = new CalendarFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        initView(view);
        return view;
    }

    @Override
    public void initView(View view) {
        mCalendarView = (MaterialCalendarView) view.findViewById(R.id.calendarView);
        tvCurrentSelecteDate = (TextView) view.findViewById(R.id.tvDate);
        mBtAddNote = (Button) view.findViewById(R.id.btnAddNote);
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                int month = date.getMonth() + 1;
                tvCurrentSelecteDate.setText(getDayOfWeek(date.getDate().getDay()) + ", " + date.getDay() + "/" + month);
            }
        });

        mCalendarView.setDateSelected(Calendar.getInstance(), true);
        Calendar calendar = Calendar.getInstance();
        tvCurrentSelecteDate.setText(getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK)) + ", " + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH));
        mBtAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void initData() {

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private String getDayOfWeek(int n) {
        if (n == 0) return "Sun";
        if (n == 1) return "Mon";
        if (n == 2) return "Tue";
        if (n == 3) return "Wed";
        if (n == 4) return "Thu";
        if (n == 5) return "Fri";
        return "Sat";
    }
}
