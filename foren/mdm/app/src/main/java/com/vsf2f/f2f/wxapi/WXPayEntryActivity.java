package com.vsf2f.f2f.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.utils.IDUtil;
import com.hy.frame.util.MyLog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private IWXAPI api;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pay_result);
        this.api = WXAPIFactory.createWXAPI(this, IDUtil.WX_APPID);
        this.api.handleIntent(getIntent(), this);
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.api.handleIntent(intent, this);
    }

    @Override // com.tencent.mm.sdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq req) {
    }

    @Override // com.tencent.mm.sdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp resp) {
        MyLog.d("onRespFinish, errCode = " + resp.errCode);
        getIntent();
        MyApplication.wxErrCode = resp.errCode;
        switch (resp.errCode) {
            case -5:
                Toast.makeText(this, "不支持", 0).show();
                break;
            case -4:
                Toast.makeText(this, "作者否认", 0).show();
                break;
            case -3:
                Toast.makeText(this, "发送失败", 0).show();
                break;
            case -2:
                Toast.makeText(this, "支付取消", 0).show();
                break;
            case -1:
                Toast.makeText(this, "支付错误", 0).show();
                break;
            case 0:
                Toast.makeText(this, "支付成功", 0).show();
                break;
        }
        finish();
    }
}
