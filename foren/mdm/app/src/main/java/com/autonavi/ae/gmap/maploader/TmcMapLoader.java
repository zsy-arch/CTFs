package com.autonavi.ae.gmap.maploader;

import com.autonavi.ae.gmap.GLMapEngine;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class TmcMapLoader extends NormalMapLoader {
    private Map<String, Object> mMd5Key = new HashMap();

    public TmcMapLoader(int i, GLMapEngine gLMapEngine, int i2) {
        super(i, gLMapEngine, i2);
    }

    @Override // com.autonavi.ae.gmap.maploader.BaseMapLoader
    public void addRequestTiles(MapSourceGridData mapSourceGridData) {
        if (this.mMapTiles != null) {
            if (mapSourceGridData.mObj != null) {
                this.mMd5Key.put(mapSourceGridData.getGridName(), mapSourceGridData.mObj);
            }
            this.mMapTiles.add(mapSourceGridData);
        }
    }

    @Override // com.autonavi.ae.gmap.maploader.NormalMapLoader
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
                    this.mGLMapEngine.putMapTMCData(this.mEngineID, this.mDataSource, bArr2, str, this.mMd5Key.containsKey(str));
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
}
