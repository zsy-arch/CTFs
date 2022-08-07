package c.e.h;

import android.view.KeyEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f851a;

    /* renamed from: b  reason: collision with root package name */
    public static Method f852b;

    /* renamed from: c  reason: collision with root package name */
    public static boolean f853c;

    /* renamed from: d  reason: collision with root package name */
    public static Field f854d;

    /* loaded from: classes.dex */
    public interface a {
        boolean a(KeyEvent keyEvent);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00bd  */
    /* JADX WARN: Removed duplicated region for block: B:75:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean a(c.e.h.c.a r7, android.view.View r8, android.view.Window.Callback r9, android.view.KeyEvent r10) {
        /*
            r0 = 0
            if (r7 != 0) goto L_0x0004
            return r0
        L_0x0004:
            int r1 = android.os.Build.VERSION.SDK_INT
            r2 = 28
            if (r1 < r2) goto L_0x000f
            boolean r7 = r7.a(r10)
            return r7
        L_0x000f:
            boolean r1 = r9 instanceof android.app.Activity
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x007e
            android.app.Activity r9 = (android.app.Activity) r9
            r9.onUserInteraction()
            android.view.Window r7 = r9.getWindow()
            r8 = 8
            boolean r8 = r7.hasFeature(r8)
            if (r8 == 0) goto L_0x0061
            android.app.ActionBar r8 = r9.getActionBar()
            int r1 = r10.getKeyCode()
            r4 = 82
            if (r1 != r4) goto L_0x0061
            if (r8 == 0) goto L_0x0061
            boolean r1 = c.e.h.c.f851a
            if (r1 != 0) goto L_0x004c
            java.lang.Class r1 = r8.getClass()     // Catch: NoSuchMethodException -> 0x004a
            java.lang.String r4 = "onMenuKeyEvent"
            java.lang.Class[] r5 = new java.lang.Class[r3]     // Catch: NoSuchMethodException -> 0x004a
            java.lang.Class<android.view.KeyEvent> r6 = android.view.KeyEvent.class
            r5[r0] = r6     // Catch: NoSuchMethodException -> 0x004a
            java.lang.reflect.Method r1 = r1.getMethod(r4, r5)     // Catch: NoSuchMethodException -> 0x004a
            c.e.h.c.f852b = r1     // Catch: NoSuchMethodException -> 0x004a
        L_0x004a:
            c.e.h.c.f851a = r3
        L_0x004c:
            java.lang.reflect.Method r1 = c.e.h.c.f852b
            if (r1 == 0) goto L_0x005e
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch: IllegalAccessException | InvocationTargetException -> 0x005e
            r4[r0] = r10     // Catch: IllegalAccessException | InvocationTargetException -> 0x005e
            java.lang.Object r8 = r1.invoke(r8, r4)     // Catch: IllegalAccessException | InvocationTargetException -> 0x005e
            java.lang.Boolean r8 = (java.lang.Boolean) r8     // Catch: IllegalAccessException | InvocationTargetException -> 0x005e
            boolean r0 = r8.booleanValue()     // Catch: IllegalAccessException | InvocationTargetException -> 0x005e
        L_0x005e:
            if (r0 == 0) goto L_0x0061
            goto L_0x007d
        L_0x0061:
            boolean r8 = r7.superDispatchKeyEvent(r10)
            if (r8 == 0) goto L_0x0068
            goto L_0x007d
        L_0x0068:
            android.view.View r7 = r7.getDecorView()
            boolean r8 = c.e.h.n.a(r7, r10)
            if (r8 == 0) goto L_0x0073
            goto L_0x007d
        L_0x0073:
            if (r7 == 0) goto L_0x0079
            android.view.KeyEvent$DispatcherState r2 = r7.getKeyDispatcherState()
        L_0x0079:
            boolean r3 = r10.dispatch(r9, r2, r9)
        L_0x007d:
            return r3
        L_0x007e:
            boolean r1 = r9 instanceof android.app.Dialog
            if (r1 == 0) goto L_0x00d3
            android.app.Dialog r9 = (android.app.Dialog) r9
            boolean r7 = c.e.h.c.f853c
            if (r7 != 0) goto L_0x0099
            java.lang.Class<android.app.Dialog> r7 = android.app.Dialog.class
            java.lang.String r8 = "mOnKeyListener"
            java.lang.reflect.Field r7 = r7.getDeclaredField(r8)     // Catch: NoSuchFieldException -> 0x0097
            c.e.h.c.f854d = r7     // Catch: NoSuchFieldException -> 0x0097
            java.lang.reflect.Field r7 = c.e.h.c.f854d     // Catch: NoSuchFieldException -> 0x0097
            r7.setAccessible(r3)     // Catch: NoSuchFieldException -> 0x0097
        L_0x0097:
            c.e.h.c.f853c = r3
        L_0x0099:
            java.lang.reflect.Field r7 = c.e.h.c.f854d
            if (r7 == 0) goto L_0x00a4
            java.lang.Object r7 = r7.get(r9)     // Catch: IllegalAccessException -> 0x00a4
            android.content.DialogInterface$OnKeyListener r7 = (android.content.DialogInterface.OnKeyListener) r7     // Catch: IllegalAccessException -> 0x00a4
            goto L_0x00a5
        L_0x00a4:
            r7 = r2
        L_0x00a5:
            if (r7 == 0) goto L_0x00b2
            int r8 = r10.getKeyCode()
            boolean r7 = r7.onKey(r9, r8, r10)
            if (r7 == 0) goto L_0x00b2
            goto L_0x00d2
        L_0x00b2:
            android.view.Window r7 = r9.getWindow()
            boolean r8 = r7.superDispatchKeyEvent(r10)
            if (r8 == 0) goto L_0x00bd
            goto L_0x00d2
        L_0x00bd:
            android.view.View r7 = r7.getDecorView()
            boolean r8 = c.e.h.n.a(r7, r10)
            if (r8 == 0) goto L_0x00c8
            goto L_0x00d2
        L_0x00c8:
            if (r7 == 0) goto L_0x00ce
            android.view.KeyEvent$DispatcherState r2 = r7.getKeyDispatcherState()
        L_0x00ce:
            boolean r3 = r10.dispatch(r9, r2, r9)
        L_0x00d2:
            return r3
        L_0x00d3:
            if (r8 == 0) goto L_0x00db
            boolean r8 = c.e.h.n.a(r8, r10)
            if (r8 != 0) goto L_0x00e1
        L_0x00db:
            boolean r7 = r7.a(r10)
            if (r7 == 0) goto L_0x00e2
        L_0x00e1:
            r0 = 1
        L_0x00e2:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.e.h.c.a(c.e.h.c$a, android.view.View, android.view.Window$Callback, android.view.KeyEvent):boolean");
    }
}
