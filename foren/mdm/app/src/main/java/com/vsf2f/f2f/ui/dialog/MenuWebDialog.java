package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import com.parse.ParseException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;

/* loaded from: classes2.dex */
public class MenuWebDialog extends BasePopupMenu {
    public MenuWebDialog(Context context, BasePopupMenu.PopupListener listener) {
        super(context);
        setListener(listener);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initLayoutId() {
        return R.layout.dlg_menu_web;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initWidth() {
        return ParseException.EXCEEDED_QUOTA;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected void initView() {
        setOnClickListener(R.id.web_txtCopy);
        setOnClickListener(R.id.web_txtShare);
        setOnClickListener(R.id.web_txtReport);
        setOnClickListener(R.id.web_txtBrowser);
        setOnClickListener(R.id.web_txtRefresh);
    }
}
