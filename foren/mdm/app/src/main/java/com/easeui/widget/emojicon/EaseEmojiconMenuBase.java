package com.easeui.widget.emojicon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.easeui.domain.EaseEmojicon;

/* loaded from: classes.dex */
public class EaseEmojiconMenuBase extends LinearLayout {
    protected EaseEmojiconMenuListener listener;

    /* loaded from: classes.dex */
    public interface EaseEmojiconMenuListener {
        void onDeleteImageClicked();

        void onExpressionClicked(EaseEmojicon easeEmojicon);
    }

    public EaseEmojiconMenuBase(Context context) {
        super(context);
    }

    @SuppressLint({"NewApi"})
    public EaseEmojiconMenuBase(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public EaseEmojiconMenuBase(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEmojiconMenuListener(EaseEmojiconMenuListener listener) {
        this.listener = listener;
    }
}
