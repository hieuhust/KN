package com.example.anonymous.periodchecker.info.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anonymous.periodchecker.BaseFragment;
import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.customview.ItemNormal;
import com.example.anonymous.periodchecker.period.OnFragmentInteractionListener;
import com.example.anonymous.periodchecker.period.PeriodInfoForCycleFragment;

import utils.TYPE_LAGUAGE;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class SettingFragment extends BaseFragment {

    private ItemNormal itemNormalCycle, itemNormalHanhKinh, itemNormalLang, itemNormalMail;

    private NumberPicker npCycle, npHanhKinh;

    private ItemNormal itemNormalVn, itemNormalEng;

    private SwitchCompat swPwd;

    private TextView tvCycleAnnotation;

    private boolean isVnlanguage = false;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment PeriodInfoForCycleFragment.
     */
    public static SettingFragment newInstance() {
        SettingFragment fragment = new SettingFragment();
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

        // Inject data to number pickers
        NumberPicker[] numberPickers = {npCycle, npHanhKinh};
        initDataForNumberPicker(1, 50, numberPickers);
        return view;
    }

    private void initView(View view) {
        // for cycle
        itemNormalCycle = (ItemNormal) view.findViewById(R.id.fragment_setting_cycle_itemnormal);
        npCycle = (NumberPicker) view.findViewById(R.id.fragment_setting_cycle_np);
        tvCycleAnnotation = (TextView) view.findViewById(R.id.fragment_setting_cycle_annotation_tv);
        View[] itemNormalCycleHides = {npCycle, tvCycleAnnotation};
        presentWhenClick(itemNormalCycle, itemNormalCycleHides);

        // for hanh kinh
        itemNormalHanhKinh = (ItemNormal) view.findViewById(R.id.fragment_setting_hanh_kinh_itemnormal);
        npHanhKinh = (NumberPicker) view.findViewById(R.id.fragment_setting_hanh_kinh_np);
        presentWhenClick(itemNormalHanhKinh, npHanhKinh);

        // for language
        itemNormalLang = (ItemNormal) view.findViewById(R.id.fragment_setting_lang_itemnomal);
        itemNormalVn = (ItemNormal) view.findViewById(R.id.fragment_setting_vn_itemnomal);
        itemNormalEng = (ItemNormal) view.findViewById(R.id.fragment_setting_eng_itemnomal);
        ItemNormal[] itemNormalHides = {itemNormalVn, itemNormalEng};
        presentWhenClick(itemNormalLang, new AfterOnClickListener() {
            @Override
            public void handle() {
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, getSelectedLanguage());
            }
        }, itemNormalHides);

        //for email
        itemNormalMail = (ItemNormal) view.findViewById(R.id.fragment_setting_email_itemnomal);

        //for password
        swPwd = (SwitchCompat) view.findViewById(R.id.fragment_setting_pwd_sw);

        // handle callback
        itemNormalVn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int language = TYPE_LAGUAGE.VIET_NAM.getValue();
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, language);
                setSelectedLanguage(language);
            }
        });

        itemNormalEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int language = TYPE_LAGUAGE.ENGLAND.getValue();
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, language);
                setSelectedLanguage(language);
            }
        });
        npHanhKinh.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        npCycle.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

            }
        });
        swPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
    }

    private int getSelectedLanguage() {

        //// TODO: 12/23/2016 Add codes to get value of language
        return TYPE_LAGUAGE
                .ENGLAND.getValue();
    }

    private void setSelectedLanguage(int language) {

        // TODO: 12/23/2016 Add codes to set value of language
    }

    private void handleSelectLangague(ItemNormal itemNormalEng, ItemNormal itemNormalVn, ItemNormal parent, int language) {
        if (language == TYPE_LAGUAGE.VIET_NAM.getValue()) {
            itemNormalVn.presentIcon(View.VISIBLE);
            itemNormalEng.presentIcon(View.GONE);
            parent.setContentText(getContext().getString(R.string.tieng_viet));
        } else {
            itemNormalVn.presentIcon(View.GONE);
            itemNormalEng.presentIcon(View.VISIBLE);
            parent.setContentText(getContext().getString(R.string.english));
        }
    }


}
