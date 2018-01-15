package com.cruse.lee.poetry.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cruse.lee.poetry.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Good Good Study, Day Day Up!
 *
 * @author Administrator
 * @date 2018/1/4
 */

public class MainBottomButton extends LinearLayout {

    private List<MainBottomButton> others;
    private ImageView ivBottom;
    private LinearLayout llBottom;
    private TextView tvBottom;
    private View.OnClickListener bottomListener;

    private int imgResource;
    private String textResource;
    private int textColor;

    public void setBottomListener(OnClickListener bottomListener) {
        this.bottomListener = bottomListener;
    }

    public MainBottomButton(@NonNull Context context) {
        this(context, null);
    }

    public MainBottomButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MainBottomButton(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        TypedArray array = getContext().obtainStyledAttributes(attrs, R.styleable.MainBottomButton);
        imgResource = array.getResourceId(R.styleable.MainBottomButton_imgResource, R.drawable.selector_main_poetry);
        textResource = array.getString(R.styleable.MainBottomButton_textResource);
        //ColorSelector用这个取到的是String  "res/color/selector_main_bottom.xml"
        textColor = array.getColor(R.styleable.MainBottomButton_textColor, ContextCompat.getColor(getContext(), R.color.selector_main_bottom));
        ivBottom.setImageResource(imgResource);
        tvBottom.setText(textResource);
        //取到ColorSelector
        ColorStateList colors = array.getColorStateList(R.styleable.MainBottomButton_textColor);
        tvBottom.setTextColor(colors);
        array.recycle();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_main_bottom_button, this, true);
        llBottom = (LinearLayout) findViewById(R.id.llBottom);
        tvBottom = (TextView) findViewById(R.id.tvBottom);
        ivBottom = (ImageView) findViewById(R.id.ivBottom);
        llBottom.setBackgroundResource(R.drawable.selector_main_bottom_bg);
        this.setOnClickListener(clickListener);
    }

    /**
     * 观察者模式，如果有变动，就通知其他两个按钮来改变状态。
     *
     * @param buttons
     */
    public void observeOther(MainBottomButton... buttons) {
        if (others == null) {
            others = new ArrayList<>();
        } else {
            others.clear();
        }
        if (buttons != null) {
            others.addAll(Arrays.asList(buttons));
        }
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        setBottomListener(l);
        super.setOnClickListener(clickListener);

    }

    //重写点击事件，修改底部的方法
    private View.OnClickListener clickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            setSelected(true);
            if (others != null && !others.isEmpty()) {
                for (MainBottomButton button : others) {
                    button.setSelected(false);
                }
            }
            if (bottomListener != null) {
                bottomListener.onClick(view);
            }
        }
    };

}
