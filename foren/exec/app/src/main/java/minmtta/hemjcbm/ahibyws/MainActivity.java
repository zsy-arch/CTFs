package minmtta.hemjcbm.ahibyws;

import android.util.Base64;
import com.tencent.smtt.sdk.BuildConfig;
import d.a.a.h;
import java.io.UnsupportedEncodingException;

/* loaded from: classes.dex */
public class MainActivity extends h {
    @Override // d.a.a.h
    public String l() {
        return "exec.azj.kny.d.c";
    }

    @Override // d.a.a.h
    public String r() {
        return "开";
    }

    @Override // d.a.a.h
    public String t() {
        try {
            return new String(Base64.decode("aHR0cHM6Ly9hbnNqay5lY3hlaW8ueHl6", 0), "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }
}
