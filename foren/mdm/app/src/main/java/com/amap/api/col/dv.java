package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.amap.api.col.ct;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;

/* compiled from: ImageFetcher.java */
/* loaded from: classes.dex */
public class dv extends dw {
    private TileProvider e = null;

    public dv(Context context, int i, int i2) {
        super(context, i, i2);
        a(context);
    }

    private void a(Context context) {
        b(context);
    }

    public void a(TileProvider tileProvider) {
        this.e = tileProvider;
    }

    private void b(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
        }
    }

    private Bitmap c(ct.b bVar) {
        try {
            Tile tile = this.e.getTile(bVar.a, bVar.b, bVar.c);
            if (tile == null || tile == TileProvider.NO_TILE) {
                return null;
            }
            return BitmapFactory.decodeByteArray(tile.data, 0, tile.data.length);
        } catch (Throwable th) {
            return null;
        }
    }

    @Override // com.amap.api.col.dw, com.amap.api.col.dx
    protected Bitmap a(Object obj) {
        return c((ct.b) obj);
    }
}
