package com.example.anonymous.periodchecker.info.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.anonymous.periodchecker.period.OnFragmentInteractionListener;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class SettingFragment extends BaseFragment {

    private OnSettingFragmentInteractionListener mListener;

    private ItemNormal itemNormalCycleMax, itemNormalCycleMin, itemNormalHanhKinh, itemNormalLang, itemNormalMail;

    private NumberPicker npCycleMax, npCycleMin, npHanhKinh;

    private ItemNormal itemNormalVn, itemNormalEng;

    private SwitchCompat swPwd;

    private TextView tvCycleAnnotationMax, tvCycleAnnotationMin;

    private SettingData mSettingData;

    private SettingPresent mSettingPresent;

    private boolean isTouchToSwitch;

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
        NumberPicker[] numberPickers = {npCycleMax, npCycleMin, npHanhKinh};
        initDataForNumberPicker(1, 50, numberPickers);
        return view;
    }

    @Override
    public void initView(View view) {
        // for max cycle
        itemNormalCycleMax = (ItemNormal) view.findViewById(R.id.fragment_setting_cycle_max);
        npCycleMax = (NumberPicker) view.findViewById(R.id.fragment_setting_cycle_max_np);
        tvCycleAnnotationMax = (TextView) view.findViewById(R.id.fragment_setting_cycle_max_annotation_tv);
        View[] itemNormalMaxCycleHides = {npCycleMax, tvCycleAnnotationMax};
        presentWhenClick(itemNormalCycleMax, itemNormalMaxCycleHides);

        // for max cycle
        itemNormalCycleMin = (ItemNormal) view.findViewById(R.id.fragment_setting_cycle_min);
        npCycleMin = (NumberPicker) view.findViewById(R.id.fragment_setting_cycle_min_np);
        tvCycleAnnotationMin = (TextView) view.findViewById(R.id.fragment_setting_cycle_min_annotation_tv);
        View[] itemNormalMinCycleHides = {npCycleMin, tvCycleAnnotationMin};
        presentWhenClick(itemNormalCycleMin, itemNormalMinCycleHides);

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
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, mSettingData.getTypeLaguage());
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
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, TYPE_LANGUAGE.VIET_NAM);
                mSettingData.setTypeLaguage(TYPE_LANGUAGE.VIET_NAM);
                mListener.onChangeLanguage(TYPE_LANGUAGE.VIET_NAM);
            }
        });

        itemNormalEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSelectLangague(itemNormalEng, itemNormalVn, itemNormalLang, TYPE_LANGUAGE.ENGLAND);
                mSettingData.setTypeLaguage(TYPE_LANGUAGE.ENGLAND);
                mListener.onChangeLanguage(TYPE_LANGUAGE.ENGLAND);
            }
        });
        npHanhKinh.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSettingData.setNumberDayHanhKinh(newVal);
                itemNormalHanhKinh.setContentText(newVal + " days");
            }
        });
        npCycleMax.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSettingData.setNumberDayMaxCycle(newVal);
                itemNormalCycleMax.setContentText(newVal + " days");
            }
        });
        npCycleMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mSettingData.setNumberDayMinCycle(newVal);
                itemNormalCycleMin.setContentText(newVal + " days");
            }
        });


        swPwd.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                isTouchToSwitch = true;
                return false;
            }
        });
        swPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                if (isTouchToSwitch) {
                    if (isChecked) {
                        if ("".equals(mSettingData.getPwd())) {
                            PasswordDialog.showDialog(R.string.pwd_dialog_title, new DialogOnClickListener() {
                                @Override
                                public boolean doPositiveClick(String pwd) {
                                    mSettingData.setPwd(pwd);
                                    mSettingData.setUsePassword(isChecked);
                                    return true;
                                }

                                @Override
                                public void doNegativeClick() {
                                    swPwd.setChecked(false);
                                }

                                @Override
                                public int describeContents() {
                                    return 0;
                                }

                                @Override
                                public void writeToParcel(Parcel dest, int flags) {

                                }
                            }, getFragmentManager());
                        } else {
                            mSettingData.setUsePassword(isChecked);
                        }
                    } else {
                        mSettingData.setUsePassword(isChecked);
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        isTouchToSwitch = false;
        mSettingPresent = SettingPresent.newInstance(getContext().getApplicationContext());
        mSettingData = mSettingPresent.getData();
        itemNormalCycleMax.setContentText(mSettingData.getNumberDayMaxCycle() + " days");
        itemNormalCycleMin.setContentText(mSettingData.getNumberDayMinCycle() + " days");
        itemNormalHanhKinh.setContentText(mSettingData.getNumberDayHanhKinh() + " days");
        if (mSettingData.getTypeLaguage().equals(TYPE_LANGUAGE.VIET_NAM)) {
            itemNormalLang.setContentText(getContext().getString(R.string.tieng_viet));
        } else {
            itemNormalLang.setContentText(getContext().getString(R.string.english));
        }
        swPwd.setChecked(mSettingData.isUsePassword());

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnSettingFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSettingFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private void handleSelectLangague(ItemNormal itemNormalEng, ItemNormal itemNormalVn, ItemNormal parent, TYPE_LANGUAGE language) {
        if (language.equals(TYPE_LANGUAGE.VIET_NAM)) {
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