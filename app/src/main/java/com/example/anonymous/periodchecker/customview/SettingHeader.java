package com.example.anonymous.periodchecker.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anonymous.periodchecker.R;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class SettingHeader extends RelativeLayout {

    private TextView tvTitle;

    public SettingHeader(Context context) {
        this(context, null);
    }

    public SettingHeader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.layout_header, this);
            tvTitle = (TextView)findViewById(R.id.layout_setting_header_tv);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Header);

            //get text attribute of ItemNormal and set to text view of it
            CharSequence title = a.getString(R.styleable.Header_android_text);
            this.tvTitle.setText(title);
            a.recycle();
        }
    }


}
