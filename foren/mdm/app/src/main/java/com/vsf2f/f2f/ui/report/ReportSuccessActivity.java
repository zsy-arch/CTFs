package com.vsf2f.f2f.ui.report;

import android.view.View;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class ReportSuccessActivity extends BaseActivity {
    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_process_success;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        int title = getBundle().getInt("title");
        int msg = getBundle().getInt("msg");
        int subMsg = getBundle().getInt(Constant.SUB_MSG);
        initHeaderBackTxt(title, 0);
        TextView tv_submsg = (TextView) getView(R.id.tv_submsg);
        ((TextView) getView(R.id.tv_title)).setText(getString(msg));
        if (subMsg != 0) {
            tv_submsg.setText(getString(subMsg));
        }
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
    }
}
