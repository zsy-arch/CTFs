package android.support.v4.util;

import android.support.annotation.RestrictTo;
import com.alimama.mobile.csdk.umupdate.a.f;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes.dex */
public class DebugUtils {
    public static void buildShortClassTag(Object cls, StringBuilder out) {
        int end;
        if (cls == null) {
            out.append(f.b);
            return;
        }
        String simpleName = cls.getClass().getSimpleName();
        if ((simpleName == null || simpleName.length() <= 0) && (end = (simpleName = cls.getClass().getName()).lastIndexOf(46)) > 0) {
            simpleName = simpleName.substring(end + 1);
        }
        out.append(simpleName);
        out.append('{');
        out.append(Integer.toHexString(System.identityHashCode(cls)));
    }
}
