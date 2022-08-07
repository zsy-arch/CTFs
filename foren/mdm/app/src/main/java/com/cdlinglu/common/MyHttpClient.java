package com.cdlinglu.common;

import android.content.Context;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.http.AjaxParams;
import com.hy.http.IMyHttpListener;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class MyHttpClient extends com.hy.http.MyHttpClient {
    public MyHttpClient(Context context, IMyHttpListener listener) {
        super(context, listener, context.getString(R.string.API_HOST));
    }

    @Override // com.hy.http.MyHttpClient
    public <T> void request(boolean isGet, int requestCode, String url, AjaxParams params, Class<T> cls, boolean list) {
        addHeader("online_type", f.a);
        addHeader("X-Requested-With", "XMLHttpRequest");
        addHeader("vs_request_fromapp", "f2f");
        addHeader("vs_device_id", DemoHelper.getInstance().getDeviceUni());
        addHeader("vs_device_name", DemoHelper.getInstance().getDeviceModel());
        addHeader("vs_app_version", DemoHelper.getInstance().getVersion());
        long sysTime = System.currentTimeMillis() - AppShare.get(getContext()).getLong("diffTime");
        String vs_nonce_str = ComUtil.getRandomString(32);
        addHeader("vs_timestamp", sysTime + "");
        addHeader("vs_nonce_str", vs_nonce_str);
        String token = ComUtil.getUserToken(getContext());
        if (HyUtil.isNoEmpty(token)) {
            AjaxParams params1 = new AjaxParams();
            params1.put("vs_access_token", token);
            params1.put("vs_timestamp", sysTime);
            params1.put("vs_nonce_str", vs_nonce_str);
            addHeader("vs_request_token", ComUtil.encryptParam(params1));
            addHeader("vs_access_token", token);
        }
        super.request(isGet, requestCode, url, params, cls, list);
    }
}
