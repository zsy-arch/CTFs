package com.vsf2f.f2f.ui.user;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.em.ui.ChatActivity;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class HelpCenterActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_help_center;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.settings_help, 0);
        setOnClickListener(R.id.system_notice);
        setOnClickListener(R.id.system_bulletin);
        setOnClickListener(R.id.settings_help);
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
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.system_notice /* 2131755356 */:
                bundle.putSerializable("username", "admin");
                startAct(ChatActivity.class, bundle);
                return;
            case R.id.system_bulletin /* 2131755357 */:
                bundle.putString(Constant.FLAG_TITLE, getString(R.string.system_bulletin));
                bundle.putString(Constant.FLAG, getString(R.string.API_HOST) + getString(R.string.SYS_MSG_URL));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            case R.id.settings_help /* 2131755358 */:
                bundle.putString(Constant.FLAG, getString(R.string.API_HOST) + getString(R.string.HELP_URL));
                startAct(WebKitLocalActivity.class, bundle);
                return;
            default:
                return;
        }
    }
}
