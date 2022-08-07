package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.view.View;
import com.em.DemoHelper;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.view.NavGroup;
import com.hy.frame.view.NavView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ConfigBean;

/* loaded from: classes2.dex */
public class PayTypeDialog extends BaseDialog implements NavGroup.OnCheckedChangeListener {
    private int type;

    public PayTypeDialog(Context context) {
        super(context);
    }

    public PayTypeDialog(Context context, int type) {
        super(context);
        this.type = type;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_pay_type;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-2.0f, -2.0f, 17, R.style.AnimTop2);
        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        NavGroup type_group = (NavGroup) getView(R.id.type_group);
        type_group.setCheckedChildByPosition(this.type);
        type_group.setOnCheckedChangeListener(this);
        type_group.setCanClickCurrent();
        NavView lly_alipay = (NavView) getView(R.id.type_ali);
        NavView lly_wxpay = (NavView) getView(R.id.type_wx);
        ConfigBean configBean = DemoHelper.getInstance().readConfig();
        if ("0".equals(configBean.getWith_wx())) {
            lly_wxpay.setVisibility(8);
        }
        if ("0".equals(configBean.getWith_ali())) {
            lly_alipay.setVisibility(8);
        }
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
    }

    @Override // com.hy.frame.view.NavGroup.OnCheckedChangeListener
    public void onCheckedChanged(NavGroup group, NavView nav, int checkedId) {
        switch (nav.getId()) {
            case R.id.type_ali /* 2131756318 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 0);
                    break;
                }
                break;
            case R.id.type_wx /* 2131756319 */:
                if (getListener() != null) {
                    getListener().onDlgConfirm(this, 1);
                    break;
                }
                break;
        }
        dismiss();
    }
}
