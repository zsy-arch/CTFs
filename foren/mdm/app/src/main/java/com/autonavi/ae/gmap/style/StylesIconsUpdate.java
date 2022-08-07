package com.autonavi.ae.gmap.style;

import android.text.TextUtils;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.style.MapTilsCacheAndResManager;
import com.autonavi.ae.gmap.utils.GLConvertUtil;
import java.io.ByteArrayOutputStream;

/* loaded from: classes.dex */
public class StylesIconsUpdate extends Thread {
    private int mEngineID;
    GLMapEngine mMapEngine;
    MapTilsCacheAndResManager.RetStyleIconsFile mRetFileRecoder;
    private String url;
    ByteArrayOutputStream netWorkData = null;
    int mReceiveDataLen = 0;
    private volatile boolean canceled = false;
    private boolean successed = false;
    private final int CONNECTION_TIMEOUT = 10000;

    public StylesIconsUpdate(int i, GLMapEngine gLMapEngine, MapTilsCacheAndResManager.RetStyleIconsFile retStyleIconsFile) {
        this.mEngineID = 0;
        this.mEngineID = i;
        this.mRetFileRecoder = retStyleIconsFile;
        this.mMapEngine = gLMapEngine;
    }

    public void cancel() {
        this.canceled = true;
    }

    public synchronized void start(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.url = new String(str);
            super.start();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.net.HttpURLConnection, java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Unknown variable types count: 1 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void run() {
        /*
            Method dump skipped, instructions count: 329
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.autonavi.ae.gmap.style.StylesIconsUpdate.run():void");
    }

    private int getResourceHeaderData(ByteArrayOutputStream byteArrayOutputStream) {
        int i = this.mRetFileRecoder.type;
        int parseInt = Integer.parseInt(this.mRetFileRecoder.fullName.split("_|\\.")[1]);
        byte[] convertInt = GLConvertUtil.convertInt(i);
        byteArrayOutputStream.write(convertInt, 0, convertInt.length);
        byte[] convertInt2 = GLConvertUtil.convertInt(parseInt);
        byteArrayOutputStream.write(convertInt2, 0, convertInt2.length);
        byte[] convertInt3 = GLConvertUtil.convertInt(this.mRetFileRecoder.clientVersion);
        byteArrayOutputStream.write(convertInt3, 0, convertInt3.length);
        byte[] convertInt4 = GLConvertUtil.convertInt(this.mRetFileRecoder.serverVersion);
        byteArrayOutputStream.write(convertInt4, 0, convertInt4.length);
        return byteArrayOutputStream.size();
    }

    private void putErrordata(GLMapEngine gLMapEngine) {
        if (gLMapEngine != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(100);
            getResourceHeaderData(byteArrayOutputStream);
            byte[] convertInt = GLConvertUtil.convertInt(-1);
            byteArrayOutputStream.write(convertInt, 0, convertInt.length);
            gLMapEngine.PutResourceData(this.mEngineID, byteArrayOutputStream.toByteArray());
        }
    }
}
