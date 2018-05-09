package com.wzrd.v.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wzrd.R;


/**
 * Created by Administrator on 2016/11/24.
 */
public class BannerTabHost extends RelativeLayout {
    private RadioButton rbtLeftIcon;
    private TextView btMessage;

    public BannerTabHost(Context context) {
        super(context);
        initView(context);
    }


    public BannerTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttr(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BannerTabHost);
        int mIcon = array.getResourceId(R.styleable.BannerTabHost_LeftIcon, R.drawable.select_left_icon_selector);
        String mText = array.getString(R.styleable.BannerTabHost_TabText);
        boolean mState = array.getBoolean(R.styleable.BannerTabHost_CheckState, false);

        rbtLeftIcon.setCompoundDrawablesRelativeWithIntrinsicBounds(0, mIcon, 0, 0);



        rbtLeftIcon.setChecked(mState);
        rbtLeftIcon.setText(mText);
    }

    public BannerTabHost(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttr(context, attrs);
    }

    /**
     * @param context
     */
    private void initView(Context context) {
        View view = View.inflate(context, R.layout.custom_radio_text_bt, this);
        rbtLeftIcon = (RadioButton) view.findViewById(R.id.rbt_leftIcon);
        btMessage = (TextView) view.findViewById(R.id.bt_message);
        btMessage.setVisibility(GONE);
    }

    /**
     * @param isVisibility
     */
    public void setBtMessageState(boolean isVisibility) {
        if (isVisibility) {
            btMessage.setVisibility(VISIBLE);
        }
    }

    //public void setRbtLeftIcon(int )

    /**
     * @param messageText
     */
    public void setBtMessageText(String messageText) {
        btMessage.setText(messageText);
    }

    public void setRbtLeftIconChecked(boolean isCheck) {
        rbtLeftIcon.setChecked(isCheck);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
