package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class NumPwdView extends LinearLayout {
    private int currentIndex;
    private int fullIndex;
    private TextView[] tvList;

    public NumPwdView(Context context) {
        this(context, null);
    }

    public NumPwdView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.currentIndex = 0;
        this.fullIndex = 6;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.NumPwdView);
        int type = ta.getColor(0, 1);
        ta.recycle();
        if (type == 2) {
            this.fullIndex = 4;
            LayoutInflater.from(context).inflate(R.layout.in_pay_num2, this);
            this.tvList = new TextView[4];
            this.tvList[0] = (TextView) findViewById(R.id.tv_pass1);
            this.tvList[1] = (TextView) findViewById(R.id.tv_pass2);
            this.tvList[2] = (TextView) findViewById(R.id.tv_pass3);
            this.tvList[3] = (TextView) findViewById(R.id.tv_pass4);
            return;
        }
        LayoutInflater.from(context).inflate(R.layout.in_pay_num, this);
        this.tvList = new TextView[6];
        this.tvList[0] = (TextView) findViewById(R.id.tv_pass1);
        this.tvList[1] = (TextView) findViewById(R.id.tv_pass2);
        this.tvList[2] = (TextView) findViewById(R.id.tv_pass3);
        this.tvList[3] = (TextView) findViewById(R.id.tv_pass4);
        this.tvList[4] = (TextView) findViewById(R.id.tv_pass5);
        this.tvList[5] = (TextView) findViewById(R.id.tv_pass6);
    }

    public void onInertKey(String text) {
        if (this.currentIndex >= 0 && this.currentIndex < this.fullIndex) {
            this.tvList[this.currentIndex].setText(text);
            if (this.currentIndex < this.fullIndex) {
                this.currentIndex++;
            }
        }
    }

    public void onDeleteKey() {
        if (this.currentIndex >= 0) {
            if (this.currentIndex > 0) {
                this.currentIndex--;
            }
            this.tvList[this.currentIndex].setText("");
        }
    }

    public int getCurrentIndex() {
        return this.currentIndex;
    }

    public boolean isFull() {
        return this.currentIndex == this.fullIndex;
    }

    public void clearPwd() {
        this.currentIndex = 0;
        for (int i = 0; i < this.tvList.length; i++) {
            this.tvList[i].setText("");
        }
    }

    public String getPwd() {
        StringBuilder pwd = new StringBuilder();
        for (int i = 0; i < this.tvList.length; i++) {
            pwd.append(this.tvList[i].getText().toString());
        }
        return pwd.toString();
    }
}
