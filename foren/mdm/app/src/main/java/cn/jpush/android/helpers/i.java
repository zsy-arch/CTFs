package cn.jpush.android.helpers;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.b;
import cn.jpush.android.util.s;
import com.tencent.smtt.utils.TbsLog;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i extends Thread {
    final /* synthetic */ String a;
    final /* synthetic */ Context b;
    final /* synthetic */ String c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(String str, Context context, String str2) {
        this.a = str;
        this.b = context;
        this.c = str2;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        boolean z = false;
        String str = null;
        int i = 0;
        while (true) {
            if (i >= 4) {
                break;
            }
            i++;
            str = s.a(this.a, 5, 8000L);
            if (!s.a(str)) {
                z = true;
                break;
            }
        }
        if (!z || TextUtils.isEmpty(str)) {
            k.a(this.c, 1021, b.b(this.b, this.a), this.b);
            k.a(this.c, TbsLog.TBSLOG_CODE_SDK_SELF_MODE, this.b);
            ac.b();
            return;
        }
        h.a(this.b, str);
    }
}
