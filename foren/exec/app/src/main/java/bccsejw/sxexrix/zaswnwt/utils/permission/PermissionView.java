package bccsejw.sxexrix.zaswnwt.utils.permission;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import d.a.a.c.a.c;
import e.b.a.b;
import e.b.a.d;
import e.b.a.e;

/* loaded from: classes.dex */
public class PermissionView extends FrameLayout {

    /* renamed from: a  reason: collision with root package name */
    public TextView f302a;

    /* renamed from: b  reason: collision with root package name */
    public TextView f303b;

    /* renamed from: c  reason: collision with root package name */
    public Button f304c;

    /* renamed from: d  reason: collision with root package name */
    public GridView f305d;

    /* renamed from: e  reason: collision with root package name */
    public LinearLayout f306e;

    public PermissionView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public void setBtnOnClickListener(View.OnClickListener onClickListener) {
        this.f304c.setOnClickListener(onClickListener);
    }

    public void setFilterColor(int i) {
        if (i != 0) {
            c cVar = (c) this.f305d.getAdapter();
            cVar.f1607c = i;
            cVar.notifyDataSetChanged();
        }
    }

    public void setGridViewAdapter(ListAdapter listAdapter) {
        this.f305d.setAdapter(listAdapter);
    }

    public void setGridViewColum(int i) {
        this.f305d.setNumColumns(i);
    }

    public void setMsg(String str) {
        this.f303b.setText(str);
    }

    public void setStyleId(int i) {
        if (i > 0) {
            int[] iArr = {b.PermissionMsgColor, b.PermissionTitleColor, b.PermissionItemTextColor, b.PermissionButtonTextColor, b.PermissionBackround, b.PermissionButtonBackground, b.PermissionBgFilterColor, b.PermissionIconFilterColor};
            Resources.Theme newTheme = getResources().newTheme();
            newTheme.applyStyle(i, true);
            TypedArray obtainStyledAttributes = newTheme.obtainStyledAttributes(iArr);
            int color = obtainStyledAttributes.getColor(0, 0);
            int color2 = obtainStyledAttributes.getColor(1, 0);
            int color3 = obtainStyledAttributes.getColor(2, 0);
            int color4 = obtainStyledAttributes.getColor(3, 0);
            Drawable drawable = obtainStyledAttributes.getDrawable(4);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(5);
            int color5 = obtainStyledAttributes.getColor(6, 0);
            int color6 = obtainStyledAttributes.getColor(7, 0);
            if (color2 != 0) {
                this.f302a.setTextColor(color2);
            }
            if (drawable != null) {
                if (color5 != 0) {
                    int blue = Color.blue(color5);
                    drawable.setColorFilter(new ColorMatrixColorFilter(new float[]{1.0f, 0.0f, 0.0f, 0.0f, Color.red(color5), 0.0f, 1.0f, 0.0f, 0.0f, Color.green(color5), 0.0f, 0.0f, 1.0f, 0.0f, blue, 0.0f, 0.0f, 0.0f, 1.0f, 1.0f}));
                }
                this.f306e.setBackgroundDrawable(drawable);
            }
            if (color != 0) {
                this.f303b.setTextColor(color);
            }
            if (color3 != 0) {
                c cVar = (c) this.f305d.getAdapter();
                cVar.f1606b = color3;
                cVar.notifyDataSetChanged();
            }
            if (drawable2 != null) {
                this.f304c.setBackgroundDrawable(drawable2);
            }
            if (color4 != 0) {
                this.f304c.setTextColor(color4);
            }
            if (color6 != 0) {
                setFilterColor(color6);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public void setTitle(String str) {
        this.f302a.setText(str);
    }

    public PermissionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        View inflate = View.inflate(getContext(), e.dialog_request_permission, this);
        this.f302a = (TextView) inflate.findViewById(d.tvTitle);
        this.f306e = (LinearLayout) inflate.findViewById(d.llRoot);
        this.f303b = (TextView) inflate.findViewById(d.tvDesc);
        this.f304c = (Button) inflate.findViewById(d.goto_settings);
        this.f305d = (GridView) inflate.findViewById(d.gvPermission);
    }
}
