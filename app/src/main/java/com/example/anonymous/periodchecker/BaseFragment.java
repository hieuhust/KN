package com.example.anonymous.periodchecker;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Huy Hieu on 12/19/2016.
 */

public class BaseFragment extends Fragment {

    /**
     * show or hide view when another view is click
     *
     * @param view     that is click
     * @param viewHides a array views that expect show or hide
     */
    public void presentWhenClick(View view, final View... viewHides) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i =0; i<viewHides.length; i++) {
                    if (viewHides[i].getVisibility() == View.VISIBLE)
                        showView(viewHides[i], View.GONE);
                    else showView(viewHides[i], View.VISIBLE);
                }
            }
        });
    }

    public void showView(View view, int visibility) {
        view.setVisibility(visibility);
    }
}
