package c.e.i;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.Editable;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class e implements ActionMode.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final ActionMode.Callback f889a;

    /* renamed from: b  reason: collision with root package name */
    public final TextView f890b;

    /* renamed from: c  reason: collision with root package name */
    public Class f891c;

    /* renamed from: d  reason: collision with root package name */
    public Method f892d;

    /* renamed from: e  reason: collision with root package name */
    public boolean f893e;
    public boolean f = false;

    public e(ActionMode.Callback callback, TextView textView) {
        this.f889a = callback;
        this.f890b = textView;
    }

    public final Intent a() {
        return new Intent().setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }

    @Override // android.view.ActionMode.Callback
    public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
        return this.f889a.onActionItemClicked(actionMode, menuItem);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
        return this.f889a.onCreateActionMode(actionMode, menu);
    }

    @Override // android.view.ActionMode.Callback
    public void onDestroyActionMode(ActionMode actionMode) {
        this.f889a.onDestroyActionMode(actionMode);
    }

    @Override // android.view.ActionMode.Callback
    public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
        String str;
        Context context = this.f890b.getContext();
        PackageManager packageManager = context.getPackageManager();
        if (!this.f) {
            this.f = true;
            try {
                this.f891c = Class.forName("com.android.internal.view.menu.MenuBuilder");
                this.f892d = this.f891c.getDeclaredMethod("removeItemAt", Integer.TYPE);
                this.f893e = true;
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                this.f891c = null;
                this.f892d = null;
                this.f893e = false;
            }
        }
        try {
            Method declaredMethod = (!this.f893e || !this.f891c.isInstance(menu)) ? menu.getClass().getDeclaredMethod("removeItemAt", Integer.TYPE) : this.f892d;
            for (int size = menu.size() - 1; size >= 0; size--) {
                MenuItem item = menu.getItem(size);
                if (item.getIntent() != null && "android.intent.action.PROCESS_TEXT".equals(item.getIntent().getAction())) {
                    declaredMethod.invoke(menu, Integer.valueOf(size));
                }
            }
            ArrayList arrayList = new ArrayList();
            if (context instanceof Activity) {
                for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(a(), 0)) {
                    if (context.getPackageName().equals(resolveInfo.activityInfo.packageName) || (resolveInfo.activityInfo.exported && ((str = resolveInfo.activityInfo.permission) == null || context.checkSelfPermission(str) == 0))) {
                        arrayList.add(resolveInfo);
                    }
                }
            }
            for (int i = 0; i < arrayList.size(); i++) {
                ResolveInfo resolveInfo2 = (ResolveInfo) arrayList.get(i);
                MenuItem add = menu.add(0, 0, i + 100, resolveInfo2.loadLabel(packageManager));
                TextView textView = this.f890b;
                add.setIntent(a().putExtra("android.intent.extra.PROCESS_TEXT_READONLY", !((textView instanceof Editable) && textView.onCheckIsTextEditor() && textView.isEnabled())).setClassName(resolveInfo2.activityInfo.packageName, resolveInfo2.activityInfo.name)).setShowAsAction(1);
            }
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused2) {
        }
        return this.f889a.onPrepareActionMode(actionMode, menu);
    }
}
