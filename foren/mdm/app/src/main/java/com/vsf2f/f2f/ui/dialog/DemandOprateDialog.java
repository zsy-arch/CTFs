package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;

/* loaded from: classes2.dex */
public class DemandOprateDialog extends BasePopupMenu {
    public DemandOprateDialog(Context context, BasePopupMenu.PopupListener listener) {
        super(context);
        setListener(listener);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initLayoutId() {
        return R.layout.dlg_demand_oprate;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initWidth() {
        return 111;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected void initView() {
        setOnClickListener(R.id.tv_entrust);
        setOnClickListener(R.id.tv_reprice);
        setOnClickListener(R.id.tv_complete);
        setOnClickListener(R.id.tv_close);
    }
}
