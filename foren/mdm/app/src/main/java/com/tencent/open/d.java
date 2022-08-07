package com.tencent.open;

import android.location.Location;
import com.tencent.map.a.a.b;
import com.tencent.open.a.f;
import com.tencent.open.c;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class d extends b {
    private c.a a;

    public d(c.a aVar) {
        super(1, 0, 0, 8);
        this.a = aVar;
    }

    @Override // com.tencent.map.a.a.b
    public void a(byte[] bArr, int i) {
        super.a(bArr, i);
    }

    @Override // com.tencent.map.a.a.b
    public void a(com.tencent.map.a.a.d dVar) {
        f.c("openSDK_LOG.SosoLocationListener", "location: onLocationUpdate = " + dVar);
        super.a(dVar);
        if (dVar != null) {
            Location location = new Location("passive");
            location.setLatitude(dVar.b);
            location.setLongitude(dVar.c);
            if (this.a != null) {
                this.a.onLocationUpdate(location);
            }
        }
    }

    @Override // com.tencent.map.a.a.b
    public void a(int i) {
        f.c("openSDK_LOG.SosoLocationListener", "location: onStatusUpdate = " + i);
        super.a(i);
    }
}
