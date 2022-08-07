package c.a.a;

import android.os.Message;
import android.view.View;
import androidx.appcompat.app.AlertController;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.a.c  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class View$OnClickListenerC0022c implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AlertController f342a;

    public View$OnClickListenerC0022c(AlertController alertController) {
        this.f342a = alertController;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Message message;
        Message message2;
        Message message3;
        Message message4;
        AlertController alertController = this.f342a;
        if (view != alertController.o || (message4 = alertController.q) == null) {
            AlertController alertController2 = this.f342a;
            if (view != alertController2.s || (message3 = alertController2.u) == null) {
                AlertController alertController3 = this.f342a;
                message = (view != alertController3.w || (message2 = alertController3.y) == null) ? null : Message.obtain(message2);
            } else {
                message = Message.obtain(message3);
            }
        } else {
            message = Message.obtain(message4);
        }
        if (message != null) {
            message.sendToTarget();
        }
        AlertController alertController4 = this.f342a;
        alertController4.R.obtainMessage(1, alertController4.f12b).sendToTarget();
    }
}
