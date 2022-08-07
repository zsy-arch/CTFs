package com.vsf2f.f2f.ui.needs.demand;

import android.text.TextUtils;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class HelpSuccessActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_help_success;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.title_help, 0);
        String title = getBundle().getString("title");
        boolean iscom = getBundle().getBoolean("com");
        if (!TextUtils.isEmpty(title)) {
            setTitle(title);
        }
        if (iscom) {
            getView(R.id.txtPrompt1).setVisibility(8);
            getView(R.id.txtPrompt2).setVisibility(8);
            getView(R.id.txtPrompt3).setVisibility(8);
        }
        setOnClickListener(R.id.HelpSuccess_txtToPay);
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
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.HelpSuccess_txtToPay /* 2131755360 */:
                startAct(EntrustActivity.class, getBundle());
                return;
            default:
                return;
        }
    }
}
