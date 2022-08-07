package com.tencent.smtt.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsListener;

/* renamed from: com.tencent.smtt.sdk.r  reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0072r extends FrameLayout implements MediaPlayer.OnErrorListener {

    /* renamed from: a  reason: collision with root package name */
    public Object f1396a;

    /* renamed from: b  reason: collision with root package name */
    public t f1397b;

    /* renamed from: c  reason: collision with root package name */
    public VideoView f1398c;

    /* renamed from: d  reason: collision with root package name */
    public Context f1399d;

    /* renamed from: e  reason: collision with root package name */
    public String f1400e;

    public C0072r(Context context) {
        super(context.getApplicationContext());
        this.f1399d = null;
        this.f1399d = context;
    }

    private void b(Bundle bundle, Object obj) {
        boolean z;
        a();
        if (b()) {
            bundle.putInt("callMode", bundle.getInt("callMode"));
            z = this.f1397b.a(this.f1396a, bundle, this, obj);
        } else {
            z = false;
        }
        if (!z) {
            VideoView videoView = this.f1398c;
            if (videoView != null) {
                videoView.stopPlayback();
            }
            if (this.f1398c == null) {
                this.f1398c = new VideoView(getContext());
            }
            this.f1400e = bundle.getString("videoUrl");
            this.f1398c.setVideoURI(Uri.parse(this.f1400e));
            this.f1398c.setOnErrorListener(this);
            Intent intent = new Intent("com.tencent.smtt.tbs.video.PLAY");
            intent.addFlags(268435456);
            Context applicationContext = getContext().getApplicationContext();
            intent.setPackage(applicationContext.getPackageName());
            applicationContext.startActivity(intent);
        }
    }

    public void a() {
        setBackgroundColor(WebView.NIGHT_MODE_COLOR);
        if (this.f1397b == null) {
            d.a(true).a(getContext().getApplicationContext(), false, false);
            s a2 = d.a(true).a();
            DexLoader dexLoader = null;
            if (a2 != null) {
                dexLoader = a2.b();
            }
            if (dexLoader != null && QbSdk.canLoadVideo(getContext())) {
                this.f1397b = new t(dexLoader);
            }
        }
        t tVar = this.f1397b;
        if (tVar != null && this.f1396a == null) {
            this.f1396a = tVar.a(getContext().getApplicationContext());
        }
    }

    public void a(Activity activity) {
        VideoView videoView;
        if (!b() && (videoView = this.f1398c) != null) {
            if (videoView.getParent() == null) {
                Window window = activity.getWindow();
                FrameLayout frameLayout = (FrameLayout) window.getDecorView();
                window.addFlags(1024);
                window.addFlags(TbsListener.ErrorCode.DOWNLOAD_INTERRUPT);
                frameLayout.setBackgroundColor(WebView.NIGHT_MODE_COLOR);
                MediaController mediaController = new MediaController(activity);
                mediaController.setMediaPlayer(this.f1398c);
                this.f1398c.setMediaController(mediaController);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                layoutParams.gravity = 17;
                frameLayout.addView(this.f1398c, layoutParams);
            }
            int i = Build.VERSION.SDK_INT;
            this.f1398c.start();
        }
    }

    public void a(Activity activity, int i) {
        VideoView videoView;
        VideoView videoView2;
        if (i == 3 && !b() && (videoView2 = this.f1398c) != null) {
            videoView2.pause();
        }
        if (i == 4) {
            this.f1399d = null;
            if (!b() && (videoView = this.f1398c) != null) {
                videoView.stopPlayback();
                this.f1398c = null;
            }
        }
        if (i == 2 && !b()) {
            this.f1399d = activity;
            a(activity);
        }
        if (b()) {
            this.f1397b.a(this.f1396a, activity, i);
        }
    }

    public void a(Bundle bundle, Object obj) {
        b(bundle, obj);
    }

    public boolean b() {
        return (this.f1397b == null || this.f1396a == null) ? false : true;
    }

    public void c() {
        if (b()) {
            this.f1397b.a(this.f1396a);
        }
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        try {
            if (this.f1399d instanceof Activity) {
                Activity activity = (Activity) this.f1399d;
                if (!activity.isFinishing()) {
                    activity.finish();
                }
            }
            Context context = getContext();
            if (context != null) {
                Toast.makeText(context, "播放失败，请选择其它播放器播放", 1).show();
                Context applicationContext = context.getApplicationContext();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(268435456);
                intent.setDataAndType(Uri.parse(this.f1400e), "video/*");
                applicationContext.startActivity(intent);
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }
}
