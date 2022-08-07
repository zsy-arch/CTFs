package com.tencent.smtt.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.TbsReaderView;
import com.tencent.smtt.sdk.TbsVideoCacheTask;
import com.tencent.smtt.sdk.WebView;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class d {

    /* renamed from: b  reason: collision with root package name */
    public static DexClassLoader f1508b;

    /* renamed from: c  reason: collision with root package name */
    public static Looper f1509c;

    /* renamed from: d  reason: collision with root package name */
    public static d f1510d;

    /* renamed from: a  reason: collision with root package name */
    public String f1511a;

    /* loaded from: classes.dex */
    public interface a {
        void a();

        void a(int i);

        void a(Throwable th);
    }

    public d(Context context) {
        this.f1511a = BuildConfig.FLAVOR;
        this.f1511a = context.getDir("debugtbs", 0).getAbsolutePath() + File.separator + "plugin";
    }

    public static d a(Context context) {
        if (f1510d == null) {
            f1510d = new d(context);
        }
        return f1510d;
    }

    @SuppressLint({"NewApi"})
    public static void a(final String str, final a aVar) {
        new Thread() { // from class: com.tencent.smtt.utils.d.2
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                Exception e2;
                int contentLength;
                InputStream inputStream;
                try {
                    InputStream inputStream2 = null;
                    r0 = null;
                    FileOutputStream fileOutputStream = null;
                    inputStream2 = null;
                    try {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("https://soft.tbs.imtt.qq.com/17421/tbs_res_imtt_tbs_DebugPlugin_DebugPlugin.tbs").openConnection();
                        contentLength = httpURLConnection.getContentLength();
                        httpURLConnection.setConnectTimeout(TbsReaderView.ReaderCallback.GET_BAR_ANIMATING);
                        httpURLConnection.connect();
                        inputStream = httpURLConnection.getInputStream();
                    } catch (Exception e3) {
                        e2 = e3;
                        fileOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                    }
                    try {
                        fileOutputStream = FileUtil.d(new File(str));
                        byte[] bArr = new byte[8192];
                        int i = 0;
                        while (true) {
                            int read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                            aVar.a((i * 100) / contentLength);
                        }
                        aVar.a();
                        try {
                            inputStream.close();
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        fileOutputStream.close();
                    } catch (Exception e5) {
                        e2 = e5;
                        inputStream2 = inputStream;
                        try {
                            e2.printStackTrace();
                            aVar.a(e2);
                            try {
                                inputStream2.close();
                            } catch (Exception e6) {
                                e6.printStackTrace();
                            }
                            fileOutputStream.close();
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                inputStream2.close();
                            } catch (Exception e7) {
                                e7.printStackTrace();
                            }
                            try {
                                fileOutputStream.close();
                            } catch (Exception e8) {
                                e8.printStackTrace();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream2 = inputStream;
                        inputStream2.close();
                        fileOutputStream.close();
                        throw th;
                    }
                } catch (Exception e9) {
                    while (true) {
                        e9.printStackTrace();
                        return;
                    }
                }
            }
        }.start();
    }

    public void a(final String str, final WebView webView, final Context context) {
        final RelativeLayout relativeLayout = new RelativeLayout(context);
        final TextView textView = new TextView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        textView.setText("加载中，请稍后...");
        relativeLayout.addView(textView, layoutParams);
        webView.addView(relativeLayout, new FrameLayout.LayoutParams(-1, -1));
        String str2 = this.f1511a + File.separator + "DebugPlugin.tbs";
        FileUtil.a(new File(str2), false);
        a(str2, new a() { // from class: com.tencent.smtt.utils.d.1
            @Override // com.tencent.smtt.utils.d.a
            public void a() {
                webView.post(new Runnable() { // from class: com.tencent.smtt.utils.d.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "下载成功", 0).show();
                        relativeLayout.setVisibility(4);
                        AnonymousClass1 r0 = AnonymousClass1.this;
                        d.this.a(str, webView, context, d.f1509c);
                    }
                });
            }

            @Override // com.tencent.smtt.utils.d.a
            public void a(final int i) {
                webView.post(new Runnable() { // from class: com.tencent.smtt.utils.d.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TextView textView2 = textView;
                        StringBuilder a2 = e.a.a.a.a.a("已下载");
                        a2.append(i);
                        a2.append("%");
                        textView2.setText(a2.toString());
                    }
                });
            }

            @Override // com.tencent.smtt.utils.d.a
            public void a(Throwable th) {
                webView.post(new Runnable() { // from class: com.tencent.smtt.utils.d.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        Toast.makeText(context, "下载失败，请检查网络", 0).show();
                    }
                });
            }
        });
    }

    @SuppressLint({"NewApi"})
    public void a(String str, WebView webView, Context context, Looper looper) {
        TbsLog.i("debugtbs", "showPluginView -- url: " + str + "; webview: " + webView + "; context: " + context);
        String str2 = this.f1511a + File.separator + "DebugPlugin.apk";
        File file = new File(this.f1511a + File.separator + "DebugPlugin.tbs");
        File file2 = new File(str2);
        f1509c = looper;
        if (file.exists()) {
            file2.delete();
            file.renameTo(file2);
        }
        if (!file2.exists()) {
            TbsLog.i("debugtbs", "showPluginView - going to download plugin...");
            a(str, webView, context);
            return;
        }
        try {
            String a2 = b.a(context, true, new File(str2));
            if (!"308203773082025fa003020102020448bb959d300d06092a864886f70d01010b0500306b310b300906035504061302636e31123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e3110300e060355040a130754656e63656e74310c300a060355040b13034d4947311530130603550403130c4d696e676875204875616e673020170d3136303532313039353730335a180f32303731303232323039353730335a306b310b300906035504061302636e31123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e3110300e060355040a130754656e63656e74310c300a060355040b13034d4947311530130603550403130c4d696e676875204875616e6730820122300d06092a864886f70d01010105000382010f003082010a02820101008c58deabefe95f699c6322f9a75620873b490d26520c7267eb8382a91da625a5876b2bd617116eb40b371c4f88c988c1ba73052caaa9964873c94b7755c3429fca47a6677229fb2e72908d3b17df82f1ebe70447b94c1e5b0a763dad8948312180322657325306f01e423e0409ef3a59e5c0e0b9c765a2322699a2dec2d4dbe58ec15f41752516192169d9596169f5bf08eaf8aab9893240ad679e82fc92b97d2ae98b28021dc5a752f0a69437ea603c541e6753cea52dbc8e8043fe21fd5da46066c92e0714905dfad3116f35aca52b13871c57481459aa4ca255a6482ba972bd17af90d0d2c21a57ef65376bbd4ce7078e6047060640669f3867fdc69fbb750203010001a321301f301d0603551d0e0416041450fb9b6362e829797b1b29ca55e6d5e082e93ff3300d06092a864886f70d01010b050003820101004952ffbfba7c00ee9b84f44b05ec62bc2400dc769fb2e83f80395e3fbb54e44d56e16527413d144f42bf8f21fa443bc42a7a732de9d5124df906c6d728e75ca94eefc918080876bd3ce6cb5f7f2d9cc8d8e708033afc1295c7f347fb2d2098be2e4a79220e9552171d5b5f8f59cff4c6478cc41dce24cbe942305757488d37659d3265838ee54ebe44fccbd1bec93d809f950034f5ef292f20179554d22f5856c03b4d44997fcb9b3579e16a49218fce0e2e6bfe1fd4aa0ab39f548344c244c171c203baff1a730883aaf4506b6865a45c3c9aba40c6326d4152b6ce09cc058864bec1d6422e83dad9496b83fb252b4bfb30d3a6badf996099793e11f9af618d".equals(a2)) {
                TbsLog.e("debugtbs", "verifyPlugin apk: " + str2 + " signature failed - sig: " + a2);
                Toast.makeText(context, "插件校验失败，请重试", 0).show();
                file.delete();
                file2.delete();
                return;
            }
            String str3 = this.f1511a + File.separator + "opt";
            File file3 = new File(str3);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            if (f1508b == null) {
                f1508b = new DexClassLoader(str2, str3, null, context.getClassLoader());
            }
            HashMap hashMap = new HashMap();
            hashMap.put(TbsVideoCacheTask.KEY_VIDEO_CACHE_PARAM_URL, str);
            StringBuilder sb = new StringBuilder();
            sb.append(BuildConfig.FLAVOR);
            WebView.getTbsSDKVersion(context);
            sb.append(43939);
            hashMap.put("tbs_version", sb.toString());
            hashMap.put("tbs_core_version", BuildConfig.FLAVOR + WebView.getTbsCoreVersion(context));
            if (f1509c != null) {
                hashMap.put("looper", looper);
            }
            Object newInstance = f1508b.loadClass("com.tencent.tbs.debug.plugin.DebugView").getConstructor(Context.class, Map.class).newInstance(context, hashMap);
            if (newInstance instanceof FrameLayout) {
                FrameLayout frameLayout = (FrameLayout) newInstance;
                webView.addView(frameLayout, new FrameLayout.LayoutParams(-1, -1));
                TbsLog.i("debugtbs", "show " + frameLayout + " successful in " + webView);
                return;
            }
            TbsLog.e("debugtbs", "get debugview failure: " + newInstance);
        } catch (Exception e2) {
            FileUtil.a(file2, false);
            e2.printStackTrace();
        }
    }
}
