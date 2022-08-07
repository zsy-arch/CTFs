package c.a.f;

import android.database.DataSetObserver;
import androidx.appcompat.widget.ActivityChooserView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.n  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0046n extends DataSetObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityChooserView f609a;

    public C0046n(ActivityChooserView activityChooserView) {
        this.f609a = activityChooserView;
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        super.onChanged();
        this.f609a.d();
    }
}
