package c.e.e;

import android.util.Base64;
import java.util.List;

/* loaded from: classes.dex */
public final class a {

    /* renamed from: a */
    public final String f787a;

    /* renamed from: b */
    public final String f788b;

    /* renamed from: c */
    public final String f789c;

    /* renamed from: d */
    public final List<List<byte[]>> f790d;

    /* renamed from: e */
    public final int f791e;
    public final String f;

    public a(String str, String str2, String str3, List<List<byte[]>> list) {
        if (str != null) {
            this.f787a = str;
            if (str2 != null) {
                this.f788b = str2;
                if (str3 != null) {
                    this.f789c = str3;
                    if (list != null) {
                        this.f790d = list;
                        this.f791e = 0;
                        this.f = this.f787a + "-" + this.f788b + "-" + this.f789c;
                        return;
                    }
                    throw new NullPointerException();
                }
                throw new NullPointerException();
            }
            throw new NullPointerException();
        }
        throw new NullPointerException();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder a2 = e.a.a.a.a.a("FontRequest {mProviderAuthority: ");
        a2.append(this.f787a);
        a2.append(", mProviderPackage: ");
        a2.append(this.f788b);
        a2.append(", mQuery: ");
        a2.append(this.f789c);
        a2.append(", mCertificates:");
        sb.append(a2.toString());
        for (int i = 0; i < this.f790d.size(); i++) {
            sb.append(" [");
            List<byte[]> list = this.f790d.get(i);
            for (int i2 = 0; i2 < list.size(); i2++) {
                sb.append(" \"");
                sb.append(Base64.encodeToString(list.get(i2), 0));
                sb.append("\"");
            }
            sb.append(" ]");
        }
        sb.append("}");
        sb.append("mCertificatesArray: " + this.f791e);
        return sb.toString();
    }
}
