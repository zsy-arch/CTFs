package com.vsf2f.f2f.ui.identify;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.alipay.sdk.util.j;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.IdentifyBean;
import com.vsf2f.f2f.ui.utils.Constant;
import java.util.List;

/* loaded from: classes2.dex */
public class IdentifyActivity extends BaseActivity {
    private TextView tv_status_real;
    private TextView tv_status_zhima;
    private IdentifyBean zhimaIdentify;
    private int realType = -1;
    private int zhimaType = -1;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.activity_identify;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.tab_identify, 0);
        this.tv_status_real = (TextView) getView(R.id.tv_status_real);
        this.tv_status_zhima = (TextView) getView(R.id.tv_status_zhima);
        setOnClickListener(R.id.ll_real);
        setOnClickListener(R.id.ll_zhima);
    }

    private void queryResult() {
        getClient().setShowDialog(true);
        getClient().get(R.string.QUERY_CERTIFY_STATUS, ComUtil.getXDDApi(this.context, getString(R.string.QUERY_CERTIFY_STATUS)) + "?userName=" + DemoHelper.getInstance().getCurrentUserName(), null, IdentifyBean.class, true);
    }

    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        queryResult();
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
        boolean z = true;
        switch (v.getId()) {
            case R.id.ll_real /* 2131756023 */:
                Bundle bundle = new Bundle();
                bundle.putInt("type", this.realType);
                startAct(IdentyIdCardActivity.class, bundle);
                return;
            case R.id.tv_real /* 2131756024 */:
            case R.id.tv_status_real /* 2131756025 */:
            default:
                return;
            case R.id.ll_zhima /* 2131756026 */:
                if (this.realType == 3) {
                    MyToast.show(getApplicationContext(), "请先进行实名认证");
                    return;
                } else if (this.realType == 2) {
                    MyToast.show(getApplicationContext(), "实名认证未通过");
                    return;
                } else if (this.zhimaType == 2 || this.zhimaType == 3) {
                    Bundle bundle2 = new Bundle();
                    if (this.zhimaIdentify.getNeedPay() != 1) {
                        z = false;
                    }
                    bundle2.putBoolean("needPay", z);
                    bundle2.putSerializable(j.c, this.zhimaIdentify);
                    startAct(BindZhiMaActivity.class, bundle2);
                    return;
                } else if (this.zhimaType == 0) {
                    MyToast.show(getApplicationContext(), "芝麻认证正在认证中");
                    return;
                } else if (this.zhimaType == 1) {
                    MyToast.show(getApplicationContext(), "芝麻认证已通过");
                    return;
                } else {
                    return;
                }
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        List<IdentifyBean> beanList;
        if (!(result.getErrorCode() != 0 || result.getObj() == null || (beanList = (List) result.getObj()) == null)) {
            for (int i = 0; i < beanList.size(); i++) {
                IdentifyBean bean = beanList.get(i);
                switch (bean.getType()) {
                    case 0:
                        this.realType = bean.getStatus();
                        UserShared.getInstance().save(Constant.CERT_REALNAME, this.realType);
                        this.tv_status_real.setText(bean.getStatusStr());
                        break;
                    case 1:
                        this.zhimaIdentify = bean;
                        this.zhimaType = bean.getStatus();
                        UserShared.getInstance().save(Constant.CERT_ZHIMA, this.zhimaType);
                        this.tv_status_zhima.setText(bean.getStatusStr());
                        break;
                }
            }
        }
    }
}
