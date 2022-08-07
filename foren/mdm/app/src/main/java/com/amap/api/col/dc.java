package com.amap.api.col;

import android.text.TextUtils;
import com.amap.api.maps.model.Tile;
import com.amap.api.maps.model.TileProvider;
import com.yolanda.nohttp.Headers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/* compiled from: BaseTileProvider.java */
/* loaded from: classes.dex */
public abstract class dc implements TileProvider {
    private final int a;
    Cif b;
    private final int c;

    public abstract String a(int i, int i2, int i3);

    public dc(int i, int i2) {
        this.a = i;
        this.c = i2;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public final Tile getTile(int i, int i2, int i3) {
        String a2 = a(i, i2, i3);
        if (TextUtils.isEmpty(a2)) {
            return NO_TILE;
        }
        try {
            return Tile.obtain(this.a, this.c, a(a2));
        } catch (IOException e) {
            return NO_TILE;
        }
    }

    private byte[] a(String str) throws IOException {
        try {
            a aVar = new a(str);
            this.b = Cif.a(false);
            return this.b.e(aVar);
        } catch (Throwable th) {
            return null;
        }
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileWidth() {
        return this.a;
    }

    @Override // com.amap.api.maps.model.TileProvider
    public int getTileHeight() {
        return this.c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: BaseTileProvider.java */
    /* loaded from: classes.dex */
    public class a extends ig {
        private String b;

        public a(String str) {
            this.b = "";
            this.b = str;
            a(gh.a(o.a));
            a(5000);
            b(50000);
        }

        @Override // com.amap.api.col.ig
        public Map<String, String> a() {
            HashMap hashMap = new HashMap();
            hashMap.put("User-Agent", g.d);
            hashMap.put(Headers.HEAD_KEY_ACCEPT_ENCODING, "gzip");
            hashMap.put("platinfo", String.format(Locale.US, "platform=Android&sdkversion=%s&product=%s", "5.2.1", "3dmap"));
            hashMap.put("X-INFO", gd.a(o.a));
            hashMap.put("key", ga.f(o.a));
            hashMap.put("logversion", "2.1");
            return hashMap;
        }

        @Override // com.amap.api.col.ig
        public Map<String, String> b() {
            return null;
        }

        @Override // com.amap.api.col.ig
        public String c() {
            return this.b;
        }
    }
}
