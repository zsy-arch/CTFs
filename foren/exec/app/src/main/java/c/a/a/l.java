package c.a.a;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.core.widget.NestedScrollView;
import c.a.a;
import c.a.f;
import c.e.h.n;
import com.tencent.smtt.sdk.WebView;

/* loaded from: classes.dex */
public class l extends B implements DialogInterface {

    /* renamed from: c */
    public final AlertController f365c = new AlertController(getContext(), this, getWindow());

    public l(Context context, int i) {
        super(context, a(context, i));
    }

    public static int a(Context context, int i) {
        if (((i >>> 24) & WebView.NORMAL_MODE_ALPHA) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // c.a.a.B, android.app.Dialog
    public void onCreate(Bundle bundle) {
        boolean z;
        ListAdapter listAdapter;
        View findViewById;
        super.onCreate(bundle);
        AlertController alertController = this.f365c;
        int i = alertController.K;
        if (i == 0) {
            i = alertController.J;
        } else if (alertController.Q != 1) {
            i = alertController.J;
        }
        alertController.f12b.a().b(i);
        View findViewById2 = alertController.f13c.findViewById(f.parentPanel);
        View findViewById3 = findViewById2.findViewById(f.topPanel);
        View findViewById4 = findViewById2.findViewById(f.contentPanel);
        View findViewById5 = findViewById2.findViewById(f.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById2.findViewById(f.customPanel);
        View view = alertController.h;
        int i2 = 0;
        if (view == null) {
            view = alertController.i != 0 ? LayoutInflater.from(alertController.f11a).inflate(alertController.i, viewGroup, false) : null;
        }
        boolean z2 = view != null;
        if (!z2 || !AlertController.a(view)) {
            alertController.f13c.setFlags(131072, 131072);
        }
        if (z2) {
            FrameLayout frameLayout = (FrameLayout) alertController.f13c.findViewById(f.custom);
            frameLayout.addView(view, new ViewGroup.LayoutParams(-1, -1));
            if (alertController.n) {
                frameLayout.setPadding(alertController.j, alertController.k, alertController.l, alertController.m);
            }
            if (alertController.g != null) {
                ((LinearLayoutCompat.a) viewGroup.getLayoutParams()).f144a = 0.0f;
            }
        } else {
            viewGroup.setVisibility(8);
        }
        View findViewById6 = viewGroup.findViewById(f.topPanel);
        View findViewById7 = viewGroup.findViewById(f.contentPanel);
        View findViewById8 = viewGroup.findViewById(f.buttonPanel);
        ViewGroup a2 = alertController.a(findViewById6, findViewById3);
        ViewGroup a3 = alertController.a(findViewById7, findViewById4);
        ViewGroup a4 = alertController.a(findViewById8, findViewById5);
        alertController.A = (NestedScrollView) alertController.f13c.findViewById(f.scrollView);
        alertController.A.setFocusable(false);
        alertController.A.setNestedScrollingEnabled(false);
        alertController.F = (TextView) a3.findViewById(16908299);
        TextView textView = alertController.F;
        if (textView != null) {
            CharSequence charSequence = alertController.f;
            if (charSequence != null) {
                textView.setText(charSequence);
            } else {
                textView.setVisibility(8);
                alertController.A.removeView(alertController.F);
                if (alertController.g != null) {
                    ViewGroup viewGroup2 = (ViewGroup) alertController.A.getParent();
                    int indexOfChild = viewGroup2.indexOfChild(alertController.A);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(alertController.g, indexOfChild, new ViewGroup.LayoutParams(-1, -1));
                } else {
                    a3.setVisibility(8);
                }
            }
        }
        alertController.o = (Button) a4.findViewById(16908313);
        alertController.o.setOnClickListener(alertController.S);
        if (!TextUtils.isEmpty(alertController.p) || alertController.r != null) {
            alertController.o.setText(alertController.p);
            Drawable drawable = alertController.r;
            if (drawable != null) {
                int i3 = alertController.f14d;
                drawable.setBounds(0, 0, i3, i3);
                alertController.o.setCompoundDrawables(alertController.r, null, null, null);
            }
            alertController.o.setVisibility(0);
            z = true;
        } else {
            alertController.o.setVisibility(8);
            z = false;
        }
        alertController.s = (Button) a4.findViewById(16908314);
        alertController.s.setOnClickListener(alertController.S);
        if (!TextUtils.isEmpty(alertController.t) || alertController.v != null) {
            alertController.s.setText(alertController.t);
            Drawable drawable2 = alertController.v;
            if (drawable2 != null) {
                int i4 = alertController.f14d;
                drawable2.setBounds(0, 0, i4, i4);
                alertController.s.setCompoundDrawables(alertController.v, null, null, null);
            }
            alertController.s.setVisibility(0);
            z |= true;
        } else {
            alertController.s.setVisibility(8);
        }
        alertController.w = (Button) a4.findViewById(16908315);
        alertController.w.setOnClickListener(alertController.S);
        if (!TextUtils.isEmpty(alertController.x) || alertController.z != null) {
            alertController.w.setText(alertController.x);
            Drawable drawable3 = alertController.r;
            if (drawable3 != null) {
                int i5 = alertController.f14d;
                drawable3.setBounds(0, 0, i5, i5);
                alertController.o.setCompoundDrawables(alertController.r, null, null, null);
            }
            alertController.w.setVisibility(0);
            z |= true;
        } else {
            alertController.w.setVisibility(8);
        }
        Context context = alertController.f11a;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(a.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (z) {
                alertController.a(alertController.o);
            } else if (z) {
                alertController.a(alertController.s);
            } else if (z) {
                alertController.a(alertController.w);
            }
        }
        if (!(z)) {
            a4.setVisibility(8);
        }
        if (alertController.G != null) {
            a2.addView(alertController.G, 0, new ViewGroup.LayoutParams(-1, -2));
            alertController.f13c.findViewById(f.title_template).setVisibility(8);
        } else {
            alertController.D = (ImageView) alertController.f13c.findViewById(16908294);
            if (!(!TextUtils.isEmpty(alertController.f15e)) || !alertController.P) {
                alertController.f13c.findViewById(f.title_template).setVisibility(8);
                alertController.D.setVisibility(8);
                a2.setVisibility(8);
            } else {
                alertController.E = (TextView) alertController.f13c.findViewById(f.alertTitle);
                alertController.E.setText(alertController.f15e);
                int i6 = alertController.B;
                if (i6 != 0) {
                    alertController.D.setImageResource(i6);
                } else {
                    Drawable drawable4 = alertController.C;
                    if (drawable4 != null) {
                        alertController.D.setImageDrawable(drawable4);
                    } else {
                        alertController.E.setPadding(alertController.D.getPaddingLeft(), alertController.D.getPaddingTop(), alertController.D.getPaddingRight(), alertController.D.getPaddingBottom());
                        alertController.D.setVisibility(8);
                    }
                }
            }
        }
        boolean z3 = viewGroup.getVisibility() != 8;
        boolean z4 = (a2 == null || a2.getVisibility() == 8) ? false : true;
        boolean z5 = a4.getVisibility() != 8;
        if (!z5 && (findViewById = a3.findViewById(f.textSpacerNoButtons)) != null) {
            findViewById.setVisibility(0);
        }
        if (z4) {
            NestedScrollView nestedScrollView = alertController.A;
            if (nestedScrollView != null) {
                nestedScrollView.setClipToPadding(true);
            }
            View findViewById9 = (alertController.f == null && alertController.g == null) ? null : a2.findViewById(f.titleDividerNoCustom);
            if (findViewById9 != null) {
                findViewById9.setVisibility(0);
            }
        } else {
            View findViewById10 = a3.findViewById(f.textSpacerNoTitle);
            if (findViewById10 != null) {
                findViewById10.setVisibility(0);
            }
        }
        ListView listView = alertController.g;
        if (listView instanceof AlertController.RecycleListView) {
            ((AlertController.RecycleListView) listView).a(z4, z5);
        }
        if (!z3) {
            View view2 = alertController.g;
            if (view2 == null) {
                view2 = alertController.A;
            }
            if (view2 != null) {
                if (z5) {
                    i2 = 2;
                }
                int i7 = z4 ? 1 : 0;
                char c2 = z4 ? 1 : 0;
                char c3 = z4 ? 1 : 0;
                int i8 = i7 | i2;
                View findViewById11 = alertController.f13c.findViewById(f.scrollIndicatorUp);
                View findViewById12 = alertController.f13c.findViewById(f.scrollIndicatorDown);
                if (Build.VERSION.SDK_INT >= 23) {
                    n.a(view2, i8, 3);
                    if (findViewById11 != null) {
                        a3.removeView(findViewById11);
                    }
                    if (findViewById12 != null) {
                        a3.removeView(findViewById12);
                    }
                } else {
                    if (findViewById11 != null && (i8 & 1) == 0) {
                        a3.removeView(findViewById11);
                        findViewById11 = null;
                    }
                    if (findViewById12 != null && (i8 & 2) == 0) {
                        a3.removeView(findViewById12);
                        findViewById12 = null;
                    }
                    if (!(findViewById11 == null && findViewById12 == null)) {
                        if (alertController.f != null) {
                            alertController.A.setOnScrollChangeListener(new C0023d(alertController, findViewById11, findViewById12));
                            alertController.A.post(new RunnableC0024e(alertController, findViewById11, findViewById12));
                        } else {
                            ListView listView2 = alertController.g;
                            if (listView2 != null) {
                                listView2.setOnScrollListener(new C0025f(alertController, findViewById11, findViewById12));
                                alertController.g.post(new RunnableC0026g(alertController, findViewById11, findViewById12));
                            } else {
                                if (findViewById11 != null) {
                                    a3.removeView(findViewById11);
                                }
                                if (findViewById12 != null) {
                                    a3.removeView(findViewById12);
                                }
                            }
                        }
                    }
                }
            }
        }
        ListView listView3 = alertController.g;
        if (listView3 != null && (listAdapter = alertController.H) != null) {
            listView3.setAdapter(listAdapter);
            int i9 = alertController.I;
            if (i9 > -1) {
                listView3.setItemChecked(i9, true);
                listView3.setSelection(i9);
            }
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f365c.A;
        if (nestedScrollView != null && nestedScrollView.a(keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.f365c.A;
        if (nestedScrollView != null && nestedScrollView.a(keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    @Override // c.a.a.B, android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        AlertController alertController = this.f365c;
        alertController.f15e = charSequence;
        TextView textView = alertController.E;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
