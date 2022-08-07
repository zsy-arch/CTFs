package com.vsf2f.f2f.ui.other;

import android.content.Context;
import android.widget.Toast;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.MyLog;
import com.hy.http.IMyHttpListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.result.GetGoldsBean;
import com.vsf2f.f2f.ui.otay.OtayDialog;
import com.vsf2f.f2f.ui.utils.GameUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class BaseUiListener implements IUiListener {
    private Context context;

    public BaseUiListener(Context context) {
        this.context = context;
    }

    @Override // com.tencent.tauth.IUiListener
    public void onComplete(Object o) {
        MyLog.e("UiListener", "分享成功:" + o.toString());
        Toast.makeText(this.context, "分享成功", 0).show();
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("method", "vsf2f.game.zqly.manor.gift.otayonii");
            jsonObject.put("bizContent", ComUtil.UTF(String.format("{\"userName\":\"%s\",\"type\":\"2060\"}", DemoHelper.getInstance().getCurrentUserName())));
            new MyHttpClient(this.context.getApplicationContext(), new IMyHttpListener() { // from class: com.vsf2f.f2f.ui.other.BaseUiListener.1
                @Override // com.hy.http.IMyHttpListener
                public void onRequestSuccess(ResultInfo result) {
                    MyLog.e("onRequestSuccess");
                    GetGoldsBean getGoldsBean = (GetGoldsBean) result.getObj();
                    if (getGoldsBean.isGainCoffer()) {
                        new OtayDialog(BaseUiListener.this.context, getGoldsBean.getNum()).show();
                    }
                }

                @Override // com.hy.http.IMyHttpListener
                public void onRequestError(ResultInfo result) {
                    MyLog.e("onRequestError");
                }
            }).post(R.string.API_GAME_POST, ComUtil.getGAMEApi(this.context, this.context.getString(R.string.API_GAME_POST)), GameUtil.getVsSign(jsonObject.toString(), true), null, GetGoldsBean.class, false);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tencent.tauth.IUiListener
    public void onError(UiError uiError) {
        MyLog.e("UiListener", "分享失败" + uiError.toString());
        Toast.makeText(this.context, "分享失败", 0).show();
    }

    @Override // com.tencent.tauth.IUiListener
    public void onCancel() {
        MyLog.e("UiListener", "已取消");
        Toast.makeText(this.context, this.context.getString(R.string.be_canceled), 0).show();
    }
}
