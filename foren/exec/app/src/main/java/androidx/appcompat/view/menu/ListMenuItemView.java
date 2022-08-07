package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import c.a.a;
import c.a.e.a.p;
import c.a.e.a.w;
import c.a.f;
import c.a.f.ka;
import c.a.g;
import c.a.h;
import c.a.j;
import c.e.h.n;
import com.tencent.smtt.sdk.BuildConfig;

/* loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements w.a, AbsListView.SelectionBoundsAdjuster {

    /* renamed from: a */
    public p f60a;

    /* renamed from: b */
    public ImageView f61b;

    /* renamed from: c */
    public RadioButton f62c;

    /* renamed from: d */
    public TextView f63d;

    /* renamed from: e */
    public CheckBox f64e;
    public TextView f;
    public ImageView g;
    public ImageView h;
    public LinearLayout i;
    public Drawable j;
    public int k;
    public Context l;
    public boolean m;
    public Drawable n;
    public boolean o;
    public LayoutInflater p;
    public boolean q;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.listMenuViewStyle);
    }

    private LayoutInflater getInflater() {
        if (this.p == null) {
            this.p = LayoutInflater.from(getContext());
        }
        return this.p;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // c.a.e.a.w.a
    public void a(p pVar, int i) {
        this.f60a = pVar;
        boolean z = false;
        setVisibility(pVar.isVisible() ? 0 : 8);
        setTitle(pVar.a(this));
        if ((pVar.y & 1) == 1) {
            z = true;
        }
        setCheckable(z);
        a(pVar.f(), pVar.b());
        setIcon(pVar.getIcon());
        setEnabled(pVar.isEnabled());
        setSubMenuArrowVisible(pVar.hasSubMenu());
        setContentDescription(pVar.r);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.h;
        if (imageView != null && imageView.getVisibility() == 0) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
            rect.top = this.h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
        }
    }

    public final void b() {
        this.f62c = (RadioButton) getInflater().inflate(g.abc_list_menu_item_radio, (ViewGroup) this, false);
        RadioButton radioButton = this.f62c;
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(radioButton, -1);
        } else {
            addView(radioButton, -1);
        }
    }

    @Override // c.a.e.a.w.a
    public boolean c() {
        return false;
    }

    @Override // c.a.e.a.w.a
    public p getItemData() {
        return this.f60a;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        n.a(this, this.j);
        this.f63d = (TextView) findViewById(f.title);
        int i = this.k;
        if (i != -1) {
            this.f63d.setTextAppearance(this.l, i);
        }
        this.f = (TextView) findViewById(f.shortcut);
        this.g = (ImageView) findViewById(f.submenuarrow);
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setImageDrawable(this.n);
        }
        this.h = (ImageView) findViewById(f.group_divider);
        this.i = (LinearLayout) findViewById(f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f61b != null && this.m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f61b.getLayoutParams();
            if (layoutParams.height > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = layoutParams.height;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (z || this.f62c != null || this.f64e != null) {
            if (this.f60a.e()) {
                if (this.f62c == null) {
                    b();
                }
                compoundButton2 = this.f62c;
                compoundButton = this.f64e;
            } else {
                if (this.f64e == null) {
                    a();
                }
                compoundButton2 = this.f64e;
                compoundButton = this.f62c;
            }
            if (z) {
                compoundButton2.setChecked((this.f60a.y & 2) == 2);
                if (compoundButton2.getVisibility() != 0) {
                    compoundButton2.setVisibility(0);
                }
                if (compoundButton != null && compoundButton.getVisibility() != 8) {
                    compoundButton.setVisibility(8);
                    return;
                }
                return;
            }
            CheckBox checkBox = this.f64e;
            if (checkBox != null) {
                checkBox.setVisibility(8);
            }
            RadioButton radioButton = this.f62c;
            if (radioButton != null) {
                radioButton.setVisibility(8);
            }
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f60a.e()) {
            if (this.f62c == null) {
                b();
            }
            compoundButton = this.f62c;
        } else {
            if (this.f64e == null) {
                a();
            }
            compoundButton = this.f64e;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.q = z;
        this.m = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility((this.o || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f60a.n.u || this.q;
        if (!z && !this.m) {
            return;
        }
        if (this.f61b != null || drawable != null || this.m) {
            if (this.f61b == null) {
                this.f61b = (ImageView) getInflater().inflate(g.abc_list_menu_item_icon, (ViewGroup) this, false);
                a(this.f61b, 0);
            }
            if (drawable != null || this.m) {
                ImageView imageView = this.f61b;
                if (!z) {
                    drawable = null;
                }
                imageView.setImageDrawable(drawable);
                if (this.f61b.getVisibility() != 0) {
                    this.f61b.setVisibility(0);
                    return;
                }
                return;
            }
            this.f61b.setVisibility(8);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence != null) {
            this.f63d.setText(charSequence);
            if (this.f63d.getVisibility() != 0) {
                this.f63d.setVisibility(0);
            }
        } else if (this.f63d.getVisibility() != 8) {
            this.f63d.setVisibility(8);
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        ka a2 = ka.a(getContext(), attributeSet, j.MenuView, i, 0);
        this.j = a2.b(j.MenuView_android_itemBackground);
        this.k = a2.f(j.MenuView_android_itemTextAppearance, -1);
        this.m = a2.a(j.MenuView_preserveIconSpacing, false);
        this.l = context;
        this.n = a2.b(j.MenuView_subMenuArrow);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{16843049}, a.dropDownListViewStyle, 0);
        this.o = obtainStyledAttributes.hasValue(0);
        a2.f605b.recycle();
        obtainStyledAttributes.recycle();
    }

    public final void a(View view, int i) {
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    public void a(boolean z, char c2) {
        String str;
        int i = (!z || !this.f60a.f()) ? 8 : 0;
        if (i == 0) {
            TextView textView = this.f;
            p pVar = this.f60a;
            char b2 = pVar.b();
            if (b2 == 0) {
                str = BuildConfig.FLAVOR;
            } else {
                Resources resources = pVar.n.f451b.getResources();
                StringBuilder sb = new StringBuilder();
                if (ViewConfiguration.get(pVar.n.f451b).hasPermanentMenuKey()) {
                    sb.append(resources.getString(h.abc_prepend_shortcut_label));
                }
                int i2 = pVar.n.f() ? pVar.k : pVar.i;
                p.a(sb, i2, 65536, resources.getString(h.abc_menu_meta_shortcut_label));
                p.a(sb, i2, 4096, resources.getString(h.abc_menu_ctrl_shortcut_label));
                p.a(sb, i2, 2, resources.getString(h.abc_menu_alt_shortcut_label));
                p.a(sb, i2, 1, resources.getString(h.abc_menu_shift_shortcut_label));
                p.a(sb, i2, 4, resources.getString(h.abc_menu_sym_shortcut_label));
                p.a(sb, i2, 8, resources.getString(h.abc_menu_function_shortcut_label));
                if (b2 == '\b') {
                    sb.append(resources.getString(h.abc_menu_delete_shortcut_label));
                } else if (b2 == '\n') {
                    sb.append(resources.getString(h.abc_menu_enter_shortcut_label));
                } else if (b2 != ' ') {
                    sb.append(b2);
                } else {
                    sb.append(resources.getString(h.abc_menu_space_shortcut_label));
                }
                str = sb.toString();
            }
            textView.setText(str);
        }
        if (this.f.getVisibility() != i) {
            this.f.setVisibility(i);
        }
    }

    public final void a() {
        this.f64e = (CheckBox) getInflater().inflate(g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        CheckBox checkBox = this.f64e;
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(checkBox, -1);
        } else {
            addView(checkBox, -1);
        }
    }
}
