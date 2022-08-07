package c.a.a;

import android.view.View;
import androidx.appcompat.app.AlertController;

/* renamed from: c.a.a.g  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class RunnableC0026g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ View f350a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ View f351b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AlertController f352c;

    public RunnableC0026g(AlertController alertController, View view, View view2) {
        this.f352c = alertController;
        this.f350a = view;
        this.f351b = view2;
    }

    @Override // java.lang.Runnable
    public void run() {
        AlertController.a(this.f352c.g, this.f350a, this.f351b);
    }
}
