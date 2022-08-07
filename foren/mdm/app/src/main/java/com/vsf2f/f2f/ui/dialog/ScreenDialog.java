package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import com.parse.ParseException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;

/* loaded from: classes2.dex */
public class ScreenDialog extends BasePopupMenu {
    public ScreenDialog(Context context, BasePopupMenu.PopupListener listener) {
        super(context);
        setListener(listener);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initLayoutId() {
        return R.layout.dlg_screen;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initWidth() {
        return ParseException.EXCEEDED_QUOTA;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected void initView() {
        setOnClickListener(R.id.txt_all);
        setOnClickListener(R.id.txt_man);
        setOnClickListener(R.id.txt_woman);
    }
}
