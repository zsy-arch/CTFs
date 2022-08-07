package com.tencent.open;

import android.app.Activity;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.a.f;
import com.tencent.open.c;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class LocationApi extends BaseApi implements c.a {
    private HandlerThread a;
    private Handler b;
    private Handler c;
    private c d;
    private Bundle e;
    private IUiListener f;

    public LocationApi(QQToken qQToken) {
        super(qQToken);
        a();
    }

    public LocationApi(QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
        a();
    }

    private void a() {
        this.d = new c();
        this.a = new HandlerThread("get_location");
        this.a.start();
        this.b = new Handler(this.a.getLooper());
        this.c = new Handler(Global.getContext().getMainLooper()) { // from class: com.tencent.open.LocationApi.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 101:
                        f.b("openSDK_LOG.LocationApi", "location: get location timeout.");
                        LocationApi.this.a(-13, Constants.MSG_LOCATION_TIMEOUT_ERROR);
                        break;
                    case 103:
                        f.b("openSDK_LOG.LocationApi", "location: verify sosocode success.");
                        LocationApi.this.d.a(Global.getContext(), LocationApi.this);
                        LocationApi.this.c.sendEmptyMessageDelayed(101, 10000L);
                        break;
                    case 104:
                        f.b("openSDK_LOG.LocationApi", "location: verify sosocode failed.");
                        LocationApi.this.a(-14, Constants.MSG_LOCATION_VERIFY_ERROR);
                        break;
                }
                super.handleMessage(message);
            }
        };
    }

    public void searchNearby(Activity activity, Bundle bundle, IUiListener iUiListener) {
        if (c()) {
            this.e = bundle;
            this.f = iUiListener;
            this.b.post(new Runnable() { // from class: com.tencent.open.LocationApi.2
                @Override // java.lang.Runnable
                public void run() {
                    if (LocationApi.this.d.a()) {
                        Message.obtain(LocationApi.this.c, 103).sendToTarget();
                    } else {
                        Message.obtain(LocationApi.this.c, 104).sendToTarget();
                    }
                }
            });
        } else if (iUiListener != null) {
            iUiListener.onComplete(d());
        }
    }

    public void deleteLocation(Activity activity, Bundle bundle, IUiListener iUiListener) {
        Bundle composeCGIParams;
        if (c()) {
            if (bundle != null) {
                composeCGIParams = new Bundle(bundle);
                composeCGIParams.putAll(composeCGIParams());
            } else {
                composeCGIParams = composeCGIParams();
            }
            composeCGIParams.putString("appid", this.mToken.getAppId());
            composeCGIParams.putString("timestamp", String.valueOf(System.currentTimeMillis()));
            composeCGIParams.putString("encrytoken", Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
            f.a("openSDK_LOG.LocationApi", "location: delete params: " + composeCGIParams);
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_delete.cgi", composeCGIParams, "GET", new b(iUiListener));
            a("delete_location", "success");
        } else if (iUiListener != null) {
            iUiListener.onComplete(d());
        }
    }

    private void a(Location location) {
        Bundle composeCGIParams;
        f.a("openSDK_LOG.LocationApi", "doSearchNearby location: search mParams: " + this.e);
        if (this.e != null) {
            composeCGIParams = new Bundle(this.e);
            composeCGIParams.putAll(composeCGIParams());
        } else {
            composeCGIParams = composeCGIParams();
        }
        String valueOf = String.valueOf(location.getLatitude());
        String valueOf2 = String.valueOf(location.getLongitude());
        composeCGIParams.putString("appid", this.mToken.getAppId());
        if (!composeCGIParams.containsKey("latitude")) {
            composeCGIParams.putString("latitude", valueOf);
        }
        if (!composeCGIParams.containsKey("longitude")) {
            composeCGIParams.putString("longitude", valueOf2);
        }
        if (!composeCGIParams.containsKey("page")) {
            composeCGIParams.putString("page", String.valueOf(1));
        }
        composeCGIParams.putString("encrytoken", Util.encrypt("tencent&sdk&qazxc***14969%%" + this.mToken.getAccessToken() + this.mToken.getAppId() + this.mToken.getOpenId() + "qzone3.4"));
        f.a("openSDK_LOG.LocationApi", "location: search params: " + composeCGIParams);
        f.b("openSDK_LOG.LocationApi", "GetNearbySwitchStart:" + SystemClock.elapsedRealtime());
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "http://fusion.qq.com/cgi-bin/qzapps/mapp_lbs_getnear.cgi", composeCGIParams, "GET", new b(this.f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str) {
        this.d.b();
        if (this.f != null) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("ret", i);
                jSONObject.put("errMsg", str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f.onComplete(jSONObject);
        }
    }

    private void b() {
        this.d.b();
    }

    private boolean c() {
        ConnectivityManager connectivityManager = (ConnectivityManager) Global.getContext().getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    private JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ret", -9);
            jSONObject.put("errMsg", Constants.MSG_IO_ERROR);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void a(final String str, final String... strArr) {
        this.b.post(new Runnable() { // from class: com.tencent.open.LocationApi.3
            @Override // java.lang.Runnable
            public void run() {
                if (strArr != null && strArr.length != 0) {
                    com.tencent.connect.a.a.a(Global.getContext(), LocationApi.this.mToken, "search_nearby".equals(str) ? "id_search_nearby" : "id_delete_location", strArr);
                }
            }
        });
    }

    @Override // com.tencent.open.c.a
    public void onLocationUpdate(Location location) {
        a(location);
        b();
        this.c.removeMessages(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class b extends a {
        private IUiListener c;

        public b(IUiListener iUiListener) {
            super();
            this.c = iUiListener;
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onComplete(JSONObject jSONObject) {
            if (this.c != null) {
                this.c.onComplete(jSONObject);
            }
            f.b("openSDK_LOG.LocationApi", "TaskRequestListener onComplete GetNearbySwitchEnd:" + SystemClock.elapsedRealtime());
        }

        @Override // com.tencent.open.LocationApi.a
        protected void a(Exception exc) {
            if (this.c != null) {
                this.c.onError(new UiError(100, exc.getMessage(), null));
            }
        }
    }

    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    private abstract class a implements IRequestListener {
        protected abstract void a(Exception exc);

        private a() {
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onIOException(IOException iOException) {
            a(iOException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onMalformedURLException(MalformedURLException malformedURLException) {
            a(malformedURLException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onJSONException(JSONException jSONException) {
            a(jSONException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onConnectTimeoutException(ConnectTimeoutException connectTimeoutException) {
            a(connectTimeoutException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onSocketTimeoutException(SocketTimeoutException socketTimeoutException) {
            a(socketTimeoutException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onNetworkUnavailableException(HttpUtils.NetworkUnavailableException networkUnavailableException) {
            a(networkUnavailableException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onHttpStatusException(HttpUtils.HttpStatusException httpStatusException) {
            a(httpStatusException);
        }

        @Override // com.tencent.tauth.IRequestListener
        public void onUnknowException(Exception exc) {
            a(exc);
        }
    }
}
