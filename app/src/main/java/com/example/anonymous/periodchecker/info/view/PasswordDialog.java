package com.example.anonymous.periodchecker.info.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.anonymous.periodchecker.R;

/**
 * Created by Huy Hieu on 12/27/2016.
 */

public class PasswordDialog extends DialogFragment {

    public static final String TITLE_KEY = "title";
    public static final String ICON_KEY = "icon";
    public static final String INTERFACE_KEY = "dialogOnClickListener";

    private int title;
    private DialogOnClickListener dialogOnClickListener;


    private static PasswordDialog newInstance(int title, DialogOnClickListener dialogOnClickListener) {
        PasswordDialog frag = new PasswordDialog();
        Bundle args = new Bundle();
        args.putInt(TITLE_KEY, title);
        args.putParcelable(INTERFACE_KEY, dialogOnClickListener);
        frag.setArguments(args);
        return frag;
    }

    public static void showDialog(int title, DialogOnClickListener dialogOnClickListener, FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = newInstance(title, dialogOnClickListener);
        newFragment.show(ft, "dialog");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getInt(TITLE_KEY);
        dialogOnClickListener = getArguments().getParcelable(INTERFACE_KEY);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.layout_fragment_dialog, container, false);
        final EditText edtPwd = (EditText) v.findViewById(R.id.layout_fragment_dialog_pwd_edt);
        final TextView tvError = (TextView) v.findViewById(R.id.layout_fragment_dialog_error_tv);
        final CheckBox ckbShowPwd = (CheckBox) v.findViewById(R.id.layout_fragment_dialog_show_pwd_ckb);
        Button btnPositive = (Button) v.findViewById(R.id.layout_fragment_dialog_positive_btn);
        Button btnNegative = (Button) v.findViewById(R.id.layout_fragment_dialog_negative_btn);

        getDialog().setTitle(title);
        ckbShowPwd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    edtPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    edtPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                edtPwd.setSelection(edtPwd.length());
            }
        });

        btnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = edtPwd.getText().toString();
                if (content != null && !"".equals(content)) {
                    dialogOnClickListener.doPositiveClick(edtPwd.getText().toString());
                    dismiss();
                } else {
                    tvError.setText(R.string.pwd_info_enter);
                    tvError.setVisibility(View.VISIBLE);
                }
            }
        });

        btnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogOnClickListener.doNegativeClick();
                dismiss();
            }
        });
        return v;
    }
}

