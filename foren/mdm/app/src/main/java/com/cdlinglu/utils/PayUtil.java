package com.cdlinglu.utils;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.alipay.sdk.app.PayTask;
import com.alipay.sdk.sys.a;
import com.cdlinglu.common.BaseActivity;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.pay.PayResult;
import com.cdlinglu.utils.pay.SignUtils;
import com.cdlinglu.utils.pay.WxPayInfo;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.frame.util.MyToast;
import com.hy.frame.view.LoadingDialog;
import com.hy.http.AjaxParams;
import com.hy.http.IMyHttpListener;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/* loaded from: classes.dex */
public class PayUtil implements IMyHttpListener {
    public static final String ACTION_PUSH_PAY_FAILURE = "com.cdlinglu.plusim.PUSH_PAY_FAILURE";
    public static final String ACTION_PUSH_PAY_SUCCESS = "com.cdlinglu.plusim.PUSH_PAY_SUCCESS";
    public static final String ALIPAY_PARTNER = "2088911711354473";
    public static final String ALIPAY_RSA_PRIVATE = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAPKtE2AiukHV9Z6IqpL6sGe/lQ5O5TUdrOOn+b0c+HQkzRLN3qvYil2cpsyNJaPHyBEz6k9B8An+2ZShZd/1yxH4jcCUs8C/05u/C6hfc/f7xrY1x1o15OlEI1z9tiujiH71OPtdhhOC38Zmsx3wTqzsvr7PTiLaDg4RcZgt/H0jAgMBAAECgYEA40Do8BIVUinHRZq+Ab35DG8AatG/GyqbCOX4nPxQPpNn58AaUdsKV/emy88x/FnOJS30shwRaYravrEV8dD/RBYnehWBkfQG5mniSra7yXqdPu7ZrmoLVEJYRkkDqm9AUIifGtkhiwb4d3bZec9+FyTADJY8/hHJEE/zOiL/H0ECQQD5sX3mtNe1moDMhTQVfsv3/OKzDPEguMObtnQPbTXntM4KjR0b7Hs//cShwb3R116VFcN6L+wSpY5xvoKGa6V7AkEA+M416VRbbTIaoIgR+c7+1o9wQBoT5/+fHmMYraN+6B1OiYgylPlVWgs+DjhVp0SSfUWQ8EQ4r6b1e7642/fyeQJAB4sxPpMgIUB1u7gahru59dgSEU7GyjJK1p2AjCbEhieUQgVZZ8qRPrGwCyMLVReop+Bsc3iXLUkLMKyMzPrbmQJBAJYrwJZoeBZcW33xIwhoS6AtS08lbkZlHyE0Jr0HJZ3rsm+IaR9Jrp5+kqH6lpgy50f9KRIb2ysJ1rkt0F3UNeECQQDuZJhIt8A+wD+vva8ENzeEuwVaHct2W74q7TXxAauc/OXsKvuc29WXHkn0Fme5KTwZEiFRO5joat4x4/OiIm+R";
    public static final String ALIPAY_SELLER = "xswlsmallm@163.com";
    private static final int SDK_CHECK_FLAG = 2;
    private static final int SDK_PAY_FLAG = 1;
    private static long lastPayTime;
    private BaseActivity act;
    private Handler handler = new Handler() { // from class: com.cdlinglu.utils.PayUtil.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    MyLog.e("Alipay::" + msg.obj);
                    PayResult payResult = new PayResult((String) msg.obj);
                    payResult.getResult();
                    String resultStatus = payResult.getResultStatus();
                    if (PayUtil.this.listener == null) {
                        return;
                    }
                    if (TextUtils.equals(resultStatus, "9000")) {
                        if (PayUtil.this.listener != null) {
                            PayUtil.this.listener.paySuccess(PayStyle.AliPay);
                            return;
                        }
                        return;
                    } else if (TextUtils.equals(resultStatus, "8000")) {
                        if (PayUtil.this.listener != null) {
                            PayUtil.this.listener.payFail("支付结果确认中");
                            return;
                        }
                        return;
                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        PayUtil.this.listener.payFail("用户中途取消");
                        return;
                    } else if (TextUtils.equals(resultStatus, "6002")) {
                        PayUtil.this.listener.payFail("网络连接出错");
                        return;
                    } else {
                        PayUtil.this.listener.payFail("订单支付失败");
                        return;
                    }
                case 2:
                    if (((Boolean) msg.obj).booleanValue()) {
                        MyLog.w("检查结果为：" + msg.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private PayListener listener;
    private LoadingDialog loading;
    private PayStyle style;

    /* loaded from: classes.dex */
    public interface PayListener {
        void payFail(String str);

        void paySuccess(PayStyle payStyle);
    }

    /* loaded from: classes.dex */
    public enum PayStyle {
        AliPay,
        WxPay
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
    }

    public PayUtil(BaseActivity act, PayStyle style) {
        this.style = style;
        this.act = act;
        this.loading = new LoadingDialog(act);
        try {
            this.listener = (PayListener) act;
        } catch (Exception e) {
            MyLog.e("Activity未实现HexingPay.PayListener");
        }
    }

    public void pay(String payName, String payDesc, String payPrice) {
        pay(payName, payDesc, payPrice, null);
    }

    public void pay(String payName, String payDesc, String payPrice, String orderId) {
        if (this.act == null || this.style == null) {
            this.listener.payFail("参数错误");
            return;
        }
        long time = System.currentTimeMillis();
        if (time - lastPayTime < 4000) {
            this.listener.payFail("支付失败，请勿重复点击");
            return;
        }
        lastPayTime = time;
        if (this.loading == null) {
            this.loading = new LoadingDialog(this.act);
        }
        this.loading.show();
        new AjaxParams().put("type", this.style == PayStyle.AliPay ? "alipay" : "wxpay");
    }

    private void notifyServer(String payId) {
    }

    public void toAlipay(String payId, String payName, String payDesc, String payPrice, String callback, String rsa) {
        if (this.loading != null) {
            this.loading.show();
        }
        String orderInfo = getOrderInfo(payId, payName, payDesc, payPrice, callback);
        try {
            final String payInfo = orderInfo + "&sign=\"" + URLEncoder.encode(sign(orderInfo, rsa), "UTF-8") + a.a + getSignType();
            ThreadPool.newThreadPool(new Runnable() { // from class: com.cdlinglu.utils.PayUtil.2
                @Override // java.lang.Runnable
                public void run() {
                    String result = new PayTask(PayUtil.this.act).pay(payInfo, false);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = result;
                    PayUtil.this.handler.sendMessage(msg);
                }
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private String getOrderInfo(String id, String subject, String body, String price, String callback) {
        return ((((((((("partner=\"2088911711354473\"&seller_id=\"xswlsmallm@163.com\"") + "&out_trade_no=\"" + id + "\"") + "&subject=\"" + subject + "\"") + "&body=\"" + body + "\"") + "&total_fee=\"" + price + "\"") + "&notify_url=\"" + callback + "\"") + "&service=\"mobile.securitypay.pay\"") + "&payment_type=\"1\"") + "&_input_charset=\"utf-8\"") + "&it_b_pay=\"30m\"";
    }

    private String sign(String content, String rsa) {
        return SignUtils.sign(content, rsa);
    }

    private String getSignType() {
        return "sign_type=\"RSA\"";
    }

    public void toWxPay(WxPayInfo payInfo) {
        if (!MyApplication.wxApi.isWXAppInstalled()) {
            MyToast.show(this.act, (int) R.string.no_install_wx);
            return;
        }
        IWXAPI wxApi = WXAPIFactory.createWXAPI(this.act, payInfo.getAppid());
        if (!wxApi.registerApp(payInfo.getAppid())) {
            MyToast.show(this.act, "APP注册失败");
            return;
        }
        PayReq req = new PayReq();
        req.appId = payInfo.getAppid();
        req.partnerId = payInfo.getPartnerid();
        req.prepayId = payInfo.getPrepayid();
        req.packageValue = payInfo.getPackage();
        req.nonceStr = payInfo.getNoncestr();
        req.timeStamp = payInfo.getTimestamp();
        req.sign = payInfo.getSign();
        if (!wxApi.sendReq(req)) {
            MyToast.show(this.act, "微信支付调用失败");
        } else if (this.listener != null) {
            this.listener.paySuccess(PayStyle.WxPay);
        }
    }

    public LoadingDialog getLoading() {
        return this.loading;
    }
}
