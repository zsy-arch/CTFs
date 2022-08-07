package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.zip.Adler32;

/* loaded from: classes2.dex */
public class DeviceInfo {
    static final byte UTDID_VERSION_CODE = 1;
    private static Device mDevice = null;
    static String HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
    static final Object CREATE_DEVICE_METADATA_LOCK = new Object();

    static long getMetadataCheckSum(Device device) {
        if (device != null) {
            String checkSumContent = String.format("%s%s%s%s%s", device.getUtdid(), device.getDeviceId(), Long.valueOf(device.getCreateTimestamp()), device.getImsi(), device.getImei());
            if (!StringUtils.isEmpty(checkSumContent)) {
                Adler32 adler32 = new Adler32();
                adler32.reset();
                adler32.update(checkSumContent.getBytes());
                return adler32.getValue();
            }
        }
        return 0L;
    }

    private static Device _initDeviceMetadata(Context aContext) {
        Throwable th;
        if (aContext != null) {
            new Device();
            try {
                synchronized (CREATE_DEVICE_METADATA_LOCK) {
                    String utdid = UTUtdid.instance(aContext).getValue();
                    if (!StringUtils.isEmpty(utdid)) {
                        if (utdid.endsWith("\n")) {
                            utdid = utdid.substring(0, utdid.length() - 1);
                        }
                        Device device = new Device();
                        try {
                            long timestamp = System.currentTimeMillis();
                            String imei = PhoneInfoUtils.getImei(aContext);
                            String imsi = PhoneInfoUtils.getImsi(aContext);
                            device.setDeviceId(imei);
                            device.setImei(imei);
                            device.setCreateTimestamp(timestamp);
                            device.setImsi(imsi);
                            device.setUtdid(utdid);
                            device.setCheckSum(getMetadataCheckSum(device));
                            return device;
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
        return null;
    }

    public static synchronized Device getDevice(Context context) {
        Device device;
        synchronized (DeviceInfo.class) {
            if (mDevice != null) {
                device = mDevice;
            } else if (context != null) {
                device = _initDeviceMetadata(context);
                mDevice = device;
            } else {
                device = null;
            }
        }
        return device;
    }
}
