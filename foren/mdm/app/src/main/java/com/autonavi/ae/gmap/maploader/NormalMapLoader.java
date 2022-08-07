package com.autonavi.ae.gmap.maploader;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.amap.api.col.de;
import com.amap.api.col.o;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.utils.GLConvertUtil;
import com.autonavi.ae.gmap.utils.GLMapUtil;
import com.hyphenate.util.HanziToPinyin;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;

/* loaded from: classes.dex */
public class NormalMapLoader extends BaseMapLoader {
    private int mMapLevel = 0;
    private int mMapMode = 0;
    private int mMapModeState = 0;

    public NormalMapLoader(int i, GLMapEngine gLMapEngine, int i2) {
        super(i);
        this.mGLMapEngine = gLMapEngine;
        this.mDataSource = i2;
        this.mCreateTime = System.currentTimeMillis();
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected String getMapAddress() {
        return this.mGLMapEngine.getMapSvrAddress();
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected String getMapServerPath() {
        switch (this.mDataSource) {
            case 0:
            case 1:
            case 7:
            case 8:
            case 9:
            case 15:
                return "/ws/mps/vmap?";
            case 2:
            case 6:
                return "/amapsrv/MPS?";
            case 3:
                return "/ws/mps/smap?";
            case 4:
                return "/ws/mps/rtt?";
            case 5:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            default:
                return null;
        }
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected String getGridParams() {
        String str;
        String str2;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.mMapTiles.size(); i++) {
            String gridName = ((MapSourceGridData) this.mMapTiles.get(i)).getGridName();
            if (!TextUtils.isEmpty(gridName) && !super.isContainIllegal(gridName) && GLMapUtil.isAssic(gridName)) {
                if (this.mDataSource != 4 || ((MapSourceGridData) this.mMapTiles.get(i)).mObj == null) {
                    str2 = gridName;
                } else {
                    try {
                        str2 = gridName + "-" + URLEncoder.encode((String) ((MapSourceGridData) this.mMapTiles.get(i)).mObj, "utf-8");
                    } catch (UnsupportedEncodingException e) {
                    }
                }
                stringBuffer.append(str2 + h.b);
            }
        }
        String stringBuffer2 = stringBuffer.toString();
        if (TextUtils.isEmpty(stringBuffer2)) {
            return null;
        }
        while (true) {
            if (!(stringBuffer2.endsWith(h.b) || stringBuffer2.endsWith(HanziToPinyin.Token.SEPARATOR))) {
                break;
            }
            stringBuffer2 = stringBuffer2.substring(0, stringBuffer2.length() - 1);
        }
        switch (this.mDataSource) {
            case 0:
                str = "type=20&mesh=" + stringBuffer2 + getMapParams();
                break;
            case 1:
                str = "type=11&mesh=" + stringBuffer2 + getMapParams();
                break;
            case 2:
                str = "t=BMPBM&mesh=" + stringBuffer2;
                break;
            case 3:
                str = "mesh=" + stringBuffer2 + getMapParams();
                break;
            case 4:
                str = "v=6.0.0&bver=2&mesh=" + stringBuffer2 + "&diu=" + de.a(o.a);
                break;
            case 5:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
            default:
                str = null;
                break;
            case 6:
                str = "t=VMMV3&type=mod&cp=0&mid=" + stringBuffer2;
                break;
            case 7:
                str = "type=1&mesh=" + stringBuffer2 + getMapParams();
                break;
            case 8:
                str = "type=4&mesh=" + stringBuffer2 + getMapParams();
                break;
            case 9:
                str = "type=40&mesh=" + stringBuffer2 + getMapParams();
                break;
            case 15:
                str = "type=2&mesh=" + stringBuffer2 + getMapParams();
                break;
        }
        return str + "&channel=amapapi";
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected void addRequestHeader(HttpURLConnection httpURLConnection) {
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected boolean processReceivedDataHeader() {
        try {
            if (this.mReceivedDataSize <= 7) {
                return false;
            }
            if (GLConvertUtil.getInt(this.mReceivedDataBuffer, 0) != 0) {
                doCancel();
                return false;
            }
            this.mReceivedDataSize -= 8;
            if (this.mReceivedDataSize > 0) {
                GLConvertUtil.moveArray(this.mReceivedDataBuffer, 8, this.mReceivedDataBuffer, 0, this.mReceivedDataSize);
            }
            this.mNextImgDataLength = 0;
            this.isReceivedHeader = true;
            if (this.mDataSource == 0 || this.mDataSource == 1 || this.mDataSource == 8 || this.mDataSource == 7 || this.mDataSource == 15) {
                super.processReceivedDataV4();
            } else {
                super.processReceivedData();
            }
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected void processReceivedDataByType() {
        try {
            if (this.mDataSource == 0 || this.mDataSource == 1 || this.mDataSource == 8 || this.mDataSource == 7 || this.mDataSource == 15) {
                super.processReceivedDataV4();
            } else {
                super.processReceivedData();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected void processReceivedVersion() {
        try {
            if (this.mDataSource == 9) {
                super.processReceivedVersionData(this.mReceivedDataBuffer, 0, this.mReceivedDataSize);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    public boolean isRequestValid() {
        return this.mGLMapEngine.isGridsInScreen(this.mEngineID, this.mMapTiles, this.mDataSource);
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected boolean isNeedReturn() {
        return this.mDataSource == 9;
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    public void processReceivedTileData(byte[] bArr, int i, int i2) {
        try {
            if (i == 0) {
                super.processReceivedTileData(bArr, i, i2);
            } else if (this.mDataSource == 2 || this.mDataSource == 3) {
                processReceivedTileDataBmp(bArr, i, i2);
            } else if (this.mDataSource == 4) {
                processReceivedTileDataVTmc(bArr, i, i2);
            } else if (this.mDataSource == 6) {
                processReceivedModels(bArr, i, i2);
            } else {
                super.processReceivedTileData(bArr, i, i2);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void processReceivedTileDataBmp(byte[] bArr, int i, int i2) {
        String str;
        boolean z = false;
        int i3 = i + 4;
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
                if (this.mGLMapEngine.putMapData(this.mEngineID, bArr, i, i2 - i, this.mDataSource, 0)) {
                }
                if (this.mGLMapEngine.GetCurrentGrideNameLen(this.mEngineID) < str.length()) {
                    z = true;
                } else if (!this.mGLMapEngine.isGridsInScreen(this.mEngineID, this.mMapTiles, this.mDataSource)) {
                    z = true;
                }
                if (z) {
                    super.doCancel();
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void processReceivedTileDataVTmc(byte[] bArr, int i, int i2) {
        boolean z = false;
        int i3 = i + 4;
        int i4 = i3 + 1;
        try {
            byte b = bArr[i3];
            if (i4 + b <= bArr.length && i4 <= bArr.length - 1 && b >= 0) {
                String str = new String(bArr, i4, b, "utf-8");
                int i5 = b + i4;
                int i6 = bArr[i5] + i5 + 1 + 4;
                if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
                    byte[] bArr2 = new byte[i2 - i];
                    System.arraycopy(bArr, i, bArr2, 0, i2 - i);
                    this.mGLMapEngine.putMapTMCData(this.mEngineID, this.mDataSource, bArr2, str, false);
                    if (this.mGLMapEngine.GetCurrentGrideNameLen(this.mEngineID) < str.length()) {
                        z = true;
                    } else if (!this.mGLMapEngine.isGridsInScreen(this.mEngineID, this.mMapTiles, this.mDataSource)) {
                        z = true;
                    }
                    if (z) {
                        super.doCancel();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    protected void processReceivedModels(byte[] bArr, int i, int i2) {
        boolean z = false;
        int i3 = i + 1;
        try {
            byte b = bArr[i];
            if (b >= 0) {
                String str = new String(bArr, i3, b, "utf-8");
                if (this.mGLMapEngine.isMapEngineValid() && i2 > i) {
                    this.mGLMapEngine.putMapData(this.mEngineID, bArr, i, i2 - i, this.mDataSource, 0);
                    if (this.mGLMapEngine.GetCurrentGrideNameLen(this.mEngineID) < str.length()) {
                        z = true;
                    } else if (!this.mGLMapEngine.isGridsInScreen(this.mEngineID, this.mMapTiles, this.mDataSource)) {
                        z = true;
                    }
                    if (z) {
                        super.doCancel();
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setMapParams(int i, int i2, int i3) {
        this.mMapLevel = i;
        this.mMapMode = i2;
        this.mMapModeState = i3;
    }

    public String getMapParams() {
        return "&zoom=" + this.mMapLevel + "&mode=" + this.mMapMode + "&state=" + this.mMapModeState;
    }
}
