package com.example.anonymous.periodchecker.common.view;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.NumberPicker;

import com.example.anonymous.periodchecker.R;

/**
 * Created by Huy Hieu on 12/19/2016.
 */

public abstract class BaseFragment extends Fragment {

    public String TAG = this.getClass().getSimpleName();

    public abstract void initView(View view);

    public abstract void initData();

    /**
     * show or hide view when another view is click
     *
     * @param view      that is click
     * @param viewHides a array views that expect show or hide
     */
    public void presentWhenClick(final View view, final View... viewHides) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickListener(view, viewHides);
            }
        });
    }

    public void presentWhenClick(final View view, final AfterOnClickListener afterOnClickListener, final View... viewHides) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleOnClickListener(view, viewHides);
                afterOnClickListener.handle();
            }
        });
    }

    private void handleOnClickListener(final View view, final View... viewHides) {
        for (int i = 0; i < viewHides.length; i++) {
            if (viewHides[i].getVisibility() == View.VISIBLE) {
                showView(viewHides[i], View.GONE);
                view.setBackgroundColor(Color.TRANSPARENT);
            } else {
                showView(viewHides[i], View.VISIBLE);
                view.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.focus_background_item_color));
            }
        }
    }

    public void showView(View view, int visibility) {
        view.setVisibility(visibility);
    }

    public void initDataForNumberPicker(int maxValue, int minValue, NumberPicker... numberPickers) {
        for (int i = 0; i < numberPickers.length; i++) {
            numberPickers[i].setMaxValue(maxValue);
            numberPickers[i].setMaxValue(minValue);
        }
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

    public interface AfterOnClickListener {
        void handle();
    }
}
