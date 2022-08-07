package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.os.Parcelable;
import c.n.b;
import c.n.c;

/* loaded from: classes.dex */
public class IconCompatParcelizer {
    public static IconCompat read(b bVar) {
        IconCompat iconCompat = new IconCompat();
        iconCompat.f207b = bVar.a(iconCompat.f207b, 1);
        byte[] bArr = iconCompat.f209d;
        if (bVar.a(2)) {
            c cVar = (c) bVar;
            int readInt = cVar.f1084b.readInt();
            if (readInt < 0) {
                bArr = null;
            } else {
                byte[] bArr2 = new byte[readInt];
                cVar.f1084b.readByteArray(bArr2);
                bArr = bArr2;
            }
        }
        iconCompat.f209d = bArr;
        iconCompat.f210e = bVar.a((b) iconCompat.f210e, 3);
        iconCompat.f = bVar.a(iconCompat.f, 4);
        iconCompat.g = bVar.a(iconCompat.g, 5);
        iconCompat.h = (ColorStateList) bVar.a((b) iconCompat.h, 6);
        String str = iconCompat.j;
        if (bVar.a(7)) {
            str = bVar.c();
        }
        iconCompat.j = str;
        iconCompat.c();
        return iconCompat;
    }

    public static void write(IconCompat iconCompat, b bVar) {
        bVar.a(true, true);
        iconCompat.a(false);
        bVar.b(iconCompat.f207b, 1);
        byte[] bArr = iconCompat.f209d;
        bVar.b(2);
        c cVar = (c) bVar;
        if (bArr != null) {
            cVar.f1084b.writeInt(bArr.length);
            cVar.f1084b.writeByteArray(bArr);
        } else {
            cVar.f1084b.writeInt(-1);
        }
        Parcelable parcelable = iconCompat.f210e;
        bVar.b(3);
        cVar.f1084b.writeParcelable(parcelable, 0);
        bVar.b(iconCompat.f, 4);
        bVar.b(iconCompat.g, 5);
        ColorStateList colorStateList = iconCompat.h;
        bVar.b(6);
        cVar.f1084b.writeParcelable(colorStateList, 0);
        String str = iconCompat.j;
        bVar.b(7);
        cVar.f1084b.writeString(str);
    }
}
