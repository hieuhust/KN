package com.example.anonymous.periodchecker.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.anonymous.periodchecker.R;

/**
 * Created by Huy Hieu on 12/21/2016.
 */

public class ItemNormal extends RelativeLayout {

    private RelativeLayout rlContainer;
    private TextView tvTitle, tvContent;
    private ImageView imv;

    public ItemNormal(Context context) {
        this(context, null);
    }

    public ItemNormal(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemNormal(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (inflater != null) {
            inflater.inflate(R.layout.layout_item_normal, this);
            this.rlContainer = (RelativeLayout) findViewById(R.id.item_normal_rl);
            this.tvTitle = (TextView) findViewById(R.id.item_normal_title_imv);
            this.tvContent = (TextView) findViewById(R.id.item_normal_content_tv);
            this.imv = (ImageView) findViewById(R.id.item_normal_imv);


            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ItemNormal);

            //get text attribute of ItemNormal and set to text view of it
            CharSequence title = a.getString(R.styleable.ItemNormal_mytitle);
            this.tvTitle.setText(title);
            CharSequence content = a.getString(R.styleable.ItemNormal_detail);
            this.tvContent.setText(content);
            a.recycle();

            //get drwable id from ItemNormal and set to image view of it
            int resId = attrs.getAttributeResourceValue("http://schemas.android.com/apk/res/android", "src", 0);
            if(resId !=0) {
                imv.setImageResource(resId);
            }
            else {
                imv.setVisibility(View.GONE);
            }
        }
    }


}
