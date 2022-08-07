package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.os.Build;
import android.support.annotation.IdRes;
import android.view.View;
import android.widget.ImageView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.BasePopupMenu;

/* loaded from: classes2.dex */
public class GuidePicDialog extends BasePopupMenu {
    private Integer resId;

    public GuidePicDialog(Context context, @IdRes Integer resId, BasePopupMenu.PopupListener listener) {
        super(context);
        this.resId = resId;
        setListener(listener);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initLayoutId() {
        return R.layout.dlg_guide_pic;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initWidth() {
        return -2;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected void initView() {
        ImageView imageView = (ImageView) getViewAndClick(R.id.img_guide);
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    public void showAsDrawTop(View anchor) {
        if (Build.VERSION.SDK_INT >= 19) {
            super.showAsDropDown(anchor, 0, 0, 8388661);
        } else {
            super.showAsDropDown(anchor, 0, anchor.getHeight());
        }
    }
}
