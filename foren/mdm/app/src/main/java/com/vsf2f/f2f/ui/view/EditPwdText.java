package com.vsf2f.f2f.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hy.frame.util.MyLog;

/* loaded from: classes2.dex */
public class EditPwdText extends LinearLayout implements View.OnClickListener, View.OnKeyListener {
    private Context context;
    private InputMethodManager imm;
    private boolean init;
    private OnInputFinishListener listener;
    private StringBuilder sb;

    /* loaded from: classes2.dex */
    public interface OnInputFinishListener {
        void onInputFinish(String str);
    }

    public EditPwdText(Context context) {
        this(context, null);
    }

    public EditPwdText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    @TargetApi(11)
    public EditPwdText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(21)
    public EditPwdText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        if (!this.init) {
            this.init = true;
            setOrientation(0);
            this.context = context;
            for (int i = 1; i <= 6; i++) {
                TextView txt = new TextView(context);
                txt.setId(i);
                txt.setGravity(17);
                txt.setText("");
                txt.setBackgroundColor(-1);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, -1);
                lp.weight = 1.0f;
                if (i > 1) {
                    lp.leftMargin = 1;
                }
                txt.setLayoutParams(lp);
                addView(txt);
            }
            setOnClickListener(this);
            setOnKeyListener(this);
            this.sb = new StringBuilder();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (this.imm == null) {
            this.imm = (InputMethodManager) this.context.getSystemService("input_method");
        }
        this.imm.toggleSoftInputFromWindow(getWindowToken(), 0, 2);
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        MyLog.d(getClass(), "keyCode=" + keyCode + " | KeyEvent=" + event);
        if (event.getAction() != 1) {
            return false;
        }
        if (keyCode >= 7 && keyCode <= 16) {
            this.sb.append(getNumber(keyCode));
            updateUI();
            return true;
        } else if (keyCode != 67) {
            return true;
        } else {
            if (this.sb.length() > 0) {
                this.sb.delete(this.sb.length() - 1, this.sb.length());
            }
            updateUI();
            return true;
        }
    }

    private int getNumber(int keyCode) {
        switch (keyCode) {
            case 7:
            default:
                return 0;
            case 8:
                return 1;
            case 9:
                return 2;
            case 10:
                return 3;
            case 11:
                return 4;
            case 12:
                return 5;
            case 13:
                return 6;
            case 14:
                return 7;
            case 15:
                return 8;
            case 16:
                return 9;
        }
    }

    private void updateUI() {
        int size = this.sb.length();
        if (size == 6 && this.listener != null) {
            this.imm.toggleSoftInputFromWindow(getWindowToken(), 0, 2);
            this.listener.onInputFinish(this.sb.toString());
        }
        for (int i = 0; i < 6; i++) {
            TextView txt = (TextView) getChildAt(i);
            if (size > i) {
                txt.setText("*");
            } else {
                txt.setText("");
            }
        }
    }

    public void setListener(OnInputFinishListener listener) {
        this.listener = listener;
    }
}
