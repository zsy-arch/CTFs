package com.vsf2f.f2f.ui.view;

import android.content.Context;
import android.text.Selection;
import android.text.Spannable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

/* loaded from: classes2.dex */
public class PasteTextView extends TextView {
    public PasteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        int startSelection = getSelectionStart();
        int endSelection = getSelectionEnd();
        if (startSelection < 0 || endSelection < 0) {
            Selection.setSelection((Spannable) getText(), getText().length());
        } else if (startSelection != endSelection && event.getActionMasked() == 0) {
            setText(getText());
        }
        return super.dispatchTouchEvent(event);
    }
}
