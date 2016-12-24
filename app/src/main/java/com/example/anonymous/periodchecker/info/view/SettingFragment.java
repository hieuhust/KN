package com.example.anonymous.periodchecker.info.view;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.anonymous.periodchecker.R;
import com.example.anonymous.periodchecker.common.model.TYPE_LANGUAGE;
import com.example.anonymous.periodchecker.common.view.BaseFragment;
import com.example.anonymous.periodchecker.customview.ItemNormal;
import com.example.anonymous.periodchecker.info.model.SettingData;
import com.example.anonymous.periodchecker.info.present.SettingPresent;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class SettingFragment extends BaseFragment {

    private ItemNormal itemNormalCycle, itemNormalHanhKinh, itemNormalLang, itemNormalMail;

    private NumberPicker npCycle, npHanhKinh;

    private ItemNormal itemNormalVn, itemNormalEng;

    private SwitchCompat swPwd;

    private TextView tvCycleAnnotation;

    private SettingData mSettingData;

    private SettingPresent mSettingPresent;

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
        initData();
        // Inject data to number pickers
        NumberPicker[] numberPickers = {npCycle, npHanhKinh};
        initDataForNumberPicker(1, 50, numberPickers);
        return view;
    }

    @Override
    public void initView(View view) {
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
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, mSettingData.getTypeLaguage().getValue());
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
                int language = TYPE_LANGUAGE.VIET_NAM.getValue();
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, language);
                mSettingData.setTypeLaguage(TYPE_LANGUAGE.VIET_NAM);
            }
        });

        itemNormalEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int language = TYPE_LANGUAGE.ENGLAND.getValue();
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, language);
                mSettingData.setTypeLaguage(TYPE_LANGUAGE.ENGLAND);
            }
        });
        npHanhKinh.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSettingData.setNumverDayHanhKinh(newVal);
            }
        });
        npCycle.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSettingData.setNumberDayOfaCycle(newVal);
            }
        });
        swPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSettingData.setUsePassword(isChecked);
            }
        });
    }

    @Override
    public void initData() {
        mSettingPresent = SettingPresent.newInstance();
        mSettingData = mSettingPresent.getData();
    }

    private void handleSelectLangague(ItemNormal itemNormalEng, ItemNormal itemNormalVn, ItemNormal parent, int language) {
        if (language == TYPE_LANGUAGE.VIET_NAM.getValue()) {
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
