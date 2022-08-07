package com.vsf2f.f2f.ui.identify;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.dialog.WarnDialog;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;

/* loaded from: classes2.dex */
public class BindZhiMaActivity extends BaseActivity {
    private boolean needPay;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_bind_zhi_ma;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.title_bind_zhima, R.drawable.icon_help_white);
        setOnClickListener(R.id.btn_bind);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        this.needPay = getBundle().getBoolean("needPay");
        if (this.needPay) {
            new WarnDialog(this.context, getString(R.string.identify_zhima_read), "我知道了", false).show();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onRightClick() {
        Bundle bundle = new Bundle();
        bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getStringIds(Integer.valueOf((int) R.string.URL_HELP_IDENTY_ZHIMA))));
        startAct(WebKitLocalActivity.class, bundle);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bind /* 2131755876 */:
                if (this.needPay) {
                    startAct(IdentifyPayActivity.class, getBundle());
                    return;
                } else {
                    startAct(AgreeAuthorByZmActivity.class);
                    return;
                }
            default:
                return;
        }
    }
}
