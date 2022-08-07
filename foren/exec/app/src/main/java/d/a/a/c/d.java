package d.a.a.c;

import android.content.Context;
import com.tencent.smtt.export.external.TbsCoreSettings;
import com.tencent.smtt.sdk.QbSdk;
import java.util.HashMap;

/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1617a = "d";

    public static void a(Context context) {
        HashMap hashMap = new HashMap();
        hashMap.put(TbsCoreSettings.TBS_SETTINGS_USE_SPEEDY_CLASSLOADER, true);
        hashMap.put(TbsCoreSettings.TBS_SETTINGS_USE_DEXLOADER_SERVICE, true);
        QbSdk.initTbsSettings(hashMap);
        QbSdk.initX5Environment(context, new c());
    }
}
