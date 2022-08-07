package com.vsf2f.f2f.ui.pay;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.easeui.widget.EaseSwitchButton;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.KeyValueView;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.IdentifyBean;
import com.vsf2f.f2f.ui.identify.IdentyIdCardActivity;
import com.vsf2f.f2f.ui.user.pwd.ChangePwdActivity;
import com.vsf2f.f2f.ui.utils.Constant;
import com.vsf2f.f2f.ui.utils.FingerUtil;
import java.util.List;

/* loaded from: classes2.dex */
public class PaySettingActivity extends BaseActivity {
    private static final int FINGER_CODE = 748;
    private static final int IDENTIFY_CODE = 788;
    private EaseSwitchButton finger_state;
    private LinearLayout llTouch;
    private int realType = -1;
    private TextView tvTouch;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_pay_setting;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.tab_pay_manager, 0);
        setOnClickListener(R.id.pay_setting_kvPwdchange);
        setOnClickListener(R.id.pay_setting_kvPwdforward);
        this.llTouch = (LinearLayout) getViewAndClick(R.id.pay_setting_llTouch);
        this.tvTouch = (TextView) getViewAndClick(R.id.pay_setting_tvTouch);
        this.finger_state = (EaseSwitchButton) findViewById(R.id.pay_setting_finger_state);
        if (ComUtil.isFinger(this.context)) {
            this.finger_state.openSwitch();
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        queryIdentifyState();
        if (!FingerUtil.checkFingerModule(this.context)) {
            this.tvTouch.setVisibility(8);
            this.llTouch.setVisibility(8);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    private void queryIdentifyState() {
        getClient().get(R.string.QUERY_CERTIFY_STATUS, ComUtil.getXDDApi(this.context, getString(R.string.QUERY_CERTIFY_STATUS)) + "?userName=" + DemoHelper.getInstance().getCurrentUserName(), null, IdentifyBean.class, true);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_CHANGE_DATA_EXT /* 2131296462 */:
                if (((Boolean) result.getObj()).booleanValue()) {
                    MyToast.show(this.context, "指纹支付关闭");
                    ComUtil.setFinger(this.context, false);
                    this.finger_state.closeSwitch();
                    return;
                }
                return;
            case R.string.QUERY_CERTIFY_STATUS /* 2131296507 */:
                List<IdentifyBean> beanList = (List) result.getObj();
                if (beanList != null) {
                    for (int i = 0; i < beanList.size(); i++) {
                        IdentifyBean bean = beanList.get(i);
                        switch (bean.getType()) {
                            case 0:
                                this.realType = bean.getStatus();
                                UserShared.getInstance().save(Constant.CERT_REALNAME, this.realType);
                                ((KeyValueView) getViewAndClick(R.id.pay_setting_kvIdentify)).setValue(bean.getStatusStr());
                                break;
                        }
                    }
                    return;
                }
                return;
            default:
                return;
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        Bundle bundle = new Bundle();
        switch (v.getId()) {
            case R.id.pay_setting_kvIdentify /* 2131755499 */:
                bundle.putInt("type", this.realType);
                startActForResult(IdentyIdCardActivity.class, bundle, IDENTIFY_CODE);
                return;
            case R.id.pay_setting_kvPwdchange /* 2131755500 */:
                int pwdType = UserShared.getInstance().readExtInfo().getPaypwdVersion();
                if (pwdType == 0) {
                    bundle.putString(com.hy.frame.util.Constant.FLAG, Constant.CHANGE_PAY_TYPE);
                    startAct(ChangePwdActivity.class, bundle);
                    return;
                } else if (pwdType == 1) {
                    startActForResult(PayPwdResetActivity.class, 999);
                    return;
                } else {
                    startActForResult(PayPwdEditActivity.class, 999);
                    return;
                }
            case R.id.pay_setting_kvPwdforward /* 2131755501 */:
                startAct(PayPwdFindActivity.class);
                return;
            case R.id.pay_setting_llTouch /* 2131755502 */:
                if (this.finger_state.isSwitchOpen()) {
                    ComUtil.setFinger(this.context, false);
                    AppShare.get(this.context).putInt("toast_finger_num", 3);
                    this.finger_state.closeSwitch();
                    return;
                }
                startActForResult(PayFingerOpenActivity.class, FINGER_CODE);
                return;
            default:
                return;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        if (requestCode == FINGER_CODE) {
            this.finger_state.openSwitch();
        } else if (requestCode == IDENTIFY_CODE) {
            queryIdentifyState();
        }
    }
}
