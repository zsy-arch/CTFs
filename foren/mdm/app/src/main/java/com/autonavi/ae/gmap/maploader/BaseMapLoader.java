package com.autonavi.ae.gmap.maploader;

import android.text.TextUtils;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.utils.GLConvertUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

/* loaded from: classes.dex */
public abstract class BaseMapLoader {
    private static final int CONNECTION_TIMEOUT = 20000;
    protected long mCreateTime;
    protected int mEngineID;
    protected GLMapEngine mGLMapEngine;
    protected byte[] mReceivedDataBuffer;
    public List<MapSourceGridData> mMapTiles = new ArrayList();
    private HttpURLConnection mURLConnection = null;
    private boolean isGetRequestMethod = true;
    protected int mDataSource = 0;
    protected int mReceivedDataSize = 0;
    protected int mNextImgDataLength = 0;
    protected int mCapaticy = 30720;
    protected int mCapaticyExt = 10240;
    protected boolean isReceivedHeader = false;
    protected volatile boolean isInRequest = false;
    protected volatile boolean isFinished = false;
    protected volatile boolean isCanceled = false;

    protected abstract void addRequestHeader(HttpURLConnection httpURLConnection);

    protected abstract String getGridParams();

    protected abstract String getMapAddress();

    protected abstract String getMapServerPath();

    protected abstract boolean isNeedReturn();

    public abstract boolean isRequestValid();

    protected abstract void processReceivedDataByType();

    protected abstract boolean processReceivedDataHeader();

    protected abstract void processReceivedVersion();

    public BaseMapLoader(int i) {
        this.mEngineID = 0;
        this.mEngineID = i;
    }

    protected boolean processHttpResponse(HttpURLConnection httpURLConnection) {
        return false;
    }

    public void setGetRequestMethod(boolean z) {
        this.isGetRequestMethod = z;
    }

    public void addRequestTiles(MapSourceGridData mapSourceGridData) {
        if (this.mMapTiles != null) {
            this.mMapTiles.add(mapSourceGridData);
        }
    }

    protected void downLoadOver(int i) {
    }

    protected String getRequestParams(String str) {
        String str2 = ("mapdataver=6&" + str) + "&aetraffic=8";
        if (this.mDataSource == 101) {
        }
        return str2;
    }

    public void processReceivedDataV4() {
        try {
            if (this.mNextImgDataLength == 0) {
                if (this.mReceivedDataSize >= 8) {
                    this.mNextImgDataLength = GLConvertUtil.getInt(this.mReceivedDataBuffer, 0) + 8;
                    processReceivedDataV4();
                }
            } else if (this.mReceivedDataSize >= this.mNextImgDataLength) {
                processReceivedTileDataV4(this.mReceivedDataBuffer, 8, this.mNextImgDataLength);
                if (this.mReceivedDataBuffer != null) {
                    GLConvertUtil.moveArray(this.mReceivedDataBuffer, this.mNextImgDataLength, this.mReceivedDataBuffer, 0, this.mReceivedDataSize - this.mNextImgDataLength);
                    this.mReceivedDataSize -= this.mNextImgDataLength;
                    this.mNextImgDataLength = 0;
                    processReceivedDataV4();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void processReceivedTileDataV4(byte[] bArr, int i, int i2) throws UnsupportedEncodingException {
        boolean z = false;
        int i3 = i + 4;
        int i4 = i3 + 1;
        byte b = bArr[i3];
        String str = "";
        if (b > 0 && (i4 + b) - 1 < i2) {
            str = new String(bArr, i4, b, "utf-8");
        }
        int i5 = b + i4;
        if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
            putMapData(bArr, i, i2 - i, this.mDataSource, 0, str);
            if (this.mGLMapEngine.GetCurrentGrideNameLen(this.mEngineID) < str.length()) {
                z = true;
            } else if (!this.mGLMapEngine.isGridsInScreen(this.mEngineID, this.mMapTiles, this.mDataSource)) {
                z = true;
            }
            if (z) {
                doCancel();
            }
        }
    }

    protected void putMapData(byte[] bArr, int i, int i2, int i3, int i4, String str) {
        this.mGLMapEngine.putMapData(this.mEngineID, bArr, i, i2, i3, i4);
    }

    public void processReceivedData() {
        try {
            if (this.mNextImgDataLength == 0) {
                if (this.mReceivedDataSize >= 8) {
                    this.mNextImgDataLength = GLConvertUtil.getInt(this.mReceivedDataBuffer, 0) + 8;
                    processReceivedData();
                }
            } else if (this.mReceivedDataSize >= this.mNextImgDataLength) {
                int i = GLConvertUtil.getInt(this.mReceivedDataBuffer, 0);
                int i2 = GLConvertUtil.getInt(this.mReceivedDataBuffer, 4);
                if (i2 == 0) {
                    processReceivedTileData(this.mReceivedDataBuffer, 8, i + 8);
                } else {
                    try {
                        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(this.mReceivedDataBuffer, 8, i));
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        byte[] bArr = new byte[128];
                        while (true) {
                            int read = gZIPInputStream.read(bArr);
                            if (read <= -1) {
                                break;
                            }
                            byteArrayOutputStream.write(bArr, 0, read);
                        }
                        processReceivedTileData(byteArrayOutputStream.toByteArray(), 0, i2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (this.mNextImgDataLength > 0 && this.mReceivedDataBuffer != null) {
                    GLConvertUtil.moveArray(this.mReceivedDataBuffer, this.mNextImgDataLength, this.mReceivedDataBuffer, 0, this.mReceivedDataSize - this.mNextImgDataLength);
                    this.mReceivedDataSize -= this.mNextImgDataLength;
                    this.mNextImgDataLength = 0;
                    processReceivedData();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void processReceivedTileData(byte[] bArr, int i, int i2) {
        String str;
        boolean z = false;
        int i3 = i + 2 + 2 + 4;
        int i4 = i3 + 1;
        try {
            byte b = bArr[i3];
            if (b <= 0 || (i4 + b) - 1 >= i2) {
                str = "";
            } else {
                str = new String(bArr, i4, b, "utf-8");
            }
            int i5 = i4 + b;
            if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
                this.mGLMapEngine.putMapData(this.mEngineID, bArr, i, i2 - i, this.mDataSource, 0);
                if (this.mGLMapEngine.GetCurrentGrideNameLen(this.mEngineID) < str.length()) {
                    z = true;
                } else if (!this.mGLMapEngine.isGridsInScreen(this.mEngineID, this.mMapTiles, this.mDataSource)) {
                    z = true;
                }
                if (z) {
                    doCancel();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void processReceivedVersionData(byte[] bArr, int i, int i2) {
        boolean z = false;
        if (i2 > 0) {
            try {
                if (i2 <= bArr.length && GLConvertUtil.getInt(bArr, 0) == 0 && GLConvertUtil.getInt(bArr, 4) == 0) {
                    int i3 = GLConvertUtil.getInt(bArr, 8);
                    ArrayList arrayList = new ArrayList();
                    int i4 = 12;
                    int i5 = 0;
                    while (true) {
                        if (i5 < i3) {
                            if (i4 >= i2) {
                                break;
                            }
                            int i6 = i4 + 1;
                            byte b = bArr[i4];
                            if (b <= 0 || i6 + b >= i2) {
                                break;
                            }
                            arrayList.add(new String(bArr, i6, b, "utf-8"));
                            i4 = b + i6 + 4;
                            i5++;
                        } else {
                            z = true;
                            break;
                        }
                    }
                    if (z) {
                        this.mGLMapEngine.putMapData(this.mEngineID, bArr, 0, i2, this.mDataSource, 0);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public boolean isContainIllegal(String str) {
        return str.contains("<") || str.contains("[");
    }

    public synchronized boolean hasFinished() {
        boolean z;
        if (!this.isCanceled) {
            if (!this.isFinished) {
                z = false;
            }
        }
        z = true;
        return z;
    }

    public void onException(int i) {
        if (!(this.mDataSource == 6 || this.mDataSource == 4 || this.mDataSource == 1)) {
            if (this.isCanceled) {
                this.mGLMapEngine.onException(this.mEngineID, 1004);
            } else if (this.mDataSource == 10) {
                this.mGLMapEngine.onException(this.mEngineID, i);
            } else {
                this.mGLMapEngine.onException(this.mEngineID, i);
                this.mGLMapEngine.setParamater(this.mEngineID, 5001, i, 0, 0, 0);
            }
        }
        downLoadOver(i);
        this.isFinished = true;
    }

    private void onConnectionReceivedData(byte[] bArr, int i) {
        if (this.mCapaticy < this.mReceivedDataSize + i) {
            try {
                this.mCapaticy += this.mCapaticyExt;
                byte[] bArr2 = new byte[this.mCapaticy];
                System.arraycopy(this.mReceivedDataBuffer, 0, bArr2, 0, this.mReceivedDataSize);
                this.mReceivedDataBuffer = bArr2;
            } catch (ArrayIndexOutOfBoundsException e) {
                doCancel();
                return;
            } catch (OutOfMemoryError e2) {
                doCancel();
                return;
            }
        }
        try {
            System.arraycopy(bArr, 0, this.mReceivedDataBuffer, this.mReceivedDataSize, i);
            this.mReceivedDataSize += i;
            if (!isNeedReturn()) {
                if (this.isReceivedHeader || processReceivedDataHeader()) {
                    processReceivedDataByType();
                }
            }
        } catch (ArrayIndexOutOfBoundsException e3) {
            doCancel();
        } catch (Exception e4) {
            doCancel();
        }
    }

    protected void onConnectionOpened() {
        this.mReceivedDataBuffer = new byte[this.mCapaticy];
        this.mNextImgDataLength = 0;
        this.mReceivedDataSize = 0;
        this.isReceivedHeader = false;
    }

    private synchronized void onConnectionOver() {
        synchronized (this) {
            if (this.mURLConnection != null && this.isInRequest) {
                this.isInRequest = false;
                try {
                    this.mURLConnection.disconnect();
                } catch (Exception e) {
                }
            }
            processReceivedVersion();
            this.mReceivedDataBuffer = null;
            this.mNextImgDataLength = 0;
            this.mReceivedDataSize = 0;
            for (int i = 0; i < this.mMapTiles.size(); i++) {
                try {
                    this.mGLMapEngine.mTileProcessCtrl.removeTile(this.mMapTiles.get(i).getKeyGridName());
                } catch (Exception e2) {
                }
            }
            downLoadOver(0);
            this.isFinished = true;
        }
    }

    public synchronized void doCancel() {
        if (!this.isCanceled && !this.isFinished) {
            this.isCanceled = true;
            onConnectionOver();
        }
    }

    public void doRequest() {
        InputStream inputStream;
        String str;
        Throwable th;
        InputStream inputStream2;
        if (!this.isCanceled && !this.isFinished) {
            if (!isRequestValid()) {
                doCancel();
                return;
            }
            String mapAddress = getMapAddress();
            String mapServerPath = getMapServerPath();
            String gridParams = getGridParams();
            if (TextUtils.isEmpty(mapAddress) || TextUtils.isEmpty(mapServerPath) || TextUtils.isEmpty(gridParams)) {
                doCancel();
                return;
            }
            String str2 = mapAddress + mapServerPath;
            String requestParams = getRequestParams(gridParams);
            if (this.isGetRequestMethod) {
                StringBuilder append = new StringBuilder().append(str2 + requestParams).append("&csid=");
                String uuid = UUID.randomUUID().toString();
                str = append.append(uuid).toString();
                inputStream = uuid;
            } else {
                StringBuilder append2 = new StringBuilder().append(str2).append("csid=");
                String uuid2 = UUID.randomUUID().toString();
                str = append2.append(uuid2).toString();
                inputStream = uuid2;
            }
            try {
                InputStream inputStream3 = null;
                try {
                    URL url = new URL(str);
                    if (url != null) {
                        if (0 != 0) {
                            this.mURLConnection = (HttpURLConnection) url.openConnection(null);
                        }
                        if (this.mURLConnection == null) {
                            this.mURLConnection = (HttpURLConnection) url.openConnection();
                        }
                        this.mURLConnection.setConnectTimeout(20000);
                        this.mURLConnection.setReadTimeout(20000);
                        addRequestHeader(this.mURLConnection);
                        if (this.isGetRequestMethod) {
                            this.mURLConnection.setRequestMethod("GET");
                        } else {
                            this.mURLConnection.setRequestMethod("POST");
                            this.mURLConnection.setDoInput(true);
                            this.mURLConnection.setDoOutput(true);
                            this.mURLConnection.setUseCaches(false);
                            this.mURLConnection.setRequestProperty("User-Agent", this.mGLMapEngine.mUserAgent);
                            byte[] bytes = requestParams.getBytes("utf-8");
                            OutputStream outputStream = this.mURLConnection.getOutputStream();
                            outputStream.write(bytes);
                            outputStream.flush();
                            outputStream.close();
                        }
                        this.isInRequest = true;
                        this.mURLConnection.connect();
                        if (processHttpResponse(this.mURLConnection)) {
                            onConnectionOver();
                            if (0 != 0 && !this.isCanceled) {
                                try {
                                    inputStream3.close();
                                    return;
                                } catch (IOException e) {
                                    onException(1005);
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e2) {
                                    return;
                                } catch (NullPointerException e3) {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } else {
                            if (this.mURLConnection.getResponseCode() == 200) {
                                onConnectionOpened();
                                inputStream2 = this.mURLConnection.getInputStream();
                                try {
                                    byte[] bArr = new byte[512];
                                    while (true) {
                                        int read = inputStream2.read(bArr);
                                        if (read <= -1 || this.isCanceled) {
                                            break;
                                        }
                                        onConnectionReceivedData(bArr, read);
                                    }
                                } catch (IOException e4) {
                                    inputStream3 = inputStream2;
                                    onException(1005);
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e5) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e6) {
                                            return;
                                        } catch (NullPointerException e7) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (IllegalStateException e8) {
                                    inputStream3 = inputStream2;
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e9) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e10) {
                                            return;
                                        } catch (NullPointerException e11) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (IndexOutOfBoundsException e12) {
                                    inputStream3 = inputStream2;
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e13) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e14) {
                                            return;
                                        } catch (NullPointerException e15) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (NullPointerException e16) {
                                    inputStream3 = inputStream2;
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e17) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e18) {
                                            return;
                                        } catch (NullPointerException e19) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (NumberFormatException e20) {
                                    inputStream3 = inputStream2;
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e21) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e22) {
                                            return;
                                        } catch (NullPointerException e23) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (SocketTimeoutException e24) {
                                    inputStream3 = inputStream2;
                                    if (this.mDataSource == 10 && ((IndoorMapLoader) this).switchFloorFailed()) {
                                        onException(1007);
                                    }
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e25) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e26) {
                                            return;
                                        } catch (NullPointerException e27) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (Exception e28) {
                                    inputStream3 = inputStream2;
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e29) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e30) {
                                            return;
                                        } catch (NullPointerException e31) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (OutOfMemoryError e32) {
                                    inputStream3 = inputStream2;
                                    onConnectionOver();
                                    if (inputStream3 != null && !this.isCanceled) {
                                        try {
                                            inputStream3.close();
                                            return;
                                        } catch (IOException e33) {
                                            onException(1005);
                                            return;
                                        } catch (ArrayIndexOutOfBoundsException e34) {
                                            return;
                                        } catch (NullPointerException e35) {
                                            return;
                                        }
                                    } else {
                                        return;
                                    }
                                } catch (Throwable th2) {
                                    inputStream = inputStream2;
                                    th = th2;
                                    onConnectionOver();
                                    if (inputStream != null && !this.isCanceled) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException e36) {
                                            onException(1005);
                                        } catch (ArrayIndexOutOfBoundsException e37) {
                                        } catch (NullPointerException e38) {
                                        }
                                    }
                                    throw th;
                                }
                            } else if (this.mDataSource != 10) {
                                onException(1002);
                            } else if (((IndoorMapLoader) this).switchFloorFailed()) {
                                onException(1007);
                                inputStream2 = null;
                            }
                            onConnectionOver();
                            if (inputStream2 != null && !this.isCanceled) {
                                try {
                                    inputStream2.close();
                                    return;
                                } catch (IOException e39) {
                                    onException(1005);
                                    return;
                                } catch (ArrayIndexOutOfBoundsException e40) {
                                    return;
                                } catch (NullPointerException e41) {
                                    return;
                                }
                            }
                        }
                    }
                    inputStream2 = null;
                    onConnectionOver();
                    if (inputStream2 != null) {
                    }
                } catch (SocketTimeoutException e42) {
                } catch (IOException e43) {
                } catch (IllegalStateException e44) {
                } catch (IndexOutOfBoundsException e45) {
                } catch (NullPointerException e46) {
                } catch (NumberFormatException e47) {
                } catch (Exception e48) {
                } catch (OutOfMemoryError e49) {
                }
            } catch (Throwable th3) {
                th = th3;
            }
        }
    }
}
