package com.autonavi.ae.gmap.maploader;

import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.autonavi.ae.gmap.GLMapEngine;
import com.autonavi.ae.gmap.utils.GLConvertUtil;
import com.autonavi.ae.gmap.utils.GLMapUtil;
import com.hyphenate.util.HanziToPinyin;
import java.net.HttpURLConnection;

/* loaded from: classes.dex */
public class HeatMapLoader extends BaseMapLoader {
    private String mMapHeatPoiId = null;

    public void setMapHeatPoiId(String str) {
        this.mMapHeatPoiId = str;
    }

    public HeatMapLoader(int i, GLMapEngine gLMapEngine, int i2) {
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
        return "/ws/mps/hot/?";
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected String getGridParams() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < this.mMapTiles.size(); i++) {
            String gridName = ((MapSourceGridData) this.mMapTiles.get(i)).getGridName();
            if (!TextUtils.isEmpty(gridName) && !super.isContainIllegal(gridName) && GLMapUtil.isAssic(gridName)) {
                stringBuffer.append(gridName + h.b);
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
            stringBuffer2 = stringBuffer.substring(0, stringBuffer2.length() - 1);
        }
        if (stringBuffer2.length() <= 0) {
            return null;
        }
        return "cp=1&mesh=" + (stringBuffer2 + "&channel=amapapi") + "&poiid=" + this.mMapHeatPoiId;
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected void addRequestHeader(HttpURLConnection httpURLConnection) {
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected boolean processReceivedDataHeader() {
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
        super.processReceivedData();
        return true;
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected void processReceivedDataByType() {
        super.processReceivedData();
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    protected void processReceivedVersion() {
        if (this.mDataSource == 9) {
            processReceivedVersionData(this.mReceivedDataBuffer, 0, this.mReceivedDataSize);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    public void processReceivedTileData(byte[] bArr, int i, int i2) {
        if (i == 0) {
            super.processReceivedTileData(bArr, i, i2);
        } else {
            processReceivedTileDataBmp(bArr, i, i2);
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
                this.mGLMapEngine.putMapHeatData(this.mEngineID, bArr, i, i2 - i, 0, this.mMapHeatPoiId);
                if (this.mGLMapEngine.GetCurrentGrideNameLen(this.mEngineID) != str.length()) {
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
}
