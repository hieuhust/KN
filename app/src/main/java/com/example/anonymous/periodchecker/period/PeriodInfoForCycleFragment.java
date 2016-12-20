package com.example.anonymous.periodchecker.period;

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

public class PeriodInfoForCycleFragment extends BaseFragment {

    private OnFragmentInteractionListener mListener;

    private RelativeLayout rlDayNumber;
    private TextView tvDayNumber;
    private ImageView imvDayNumber;
    private NumberPicker npDayNumber;
    private TextView tvAnnotationDayNumber;

    private RelativeLayout rlHanhKinhDayNumber;
    private TextView tvHanhKinhDayNumber;
    private ImageView imvHanhKinhDayNumber;
    private NumberPicker npHanhKinhDayNumber;

    private RelativeLayout rlFirstDayCycle;
    private ImageView imvFirstDayCycle;
    private DatePicker dpFirstDayCycle;

    public PeriodInfoForCycleFragment() {
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
        View view = inflater.inflate(R.layout.fragment_period_info_cycle, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rlDayNumber = (RelativeLayout) view.findViewById(R.id.fragment_period_info_cycle_day_number_rl);
        tvDayNumber = (TextView) view.findViewById(R.id.fragment_period_info_cycle_day_number_tv);
        imvDayNumber = (ImageView) view.findViewById(R.id.fragment_period_info_cycle_day_number_imv);
        npDayNumber = (NumberPicker) view.findViewById(R.id.fragment_period_info_cycle_day_number_np);
        tvAnnotationDayNumber = (TextView) view.findViewById(R.id.fragment_period_info_cycle_day_number_annotation_tv);
        rlHanhKinhDayNumber = (RelativeLayout) view.findViewById(R.id.fragment_period_info_cycle_day_number_hanh_kinh_rl);
        tvHanhKinhDayNumber = (TextView) view.findViewById(R.id.fragment_period_info_cycle_day_number_hanh_kinh_tv);
        imvHanhKinhDayNumber = (ImageView) view.findViewById(R.id.fragment_period_info_cycle_day_number_hanh_kinh_imv);
        npHanhKinhDayNumber = (NumberPicker) view.findViewById(R.id.fragment_period_info_cycle_day_number_hanh_kinh_np);
        rlFirstDayCycle = (RelativeLayout) view.findViewById(R.id.fragment_period_info_cycle_first_day_cycle_rl);
        imvFirstDayCycle = (ImageView) view.findViewById(R.id.fragment_period_info_cycle_first_day_cycle_imv);
        dpFirstDayCycle = (DatePicker) view.findViewById(R.id.fragment_period_info_cycle_first_day_cycle_dp);
        View[] dayNumberViewHides = {npDayNumber, tvAnnotationDayNumber};
        presentWhenClick(rlDayNumber, dayNumberViewHides);
        presentWhenClick(rlHanhKinhDayNumber, npHanhKinhDayNumber);
        presentWhenClick(rlFirstDayCycle, dpFirstDayCycle);
    }


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

}
