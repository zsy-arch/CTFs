package cn.jpush.android.service;

import android.os.Handler;
import android.os.Message;
import cn.jpush.android.api.n;

/* loaded from: classes.dex */
final class f extends Handler {
    final /* synthetic */ DownloadService a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(DownloadService downloadService) {
        this.a = downloadService;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        super.handleMessage(message);
        n.b(this.a, message.what);
    }
}
