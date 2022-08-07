package d.a.a.d;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Handler;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.view.inputmethod.InputMethodManager;
import bccsejw.sxexrix.zaswnwt.widget.PromptView;
import com.tencent.smtt.sdk.WebView;
import e.a.a.a.a;
import e.b.a.f;

/* loaded from: classes.dex */
public class j {

    /* renamed from: a */
    public static long f1642a = 300;

    /* renamed from: b */
    public InputMethodManager f1643b;

    /* renamed from: c */
    public Animation f1644c;

    /* renamed from: d */
    public Animation f1645d;

    /* renamed from: e */
    public PromptView f1646e;
    public ViewGroup f;
    public ValueAnimator g;
    public boolean h;
    public boolean i;
    public boolean j;
    public AnimationSet k;
    public AlphaAnimation l;
    public AnimationSet m;
    public AnimationSet n;

    public j(Activity activity) {
        if (a.f1619a == null) {
            a.f1619a = new a();
        }
        a aVar = a.f1619a;
        this.f = (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290);
        this.f1646e = new PromptView(activity, aVar, this);
        int i = activity.getResources().getDisplayMetrics().widthPixels;
        int i2 = activity.getResources().getDisplayMetrics().heightPixels;
        this.m = new AnimationSet(true);
        float f = i * 0.5f;
        float f2 = i2;
        float f3 = f2 * 0.45f;
        this.m.addAnimation(new ScaleAnimation(2.0f, 1.0f, 2.0f, 1.0f, f, f3));
        this.m.addAnimation(new AlphaAnimation(0.0f, 1.0f));
        this.m.setDuration(f1642a);
        this.m.setFillAfter(false);
        this.m.setInterpolator(new DecelerateInterpolator());
        this.n = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f, f, f3);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200L);
        this.n.addAnimation(scaleAnimation);
        this.n.addAnimation(alphaAnimation);
        this.n.setDuration(f1642a);
        this.n.setFillAfter(false);
        this.n.setInterpolator(new AccelerateInterpolator());
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 1.0f, 1.0f, 1.0f, f, f2 * 0.5f);
        this.k = new AnimationSet(true);
        this.k.addAnimation(alphaAnimation2);
        this.k.addAnimation(scaleAnimation2);
        this.k.setDuration(f1642a);
        this.k.setFillAfter(false);
        this.l = new AlphaAnimation(1.0f, 0.0f);
        this.l.setDuration(f1642a);
        this.l.setFillAfter(false);
        this.f1643b = (InputMethodManager) activity.getSystemService("input_method");
        new Handler();
    }

    public void a() {
        if (this.j && !this.i) {
            if (this.f1646e.b().l && this.f1644c != null) {
                if (this.f1646e.c() == 102) {
                    this.f1644c.setStartOffset(this.f1646e.b().q);
                } else {
                    this.f1644c.setStartOffset(0L);
                }
                if (this.f1646e.c() == 110) {
                    this.f1646e.d();
                }
                this.f1646e.a();
                this.f1646e.startAnimation(this.f1644c);
                this.f1644c.setAnimationListener(new h(this));
            } else if (this.j && !this.i) {
                this.f.removeView(this.f1646e);
                this.j = false;
            }
        }
    }

    public void b() {
    }

    public void a(String str, boolean z, g... gVarArr) {
        Animation animation;
        if (gVarArr.length > 2) {
            StringBuilder a2 = a.a("showAlert: ");
            a2.append(this.f1646e.getScrollY());
            a2.toString();
            this.f1645d = this.k;
            this.f1644c = this.l;
        } else {
            this.f1645d = this.m;
            this.f1644c = this.n;
        }
        if (a.f1620b == null) {
            a aVar = new a();
            aVar.i = -1;
            aVar.j = WebView.NORMAL_MODE_ALPHA;
            aVar.f1623e = -7829368;
            aVar.f = 15.0f;
            aVar.n = false;
            a.f1620b = aVar;
        }
        a aVar2 = a.f1620b;
        aVar2.p = str;
        aVar2.o = f.ic_prompt_alert_warn;
        ViewGroup viewGroup = this.f;
        if (viewGroup != null) {
            this.f1643b.hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);
        }
        this.f1646e.a(aVar2);
        if (!this.j) {
            this.f.addView(this.f1646e);
            this.j = true;
            if (this.f1646e.b().l && (animation = this.f1645d) != null && z) {
                this.f1646e.startAnimation(animation);
            }
        }
        this.f1646e.a(gVarArr);
        ValueAnimator valueAnimator = this.g;
        if (valueAnimator == null) {
            this.g = ValueAnimator.ofInt(0, 1);
            this.g.setDuration(this.f1646e.b().m);
            this.g.addListener(new i(this));
        } else if (valueAnimator.isRunning()) {
            this.h = true;
            this.g.end();
        }
    }
}
