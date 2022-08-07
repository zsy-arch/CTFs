package c.a.e.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ContextMenu;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewConfiguration;
import c.e.h.o;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class l implements c.e.d.a.a {

    /* renamed from: a */
    public static final int[] f450a = {1, 4, 5, 3, 2, 0};
    public boolean A;

    /* renamed from: b */
    public final Context f451b;

    /* renamed from: c */
    public final Resources f452c;

    /* renamed from: d */
    public boolean f453d;

    /* renamed from: e */
    public boolean f454e;
    public a f;
    public ContextMenu.ContextMenuInfo n;
    public CharSequence o;
    public Drawable p;
    public View q;
    public p y;
    public int m = 0;
    public boolean r = false;
    public boolean s = false;
    public boolean t = false;
    public boolean u = false;
    public boolean v = false;
    public ArrayList<p> w = new ArrayList<>();
    public CopyOnWriteArrayList<WeakReference<v>> x = new CopyOnWriteArrayList<>();
    public boolean z = false;
    public ArrayList<p> g = new ArrayList<>();
    public ArrayList<p> h = new ArrayList<>();
    public boolean i = true;
    public ArrayList<p> j = new ArrayList<>();
    public ArrayList<p> k = new ArrayList<>();
    public boolean l = true;

    /* loaded from: classes.dex */
    public interface a {
        void a(l lVar);

        boolean a(l lVar, MenuItem menuItem);
    }

    /* loaded from: classes.dex */
    public interface b {
        boolean a(p pVar);
    }

    public l(Context context) {
        this.f451b = context;
        this.f452c = context.getResources();
        boolean z = true;
        this.f454e = (this.f452c.getConfiguration().keyboard == 1 || !o.a(ViewConfiguration.get(this.f451b), this.f451b)) ? false : z;
    }

    public void a(v vVar, Context context) {
        this.x.add(new WeakReference<>(vVar));
        vVar.a(context, this);
        this.l = true;
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(0, 0, 0, charSequence);
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        int i5;
        PackageManager packageManager = this.f451b.getPackageManager();
        List<ResolveInfo> queryIntentActivityOptions = packageManager.queryIntentActivityOptions(componentName, intentArr, intent, 0);
        int size = queryIntentActivityOptions != null ? queryIntentActivityOptions.size() : 0;
        if ((i4 & 1) == 0) {
            int size2 = size();
            int i6 = 0;
            while (true) {
                if (i6 >= size2) {
                    i6 = -1;
                    break;
                } else if (this.g.get(i6).f461b == i) {
                    break;
                } else {
                    i6++;
                }
            }
            if (i6 >= 0) {
                int size3 = this.g.size() - i6;
                int i7 = 0;
                while (true) {
                    i7++;
                    if (i7 >= size3 || this.g.get(i6).f461b != i) {
                        break;
                    }
                    a(i6, false);
                }
                b(true);
            }
        }
        for (int i8 = 0; i8 < size; i8++) {
            ResolveInfo resolveInfo = queryIntentActivityOptions.get(i8);
            int i9 = resolveInfo.specificIndex;
            Intent intent2 = new Intent(i9 < 0 ? intent : intentArr[i9]);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.applicationInfo.packageName, resolveInfo.activityInfo.name));
            MenuItem intent3 = a(i, i2, i3, resolveInfo.loadLabel(packageManager)).setIcon(resolveInfo.loadIcon(packageManager)).setIntent(intent2);
            if (menuItemArr != null && (i5 = resolveInfo.specificIndex) >= 0) {
                menuItemArr[i5] = intent3;
            }
        }
        return size;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return addSubMenu(0, 0, 0, charSequence);
    }

    public String b() {
        return "android:menu:actionviewstates";
    }

    public void b(Bundle bundle) {
        int size = size();
        SparseArray<? extends Parcelable> sparseArray = null;
        for (int i = 0; i < size; i++) {
            MenuItem item = getItem(i);
            View actionView = item.getActionView();
            if (!(actionView == null || actionView.getId() == -1)) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>();
                }
                actionView.saveHierarchyState(sparseArray);
                if (item.isActionViewExpanded()) {
                    bundle.putInt("android:menu:expandedactionview", item.getItemId());
                }
            }
            if (item.hasSubMenu()) {
                ((C) item.getSubMenu()).b(bundle);
            }
        }
        if (sparseArray != null) {
            bundle.putSparseParcelableArray(b(), sparseArray);
        }
    }

    public l c() {
        return this;
    }

    public void c(p pVar) {
        this.i = true;
        b(true);
    }

    @Override // android.view.Menu
    public void clear() {
        p pVar = this.y;
        if (pVar != null) {
            a(pVar);
        }
        this.g.clear();
        b(true);
    }

    public void clearHeader() {
        this.p = null;
        this.o = null;
        this.q = null;
        b(false);
    }

    @Override // android.view.Menu
    public void close() {
        a(true);
    }

    public ArrayList<p> d() {
        if (!this.i) {
            return this.h;
        }
        this.h.clear();
        int size = this.g.size();
        for (int i = 0; i < size; i++) {
            p pVar = this.g.get(i);
            if (pVar.isVisible()) {
                this.h.add(pVar);
            }
        }
        this.i = false;
        this.l = true;
        return this.h;
    }

    public boolean e() {
        return this.z;
    }

    public boolean f() {
        return this.f453d;
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        MenuItem findItem;
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = this.g.get(i2);
            if (pVar.f460a == i) {
                return pVar;
            }
            if (pVar.hasSubMenu() && (findItem = pVar.o.findItem(i)) != null) {
                return findItem;
            }
        }
        return null;
    }

    public boolean g() {
        return this.f454e;
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return this.g.get(i);
    }

    public void h() {
        this.r = false;
        if (this.s) {
            this.s = false;
            b(this.t);
        }
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        if (this.A) {
            return true;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (this.g.get(i).isVisible()) {
                return true;
            }
        }
        return false;
    }

    public void i() {
        if (!this.r) {
            this.r = true;
            this.s = false;
            this.t = false;
        }
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return a(i, keyEvent) != null;
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return a(findItem(i), i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        p a2 = a(i, keyEvent);
        boolean a3 = a2 != null ? a(a2, (v) null, i2) : false;
        if ((i2 & 2) != 0) {
            a(true);
        }
        return a3;
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (this.g.get(i2).f461b == i) {
                break;
            } else {
                i2++;
            }
        }
        if (i2 >= 0) {
            int size2 = this.g.size() - i2;
            int i3 = 0;
            while (true) {
                i3++;
                if (i3 >= size2 || this.g.get(i2).f461b != i) {
                    break;
                }
                a(i2, false);
            }
            b(true);
        }
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        int size = size();
        int i2 = 0;
        while (true) {
            if (i2 >= size) {
                i2 = -1;
                break;
            } else if (this.g.get(i2).f460a == i) {
                break;
            } else {
                i2++;
            }
        }
        a(i2, true);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = this.g.get(i2);
            if (pVar.f461b == i) {
                pVar.y = (pVar.y & (-5)) | (z2 ? 4 : 0);
                int i3 = pVar.y;
                pVar.y = (i3 & (-2)) | (z ? 1 : 0);
                if (i3 != pVar.y) {
                    pVar.n.b(false);
                }
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupDividerEnabled(boolean z) {
        this.z = z;
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        int size = this.g.size();
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = this.g.get(i2);
            if (pVar.f461b == i) {
                if (z) {
                    pVar.y |= 16;
                } else {
                    pVar.y &= -17;
                }
                pVar.n.b(false);
            }
        }
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        int size = this.g.size();
        boolean z2 = false;
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = this.g.get(i2);
            if (pVar.f461b == i && pVar.d(z)) {
                z2 = true;
            }
        }
        if (z2) {
            b(true);
        }
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        this.f453d = z;
        b(false);
    }

    @Override // android.view.Menu
    public int size() {
        return this.g.size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(0, 0, 0, this.f452c.getString(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return addSubMenu(0, 0, 0, this.f452c.getString(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(i, i2, i3, charSequence);
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        p pVar = (p) a(i, i2, i3, charSequence);
        C c2 = new C(this.f451b, this, pVar);
        pVar.o = c2;
        c2.setHeaderTitle(pVar.f464e);
        return c2;
    }

    public void a(v vVar) {
        Iterator<WeakReference<v>> it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<v> next = it.next();
            v vVar2 = next.get();
            if (vVar2 == null || vVar2 == vVar) {
                this.x.remove(next);
            }
        }
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(i, i2, i3, this.f452c.getString(i4));
    }

    public void a(Bundle bundle) {
        MenuItem findItem;
        if (bundle != null) {
            SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray(b());
            int size = size();
            for (int i = 0; i < size; i++) {
                MenuItem item = getItem(i);
                View actionView = item.getActionView();
                if (!(actionView == null || actionView.getId() == -1)) {
                    actionView.restoreHierarchyState(sparseParcelableArray);
                }
                if (item.hasSubMenu()) {
                    ((C) item.getSubMenu()).a(bundle);
                }
            }
            int i2 = bundle.getInt("android:menu:expandedactionview");
            if (i2 > 0 && (findItem = findItem(i2)) != null) {
                findItem.expandActionView();
            }
        }
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return addSubMenu(i, i2, i3, this.f452c.getString(i4));
    }

    public void b(boolean z) {
        if (!this.r) {
            if (z) {
                this.i = true;
                this.l = true;
            }
            if (!this.x.isEmpty()) {
                i();
                Iterator<WeakReference<v>> it = this.x.iterator();
                while (it.hasNext()) {
                    WeakReference<v> next = it.next();
                    v vVar = next.get();
                    if (vVar == null) {
                        this.x.remove(next);
                    } else {
                        vVar.a(z);
                    }
                }
                h();
                return;
            }
            return;
        }
        this.s = true;
        if (z) {
            this.t = true;
        }
    }

    public void a(a aVar) {
        this.f = aVar;
    }

    public final void a(int i, boolean z) {
        if (i >= 0 && i < this.g.size()) {
            this.g.remove(i);
            if (z) {
                b(true);
            }
        }
    }

    public void a(MenuItem menuItem) {
        int groupId = menuItem.getGroupId();
        int size = this.g.size();
        i();
        for (int i = 0; i < size; i++) {
            p pVar = this.g.get(i);
            if (pVar.f461b == groupId && pVar.e()) {
                boolean z = true;
                if ((pVar.y & 1) == 1) {
                    if (pVar != menuItem) {
                        z = false;
                    }
                    pVar.b(z);
                }
            }
        }
        h();
    }

    public boolean b(p pVar) {
        boolean z = false;
        if (this.x.isEmpty()) {
            return false;
        }
        i();
        Iterator<WeakReference<v>> it = this.x.iterator();
        while (it.hasNext()) {
            WeakReference<v> next = it.next();
            v vVar = next.get();
            if (vVar == null) {
                this.x.remove(next);
            } else {
                z = vVar.b(this, pVar);
                if (z) {
                    break;
                }
            }
        }
        h();
        if (z) {
            this.y = pVar;
        }
        return z;
    }

    public MenuItem a(int i, int i2, int i3, CharSequence charSequence) {
        int i4;
        int i5 = ((-65536) & i3) >> 16;
        if (i5 >= 0) {
            int[] iArr = f450a;
            if (i5 < iArr.length) {
                int i6 = (iArr[i5] << 16) | (65535 & i3);
                p pVar = new p(this, i, i2, i3, i6, charSequence, this.m);
                ContextMenu.ContextMenuInfo contextMenuInfo = this.n;
                if (contextMenuInfo != null) {
                    pVar.E = contextMenuInfo;
                }
                ArrayList<p> arrayList = this.g;
                int size = arrayList.size();
                while (true) {
                    size--;
                    if (size >= 0) {
                        if (arrayList.get(size).f463d <= i6) {
                            i4 = size + 1;
                            break;
                        }
                    } else {
                        i4 = 0;
                        break;
                    }
                }
                arrayList.add(i4, pVar);
                b(true);
                return pVar;
            }
        }
        throw new IllegalArgumentException("order does not contain a valid category.");
    }

    public final void a(int i, CharSequence charSequence, int i2, Drawable drawable, View view) {
        Resources resources = this.f452c;
        if (view != null) {
            this.q = view;
            this.o = null;
            this.p = null;
        } else {
            if (i > 0) {
                this.o = resources.getText(i);
            } else if (charSequence != null) {
                this.o = charSequence;
            }
            if (i2 > 0) {
                this.p = c.e.b.a.b(this.f451b, i2);
            } else if (drawable != null) {
                this.p = drawable;
            }
            this.q = null;
        }
        b(false);
    }

    public boolean a(l lVar, MenuItem menuItem) {
        a aVar = this.f;
        return aVar != null && aVar.a(lVar, menuItem);
    }

    public void a(List<p> list, int i, KeyEvent keyEvent) {
        char c2;
        int i2;
        boolean f = f();
        int modifiers = keyEvent.getModifiers();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        if (keyEvent.getKeyData(keyData) || i == 67) {
            int size = this.g.size();
            for (int i3 = 0; i3 < size; i3++) {
                p pVar = this.g.get(i3);
                if (pVar.hasSubMenu()) {
                    pVar.o.a(list, i, keyEvent);
                }
                if (f) {
                    c2 = pVar.j;
                } else {
                    c2 = pVar.h;
                }
                if (f) {
                    i2 = pVar.k;
                } else {
                    i2 = pVar.i;
                }
                if (((modifiers & 69647) == (i2 & 69647)) && c2 != 0) {
                    char[] cArr = keyData.meta;
                    if ((c2 == cArr[0] || c2 == cArr[2] || (f && c2 == '\b' && i == 67)) && pVar.isEnabled()) {
                        list.add(pVar);
                    }
                }
            }
        }
    }

    public p a(int i, KeyEvent keyEvent) {
        char c2;
        ArrayList<p> arrayList = this.w;
        arrayList.clear();
        a(arrayList, i, keyEvent);
        if (arrayList.isEmpty()) {
            return null;
        }
        int metaState = keyEvent.getMetaState();
        KeyCharacterMap.KeyData keyData = new KeyCharacterMap.KeyData();
        keyEvent.getKeyData(keyData);
        int size = arrayList.size();
        if (size == 1) {
            return arrayList.get(0);
        }
        boolean f = f();
        for (int i2 = 0; i2 < size; i2++) {
            p pVar = arrayList.get(i2);
            if (f) {
                c2 = pVar.j;
            } else {
                c2 = pVar.h;
            }
            if ((c2 == keyData.meta[0] && (metaState & 2) == 0) || ((c2 == keyData.meta[2] && (metaState & 2) != 0) || (f && c2 == '\b' && i == 67))) {
                return pVar;
            }
        }
        return null;
    }

    public boolean a(MenuItem menuItem, int i) {
        return a(menuItem, (v) null, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x007e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean a(android.view.MenuItem r7, c.a.e.a.v r8, int r9) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: c.a.e.a.l.a(android.view.MenuItem, c.a.e.a.v, int):boolean");
    }

    public final void a(boolean z) {
        if (!this.v) {
            this.v = true;
            Iterator<WeakReference<v>> it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference<v> next = it.next();
                v vVar = next.get();
                if (vVar == null) {
                    this.x.remove(next);
                } else {
                    vVar.a(this, z);
                }
            }
            this.v = false;
        }
    }

    public void a() {
        ArrayList<p> d2 = d();
        if (this.l) {
            Iterator<WeakReference<v>> it = this.x.iterator();
            boolean z = false;
            while (it.hasNext()) {
                WeakReference<v> next = it.next();
                v vVar = next.get();
                if (vVar == null) {
                    this.x.remove(next);
                } else {
                    z |= vVar.a();
                }
            }
            if (z) {
                this.j.clear();
                this.k.clear();
                int size = d2.size();
                for (int i = 0; i < size; i++) {
                    p pVar = d2.get(i);
                    if (pVar.d()) {
                        this.j.add(pVar);
                    } else {
                        this.k.add(pVar);
                    }
                }
            } else {
                this.j.clear();
                this.k.clear();
                this.k.addAll(d());
            }
            this.l = false;
        }
    }

    public boolean a(p pVar) {
        boolean z = false;
        if (!this.x.isEmpty() && this.y == pVar) {
            i();
            Iterator<WeakReference<v>> it = this.x.iterator();
            while (it.hasNext()) {
                WeakReference<v> next = it.next();
                v vVar = next.get();
                if (vVar == null) {
                    this.x.remove(next);
                } else {
                    z = vVar.a(this, pVar);
                    if (z) {
                        break;
                    }
                }
            }
            h();
            if (z) {
                this.y = null;
            }
        }
        return z;
    }
}
