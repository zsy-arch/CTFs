package u.aly;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: UTDIdTracker.java */
/* loaded from: classes2.dex */
public class ab extends r {
    private static final String a = "utdid";
    private static final String b = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static final Pattern c = Pattern.compile("UTDID\">([^<]+)");
    private Context d;

    public ab(Context context) {
        super("utdid");
        this.d = context;
    }

    @Override // u.aly.r
    public String f() {
        try {
            return (String) Class.forName("com.ut.device.UTDevice").getMethod("getUtdid", Context.class).invoke(null, this.d);
        } catch (Exception e) {
            return g();
        }
    }

    private String g() {
        File h = h();
        if (h == null || !h.exists()) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(h);
            String b2 = b(bm.a(fileInputStream));
            bm.c(fileInputStream);
            return b2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = c.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private File h() {
        if (!bl.a(this.d, "android.permission.WRITE_EXTERNAL_STORAGE") || !Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        try {
            return new File(Environment.getExternalStorageDirectory().getCanonicalPath(), ".UTSystemConfig/Global/Alvin2.xml");
        } catch (Exception e) {
            return null;
        }
    }
}
