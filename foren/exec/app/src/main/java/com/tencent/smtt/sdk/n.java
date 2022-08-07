package com.tencent.smtt.sdk;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsMediaPlayer;

/* loaded from: classes.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    public DexLoader f1385a;

    /* renamed from: b  reason: collision with root package name */
    public Object f1386b;

    public n(DexLoader dexLoader, Context context) {
        this.f1385a = null;
        this.f1386b = null;
        this.f1385a = dexLoader;
        this.f1386b = this.f1385a.newInstance("com.tencent.tbs.player.TbsMediaPlayerProxy", new Class[]{Context.class}, context);
    }

    public void a(float f) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setVolume", new Class[]{Float.TYPE}, Float.valueOf(f));
    }

    public void a(int i) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "subtitle", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    public void a(long j) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "seek", new Class[]{Long.TYPE}, Long.valueOf(j));
    }

    public void a(SurfaceTexture surfaceTexture) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setSurfaceTexture", new Class[]{SurfaceTexture.class}, surfaceTexture);
    }

    public void a(TbsMediaPlayer.TbsMediaPlayerListener tbsMediaPlayerListener) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "setPlayerListener", new Class[]{Object.class}, tbsMediaPlayerListener);
    }

    public void a(String str, Bundle bundle) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "startPlay", new Class[]{String.class, Bundle.class}, str, bundle);
    }

    public boolean a() {
        return this.f1386b != null;
    }

    public float b() {
        Float f = (Float) this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "getVolume", new Class[0], new Object[0]);
        if (f != null) {
            return f.floatValue();
        }
        return 0.0f;
    }

    public void b(int i) {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "audio", new Class[]{Integer.TYPE}, Integer.valueOf(i));
    }

    public void c() {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "pause", new Class[0], new Object[0]);
    }

    public void d() {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "play", new Class[0], new Object[0]);
    }

    public void e() {
        this.f1385a.invokeMethod(this.f1386b, "com.tencent.tbs.player.TbsMediaPlayerProxy", "close", new Class[0], new Object[0]);
    }
}
