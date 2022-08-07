package com.vsf2f.f2f.ui.identify;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
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
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.util.EMPrivateConstant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.IdentifyBean;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class IdentifyPayActivity extends BaseActivity implements PayUtil.PayListener {
    private static final int ALI_PAY_FLAG = 101;
    private static final int WX_PAY_FLAG = 102;
    private ImageView alipay_check;
    private IdentifyBean infoResult;
    private boolean isOpenWx;
    private PayUtil.PayStyle style;
    private TextView tv_price;
    private String url_activateParam;
    private ImageView wxpay_check;
    private int reWard = 0;
    private int payType = 0;
    @SuppressLint({"HandlerLeak"})
    protected Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.ui.identify.IdentifyPayActivity.2
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
                            IdentifyPayActivity.this.sendAliResult(resultStatus, Base64.encode(result.getBytes("utf-8")), memo);
                            return;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return;
                        }
                    } else if (TextUtils.equals(resultStatus, "8000")) {
                        Toast.makeText(IdentifyPayActivity.this, IdentifyPayActivity.this.getString(R.string.pay_ensure), 0).show();
                        return;
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(IdentifyPayActivity.this, IdentifyPayActivity.this.getString(R.string.be_canceled), 0).show();
                        return;
                    } else {
                        Toast.makeText(IdentifyPayActivity.this, IdentifyPayActivity.this.getString(R.string.pay_failed), 0).show();
                        return;
                    }
                default:
                    return;
            }
        }
    };

    @Override // com.hy.frame.common.IBaseActivity
    public int initLayoutId() {
        MyApplication.wxErrCode = 1;
        return R.layout.activity_entrust;
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initView() {
        initHeaderBackTxt(R.string.title_identify_zhima, 0);
        ((TextView) getView(R.id.tv_paytitle)).setText("此次支付金额");
        ((TextView) getView(R.id.tv_payZhima)).setVisibility(0);
        this.infoResult = (IdentifyBean) getBundle().getSerializable(j.c);
        setOnClickListener(R.id.tv_pay);
        setOnClickListener(R.id.Entrust_llyAlipay);
        setOnClickListener(R.id.Entrust_llyWxpay);
        this.alipay_check = (ImageView) getView(R.id.Entrust_llyAlipay_check);
        this.wxpay_check = (ImageView) getView(R.id.Entrust_llyWxpay_check);
        this.alipay_check.setSelected(true);
        this.tv_price = (TextView) getView(R.id.tv_price);
        this.reWard = this.infoResult.getAmount();
        this.tv_price.setText(this.reWard + "");
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    public void setPayType(int type) {
        boolean z = true;
        this.payType = type;
        this.alipay_check.setSelected(type == 0);
        ImageView imageView = this.wxpay_check;
        if (type == 0) {
            z = false;
        }
        imageView.setSelected(z);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        if (this.reWard == 0) {
            MyToast.show(getApplicationContext(), "订单金额异常");
            return;
        }
        switch (v.getId()) {
            case R.id.Entrust_llyAlipay /* 2131756007 */:
                setPayType(0);
                return;
            case R.id.Entrust_llyAlipay_check /* 2131756008 */:
            case R.id.Entrust_llyWxpay_check /* 2131756010 */:
            default:
                return;
            case R.id.Entrust_llyWxpay /* 2131756009 */:
                setPayType(1);
                return;
            case R.id.tv_pay /* 2131756011 */:
                if (this.payType == 0) {
                    this.style = PayUtil.PayStyle.AliPay;
                    this.url_activateParam = "alipay";
                } else {
                    this.style = PayUtil.PayStyle.WxPay;
                    this.url_activateParam = "weixin";
                }
                getPayOrder();
                return;
        }
    }

    private void getPayOrder() {
        getClient().setShowDialog(true);
        String uName = DemoHelper.getInstance().getCurrentUserName();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("demandSnapShotId", "");
            jsonObject.put("orderId", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String bizMap = jsonObject.toString();
        AjaxParams params = new AjaxParams();
        params.put("bizId", this.infoResult.getBizId());
        params.put("bizType", 41);
        params.put("tradeType", "APP");
        params.put("appType", "F2F");
        params.put("userName", uName);
        params.put(EMPrivateConstant.EMMultiUserConstant.ROOM_OWNER, uName);
        params.put("totalFee", this.reWard);
        params.put("payType", this.url_activateParam);
        params.put("subject", "面对面-芝麻认证");
        params.put("remark", "");
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("bizMap", bizMap);
        params.put("sign", ComUtil.encryptMap(params.getUrlParams(), true));
        getClient().post(R.string.API_UNIFIEDORDER, ComUtil.getZCApi(this.context, getString(R.string.API_UNIFIEDORDER)), params, Map.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendAliResult(String resultStatus, String result, String memo) {
        AjaxParams params = new AjaxParams();
        params.put(j.a, resultStatus);
        params.put(j.c, result);
        params.put(j.b, memo);
        getClient().post(R.string.API_USER_ALIPAY_RESULT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_ALIPAY_RESULT)), params, String.class, false);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_UNIFIEDORDER /* 2131296446 */:
                Map<String, String> map = (Map) result.getObj();
                if (map == null) {
                    map = new HashMap<>();
                }
                final String orderInfo = "";
                try {
                    orderInfo = new String(com.alibaba.fastjson.util.Base64.decodeFast(map.get("orderInfo")), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if (this.style == PayUtil.PayStyle.AliPay) {
                    ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.identify.IdentifyPayActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String result2 = new PayTask(IdentifyPayActivity.this).pay(orderInfo, true);
                            Message msg = new Message();
                            msg.what = 101;
                            msg.obj = result2;
                            IdentifyPayActivity.this.mHandler.sendMessage(msg);
                        }
                    });
                    return;
                }
                try {
                    new PayUtil(this, this.style).toWxPay(new WxPayInfo(new JSONObject(orderInfo)));
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    MyToast.show(this, "调取微信失败");
                    return;
                }
            case R.string.API_USER_ALIPAY_RESULT /* 2131296452 */:
                if (result.getObj().equals("true")) {
                    returnSuccess();
                    return;
                } else {
                    MyToast.show(this, getString(R.string.pay_failed));
                    return;
                }
            default:
                return;
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
                returnSuccess();
            }
        }
    }

    private void returnSuccess() {
        startAct(AgreeAuthorByZmActivity.class);
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
