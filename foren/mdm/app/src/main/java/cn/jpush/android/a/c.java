package cn.jpush.android.a;

import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c extends PhoneStateListener {
    final /* synthetic */ b a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(b bVar) {
        this.a = bVar;
    }

    @Override // android.telephony.PhoneStateListener
    public final void onCellLocationChanged(CellLocation cellLocation) {
    }

    @Override // android.telephony.PhoneStateListener
    public final void onSignalStrengthChanged(int i) {
        this.a.a = i;
    }
}
