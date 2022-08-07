package c.a.f;

import android.database.DataSetObserver;
import androidx.appcompat.widget.ActivityChooserView;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: c.a.f.j  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0042j extends DataSetObserver {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ActivityChooserView f602a;

    public C0042j(ActivityChooserView activityChooserView) {
        this.f602a = activityChooserView;
    }

    @Override // android.database.DataSetObserver
    public void onChanged() {
        super.onChanged();
        this.f602a.f85a.notifyDataSetChanged();
    }

    @Override // android.database.DataSetObserver
    public void onInvalidated() {
        super.onInvalidated();
        this.f602a.f85a.notifyDataSetInvalidated();
    }
}
