package c.a.a;

import android.view.View;
import android.widget.AbsListView;
import androidx.appcompat.app.AlertController;

/* renamed from: c.a.a.f  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0025f implements AbsListView.OnScrollListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f348a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f349b;

    public C0025f(AlertController alertController, View view, View view2) {
        this.f348a = view;
        this.f349b = view2;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AlertController.a(absListView, this.f348a, this.f349b);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
    }
}
