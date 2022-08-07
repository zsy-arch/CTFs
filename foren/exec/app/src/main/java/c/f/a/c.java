package c.f.a;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/* loaded from: classes.dex */
public abstract class c extends a {
    public int i;
    public int j;
    public LayoutInflater k;

    @Deprecated
    public c(Context context, int i, Cursor cursor, boolean z) {
        super(context, cursor, z);
        this.j = i;
        this.i = i;
        this.k = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    @Override // c.f.a.a
    public View a(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.k.inflate(this.i, viewGroup, false);
    }
}
