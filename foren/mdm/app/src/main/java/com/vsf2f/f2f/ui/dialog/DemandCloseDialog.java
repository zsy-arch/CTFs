package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class DemandCloseDialog extends BaseDialog implements NavGroup.OnCheckedChangeListener {
    private String checkReason;
    private CloseListener closeListener;

    /* loaded from: classes2.dex */
    public interface CloseListener {
        void cancelDemand(String str);
    }

    public DemandCloseDialog(Context context, CloseListener closeListener) {
        super(context);
        this.closeListener = closeListener;
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        this.checkReason = nav.getTxtKey().getText().toString();
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_demand_close;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, 17, R.style.AnimBottom);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        NavGroup ng_close = (NavGroup) getView(R.id.ng_close);
        ng_close.setOnCheckedChangeListener(this);
        ng_close.setCheckedChildById(R.id.nv_1);
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_sure).setOnClickListener(this);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        if (v.getId() == R.id.btn_sure) {
            this.closeListener.cancelDemand(this.checkReason);
        }
        dismiss();
    }
}
