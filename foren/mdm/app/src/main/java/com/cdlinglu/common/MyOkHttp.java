package com.cdlinglu.common;

import android.content.Context;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.cdlinglu.utils.ComUtil;
import com.em.DemoHelper;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.http.AjaxParams;
import com.hy.http.IMyHttpListener;
import com.yolanda.nohttp.RequestMethod;
import java.util.Map;

/* loaded from: classes.dex */
public class MyOkHttp extends com.hy.http.MyOkHttp {
    public MyOkHttp(Context context, IMyHttpListener listener) {
        super(context, listener);
    }

    @Override // com.hy.http.MyOkHttp
    public <T> void request(RequestMethod methodType, int requestCode, String url, Map<String, String> params, Class<T> cls, boolean list) {
        addHeader("online_type", f.a);
        addHeader("X-Requested-With", "XMLHttpRequest");
        addHeader("vs_request_fromapp", "f2f");
        String token = ComUtil.getUserToken(getContext());
        if (HyUtil.isNoEmpty(token)) {
            AjaxParams params1 = new AjaxParams();
            params1.put("vs_access_token", token);
            params1.put("vs_nonce_str", ComUtil.getRandomString(32));
            params1.put("vs_timestamp", System.currentTimeMillis() - AppShare.get(getContext()).getLong("diffTime"));
            addHeader("vs_timestamp", params1.getUrlParams().get("vs_timestamp"));
            addHeader("vs_nonce_str", params1.getUrlParams().get("vs_nonce_str"));
            addHeader("vs_device_id", DemoHelper.getInstance().getDeviceUni());
            addHeader("vs_device_name", DemoHelper.getInstance().getDeviceModel());
            addHeader("vs_app_version", DemoHelper.getInstance().getVersion());
            addHeader("vs_request_token", ComUtil.encryptParam(params1));
            addHeader("vs_access_token", token);
        }
        super.request(methodType, requestCode, url, params, cls, list);
    }
}
