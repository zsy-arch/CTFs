package com.easeui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/* loaded from: classes.dex */
public abstract class EaseChatPrimaryMenuBase extends RelativeLayout {
    protected Activity activity;
    protected InputMethodManager inputManager;
    protected EaseChatPrimaryMenuListener listener;

    /* loaded from: classes.dex */
    public interface EaseChatPrimaryMenuListener {
        void onEditTextClicked();

        boolean onPressToSpeakBtnTouch(View view, MotionEvent motionEvent);

        void onSendBtnClicked(String str);

        void onToggleEmojiconClicked();

        void onToggleExtendClicked();

        void onToggleVoiceBtnClicked();
    }

    public abstract ImageView getBtnMOre();

    public abstract EditText getEditText();

    public abstract void onEmojiconDeleteEvent();

    public abstract void onEmojiconInputEvent(CharSequence charSequence);

    public abstract void onExtendMenuContainerHide();

    public abstract void onTextInsert(CharSequence charSequence);

    public abstract void showMenu(boolean z);

    public EaseChatPrimaryMenuBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public EaseChatPrimaryMenuBase(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EaseChatPrimaryMenuBase(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        this.activity = (Activity) context;
        this.inputManager = (InputMethodManager) context.getSystemService("input_method");
    }

    public void setChatPrimaryMenuListener(EaseChatPrimaryMenuListener listener) {
        this.listener = listener;
    }

    public void hideKeyboard() {
        if (this.activity.getWindow().getAttributes().softInputMode != 2 && this.activity.getCurrentFocus() != null) {
            this.inputManager.hideSoftInputFromWindow(this.activity.getCurrentFocus().getWindowToken(), 2);
        }
    }
}
