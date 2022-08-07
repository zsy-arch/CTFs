package c.a.a;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.CursorAdapter;
import androidx.appcompat.app.AlertController;

/* renamed from: c.a.a.i  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0028i extends CursorAdapter {

    /* renamed from: a  reason: collision with root package name */
    public final int f355a;

    /* renamed from: b  reason: collision with root package name */
    public final int f356b;

    /* renamed from: c  reason: collision with root package name */
    public final /* synthetic */ AlertController.RecycleListView f357c;

    /* renamed from: d  reason: collision with root package name */
    public final /* synthetic */ AlertController f358d;

    /* renamed from: e  reason: collision with root package name */
    public final /* synthetic */ AlertController.a f359e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0028i(AlertController.a aVar, Context context, Cursor cursor, boolean z, AlertController.RecycleListView recycleListView, AlertController alertController) {
        super(context, cursor, z);
        this.f359e = aVar;
        this.f357c = recycleListView;
        this.f358d = alertController;
        Cursor cursor2 = getCursor();
        this.f355a = cursor2.getColumnIndexOrThrow(this.f359e.L);
        this.f356b = cursor2.getColumnIndexOrThrow(this.f359e.M);
    }

    @Override // android.widget.CursorAdapter
    public void bindView(View view, Context context, Cursor cursor) {
        ((CheckedTextView) view.findViewById(16908308)).setText(cursor.getString(this.f355a));
        AlertController.RecycleListView recycleListView = this.f357c;
        int position = cursor.getPosition();
        boolean z = true;
        if (cursor.getInt(this.f356b) != 1) {
            z = false;
        }
        recycleListView.setItemChecked(position, z);
    }

    @Override // android.widget.CursorAdapter
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return this.f359e.f19b.inflate(this.f358d.M, viewGroup, false);
    }
}
