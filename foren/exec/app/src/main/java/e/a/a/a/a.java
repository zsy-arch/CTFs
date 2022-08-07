package e.a.a.a;

import com.tencent.smtt.utils.TbsLog;
import org.xmlpull.v1.XmlPullParser;

/* compiled from: outline */
/* loaded from: classes.dex */
public class a {
    public static String a(String str, Object obj) {
        return str + obj;
    }

    public static String a(String str, Object obj, String str2) {
        return str + obj + str2;
    }

    public static String a(String str, String str2) {
        return str + str2;
    }

    public static String a(XmlPullParser xmlPullParser, StringBuilder sb, String str) {
        sb.append(xmlPullParser.getPositionDescription());
        sb.append(str);
        return sb.toString();
    }

    public static StringBuilder a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    public static void a(String str, Object obj, String str2, String str3) {
        String str4 = str + obj + str2 + str3;
    }

    public static void a(String str, boolean z, String str2) {
        TbsLog.i(str2, str + z);
    }

    public static void b(String str, Object obj) {
        String str2 = str + obj;
    }
}
