package com.vsf2f.f2f.ui.pay;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.util.j;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.PayUtil;
import com.cdlinglu.utils.pay.Base64;
import com.cdlinglu.utils.pay.PayResult;
import com.cdlinglu.utils.pay.WxPayInfo;
import com.em.DemoHelper;
import com.em.utils.UserShared;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.NavGroup;
import com.hy.http.AjaxParams;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ConfigBean;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class PayActivity extends BaseActivity implements PayUtil.PayListener {
    public static final int ACTIVATE = 2;
    private static final int ALI_PAY_FLAG = 101;
    public static final int USER_VIP = 1;
    private static final int WX_PAY_FLAG = 102;
    private NavGroup groupPay;
    private boolean isOpenWx;
    @SuppressLint({"HandlerLeak"})
    protected Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.ui.pay.PayActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    PayResult payResult = new PayResult((String) msg.obj);
                    String result = payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    String memo = payResult.getMemo();
                    if (TextUtils.equals(resultStatus, "9000")) {
                        try {
                            switch (PayActivity.this.type) {
                                case 1:
                                    PayActivity.this.sendAliResult(resultStatus, Base64.encode(result.getBytes("utf-8")), memo);
                                    break;
                                case 2:
                                    PayActivity.this.payOK_ACTIVATE();
                                    break;
                            }
                            return;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return;
                        }
                    } else if (TextUtils.equals(resultStatus, "8000")) {
                        Toast.makeText(PayActivity.this, PayActivity.this.getString(R.string.pay_result), 0).show();
                        return;
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(PayActivity.this, PayActivity.this.getString(R.string.be_canceled), 0).show();
                        return;
                    } else {
                        Toast.makeText(PayActivity.this, PayActivity.this.getString(R.string.pay_failed), 0).show();
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private String money;
    private PayUtil.PayStyle style;
    private String title;
    private TextView txtMoney;
    private TextView txtPrompt;
    private int type;
    private String url_activateParam;
    private String url_vipParam;

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        MyApplication.wxErrCode = 1;
        return R.layout.act_pay;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBack(R.string.pay, 0);
        this.money = getBundle().getString(Constant.FLAG);
        this.title = getBundle().getString(Constant.FLAG_TITLE);
        this.type = getBundle().getInt(Constant.FLAG_TYPE);
        this.txtMoney = (TextView) getView(R.id.pay_txtMoney);
        this.groupPay = (NavGroup) getView(R.id.pay_groupPay);
        this.txtPrompt = (TextView) getView(R.id.pay_txtPrompt);
        setOnClickListener(R.id.txt_paysure);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        if (!TextUtils.isEmpty(this.title)) {
            setTitle(this.title);
        }
        this.txtMoney.setText("￥" + this.money);
        if (this.type == 2) {
            this.txtPrompt.setVisibility(0);
        }
        ConfigBean configBean = DemoHelper.getInstance().readConfig();
        if ("0".equals(configBean.getPay_wx())) {
            getView(R.id.pay_navWxPay).setVisibility(8);
        }
        if ("0".equals(configBean.getPay_ali())) {
            getView(R.id.pay_navAliPay).setVisibility(8);
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.txt_paysure /* 2131755486 */:
                if (this.groupPay.getCheckedNavViewId() == R.id.pay_navAliPay) {
                    this.style = PayUtil.PayStyle.AliPay;
                    this.url_vipParam = "append_alipay_4";
                    this.url_activateParam = "alipay";
                } else {
                    this.style = PayUtil.PayStyle.WxPay;
                    this.url_vipParam = "append_weixin_4";
                    this.url_activateParam = "weixin";
                }
                switch (this.type) {
                    case 1:
                        getPayOrder_VIP();
                        return;
                    case 2:
                        getPayOrder_ACTIVATE();
                        return;
                    default:
                        return;
                }
            default:
                return;
        }
    }

    private void getPayOrder_VIP() {
        getClient().setShowDialog(true);
        getClient().post(R.string.API_USER_OPENVIP, ComUtil.getZCApi(this.context, getString(R.string.API_USER_OPENVIP, new Object[]{this.url_vipParam})), new AjaxParams(), String.class, false);
    }

    private void getPayOrder_ACTIVATE() {
        getClient().setShowDialog(true);
        AjaxParams params = new AjaxParams();
        params.put("payFlag", this.url_activateParam);
        getClient().post(R.string.API_USER_ACTIVATE, ComUtil.getZCApi(this.context, getString(R.string.API_USER_ACTIVATE)), params, String.class, false);
    }

    public void testApi(View view) {
        sendAliResult(j.a, j.c, j.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAliResult(String resultStatus, String result, String memo) {
        AjaxParams params = new AjaxParams();
        params.put(j.a, resultStatus);
        params.put(j.c, result);
        params.put(j.b, memo);
        getClient().post(R.string.API_USER_ALIPAY_RESULT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_ALIPAY_RESULT)), params);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        String obj = null;
        switch (result.getRequestCode()) {
            case R.string.API_USER_ACTIVATE /* 2131296451 */:
                obj = (String) result.getObj();
                try {
                    obj = new JSONObject(obj).optString("orderInfo");
                    break;
                } catch (JSONException e) {
                    e.printStackTrace();
                    break;
                }
            case R.string.API_USER_ALIPAY_RESULT /* 2131296452 */:
                if (result.getObj().equals("true")) {
                    switch (this.type) {
                        case 1:
                            payOK_OPENVIP();
                            return;
                        case 2:
                            payOK_ACTIVATE();
                            return;
                        default:
                            return;
                    }
                } else {
                    MyToast.show(this, getString(R.string.pay_failed));
                    return;
                }
            case R.string.API_USER_OPENVIP /* 2131296470 */:
                break;
            default:
                return;
        }
        if (obj == null) {
            obj = (String) result.getObj();
        }
        final String orderInfo = "";
        try {
            orderInfo = new String(com.alibaba.fastjson.util.Base64.decodeFast(obj), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        if (this.style == PayUtil.PayStyle.AliPay) {
            ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.pay.PayActivity.1
                @Override // java.lang.Runnable
                public void run() {
                    String result2 = new PayTask(PayActivity.this).pay(orderInfo, true);
                    Message msg = new Message();
                    msg.what = 101;
                    msg.obj = result2;
                    PayActivity.this.mHandler.sendMessage(msg);
                }
            });
            return;
        }
        try {
            new PayUtil(this, this.style).toWxPay(new WxPayInfo(new JSONObject(orderInfo)));
        } catch (JSONException e3) {
            e3.printStackTrace();
            MyToast.show(this, "调取微信失败");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isOpenWx) {
            this.isOpenWx = false;
            MyLog.d("PayAct:errCode=" + MyApplication.wxErrCode);
            if (MyApplication.wxErrCode == 0) {
                MyApplication.wxErrCode = 1;
                switch (this.type) {
                    case 1:
                        payOK_OPENVIP();
                        return;
                    case 2:
                        payOK_ACTIVATE();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void payOK_ACTIVATE() {
        setResult(-1);
        finish();
    }

    public void payOK_OPENVIP() {
        UserShared.getInstance().setOpenVip(4);
        setResult(-1);
        finish();
    }

    @Override // com.cdlinglu.utils.PayUtil.PayListener
    public void paySuccess(PayUtil.PayStyle style) {
        this.isOpenWx = true;
    }

    @Override // com.cdlinglu.utils.PayUtil.PayListener
    public void payFail(String msg) {
    }
}
