package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.easeui.EaseConstant;
import com.hyphenate.util.ImageUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.Constants;
import com.tencent.open.b.d;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.open.utils.Util;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ProGuard */
/* loaded from: classes2.dex */
public class ImageActivity extends Activity {
    RelativeLayout a;
    private QQToken b;
    private String c;
    private Handler d;
    private c e;
    private Button f;
    private Button g;
    private b h;
    private TextView i;
    private ProgressBar j;
    private String r;
    private Bitmap s;
    private int k = 0;
    private boolean l = false;
    private long m = 0;
    private int n = 0;
    private final int o = ImageUtils.SCALE_IMAGE_WIDTH;
    private final int p = ImageUtils.SCALE_IMAGE_WIDTH;
    private Rect q = new Rect();
    private final View.OnClickListener t = new View.OnClickListener() { // from class: com.tencent.connect.avatar.ImageActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageActivity.this.j.setVisibility(0);
            ImageActivity.this.g.setEnabled(false);
            ImageActivity.this.g.setTextColor(Color.rgb(21, 21, 21));
            ImageActivity.this.f.setEnabled(false);
            ImageActivity.this.f.setTextColor(Color.rgb(36, 94, 134));
            new Thread(new Runnable() { // from class: com.tencent.connect.avatar.ImageActivity.2.1
                @Override // java.lang.Runnable
                public void run() {
                    ImageActivity.this.c();
                }
            }).start();
            if (ImageActivity.this.l) {
                ImageActivity.this.a("10657", 0L);
                return;
            }
            ImageActivity.this.a("10655", System.currentTimeMillis() - ImageActivity.this.m);
            if (ImageActivity.this.e.b) {
                ImageActivity.this.a("10654", 0L);
            }
        }
    };

    /* renamed from: u  reason: collision with root package name */
    private final View.OnClickListener f40u = new View.OnClickListener() { // from class: com.tencent.connect.avatar.ImageActivity.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ImageActivity.this.a("10656", System.currentTimeMillis() - ImageActivity.this.m);
            ImageActivity.this.setResult(0);
            ImageActivity.this.d();
        }
    };
    private final IUiListener v = new IUiListener() { // from class: com.tencent.connect.avatar.ImageActivity.5
        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            ImageActivity.this.g.setEnabled(true);
            ImageActivity.this.g.setTextColor(-1);
            ImageActivity.this.f.setEnabled(true);
            ImageActivity.this.f.setTextColor(-1);
            ImageActivity.this.f.setText("重试");
            ImageActivity.this.j.setVisibility(8);
            ImageActivity.this.l = true;
            ImageActivity.this.a(uiError.errorMessage, 1);
            ImageActivity.this.a("10660", 0L);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            int i;
            ImageActivity.this.g.setEnabled(true);
            ImageActivity.this.g.setTextColor(-1);
            ImageActivity.this.f.setEnabled(true);
            ImageActivity.this.f.setTextColor(-1);
            ImageActivity.this.j.setVisibility(8);
            JSONObject jSONObject = (JSONObject) obj;
            try {
                i = jSONObject.getInt("ret");
            } catch (JSONException e) {
                e.printStackTrace();
                i = -1;
            }
            if (i == 0) {
                ImageActivity.this.a("设置成功", 0);
                ImageActivity.this.a("10658", 0L);
                d.a().a(ImageActivity.this.b.getOpenId(), ImageActivity.this.b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, "12", "3", "0");
                ImageActivity imageActivity = ImageActivity.this;
                if (ImageActivity.this.c != null && !"".equals(ImageActivity.this.c)) {
                    Intent intent = new Intent();
                    intent.setClassName(imageActivity, ImageActivity.this.c);
                    if (imageActivity.getPackageManager().resolveActivity(intent, 0) != null) {
                        imageActivity.startActivity(intent);
                    }
                }
                ImageActivity.this.a(0, jSONObject.toString(), null, null);
                ImageActivity.this.d();
                return;
            }
            ImageActivity.this.a("设置出错了，请重新登录再尝试下呢：）", 1);
            d.a().a(ImageActivity.this.b.getOpenId(), ImageActivity.this.b.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, "12", "19", "1");
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
        }
    };
    private final IUiListener w = new IUiListener() { // from class: com.tencent.connect.avatar.ImageActivity.6
        @Override // com.tencent.tauth.IUiListener
        public void onError(UiError uiError) {
            a(0);
        }

        @Override // com.tencent.tauth.IUiListener
        public void onComplete(Object obj) {
            JSONObject jSONObject = (JSONObject) obj;
            int i = -1;
            try {
                i = jSONObject.getInt("ret");
                if (i == 0) {
                    final String string = jSONObject.getString(EaseConstant.EXTRA_NICK_NAME);
                    ImageActivity.this.d.post(new Runnable() { // from class: com.tencent.connect.avatar.ImageActivity.6.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ImageActivity.this.c(string);
                        }
                    });
                    ImageActivity.this.a("10659", 0L);
                } else {
                    ImageActivity.this.a("10661", 0L);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (i != 0) {
                a(i);
            }
        }

        @Override // com.tencent.tauth.IUiListener
        public void onCancel() {
        }

        private void a(int i) {
            if (ImageActivity.this.k < 2) {
                ImageActivity.this.e();
            }
        }
    };

    private Bitmap a(String str) throws IOException {
        int i = 1;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Uri parse = Uri.parse(str);
        InputStream openInputStream = getContentResolver().openInputStream(parse);
        if (openInputStream == null) {
            return null;
        }
        try {
            BitmapFactory.decodeStream(openInputStream, null, options);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        openInputStream.close();
        int i2 = options.outWidth;
        int i3 = options.outHeight;
        while (i2 * i3 > 4194304) {
            i2 /= 2;
            i3 /= 2;
            i *= 2;
        }
        options.inJustDecodeBounds = false;
        options.inSampleSize = i;
        try {
            return BitmapFactory.decodeStream(getContentResolver().openInputStream(parse), null, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Drawable b(String str) {
        IOException e;
        Drawable drawable;
        try {
            InputStream open = getAssets().open(str);
            drawable = Drawable.createFromStream(open, str);
            try {
                open.close();
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                return drawable;
            }
        } catch (IOException e3) {
            drawable = null;
            e = e3;
        }
        return drawable;
    }

    private View a() {
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -1);
        ViewGroup.LayoutParams layoutParams2 = new ViewGroup.LayoutParams(-1, -1);
        ViewGroup.LayoutParams layoutParams3 = new ViewGroup.LayoutParams(-2, -2);
        this.a = new RelativeLayout(this);
        this.a.setLayoutParams(layoutParams);
        this.a.setBackgroundColor(-16777216);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setLayoutParams(layoutParams3);
        this.a.addView(relativeLayout);
        this.e = new c(this);
        this.e.setLayoutParams(layoutParams2);
        this.e.setScaleType(ImageView.ScaleType.MATRIX);
        relativeLayout.addView(this.e);
        this.h = new b(this);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(layoutParams2);
        layoutParams4.addRule(14, -1);
        layoutParams4.addRule(15, -1);
        this.h.setLayoutParams(layoutParams4);
        relativeLayout.addView(this.h);
        LinearLayout linearLayout = new LinearLayout(this);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, a.a(this, 80.0f));
        layoutParams5.addRule(14, -1);
        linearLayout.setLayoutParams(layoutParams5);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        this.a.addView(linearLayout);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 24.0f), a.a(this, 24.0f)));
        imageView.setImageDrawable(b("com.tencent.plus.logo.png"));
        linearLayout.addView(imageView);
        this.i = new TextView(this);
        LinearLayout.LayoutParams layoutParams6 = new LinearLayout.LayoutParams(layoutParams3);
        layoutParams6.leftMargin = a.a(this, 7.0f);
        this.i.setLayoutParams(layoutParams6);
        this.i.setEllipsize(TextUtils.TruncateAt.END);
        this.i.setSingleLine();
        this.i.setTextColor(-1);
        this.i.setTextSize(24.0f);
        this.i.setVisibility(8);
        linearLayout.addView(this.i);
        RelativeLayout relativeLayout2 = new RelativeLayout(this);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-1, a.a(this, 60.0f));
        layoutParams7.addRule(12, -1);
        layoutParams7.addRule(9, -1);
        relativeLayout2.setLayoutParams(layoutParams7);
        relativeLayout2.setBackgroundDrawable(b("com.tencent.plus.bar.png"));
        int a2 = a.a(this, 10.0f);
        relativeLayout2.setPadding(a2, a2, a2, 0);
        this.a.addView(relativeLayout2);
        a aVar = new a(this);
        int a3 = a.a(this, 14.0f);
        int a4 = a.a(this, 7.0f);
        this.g = new Button(this);
        this.g.setLayoutParams(new RelativeLayout.LayoutParams(a.a(this, 78.0f), a.a(this, 45.0f)));
        this.g.setText("取消");
        this.g.setTextColor(-1);
        this.g.setTextSize(18.0f);
        this.g.setPadding(a3, a4, a3, a4);
        aVar.b(this.g);
        relativeLayout2.addView(this.g);
        this.f = new Button(this);
        RelativeLayout.LayoutParams layoutParams8 = new RelativeLayout.LayoutParams(a.a(this, 78.0f), a.a(this, 45.0f));
        layoutParams8.addRule(11, -1);
        this.f.setLayoutParams(layoutParams8);
        this.f.setTextColor(-1);
        this.f.setTextSize(18.0f);
        this.f.setPadding(a3, a4, a3, a4);
        this.f.setText("选取");
        aVar.a(this.f);
        relativeLayout2.addView(this.f);
        TextView textView = new TextView(this);
        RelativeLayout.LayoutParams layoutParams9 = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams9.addRule(13, -1);
        textView.setLayoutParams(layoutParams9);
        textView.setText("移动和缩放");
        textView.setPadding(0, a.a(this, 3.0f), 0, 0);
        textView.setTextSize(18.0f);
        textView.setTextColor(-1);
        relativeLayout2.addView(textView);
        this.j = new ProgressBar(this);
        RelativeLayout.LayoutParams layoutParams10 = new RelativeLayout.LayoutParams(layoutParams3);
        layoutParams10.addRule(14, -1);
        layoutParams10.addRule(15, -1);
        this.j.setLayoutParams(layoutParams10);
        this.j.setVisibility(8);
        this.a.addView(this.j);
        return this.a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class a extends View {
        public a(Context context) {
            super(context);
        }

        public void a(Button button) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable b = ImageActivity.this.b("com.tencent.plus.blue_normal.png");
            Drawable b2 = ImageActivity.this.b("com.tencent.plus.blue_down.png");
            Drawable b3 = ImageActivity.this.b("com.tencent.plus.blue_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, b2);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, b);
            stateListDrawable.addState(View.ENABLED_STATE_SET, b);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, b);
            stateListDrawable.addState(View.EMPTY_STATE_SET, b3);
            button.setBackgroundDrawable(stateListDrawable);
        }

        public void b(Button button) {
            StateListDrawable stateListDrawable = new StateListDrawable();
            Drawable b = ImageActivity.this.b("com.tencent.plus.gray_normal.png");
            Drawable b2 = ImageActivity.this.b("com.tencent.plus.gray_down.png");
            Drawable b3 = ImageActivity.this.b("com.tencent.plus.gray_disable.png");
            stateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, b2);
            stateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, b);
            stateListDrawable.addState(View.ENABLED_STATE_SET, b);
            stateListDrawable.addState(View.FOCUSED_STATE_SET, b);
            stateListDrawable.addState(View.EMPTY_STATE_SET, b3);
            button.setBackgroundDrawable(stateListDrawable);
        }
    }

    private void b() {
        try {
            this.s = a(this.r);
        } catch (IOException e) {
            e.printStackTrace();
            a(Constants.MSG_IMAGE_ERROR, 1);
            a(-5, null, Constants.MSG_IMAGE_ERROR, e.getMessage());
            d();
        }
        if (this.s == null) {
            throw new IOException("cannot read picture: '" + this.r + "'!");
        }
        this.e.setImageBitmap(this.s);
        this.f.setOnClickListener(this.t);
        this.g.setOnClickListener(this.f40u);
        this.a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.tencent.connect.avatar.ImageActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ImageActivity.this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                ImageActivity.this.q = ImageActivity.this.h.a();
                ImageActivity.this.e.a(ImageActivity.this.q);
            }
        });
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setRequestedOrientation(1);
        setContentView(a());
        this.d = new Handler();
        Bundle bundleExtra = getIntent().getBundleExtra(Constants.KEY_PARAMS);
        this.r = bundleExtra.getString("picture");
        this.c = bundleExtra.getString("return_activity");
        String string = bundleExtra.getString("appid");
        String string2 = bundleExtra.getString(Constants.PARAM_ACCESS_TOKEN);
        long j = bundleExtra.getLong(Constants.PARAM_EXPIRES_IN);
        String string3 = bundleExtra.getString("openid");
        this.n = bundleExtra.getInt("exitAnim");
        this.b = new QQToken(string);
        this.b.setAccessToken(string2, ((j - System.currentTimeMillis()) / 1000) + "");
        this.b.setOpenId(string3);
        b();
        e();
        this.m = System.currentTimeMillis();
        a("10653", 0L);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        setResult(0);
        d();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.e.setImageBitmap(null);
        if (this.s != null && !this.s.isRecycled()) {
            this.s.recycle();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Matrix imageMatrix = this.e.getImageMatrix();
        float[] fArr = new float[9];
        imageMatrix.getValues(fArr);
        float f = fArr[2];
        float f2 = fArr[5];
        float f3 = fArr[0];
        float width = 640.0f / this.q.width();
        int i = (int) ((this.q.left - f) / f3);
        if (i < 0) {
            i = 0;
        }
        int i2 = (int) ((this.q.top - f2) / f3);
        if (i2 < 0) {
            i2 = 0;
        }
        Matrix matrix = new Matrix();
        matrix.set(imageMatrix);
        matrix.postScale(width, width);
        int i3 = (int) (650.0f / f3);
        try {
            Bitmap createBitmap = Bitmap.createBitmap(this.s, i, i2, Math.min(this.s.getWidth() - i, i3), Math.min(this.s.getHeight() - i2, i3), matrix, true);
            Bitmap createBitmap2 = Bitmap.createBitmap(createBitmap, 0, 0, (int) ImageUtils.SCALE_IMAGE_WIDTH, (int) ImageUtils.SCALE_IMAGE_WIDTH);
            createBitmap.recycle();
            a(createBitmap2);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            a(Constants.MSG_IMAGE_ERROR, 1);
            a(-5, null, Constants.MSG_IMAGE_ERROR, e.getMessage());
            d();
        }
    }

    private void a(Bitmap bitmap) {
        new QQAvatarImp(this.b).setAvator(bitmap, this.v);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: ProGuard */
    /* loaded from: classes2.dex */
    public class QQAvatarImp extends BaseApi {
        public QQAvatarImp(QQToken qQToken) {
            super(qQToken);
        }

        public void setAvator(Bitmap bitmap, IUiListener iUiListener) {
            Bundle composeCGIParams = composeCGIParams();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 40, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            bitmap.recycle();
            BaseApi.TempRequestListener tempRequestListener = new BaseApi.TempRequestListener(iUiListener);
            composeCGIParams.putByteArray("picture", byteArray);
            HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/set_user_face", composeCGIParams, "POST", tempRequestListener);
            d.a().a(this.mToken.getOpenId(), this.mToken.getAppId(), Constants.VIA_SET_AVATAR_SUCCEED, "12", "19", "0");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final int i) {
        this.d.post(new Runnable() { // from class: com.tencent.connect.avatar.ImageActivity.4
            @Override // java.lang.Runnable
            public void run() {
                ImageActivity.this.b(str, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, int i) {
        Toast makeText = Toast.makeText(this, str, 1);
        LinearLayout linearLayout = (LinearLayout) makeText.getView();
        ((TextView) linearLayout.getChildAt(0)).setPadding(8, 0, 0, 0);
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 16.0f), a.a(this, 16.0f)));
        if (i == 0) {
            imageView.setImageDrawable(b("com.tencent.plus.ic_success.png"));
        } else {
            imageView.setImageDrawable(b("com.tencent.plus.ic_error.png"));
        }
        linearLayout.addView(imageView, 0);
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        makeText.setView(linearLayout);
        makeText.setGravity(17, 0, 0);
        makeText.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.putExtra(Constants.KEY_ERROR_CODE, i);
        intent.putExtra(Constants.KEY_ERROR_MSG, str2);
        intent.putExtra(Constants.KEY_ERROR_DETAIL, str3);
        intent.putExtra(Constants.KEY_RESPONSE, str);
        setResult(-1, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        finish();
        if (this.n != 0) {
            overridePendingTransition(0, this.n);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.k++;
        new UserInfo(this, this.b).getUserInfo(this.w);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        String d = d(str);
        if (!"".equals(d)) {
            this.i.setText(d);
            this.i.setVisibility(0);
        }
    }

    private String d(String str) {
        return str.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;", com.alipay.sdk.sys.a.b);
    }

    public void a(String str, long j) {
        Util.reportBernoulli(this, str, j, this.b.getAppId());
    }
}
