package com.example.anonymous.periodchecker.info;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anonymous.periodchecker.BaseFragment;
import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.period.OnFragmentInteractionListener;
import com.example.anonymous.periodchecker.period.PeriodInfoForCycleFragment;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class SettingFragment extends BaseFragment {



    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PeriodInfoForCycleFragment.
     */
    public static PeriodInfoForCycleFragment newInstance() {
        PeriodInfoForCycleFragment fragment = new PeriodInfoForCycleFragment();
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
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

    }


    public void onButtonPressed(Uri uri) {
       /* if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }*/
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
