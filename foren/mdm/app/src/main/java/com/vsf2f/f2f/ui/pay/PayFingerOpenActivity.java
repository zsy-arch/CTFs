package com.vsf2f.f2f.ui.pay;

import android.os.Bundle;
import android.view.View;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.MD5;
import com.easeui.widget.EaseAlertDialog;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.view.NumKeyView;
import com.vsf2f.f2f.ui.view.NumPwdView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PayFingerOpenActivity extends BaseActivity {
    private NumPwdView mPwdView;
    private String md5pwd;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        return R.layout.act_pay_finger_open;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.pay_finger_open, 0);
        this.mPwdView = (NumPwdView) findViewById(R.id.pwdeditview);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        ((NumKeyView) findViewById(R.id.keyboardview)).setOnKeyPressListener(new NumKeyView.OnKeyPressListener() { // from class: com.vsf2f.f2f.ui.pay.PayFingerOpenActivity.1
            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onInertKey(String text) {
                PayFingerOpenActivity.this.mPwdView.onInertKey(text);
                if (PayFingerOpenActivity.this.mPwdView.isFull()) {
                    try {
                        PayFingerOpenActivity.this.md5pwd = MD5.md5Encode(MD5.md5Encode(PayFingerOpenActivity.this.mPwdView.getPwd()));
                        PayFingerOpenActivity.this.requestFinger(PayFingerOpenActivity.this.md5pwd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.vsf2f.f2f.ui.view.NumKeyView.OnKeyPressListener
            public void onDeleteKey() {
                PayFingerOpenActivity.this.mPwdView.onDeleteKey();
            }
        });
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    public void requestFinger(String pwdMd5) {
        JSONObject jsonObj = new JSONObject();
        try {
            JSONObject jsonDatas = new JSONObject();
            JSONObject jsonCheck = new JSONObject();
            jsonDatas.put("fingerprintPayment", 1);
            jsonCheck.put("payPwd", pwdMd5);
            jsonObj.put("type", "PAY_PWD");
            jsonObj.put("datas", jsonDatas);
            jsonObj.put("check", jsonCheck);
            MyLog.e(jsonObj.toString() + "");
            getClient().setShowDialog(true);
            getClient().post(R.string.API_USER_CHANGE_DATA_EXT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_CHANGE_DATA_EXT)), jsonObj.toString() + "", null, Boolean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_USER_CHANGE_DATA_EXT /* 2131296462 */:
                if (((Boolean) result.getObj()).booleanValue()) {
                    setResult(-1);
                    try {
                        ComUtil.setFinger(this.context, true);
                        ComUtil.setPaypwd(this.context, this.md5pwd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    new EaseAlertDialog(this.context, "", "已开启指纹支付,下次可通过验证指纹完成支付", (Bundle) null, new EaseAlertDialog.AlertDialogListener() { // from class: com.vsf2f.f2f.ui.pay.PayFingerOpenActivity.2
                        @Override // com.easeui.widget.EaseAlertDialog.AlertDialogListener
                        public void onResult(boolean confirmed, Bundle bundle) {
                            PayFingerOpenActivity.this.finish();
                        }
                    }, false).setOnTouch(false).show();
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
    }
}
