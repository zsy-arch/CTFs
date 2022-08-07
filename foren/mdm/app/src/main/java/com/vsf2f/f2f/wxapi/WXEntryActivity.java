package com.vsf2f.f2f.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.cdlinglu.common.MyApplication;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.IDUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.Constant;
import com.hy.frame.util.MyLog;
import com.hy.http.IMyHttpListener;
import com.tencent.connect.common.Constants;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.result.GetGoldsBean;
import com.vsf2f.f2f.ui.otay.OtayDialog;
import com.vsf2f.f2f.ui.utils.GameUtil;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    public static final String ACTION_SHARE_SUCCESS = "com.vsf2f.f2f.LOGIN_SUCCESS";
    private static final String LOG_TAG = "WXEntryActivity";
    private OkHttpClient okclient;
    private BaseResp resp = null;
    private String GetCodeRequest = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String GetUserInfo = "https://api.weixin.qq.com/sns/userinfo";

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_success);
        MyApplication.wxApi.handleIntent(getIntent(), this);
        findViewById(R.id.tv_back).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.wxapi.WXEntryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WXEntryActivity.this.finish();
            }
        });
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        MyApplication.wxApi.handleIntent(intent, this);
    }

    @Override // com.tencent.mm.sdk.openapi.IWXAPIEventHandler
    public void onReq(BaseReq arg0) {
        MyLog.e("");
    }

    @Override // com.tencent.mm.sdk.openapi.IWXAPIEventHandler
    public void onResp(BaseResp resp) {
        this.resp = resp;
        MyLog.e(Integer.valueOf(resp.errCode));
        switch (resp.errCode) {
            case -4:
                Toast.makeText(this, "被拒绝", 0).show();
                finish();
                return;
            case -3:
            case -1:
            default:
                Toast.makeText(this, "已返回", 0).show();
                finish();
                return;
            case -2:
                Toast.makeText(this, getString(R.string.be_canceled), 0).show();
                finish();
                return;
            case 0:
                if (resp instanceof SendAuth.Resp) {
                    getInfo(((SendAuth.Resp) resp).code);
                    finish();
                    return;
                }
                findViewById(R.id.share_success).setVisibility(0);
                try {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("method", "vsf2f.game.zqly.manor.gift.otayonii");
                    jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\",\"type\":\"2060\"}", DemoHelper.getInstance().getCurrentUserName())));
                    new MyHttpClient(this, new IMyHttpListener() { // from class: com.vsf2f.f2f.wxapi.WXEntryActivity.2
                        @Override // com.hy.http.IMyHttpListener
                        public void onRequestSuccess(ResultInfo result) {
                            MyLog.e("onRequestSuccess");
                            GetGoldsBean getGoldsBean = (GetGoldsBean) result.getObj();
                            if (getGoldsBean.isGainCoffer()) {
                                new OtayDialog(WXEntryActivity.this, getGoldsBean.getNum()).show();
                            }
                        }

                        @Override // com.hy.http.IMyHttpListener
                        public void onRequestError(ResultInfo result) {
                            MyLog.e("onRequestError");
                        }
                    }).post(R.string.API_GAME_POST, ComUtil.getGAMEApi(this, ComUtil.getGAMEApi(getApplicationContext(), getApplicationContext().getString(R.string.API_GAME_POST))), GameUtil.getVsSign(jsonObject.toString(), true), null, GetGoldsBean.class, false);
                    return;
                } catch (JSONException e) {
                    e.printStackTrace();
                    return;
                }
        }
    }

    private void getInfo(String code) {
        String url = String.format(this.GetCodeRequest + "?appid=%s&secret=%s&code=%s&grant_type=authorization_code", IDUtil.WX_APPID, IDUtil.WX_APPSECRECT, code);
        this.okclient = new OkHttpClient();
        this.okclient.newCall(new Request.Builder().url(url).build()).enqueue(new Callback() { // from class: com.vsf2f.f2f.wxapi.WXEntryActivity.3
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() != null) {
                    try {
                        JSONObject json = new JSONObject(new String(response.body().bytes()));
                        WXEntryActivity.this.getUserInfo(String.format(WXEntryActivity.this.GetUserInfo + "?access_token=%s&openid=%s", json.getString(Constants.PARAM_ACCESS_TOKEN), json.getString("openid")));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo(String userinfo_url) {
        this.okclient = new OkHttpClient();
        this.okclient.newCall(new Request.Builder().url(userinfo_url).build()).enqueue(new Callback() { // from class: com.vsf2f.f2f.wxapi.WXEntryActivity.4
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    WXEntryActivity.this.sendBroad(new String(response.body().bytes()));
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendBroad(String str) {
        Intent intent = new Intent();
        intent.putExtra(Constant.FLAG, str);
        intent.setAction(ACTION_SHARE_SUCCESS);
        sendBroadcast(intent);
    }
}
