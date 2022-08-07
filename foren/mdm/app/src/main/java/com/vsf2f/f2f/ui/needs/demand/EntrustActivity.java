package com.vsf2f.f2f.ui.needs.demand;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.sys.a;
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
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.http.AjaxParams;
import com.hyphenate.util.EMPrivateConstant;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.ApplyPayInfoBean;
import com.vsf2f.f2f.bean.DemandDetailBean;
import com.vsf2f.f2f.ui.needs.service.ServiceInfoActivity;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import com.vsf2f.f2f.ui.utils.area.DateUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EntrustActivity extends BaseActivity implements PayUtil.PayListener {
    private static final int ALI_PAY_FLAG = 101;
    private static final int TITLE1 = 2131296655;
    private static final int TITLE2 = 2131296657;
    private static final int TITLE3 = 2131296732;
    private static final int WX_PAY_FLAG = 102;
    private ImageView alipay_check;
    private DemandDetailBean detailBean;
    private String fromActivity;
    private boolean isOpenWx;
    private Map<String, String> orderMap;
    private ApplyPayInfoBean payInfoBean;
    private String payTypeStr;
    private PayUtil.PayStyle style;
    private ImageView wxpay_check;
    private int payType = 0;
    private String reWard = "0";
    private boolean isSubmitOrder = false;
    protected Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.ui.needs.demand.EntrustActivity.2
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
                            EntrustActivity.this.sendAliResult(resultStatus, Base64.encode(result.getBytes("utf-8")), memo);
                            return;
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return;
                        }
                    } else if (TextUtils.equals(resultStatus, "8000")) {
                        Toast.makeText(EntrustActivity.this.context, EntrustActivity.this.getString(R.string.pay_result), 0).show();
                        return;
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        Toast.makeText(EntrustActivity.this.context, EntrustActivity.this.getString(R.string.be_canceled), 0).show();
                        return;
                    } else {
                        Toast.makeText(EntrustActivity.this.context, EntrustActivity.this.getString(R.string.pay_failed), 0).show();
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
        int strid = getBundle().getInt(Constant.FLAG_TITLE, 0);
        if (strid == 0) {
            strid = R.string.entrust_;
        } else if (strid == R.string.bail_demand) {
            getView(R.id.tv_payDemand).setVisibility(0);
        } else if (strid == R.string.bail_service) {
            getView(R.id.tv_payService).setVisibility(0);
        }
        initHeaderBackTxt(strid, 0);
        this.fromActivity = getBundle().getString(Constant.FLAG_TYPE, "");
        this.payInfoBean = (ApplyPayInfoBean) getBundle().getSerializable("payinfo");
        this.detailBean = (DemandDetailBean) getBundle().getSerializable("needinfo");
        setOnClickListener(R.id.tv_pay);
        setOnClickListener(R.id.Entrust_llyAlipay);
        setOnClickListener(R.id.Entrust_llyWxpay);
        this.alipay_check = (ImageView) getView(R.id.Entrust_llyAlipay_check);
        this.wxpay_check = (ImageView) getView(R.id.Entrust_llyWxpay_check);
        this.alipay_check.setSelected(true);
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void initData() {
        TextView tv_price = (TextView) getView(R.id.tv_price);
        if (this.detailBean != null) {
            this.reWard = this.detailBean.getPayAmount() + "";
        } else if (this.payInfoBean != null) {
            this.reWard = this.payInfoBean.getPayAmount() + "";
        }
        if (TextUtils.isEmpty(this.reWard)) {
            MyToast.show(getApplicationContext(), "获取金额异常");
        } else {
            tv_price.setText(this.reWard + "");
        }
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void requestData() {
    }

    @Override // com.hy.frame.common.BaseActivity, com.hy.frame.common.IBaseActivity
    public void onLeftClick() {
        if (ChoiceServerActivity.class.getName().equals(this.fromActivity) && this.isSubmitOrder) {
            getApp().removeFinish(ChoiceServerActivity.class);
        }
        finish();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        onLeftClick();
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void updateUI() {
    }

    @Override // com.hy.frame.common.IBaseActivity
    public void onViewClick(View v) {
        if (TextUtils.isEmpty(this.reWard) || "0".equals(this.reWard)) {
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
                    this.payTypeStr = "alipay";
                } else {
                    this.style = PayUtil.PayStyle.WxPay;
                    this.payTypeStr = "weixin";
                }
                if (!ChoiceServerActivity.class.getName().equals(this.fromActivity)) {
                    getPayOrder(null);
                    return;
                } else if (this.isSubmitOrder) {
                    getPayOrder(this.orderMap);
                    return;
                } else {
                    submitOrder();
                    return;
                }
        }
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

    private void getPayOrder(Map<String, String> map) {
        getClient().setShowDialog(true);
        String uName = DemoHelper.getInstance().getCurrentUserName();
        AjaxParams params = new AjaxParams();
        if (this.payInfoBean != null) {
            params.put("bizId", this.payInfoBean.getBizId());
            params.put("bizType", this.payInfoBean.getBizType());
            if (this.payInfoBean.getBatchNo() != null) {
                params.put("batchNo", this.payInfoBean.getBatchNo());
            }
            if (this.payInfoBean.getPayTitle() == null) {
                this.payInfoBean.setPayTitle("面对面支付");
            }
            params.put("subject", this.payInfoBean.getPayTitle());
        } else if (this.detailBean != null) {
            String bizMap = "";
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("demandSnapShotId", this.detailBean.getDemandSnapShotId());
                if (map != null) {
                    jsonObject.put("orderId", map.get("orderId"));
                } else if (!TextUtils.isEmpty(this.detailBean.getOrderId())) {
                    jsonObject.put("orderId", this.detailBean.getOrderId());
                }
                bizMap = jsonObject.toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            params.put("bizId", this.detailBean.getBizId());
            params.put("bizType", this.detailBean.getBizType());
            params.put("batchNo", this.detailBean.getBatchNo());
            params.put("subject", this.detailBean.getTitle().replace(a.b, ""));
            params.put("remark", ComUtil.UTF(this.detailBean.getDescription().replace(a.b, "")));
            params.put("bizMap", bizMap);
        }
        params.put("tradeType", "APP");
        params.put("appType", "F2F");
        params.put("userName", uName);
        params.put(EMPrivateConstant.EMMultiUserConstant.ROOM_OWNER, uName);
        params.put("totalFee", this.reWard);
        params.put("payType", this.payTypeStr);
        params.put("timestamp", System.currentTimeMillis());
        params.put("nonceStr", ComUtil.getRandomString(32));
        params.put("sign", ComUtil.encryptMap(params.getUrlParams(), true));
        getClient().post(R.string.API_UNIFIEDORDER, ComUtil.getZCApi(this.context, getString(R.string.API_UNIFIEDORDER)), params, Map.class);
    }

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        super.onRequestSuccess(result);
        getClient().setShowDialog(false);
        switch (result.getRequestCode()) {
            case R.string.API_DEMAND_ORDER_SUBMIT /* 2131296329 */:
                this.orderMap = (Map) result.getObj();
                this.isSubmitOrder = true;
                getPayOrder(this.orderMap);
                return;
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
                    ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.needs.demand.EntrustActivity.1
                        @Override // java.lang.Runnable
                        public void run() {
                            String result2 = new PayTask(EntrustActivity.this).pay(orderInfo, true);
                            Message msg = new Message();
                            msg.what = 101;
                            msg.obj = result2;
                            EntrustActivity.this.mHandler.sendMessage(msg);
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

    @Override // com.cdlinglu.common.BaseActivity, com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        super.onRequestError(result);
    }

    @Override // com.cdlinglu.common.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.isOpenWx) {
            this.isOpenWx = false;
            MyLog.d("PayAct", "errCode=" + MyApplication.wxErrCode);
            if (MyApplication.wxErrCode == 0) {
                MyApplication.wxErrCode = 1;
                returnSuccess();
            }
        }
    }

    public void sendAliResult(String resultStatus, String result, String memo) {
        AjaxParams params = new AjaxParams();
        params.put(j.a, resultStatus);
        params.put(j.c, result);
        params.put(j.b, memo);
        getClient().post(R.string.API_USER_ALIPAY_RESULT, ComUtil.getZCApi(this.context, getString(R.string.API_USER_ALIPAY_RESULT)), params, String.class, false);
    }

    @Override // com.cdlinglu.utils.PayUtil.PayListener
    public void paySuccess(PayUtil.PayStyle style) {
        this.isOpenWx = true;
    }

    @Override // com.cdlinglu.utils.PayUtil.PayListener
    public void payFail(String msg) {
        MyToast.show(this.context, msg);
    }

    private void submitOrder() {
        getClient().setShowDialog(true);
        int serviceProviderId = getBundle().getInt("serverId");
        AjaxParams params = new AjaxParams();
        params.put("shareId", this.detailBean.getMoId() + "");
        params.put("serviceOrderId", serviceProviderId);
        getClient().post(R.string.API_DEMAND_ORDER_SUBMIT, ComUtil.getXDDApi(this.context, getString(R.string.API_DEMAND_ORDER_SUBMIT)), params, Map.class);
    }

    private void returnSuccess() {
        getApp().removeFinish(HelpSuccessActivity.class);
        getApp().removeFinish(ChoiceServerActivity.class);
        getApp().removeFinish(DemandPublishScucessActivity.class);
        getApp().removeFinish(DemandPublishActivity.class);
        getApp().removeFinish(DemandInfoActivity.class);
        getApp().removeFinish(ServiceInfoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(f.aS, this.reWard);
        bundle.putString(f.az, DateUtils.getNowDateStr("yyyy.MM.dd HH:mm:ss"));
        bundle.putInt("type", this.payType);
        startAct(EntrustSuccessActivity.class, bundle);
        setResult(-1);
        finish();
    }
}
