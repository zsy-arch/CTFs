package com.amap.api.col;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* compiled from: IndoorFloorSwitchView.java */
/* loaded from: classes.dex */
public class ee extends ScrollView {
    public static final String a = ee.class.getSimpleName();
    private Context c;
    private LinearLayout d;
    private List<String> f;
    private int h;
    private int n;
    private int o;
    private Runnable p;
    private a r;
    private int e = 0;
    private int g = -1;
    private Bitmap i = null;
    private int j = Color.parseColor("#eeffffff");
    private int k = Color.parseColor("#44383838");
    private int l = 4;
    private int m = 1;
    int b = 1;
    private int q = 50;

    /* compiled from: IndoorFloorSwitchView.java */
    /* loaded from: classes.dex */
    public interface a {
        void a(int i);
    }

    public ee(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        setVerticalScrollBarEnabled(false);
        try {
            if (this.i == null) {
                InputStream open = dq.a(context).open("map_indoor_select.png");
                this.i = BitmapFactory.decodeStream(open);
                open.close();
            }
        } catch (Throwable th) {
        }
        this.d = new LinearLayout(context);
        this.d.setOrientation(1);
        addView(this.d);
        this.p = new Runnable() { // from class: com.amap.api.col.ee.1
            @Override // java.lang.Runnable
            public void run() {
                if (ee.this.o - ee.this.getScrollY() != 0) {
                    ee.this.o = ee.this.getScrollY();
                    ee.this.postDelayed(ee.this.p, ee.this.q);
                } else if (ee.this.e != 0) {
                    final int i = ee.this.o % ee.this.e;
                    final int i2 = ee.this.o / ee.this.e;
                    if (i == 0) {
                        ee.this.b = i2 + ee.this.m;
                        ee.this.g();
                    } else if (i > ee.this.e / 2) {
                        ee.this.post(new Runnable() { // from class: com.amap.api.col.ee.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                ee.this.smoothScrollTo(0, (ee.this.o - i) + ee.this.e);
                                ee.this.b = i2 + ee.this.m + 1;
                                ee.this.g();
                            }
                        });
                    } else {
                        ee.this.post(new Runnable() { // from class: com.amap.api.col.ee.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                ee.this.smoothScrollTo(0, ee.this.o - i);
                                ee.this.b = i2 + ee.this.m;
                                ee.this.g();
                            }
                        });
                    }
                }
            }
        };
    }

    public void a() {
        this.o = getScrollY();
        postDelayed(this.p, this.q);
    }

    private void e() {
        if (!(this.f == null || this.f.size() == 0)) {
            this.d.removeAllViews();
            this.n = (this.m * 2) + 1;
            for (int size = this.f.size() - 1; size >= 0; size--) {
                this.d.addView(b(this.f.get(size)));
            }
            a(0);
        }
    }

    private TextView b(String str) {
        TextView textView = new TextView(this.c);
        textView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
        textView.setSingleLine(true);
        textView.setTextSize(2, 16.0f);
        textView.setText(str);
        textView.setGravity(17);
        textView.getPaint().setFakeBoldText(true);
        int a2 = a(this.c, 8.0f);
        int a3 = a(this.c, 6.0f);
        textView.setPadding(a2, a3, a2, a3);
        if (this.e == 0) {
            this.e = a(textView);
            this.d.setLayoutParams(new FrameLayout.LayoutParams(-2, this.e * this.n));
            setLayoutParams(new LinearLayout.LayoutParams(-2, this.e * this.n));
        }
        return textView;
    }

    private void a(int i) {
        if (this.e != 0) {
            int i2 = (i / this.e) + this.m;
            int i3 = i % this.e;
            int i4 = i / this.e;
            if (i3 == 0) {
                i2 = this.m + i4;
            } else if (i3 > this.e / 2) {
                i2 = this.m + i4 + 1;
            }
            int childCount = this.d.getChildCount();
            for (int i5 = 0; i5 < childCount; i5++) {
                TextView textView = (TextView) this.d.getChildAt(i5);
                if (textView != null) {
                    if (i2 == i5) {
                        textView.setTextColor(Color.parseColor("#0288ce"));
                    } else {
                        textView.setTextColor(Color.parseColor("#bbbbbb"));
                    }
                } else {
                    return;
                }
            }
        }
    }

    public void a(String[] strArr) {
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.clear();
        for (String str : strArr) {
            this.f.add(str);
        }
        for (int i = 0; i < this.m; i++) {
            this.f.add(0, "");
            this.f.add("");
        }
        e();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.j = i;
    }

    public void b() {
        if (this.i != null && !this.i.isRecycled()) {
            this.i.recycle();
            this.i = null;
        }
        if (this.r != null) {
            this.r = null;
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        if (this.h == 0) {
            try {
                this.h = ((WindowManager) this.c.getSystemService("window")).getDefaultDisplay().getWidth();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        super.setBackgroundDrawable(new Drawable() { // from class: com.amap.api.col.ee.2
            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas canvas) {
                try {
                    a(canvas);
                    b(canvas);
                    c(canvas);
                } catch (Throwable th2) {
                }
            }

            private void a(Canvas canvas) {
                canvas.drawColor(ee.this.j);
            }

            private void b(Canvas canvas) {
                Paint paint = new Paint();
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                rect.left = 0;
                rect.top = 0;
                rect.right = ee.this.i.getWidth() + 0;
                rect.bottom = ee.this.i.getHeight() + 0;
                rect2.left = 0;
                rect2.top = ee.this.f()[0];
                rect2.right = ee.this.h + 0;
                rect2.bottom = ee.this.f()[1];
                canvas.drawBitmap(ee.this.i, rect, rect2, paint);
            }

            private void c(Canvas canvas) {
                Paint paint = new Paint();
                Rect clipBounds = canvas.getClipBounds();
                paint.setColor(ee.this.k);
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(ee.this.l);
                canvas.drawRect(clipBounds, paint);
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int i) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter colorFilter) {
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return 0;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int[] f() {
        if (0 == 0) {
            return new int[]{this.e * this.m, this.e * (this.m + 1)};
        }
        return null;
    }

    @Override // android.widget.ScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        this.h = i;
        try {
            setBackgroundDrawable(null);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // android.view.View
    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        super.onScrollChanged(i, i2, i3, i4);
        a(i2);
        if (i2 > i4) {
            this.g = 1;
        } else {
            this.g = 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (this.r != null) {
            try {
                this.r.a(c());
            } catch (Throwable th) {
            }
        }
    }

    public void a(String str) {
        if (this.f != null && this.f.size() != 0) {
            final int size = ((this.f.size() - this.m) - 1) - this.f.indexOf(str);
            this.b = this.m + size;
            post(new Runnable() { // from class: com.amap.api.col.ee.3
                @Override // java.lang.Runnable
                public void run() {
                    ee.this.smoothScrollTo(0, size * ee.this.e);
                }
            });
        }
    }

    public int c() {
        if (this.f == null || this.f.size() == 0) {
            return 0;
        }
        return Math.min(this.f.size() - (this.m * 2), Math.max(0, ((this.f.size() - 1) - this.b) - this.m));
    }

    @Override // android.widget.ScrollView
    public void fling(int i) {
        super.fling(i / 3);
    }

    @Override // android.widget.ScrollView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            a();
        }
        return super.onTouchEvent(motionEvent);
    }

    public void a(a aVar) {
        this.r = aVar;
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int a(View view) {
        b(view);
        return view.getMeasuredHeight();
    }

    public static void b(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }

    public void a(boolean z) {
        setVisibility(z ? 0 : 8);
    }

    public boolean d() {
        return getVisibility() == 0;
    }
}
