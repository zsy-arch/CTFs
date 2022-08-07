package com.hyphenate.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.http.config.URLConfig;
import com.hyphenate.chat.a.b;
import com.hyphenate.cloud.HttpClientManager;
import com.hyphenate.util.DeviceUuidFactory;
import com.hyphenate.util.EMLog;
import java.util.HashMap;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class EMActiveCollector {
    private static final String perf_actived = "actived";

    public static String collectActiveInfo(Context context, b bVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", Build.VERSION.RELEASE);
            jSONObject.put("manufacturer", Build.MANUFACTURER);
            jSONObject.put("model", Build.MODEL);
            try {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
                jSONObject.put("imei", telephonyManager.getDeviceId());
                jSONObject.put("operator", telephonyManager.getNetworkOperatorName());
            } catch (Exception e) {
                if (e != null) {
                    EMLog.d(perf_actived, e.getMessage());
                }
            }
            jSONObject.put("hyphenate.version", bVar.e());
            try {
                LocationManager locationManager = (LocationManager) context.getSystemService("location");
                Location lastKnownLocation = locationManager.getLastKnownLocation(GeocodeSearch.GPS);
                Location lastKnownLocation2 = lastKnownLocation == null ? locationManager.getLastKnownLocation("network") : lastKnownLocation;
                if (lastKnownLocation2 != null) {
                    jSONObject.put("loc.lat", lastKnownLocation2.getLatitude());
                    jSONObject.put("loc.lng", lastKnownLocation2.getLongitude());
                } else {
                    EMLog.d("ana", "no last location info to use");
                }
            } catch (Exception e2) {
                if (e2 != null) {
                    EMLog.d(perf_actived, e2.getMessage());
                }
            }
            String uuid = new DeviceUuidFactory(context).getDeviceUuid().toString();
            jSONObject.put("token", uuid);
            jSONObject.put("device_uuid", uuid);
        } catch (Exception e3) {
            if (e3 != null) {
                EMLog.d(perf_actived, e3.getMessage());
            }
        }
        EMLog.d("EMActiveCollector", "device info: " + jSONObject.toString());
        return jSONObject.toString();
    }

    public static void sendActivePacket(final Context context, final b bVar) {
        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(perf_actived, false)) {
            EMLog.d("init", URLConfig.baidu_url);
            return;
        }
        try {
            EMLog.d("init", "d");
            new Thread(new Runnable() { // from class: com.hyphenate.analytics.EMActiveCollector.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String sendHttpRequest = HttpClientManager.sendHttpRequest(b.this.f() + "/devices", new HashMap(), EMActiveCollector.collectActiveInfo(context, b.this), HttpClientManager.Method_POST);
                        if (sendHttpRequest.contains("uuid") || sendHttpRequest.contains("duplicate_unique_property_exists")) {
                            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(context).edit();
                            edit.putBoolean(EMActiveCollector.perf_actived, true);
                            edit.apply();
                        }
                    } catch (Exception e) {
                        if (e.toString().contains("duplicate_unique")) {
                            SharedPreferences.Editor edit2 = PreferenceManager.getDefaultSharedPreferences(context).edit();
                            edit2.putBoolean(EMActiveCollector.perf_actived, true);
                            edit2.apply();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendUninstallPacket() {
    }
}
