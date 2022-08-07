package com.alimama.mobile;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.alimama.mobile.a;
import com.alimama.mobile.csdk.umupdate.a.g;
import com.alimama.mobile.csdk.umupdate.models.MMEntity;
import com.alimama.mobile.csdk.umupdate.models.Promoter;
import com.umeng.update.net.j;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class MMAdView extends FrameLayout implements View.OnClickListener, a.AbstractC0004a {
    a a;
    MMEntity b;
    View c;
    View d;
    TextView e;
    LinearLayout f;
    boolean g = false;

    public MMAdView(Context context) {
        super(context);
    }

    public MMAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MMAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean init(String str) {
        this.a = a.a();
        if (!this.a.e()) {
            this.a.a(getContext());
        }
        try {
            inflate(getContext(), a.a().d().a(), this);
            this.c = findViewById(this.a.d().d());
            this.f = (LinearLayout) findViewById(this.a.d().e());
            this.b = new MMEntity(str);
            setVisibility(0);
            this.c.setVisibility(0);
            this.e = (TextView) findViewById(this.a.d().f());
            this.e.setVisibility(4);
            this.d = findViewById(this.a.d().g());
            this.d.setVisibility(0);
            this.a.a(this.b, this);
            return true;
        } catch (Exception e) {
            Log.w("mmsdk", "An error occurred while initializing MMAdView.", e);
            return false;
        }
    }

    @Override // com.alimama.mobile.a.AbstractC0004a
    public void dataReceived(int i, List<Promoter> list) {
        if (this.g) {
            g.c("reviced mm promoters,but the activity is finish.", new Object[0]);
        } else if (list == null || list.size() <= 0) {
            this.e.setVisibility(0);
            this.d.setVisibility(4);
            this.e.setText("加载失败...");
        } else {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < list.size() && i2 < 4; i2++) {
                Promoter promoter = list.get(i2);
                View inflate = inflate(getContext(), this.a.d().b(), null);
                inflate.setTag(promoter);
                j.a(getContext(), (ImageView) inflate.findViewById(this.a.d().h()), promoter.icon, false);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.weight = 1.0f;
                this.f.addView(inflate, layoutParams);
                inflate.setOnClickListener(this);
                arrayList.add(promoter);
            }
            this.a.a(this.b, (Promoter[]) arrayList.toArray(new Promoter[arrayList.size()]));
            this.c.setVisibility(8);
        }
    }

    public void destroy() {
        this.g = true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getTag() instanceof Promoter) {
            this.a.a(this.b, (Promoter) view.getTag());
        }
    }
}
