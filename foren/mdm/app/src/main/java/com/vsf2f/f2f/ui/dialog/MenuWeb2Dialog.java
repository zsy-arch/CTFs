package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.parse.ParseException;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.WebInitBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes2.dex */
public class MenuWeb2Dialog extends BasePopupMenu {
    private WebMenuListener listener;
    private List<WebInitBean.MoreMenusBean> menus = new ArrayList();

    /* loaded from: classes2.dex */
    public interface WebMenuListener {
        void onMenuSelect(String str, String str2);
    }

    public MenuWeb2Dialog(Context context, List<WebInitBean.MoreMenusBean> menus, WebMenuListener listener) {
        super(context);
        this.menus.addAll(menus);
        this.listener = listener;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initLayoutId() {
        return R.layout.dlg_menu_web2;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected int initWidth() {
        return ParseException.EXCEEDED_QUOTA;
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu
    protected void initView() {
    }

    @Override // com.vsf2f.f2f.ui.dialog.BasePopupMenu, android.widget.PopupWindow
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        LinearLayout menus_parent = (LinearLayout) getView(R.id.web_menus_parent);
        for (final WebInitBean.MoreMenusBean menu : this.menus) {
            TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.web_menu_textview, (ViewGroup) null);
            textView.setText(menu.getTitle());
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.dialog.MenuWeb2Dialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    MenuWeb2Dialog.this.listener.onMenuSelect(menu.getEvent(), menu.getUrl());
                    MenuWeb2Dialog.this.dismiss();
                }
            });
            textView.setVisibility(0);
            menus_parent.addView(textView);
        }
    }
}
