package c.a.f;

import android.view.View;
import androidx.appcompat.widget.Toolbar;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class na implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Toolbar f610a;

    public na(Toolbar toolbar) {
        this.f610a = toolbar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        this.f610a.c();
    }
}
