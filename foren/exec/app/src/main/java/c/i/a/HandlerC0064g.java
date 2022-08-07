package c.i.a;

import android.os.Handler;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.i.a.g  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class HandlerC0064g extends Handler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityC0065h f961a;

    public HandlerC0064g(ActivityC0065h hVar) {
        this.f961a = hVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (message.what != 2) {
            super.handleMessage(message);
            return;
        }
        this.f961a.e();
        this.f961a.f963c.a();
    }
}
