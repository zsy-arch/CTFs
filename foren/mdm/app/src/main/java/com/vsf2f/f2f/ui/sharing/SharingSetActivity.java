package com.vsf2f.f2f.ui.sharing;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.hy.frame.util.Constant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.user.WebKitLocalActivity;

/* loaded from: classes2.dex */
public class SharingSetActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_sharing_set;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.sharing_title_set, 0);
        setOnClickListener(R.id.sharing_pay_kvRecord);
        setOnClickListener(R.id.sharing_pay_kvManager);
        setOnClickListener(R.id.sharing_pay_kvApply);
        setOnClickListener(R.id.sharing_pay_kvHelp);
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
            case R.id.sharing_pay_kvRecord /* 2131755642 */:
                startAct(SharingRecordActivity.class);
                return;
            case R.id.sharing_pay_kvManager /* 2131755643 */:
                startAct(SharingListActivity.class);
                return;
            case R.id.sharing_pay_kvApply /* 2131755644 */:
                startAct(SharingApplyActivity.class);
                return;
            case R.id.sharing_pay_kvHelp /* 2131755645 */:
                Bundle bundle = new Bundle();
                bundle.putString(Constant.FLAG_TITLE, getString(R.string.sharing_title_help));
                bundle.putString(Constant.FLAG, ComUtil.getZCApi(this.context, getString(R.string.URL_HELP_COMMON_CODE, new Object[]{"gxb_notice_info"})));
                bundle.putBoolean(Constant.FLAG2, false);
                startAct(WebKitLocalActivity.class, bundle);
                return;
            default:
                return;
        }
    }
}
