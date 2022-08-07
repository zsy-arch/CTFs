package com.vsf2f.f2f.ui.needs;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.easeui.widget.EaseAlertDialog;
import com.em.utils.UserShared;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.needs.demand.DemandOrderActivity;
import com.vsf2f.f2f.ui.needs.demand.MyPublishActivity;
import com.vsf2f.f2f.ui.needs.service.ServiceOrderActivity;
import com.vsf2f.f2f.ui.shop.ShopMainActivity;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;
import com.vsf2f.f2f.ui.user.change.BindPhoneActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.web.WebUtils;

/* loaded from: classes2.dex */
public class NeedsManagerActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_needs_manager;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.needs_manager, 0);
        setOnClickListener(R.id.myneeds_navDemand);
        setOnClickListener(R.id.myneeds_navService);
        setOnClickListener(R.id.myneeds_navDemandOrder);
        setOnClickListener(R.id.myneeds_navServiceOrder);
        setOnClickListener(R.id.myneeds_navShopManager);
        setOnClickListener(R.id.myneeds_navShopOrder);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.myneeds_navDemand /* 2131755470 */:
                startAct(MyPublishActivity.class);
                return;
            case R.id.myneeds_navService /* 2131755471 */:
                bundle.putBoolean("isService", true);
                startAct(MyPublishActivity.class, bundle);
                return;
            case R.id.myneeds_navDemandOrder /* 2131755472 */:
                startAct(DemandOrderActivity.class);
                return;
            case R.id.myneeds_navServiceOrder /* 2131755473 */:
                startAct(ServiceOrderActivity.class);
                return;
            case R.id.myneeds_navShopManager /* 2131755474 */:
                if (!UserShared.getInstance().getIsVerifyState(this.context)) {
                    return;
                }
                if (UserShared.getInstance().getInt(Constant.CERT_MOBILE) != 1) {
                    new EaseAlertDialog(this.context, (int) R.string.prompt, (int) R.string.not_bing_phone_prompt, (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.needs.NeedsManagerActivity.1
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle2) {
                            if (confirmed) {
                                NeedsManagerActivity.this.startAct(BindPhoneActivity.class);
                            }
                        }
                    }, true).show();
                    return;
                } else {
                    startAct(ShopMainActivity.class);
                    return;
                }
            case R.id.myneeds_navShopOrder /* 2131755475 */:
                bundle.putString(com.hy.frame.util.Constant.FLAG, WebUtils.getTokenUrl(this.context, getString(R.string.ORDER_URL)));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            default:
                return;
        }
    }
}
