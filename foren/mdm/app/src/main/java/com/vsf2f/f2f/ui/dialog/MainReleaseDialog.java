package com.vsf2f.f2f.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.circle.CircleActivity;
import com.vsf2f.f2f.ui.needs.demand.DemandPublishActivity;
import com.vsf2f.f2f.ui.needs.service.ServicePublishActivity;
import com.vsf2f.f2f.ui.shop.GoodsPublishActivity;
import com.vsf2f.f2f.ui.shop.ShopMainActivity;

/* loaded from: classes2.dex */
public class MainReleaseDialog extends BaseDialog {
    public MainReleaseDialog(Context context) {
        super(context);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected int initLayoutId() {
        return R.layout.dlg_to_release;
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initWindow() {
        windowDeploy(-1.0f, -2.0f, 80, R.style.AnimBottom);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initView() {
        setOnClickListener(R.id.txt_cancel);
        setOnClickListener(R.id.release_btnDemand);
        setOnClickListener(R.id.release_btnService);
        setOnClickListener(R.id.release_btnCircle);
        setOnClickListener(R.id.release_btnProduct);
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void initData() {
    }

    @Override // com.hy.frame.common.BaseDialog
    protected void onViewClick(View v) {
        if (UserShared.getInstance().getIsVerifyState(getContext())) {
            dismiss();
            switch (v.getId()) {
                case R.id.release_btnDemand /* 2131756379 */:
                    getContext().startActivity(new Intent(getContext(), DemandPublishActivity.class));
                    return;
                case R.id.release_btnService /* 2131756380 */:
                    getContext().startActivity(new Intent(getContext(), ServicePublishActivity.class));
                    return;
                case R.id.release_btnCircle /* 2131756381 */:
                    Intent intent = new Intent(getContext(), CircleActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putBoolean("add", true);
                    intent.putExtra(Constant.BUNDLE, bundle);
                    getContext().startActivity(intent);
                    return;
                case R.id.release_btnProduct /* 2131756382 */:
                    if (DemoHelper.getInstance().isOpenStore() == 1) {
                        getContext().startActivity(new Intent(getContext(), GoodsPublishActivity.class));
                        return;
                    } else {
                        getContext().startActivity(new Intent(getContext(), ShopMainActivity.class));
                        return;
                    }
                default:
                    return;
            }
        }
    }
}
