package c.a.a;

import android.view.View;
import androidx.appcompat.app.AlertController;

/* renamed from: c.a.a.e  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0024e implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f345a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f346b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AlertController f347c;

    public RunnableC0024e(AlertController alertController, View view, View view2) {
        this.f347c = alertController;
        this.f345a = view;
        this.f346b = view2;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlertController.a(this.f347c.A, this.f345a, this.f346b);
    }
}
