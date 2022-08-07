package c.a.e.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import c.e.d.a.a;
import c.e.d.a.b;
import c.e.d.a.c;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
public class x extends AbstractC0031c<a> implements Menu {
    public x(Context context, a aVar) {
        super(context, aVar);
    }

    @Override // android.view.Menu
    public MenuItem add(CharSequence charSequence) {
        return a(((a) this.f423a).add(charSequence));
    }

    @Override // android.view.Menu
    public int addIntentOptions(int i, int i2, int i3, ComponentName componentName, Intent[] intentArr, Intent intent, int i4, MenuItem[] menuItemArr) {
        MenuItem[] menuItemArr2 = menuItemArr != null ? new MenuItem[menuItemArr.length] : null;
        int addIntentOptions = ((a) this.f423a).addIntentOptions(i, i2, i3, componentName, intentArr, intent, i4, menuItemArr2);
        if (menuItemArr2 != null) {
            int length = menuItemArr2.length;
            for (int i5 = 0; i5 < length; i5++) {
                menuItemArr[i5] = a(menuItemArr2[i5]);
            }
        }
        return addIntentOptions;
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(CharSequence charSequence) {
        return a(((a) this.f423a).addSubMenu(charSequence));
    }

    @Override // android.view.Menu
    public void clear() {
        Map<b, MenuItem> map = this.f421c;
        if (map != null) {
            map.clear();
        }
        Map<c, SubMenu> map2 = this.f422d;
        if (map2 != null) {
            map2.clear();
        }
        ((a) this.f423a).clear();
    }

    @Override // android.view.Menu
    public void close() {
        ((a) this.f423a).close();
    }

    @Override // android.view.Menu
    public MenuItem findItem(int i) {
        return a(((a) this.f423a).findItem(i));
    }

    @Override // android.view.Menu
    public MenuItem getItem(int i) {
        return a(((a) this.f423a).getItem(i));
    }

    @Override // android.view.Menu
    public boolean hasVisibleItems() {
        return ((a) this.f423a).hasVisibleItems();
    }

    @Override // android.view.Menu
    public boolean isShortcutKey(int i, KeyEvent keyEvent) {
        return ((a) this.f423a).isShortcutKey(i, keyEvent);
    }

    @Override // android.view.Menu
    public boolean performIdentifierAction(int i, int i2) {
        return ((a) this.f423a).performIdentifierAction(i, i2);
    }

    @Override // android.view.Menu
    public boolean performShortcut(int i, KeyEvent keyEvent, int i2) {
        return ((a) this.f423a).performShortcut(i, keyEvent, i2);
    }

    @Override // android.view.Menu
    public void removeGroup(int i) {
        Map<b, MenuItem> map = this.f421c;
        if (map != null) {
            Iterator<b> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (i == it.next().getGroupId()) {
                    it.remove();
                }
            }
        }
        ((a) this.f423a).removeGroup(i);
    }

    @Override // android.view.Menu
    public void removeItem(int i) {
        Map<b, MenuItem> map = this.f421c;
        if (map != null) {
            Iterator<b> it = map.keySet().iterator();
            while (true) {
                if (it.hasNext()) {
                    if (i == it.next().getItemId()) {
                        it.remove();
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        ((a) this.f423a).removeItem(i);
    }

    @Override // android.view.Menu
    public void setGroupCheckable(int i, boolean z, boolean z2) {
        ((a) this.f423a).setGroupCheckable(i, z, z2);
    }

    @Override // android.view.Menu
    public void setGroupEnabled(int i, boolean z) {
        ((a) this.f423a).setGroupEnabled(i, z);
    }

    @Override // android.view.Menu
    public void setGroupVisible(int i, boolean z) {
        ((a) this.f423a).setGroupVisible(i, z);
    }

    @Override // android.view.Menu
    public void setQwertyMode(boolean z) {
        ((a) this.f423a).setQwertyMode(z);
    }

    @Override // android.view.Menu
    public int size() {
        return ((a) this.f423a).size();
    }

    @Override // android.view.Menu
    public MenuItem add(int i) {
        return a(((a) this.f423a).add(i));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i) {
        return a(((a) this.f423a).addSubMenu(i));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, CharSequence charSequence) {
        return a(((a) this.f423a).add(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, CharSequence charSequence) {
        return a(((a) this.f423a).addSubMenu(i, i2, i3, charSequence));
    }

    @Override // android.view.Menu
    public MenuItem add(int i, int i2, int i3, int i4) {
        return a(((a) this.f423a).add(i, i2, i3, i4));
    }

    @Override // android.view.Menu
    public SubMenu addSubMenu(int i, int i2, int i3, int i4) {
        return a(((a) this.f423a).addSubMenu(i, i2, i3, i4));
    }
}
