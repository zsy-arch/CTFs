package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;

/* loaded from: classes2.dex */
public class MenuNearDialog extends BasePopupMenu {
    public MenuNearDialog(Context context, BasePopupMenu.PopupListener listener) {
        super(context);
        setListener(listener);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initLayoutId() {
        return R.layout.dlg_menu_near;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initWidth() {
        return 150;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected void initView() {
        setOnClickListener(R.id.nv_nearby_demand);
        setOnClickListener(R.id.nv_nearby_service);
        setOnClickListener(R.id.nv_nearby_seller);
        setOnClickListener(R.id.nv_nearby_people);
    }
}
