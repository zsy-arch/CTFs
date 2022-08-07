package c.i.a;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import androidx.fragment.app.Fragment;
import c.c.j;
import c.e.a.b;
import c.e.a.c;
import c.j.f;
import c.j.r;
import c.j.s;
import java.io.FileDescriptor;
import java.io.PrintWriter;

/* renamed from: c.i.a.h */
/* loaded from: classes.dex */
public class ActivityC0065h extends c implements s, b.a, b.AbstractC0008b {

    /* renamed from: d */
    public r f964d;

    /* renamed from: e */
    public boolean f965e;
    public boolean f;
    public boolean h;
    public boolean i;
    public boolean j;
    public int k;
    public j<String> l;

    /* renamed from: b */
    public final Handler f962b = new HandlerC0064g(this);

    /* renamed from: c */
    public final C0067j f963c = new C0067j(new a());
    public boolean g = true;

    /* renamed from: c.i.a.h$a */
    /* loaded from: classes.dex */
    public class a extends AbstractC0068k<ActivityC0065h> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a() {
            super(r1);
            ActivityC0065h.this = r1;
        }

        @Override // c.i.a.AbstractC0066i
        public View a(int i) {
            return ActivityC0065h.this.findViewById(i);
        }

        @Override // c.i.a.AbstractC0066i
        public boolean a() {
            Window window = ActivityC0065h.this.getWindow();
            return (window == null || window.peekDecorView() == null) ? false : true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: c.i.a.h$b */
    /* loaded from: classes.dex */
    public static final class b {

        /* renamed from: a */
        public r f967a;

        /* renamed from: b */
        public t f968b;
    }

    @Override // c.j.h
    public f a() {
        return this.f743a;
    }

    public void a(Fragment fragment) {
    }

    @Override // c.j.s
    public r b() {
        if (getApplication() != null) {
            if (this.f964d == null) {
                b bVar = (b) getLastNonConfigurationInstance();
                if (bVar != null) {
                    this.f964d = bVar.f967a;
                }
                if (this.f964d == null) {
                    this.f964d = new r();
                }
            }
            return this.f964d;
        }
        throw new IllegalStateException("Your activity is not yet attached to the Application instance. You can't request ViewModel before onCreate call.");
    }

    public AbstractC0069l d() {
        return this.f963c.b();
    }

    @Override // android.app.Activity
    public void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        printWriter.print(str);
        printWriter.print("Local FragmentActivity ");
        printWriter.print(Integer.toHexString(System.identityHashCode(this)));
        printWriter.println(" State:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("mCreated=");
        printWriter.print(this.f965e);
        printWriter.print(" mResumed=");
        printWriter.print(this.f);
        printWriter.print(" mStopped=");
        printWriter.print(this.g);
        if (getApplication() != null) {
            ((c.k.a.b) c.k.a.a.a(this)).f1036c.a(str2, fileDescriptor, printWriter, strArr);
        }
        this.f963c.b().a(str, fileDescriptor, printWriter, strArr);
    }

    public void e() {
        this.f963c.f969a.f973d.l();
    }

    public Object f() {
        return null;
    }

    @Deprecated
    public void g() {
        invalidateOptionsMenu();
    }

    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.f963c.c();
        int i3 = i >> 16;
        if (i3 != 0) {
            int i4 = i3 - 1;
            String a2 = this.l.a(i4);
            this.l.c(i4);
            if (a2 != null && this.f963c.f969a.f973d.a(a2) == null) {
                String str = "Activity result no fragment exists for who: " + a2;
                return;
            }
            return;
        }
        c.e.a.b.a();
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        AbstractC0069l b2 = this.f963c.b();
        boolean b3 = b2.b();
        if (b3 && Build.VERSION.SDK_INT <= 25) {
            return;
        }
        if (b3 || !b2.c()) {
            super.onBackPressed();
        }
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.f963c.c();
        this.f963c.f969a.f973d.a(configuration);
    }

    @Override // c.e.a.c, android.app.Activity
    public void onCreate(Bundle bundle) {
        r rVar;
        AbstractC0068k<?> kVar = this.f963c.f969a;
        t tVar = null;
        kVar.f973d.a(kVar, kVar, (Fragment) null);
        super.onCreate(bundle);
        b bVar = (b) getLastNonConfigurationInstance();
        if (!(bVar == null || (rVar = bVar.f967a) == null || this.f964d != null)) {
            this.f964d = rVar;
        }
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            C0067j jVar = this.f963c;
            if (bVar != null) {
                tVar = bVar.f968b;
            }
            jVar.f969a.f973d.a(parcelable, tVar);
            if (bundle.containsKey("android:support:next_request_index")) {
                this.k = bundle.getInt("android:support:next_request_index");
                int[] intArray = bundle.getIntArray("android:support:request_indicies");
                String[] stringArray = bundle.getStringArray("android:support:request_fragment_who");
                if (!(intArray == null || stringArray == null || intArray.length != stringArray.length)) {
                    this.l = new j<>(intArray.length);
                    for (int i = 0; i < intArray.length; i++) {
                        this.l.c(intArray[i], stringArray[i]);
                    }
                }
            }
        }
        if (this.l == null) {
            this.l = new j<>(10);
            this.k = 0;
        }
        this.f963c.f969a.f973d.h();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        if (i != 0) {
            return super.onCreatePanelMenu(i, menu);
        }
        boolean onCreatePanelMenu = super.onCreatePanelMenu(i, menu);
        C0067j jVar = this.f963c;
        return onCreatePanelMenu | jVar.f969a.f973d.a(menu, getMenuInflater());
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        View a2 = a(view, str, context, attributeSet);
        return a2 == null ? super.onCreateView(view, str, context, attributeSet) : a2;
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.f964d != null && !isChangingConfigurations()) {
            this.f964d.a();
        }
        this.f963c.f969a.f973d.i();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
        this.f963c.f969a.f973d.j();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        if (i == 0) {
            return this.f963c.f969a.f973d.b(menuItem);
        }
        if (i != 6) {
            return false;
        }
        return this.f963c.f969a.f973d.a(menuItem);
    }

    @Override // android.app.Activity
    public void onMultiWindowModeChanged(boolean z) {
        this.f963c.f969a.f973d.a(z);
    }

    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.f963c.c();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
        if (i == 0) {
            this.f963c.f969a.f973d.a(menu);
        }
        super.onPanelClosed(i, menu);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        this.f = false;
        if (this.f962b.hasMessages(2)) {
            this.f962b.removeMessages(2);
            e();
        }
        this.f963c.f969a.f973d.k();
    }

    @Override // android.app.Activity
    public void onPictureInPictureModeChanged(boolean z) {
        this.f963c.f969a.f973d.b(z);
    }

    @Override // android.app.Activity
    public void onPostResume() {
        super.onPostResume();
        this.f962b.removeMessages(2);
        e();
        this.f963c.a();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        if (i != 0 || menu == null) {
            return super.onPreparePanel(i, view, menu);
        }
        return a(view, menu) | this.f963c.f969a.f973d.b(menu);
    }

    @Override // android.app.Activity, c.e.a.b.a
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        this.f963c.c();
        int i2 = (i >> 16) & 65535;
        if (i2 != 0) {
            int i3 = i2 - 1;
            String a2 = this.l.a(i3);
            this.l.c(i3);
            if (a2 != null && this.f963c.f969a.f973d.a(a2) == null) {
                String str = "Activity result no fragment exists for who: " + a2;
            }
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        this.f962b.sendEmptyMessage(2);
        this.f = true;
        this.f963c.a();
    }

    @Override // android.app.Activity
    public final Object onRetainNonConfigurationInstance() {
        Object f = f();
        s sVar = this.f963c.f969a.f973d;
        s.a(sVar.G);
        t tVar = sVar.G;
        if (tVar == null && this.f964d == null && f == null) {
            return null;
        }
        b bVar = new b();
        bVar.f967a = this.f964d;
        bVar.f968b = tVar;
        return bVar;
    }

    @Override // c.e.a.c, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        do {
        } while (a(d(), f.b.CREATED));
        Parcelable r = this.f963c.f969a.f973d.r();
        if (r != null) {
            bundle.putParcelable("android:support:fragments", r);
        }
        if (this.l.b() > 0) {
            bundle.putInt("android:support:next_request_index", this.k);
            int[] iArr = new int[this.l.b()];
            String[] strArr = new String[this.l.b()];
            for (int i = 0; i < this.l.b(); i++) {
                iArr[i] = this.l.b(i);
                strArr[i] = this.l.d(i);
            }
            bundle.putIntArray("android:support:request_indicies", iArr);
            bundle.putStringArray("android:support:request_fragment_who", strArr);
        }
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        this.g = false;
        if (!this.f965e) {
            this.f965e = true;
            this.f963c.f969a.f973d.g();
        }
        this.f963c.c();
        this.f963c.a();
        this.f963c.f969a.f973d.m();
    }

    @Override // android.app.Activity
    public void onStateNotSaved() {
        this.f963c.c();
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        this.g = true;
        do {
        } while (a(d(), f.b.CREATED));
        s sVar = this.f963c.f969a.f973d;
        sVar.w = true;
        sVar.a(2);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i) {
        if (!this.j && i != -1) {
            b(i);
        }
        super.startActivityForResult(intent, i);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4) {
        if (!this.i && i != -1) {
            b(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    public final View a(View view, String str, Context context, AttributeSet attributeSet) {
        return this.f963c.f969a.f973d.onCreateView(view, str, context, attributeSet);
    }

    @Override // android.app.Activity, android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        View a2 = a(null, str, context, attributeSet);
        return a2 == null ? super.onCreateView(str, context, attributeSet) : a2;
    }

    public boolean a(View view, Menu menu) {
        return super.onPreparePanel(0, view, menu);
    }

    @Override // android.app.Activity
    public void startActivityForResult(Intent intent, int i, Bundle bundle) {
        if (!this.j && i != -1) {
            b(i);
        }
        super.startActivityForResult(intent, i, bundle);
    }

    @Override // android.app.Activity
    public void startIntentSenderForResult(IntentSender intentSender, int i, Intent intent, int i2, int i3, int i4, Bundle bundle) {
        if (!this.i && i != -1) {
            b(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4, bundle);
    }

    @Override // c.e.a.b.AbstractC0008b
    public final void a(int i) {
        if (!this.h && i != -1) {
            b(i);
        }
    }

    public static boolean a(AbstractC0069l lVar, f.b bVar) {
        boolean z = false;
        for (Fragment fragment : lVar.a()) {
            if (fragment != null) {
                if (fragment.U.f1026b.compareTo(f.b.STARTED) >= 0) {
                    fragment.U.a(bVar);
                    z = true;
                }
                s sVar = fragment.v;
                if (sVar != null) {
                    z |= a(sVar, bVar);
                }
            }
        }
        return z;
    }

    public static void b(int i) {
        if ((i & (-65536)) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }
}
