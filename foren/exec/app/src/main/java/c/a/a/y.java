package c.a.a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatDelegateImpl;

/* loaded from: classes.dex */
public class y extends BroadcastReceiver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppCompatDelegateImpl.d f376a;

    public y(AppCompatDelegateImpl.d dVar) {
        this.f376a = dVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        AppCompatDelegateImpl.d dVar = this.f376a;
        boolean a2 = dVar.f40a.a();
        if (a2 != dVar.f41b) {
            dVar.f41b = a2;
            AppCompatDelegateImpl.this.a();
        }
    }
}
