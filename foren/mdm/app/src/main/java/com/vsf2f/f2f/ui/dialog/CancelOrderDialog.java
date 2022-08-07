package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.View;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.area.ScreenUtils;

/* loaded from: classes2.dex */
public class CancelOrderDialog extends BaseDialog implements NavGroup.OnCheckedChangeListener, View.OnClickListener {
    private CancelOrderImpl cancelOrder;
    private String checkReason = "";
    private Context context;
    private boolean isService;
    private NavGroup ng_close;

    /* loaded from: classes2.dex */
    public interface CancelOrderImpl {
        void cancelOrder(String str);
    }

    public CancelOrderDialog(Context context, CancelOrderImpl cancelOrder) {
        super(context, R.style.Dialog_FS);
        this.context = context;
        this.cancelOrder = cancelOrder;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_cancel_order;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(ScreenUtils.widthPixels(this.context), 0.0f, 80, R.style.AnimBottom);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        this.ng_close = (NavGroup) findViewById(R.id.ng_close);
        this.ng_close.setOnCheckedChangeListener(this);
        setOnClickListener(R.id.btn_sure);
        setOnClickListener(R.id.btn_cancel);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.checkReason = this.context.getString(R.string.demand_order_cancel_nav1);
        this.ng_close.setCheckedChildById(R.id.nv_1);
    }

    public void setIsService(boolean isService) {
        this.isService = isService;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, @IdRes int checkedId) {
        switch (nav.getId()) {
            case R.id.nv_1 /* 2131755719 */:
                this.checkReason = this.context.getString(R.string.demand_order_cancel_nav1);
                return;
            case R.id.nv_2 /* 2131755720 */:
                this.checkReason = this.context.getString(R.string.demand_order_cancel_nav2);
                return;
            case R.id.nv_3 /* 2131756210 */:
                this.checkReason = this.context.getString(R.string.demand_order_cancel_nav3);
                return;
            case R.id.nv_4 /* 2131756211 */:
                this.checkReason = this.context.getString(R.string.demand_order_cancel_nav4);
                return;
            case R.id.nv_5 /* 2131756212 */:
                this.checkReason = this.context.getString(R.string.demand_order_cancel_nav5);
                return;
            case R.id.nv_6 /* 2131756213 */:
                this.checkReason = this.context.getString(R.string.demand_order_cancel_nav6);
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.BaseDialog, android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_sure) {
            this.cancelOrder.cancelOrder(this.checkReason);
        }
        dismiss();
    }
}
