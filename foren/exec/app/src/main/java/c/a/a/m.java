package c.a.a;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.appcompat.app.AppCompatDelegateImpl;
import c.a.e.a;
import c.a.e.f;
import c.a.f.C0049q;
import c.a.f.wa;
import c.e.a.b;
import c.e.a.e;
import c.e.h.c;
import c.e.h.n;
import c.i.a.ActivityC0065h;
import java.util.ArrayList;

/* loaded from: classes.dex */
public class m extends ActivityC0065h implements n, e.a, AbstractC0021b {
    public o m;
    public int n = 0;
    public Resources o;

    @Override // c.a.a.n
    public a a(a.AbstractC0005a aVar) {
        return null;
    }

    public void a(Intent intent) {
        int i = Build.VERSION.SDK_INT;
        navigateUpTo(intent);
    }

    @Override // c.a.a.n
    public void a(a aVar) {
    }

    @Override // android.app.Activity
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        appCompatDelegateImpl.f();
        ((ViewGroup) appCompatDelegateImpl.w.findViewById(16908290)).addView(view, layoutParams);
        appCompatDelegateImpl.g.onContentChanged();
    }

    @Override // c.a.a.n
    public void b(a aVar) {
    }

    public void b(e eVar) {
    }

    public boolean b(Intent intent) {
        int i = Build.VERSION.SDK_INT;
        return shouldUpRecreateTask(intent);
    }

    @Override // c.e.a.e.a
    public Intent c() {
        return C.a((Activity) this);
    }

    @Override // android.app.Activity
    public void closeOptionsMenu() {
        i();
        if (getWindow().hasFeature(0)) {
            super.closeOptionsMenu();
        }
    }

    @Override // c.e.a.c, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        keyEvent.getKeyCode();
        i();
        View decorView = getWindow().getDecorView();
        if (decorView == null || !n.b(decorView, keyEvent)) {
            return c.a(this, decorView, this, keyEvent);
        }
        return true;
    }

    @Override // android.app.Activity
    public <T extends View> T findViewById(int i) {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        appCompatDelegateImpl.f();
        return (T) appCompatDelegateImpl.f.findViewById(i);
    }

    @Override // c.i.a.ActivityC0065h
    public void g() {
        h().c();
    }

    @Override // android.app.Activity
    public MenuInflater getMenuInflater() {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        if (appCompatDelegateImpl.k == null) {
            appCompatDelegateImpl.i();
            AbstractC0020a aVar = appCompatDelegateImpl.j;
            appCompatDelegateImpl.k = new f(aVar != null ? aVar.c() : appCompatDelegateImpl.f27e);
        }
        return appCompatDelegateImpl.k;
    }

    @Override // android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.o == null) {
            wa.a();
        }
        Resources resources = this.o;
        return resources == null ? super.getResources() : resources;
    }

    public o h() {
        if (this.m == null) {
            this.m = new AppCompatDelegateImpl(this, getWindow(), this);
        }
        return this.m;
    }

    public AbstractC0020a i() {
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        appCompatDelegateImpl.i();
        return appCompatDelegateImpl.j;
    }

    @Override // android.app.Activity
    public void invalidateOptionsMenu() {
        h().c();
    }

    @Deprecated
    public void j() {
    }

    public boolean k() {
        Intent c2 = c();
        if (c2 == null) {
            return false;
        }
        if (b(c2)) {
            e eVar = new e(this);
            a(eVar);
            b(eVar);
            if (!eVar.f744a.isEmpty()) {
                ArrayList<Intent> arrayList = eVar.f744a;
                Intent[] intentArr = (Intent[]) arrayList.toArray(new Intent[arrayList.size()]);
                intentArr[0] = new Intent(intentArr[0]).addFlags(268484608);
                c.e.b.a.a(eVar.f745b, intentArr, null);
                try {
                    b.a((Activity) this);
                    return true;
                } catch (IllegalStateException unused) {
                    finish();
                    return true;
                }
            } else {
                throw new IllegalStateException("No intents added to TaskStackBuilder; cannot startActivities");
            }
        } else {
            a(c2);
            return true;
        }
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        if (appCompatDelegateImpl.B && appCompatDelegateImpl.v) {
            appCompatDelegateImpl.i();
            AbstractC0020a aVar = appCompatDelegateImpl.j;
            if (aVar != null) {
                aVar.a(configuration);
            }
        }
        C0049q.a().b(appCompatDelegateImpl.f27e);
        appCompatDelegateImpl.a();
        if (this.o != null) {
            this.o.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onContentChanged() {
        j();
    }

    @Override // c.i.a.ActivityC0065h, c.e.a.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        int i;
        o h = h();
        h.b();
        h.a(bundle);
        if (h.a() && (i = this.n) != 0) {
            if (Build.VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.n, false);
            } else {
                setTheme(i);
            }
        }
        super.onCreate(bundle);
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        if (appCompatDelegateImpl.O) {
            appCompatDelegateImpl.f.getDecorView().removeCallbacks(appCompatDelegateImpl.Q);
        }
        appCompatDelegateImpl.K = true;
        AbstractC0020a aVar = appCompatDelegateImpl.j;
        if (aVar != null) {
            aVar.d();
        }
        AppCompatDelegateImpl.d dVar = appCompatDelegateImpl.N;
        if (dVar != null) {
            dVar.a();
        }
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        Window window;
        if (Build.VERSION.SDK_INT < 26 && !keyEvent.isCtrlPressed() && !KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState()) && keyEvent.getRepeatCount() == 0 && !KeyEvent.isModifierKey(keyEvent.getKeyCode()) && (window = getWindow()) != null && window.getDecorView() != null && window.getDecorView().dispatchKeyShortcutEvent(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity, android.view.Window.Callback
    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        AbstractC0020a i2 = i();
        if (menuItem.getItemId() != 16908332 || i2 == null || (i2.b() & 4) == 0) {
            return false;
        }
        return k();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        ((AppCompatDelegateImpl) h()).f();
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        appCompatDelegateImpl.i();
        AbstractC0020a aVar = appCompatDelegateImpl.j;
        if (aVar != null) {
            aVar.c(true);
        }
    }

    @Override // c.i.a.ActivityC0065h, c.e.a.c, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        int i = ((AppCompatDelegateImpl) h()).L;
        if (i != -100) {
            bundle.putInt("appcompat:local_night_mode", i);
        }
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onStart() {
        super.onStart();
        ((AppCompatDelegateImpl) h()).a();
    }

    @Override // c.i.a.ActivityC0065h, android.app.Activity
    public void onStop() {
        super.onStop();
        AppCompatDelegateImpl appCompatDelegateImpl = (AppCompatDelegateImpl) h();
        appCompatDelegateImpl.i();
        AbstractC0020a aVar = appCompatDelegateImpl.j;
        if (aVar != null) {
            aVar.c(false);
        }
        AppCompatDelegateImpl.d dVar = appCompatDelegateImpl.N;
        if (dVar != null) {
            dVar.a();
        }
    }

    @Override // android.app.Activity
    public void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        h().a(charSequence);
    }

    @Override // android.app.Activity
    public void openOptionsMenu() {
        i();
        if (getWindow().hasFeature(0)) {
            super.openOptionsMenu();
        }
    }

    @Override // android.app.Activity
    public void setContentView(int i) {
        h().b(i);
    }

    @Override // android.app.Activity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        super.setTheme(i);
        this.n = i;
    }

    @Override // android.app.Activity
    public void setContentView(View view) {
        h().a(view);
    }

    public void a(e eVar) {
        eVar.a(this);
    }

    @Override // android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        h().a(view, layoutParams);
    }
}
