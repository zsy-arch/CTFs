package c.a.e;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.LayoutInflater;
import c.a.i;

/* loaded from: classes.dex */
public class c extends ContextWrapper {

    /* renamed from: a  reason: collision with root package name */
    public int f480a;

    /* renamed from: b  reason: collision with root package name */
    public Resources.Theme f481b;

    /* renamed from: c  reason: collision with root package name */
    public LayoutInflater f482c;

    /* renamed from: d  reason: collision with root package name */
    public Configuration f483d;

    /* renamed from: e  reason: collision with root package name */
    public Resources f484e;

    public c() {
        super(null);
    }

    public final void a() {
        if (this.f481b == null) {
            this.f481b = getResources().newTheme();
            Resources.Theme theme = getBaseContext().getTheme();
            if (theme != null) {
                this.f481b.setTo(theme);
            }
        }
        this.f481b.applyStyle(this.f480a, true);
    }

    @Override // android.content.ContextWrapper
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.f484e == null) {
            Configuration configuration = this.f483d;
            if (configuration == null) {
                this.f484e = super.getResources();
            } else {
                int i = Build.VERSION.SDK_INT;
                this.f484e = createConfigurationContext(configuration).getResources();
            }
        }
        return this.f484e;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.f482c == null) {
            this.f482c = LayoutInflater.from(getBaseContext()).cloneInContext(this);
        }
        return this.f482c;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Resources.Theme theme = this.f481b;
        if (theme != null) {
            return theme;
        }
        if (this.f480a == 0) {
            this.f480a = i.Theme_AppCompat_Light;
        }
        a();
        return this.f481b;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public void setTheme(int i) {
        if (this.f480a != i) {
            this.f480a = i;
            a();
        }
    }

    public c(Context context, int i) {
        super(context);
        this.f480a = i;
    }
}
