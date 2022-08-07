package com.tencent.smtt.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import com.tencent.smtt.export.external.interfaces.IX5WebSettings;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.i;

/* loaded from: classes.dex */
public class WebSettings {
    public static final int LOAD_CACHE_ELSE_NETWORK = 1;
    public static final int LOAD_CACHE_ONLY = 3;
    public static final int LOAD_DEFAULT = -1;
    public static final int LOAD_NORMAL = 0;
    public static final int LOAD_NO_CACHE = 2;

    /* renamed from: a  reason: collision with root package name */
    public IX5WebSettings f1288a;

    /* renamed from: b  reason: collision with root package name */
    public android.webkit.WebSettings f1289b;

    /* renamed from: c  reason: collision with root package name */
    public boolean f1290c;

    /* loaded from: classes.dex */
    public enum LayoutAlgorithm {
        NORMAL,
        SINGLE_COLUMN,
        NARROW_COLUMNS
    }

    /* loaded from: classes.dex */
    public enum PluginState {
        ON,
        ON_DEMAND,
        OFF
    }

    /* loaded from: classes.dex */
    public enum RenderPriority {
        NORMAL,
        HIGH,
        LOW
    }

    /* loaded from: classes.dex */
    public enum TextSize {
        SMALLEST(50),
        SMALLER(75),
        NORMAL(100),
        LARGER(TbsListener.ErrorCode.DOWNLOAD_THROWABLE),
        LARGEST(150);
        
        public int value;

        TextSize(int i) {
            this.value = i;
        }
    }

    /* loaded from: classes.dex */
    public enum ZoomDensity {
        FAR(150),
        MEDIUM(100),
        CLOSE(75);
        
        public int value;

        ZoomDensity(int i) {
            this.value = i;
        }
    }

    public WebSettings(android.webkit.WebSettings webSettings) {
        this.f1288a = null;
        this.f1289b = null;
        this.f1290c = false;
        this.f1288a = null;
        this.f1289b = webSettings;
        this.f1290c = false;
    }

    public WebSettings(IX5WebSettings iX5WebSettings) {
        this.f1288a = null;
        this.f1289b = null;
        this.f1290c = false;
        this.f1288a = iX5WebSettings;
        this.f1289b = null;
        this.f1290c = true;
    }

    @TargetApi(17)
    public static String getDefaultUserAgent(Context context) {
        if (u.a().b()) {
            return u.a().c().i(context);
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a((Class<?>) android.webkit.WebSettings.class, "getDefaultUserAgent", (Class<?>[]) new Class[]{Context.class}, context);
        if (a2 == null) {
            return null;
        }
        return (String) a2;
    }

    @Deprecated
    public boolean enableSmoothTransition() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.enableSmoothTransition();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(webSettings, "enableSmoothTransition");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(11)
    public boolean getAllowContentAccess() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getAllowContentAccess();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(webSettings, "getAllowContentAccess");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(3)
    public boolean getAllowFileAccess() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getAllowFileAccess();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.getAllowFileAccess();
    }

    public synchronized boolean getBlockNetworkImage() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getBlockNetworkImage();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getBlockNetworkImage();
        }
    }

    @TargetApi(8)
    public synchronized boolean getBlockNetworkLoads() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getBlockNetworkLoads();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            int i = Build.VERSION.SDK_INT;
            return this.f1289b.getBlockNetworkLoads();
        }
    }

    @TargetApi(3)
    public boolean getBuiltInZoomControls() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getBuiltInZoomControls();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.getBuiltInZoomControls();
    }

    public int getCacheMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getCacheMode();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return 0;
        }
        return webSettings.getCacheMode();
    }

    public synchronized String getCursiveFontFamily() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getCursiveFontFamily();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getCursiveFontFamily();
        }
    }

    @TargetApi(5)
    public synchronized boolean getDatabaseEnabled() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getDatabaseEnabled();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getDatabaseEnabled();
        }
    }

    @TargetApi(5)
    public synchronized String getDatabasePath() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getDatabasePath();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getDatabasePath();
        }
    }

    public synchronized int getDefaultFixedFontSize() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getDefaultFixedFontSize();
        } else if (this.f1290c || this.f1289b == null) {
            return 0;
        } else {
            return this.f1289b.getDefaultFixedFontSize();
        }
    }

    public synchronized int getDefaultFontSize() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getDefaultFontSize();
        } else if (this.f1290c || this.f1289b == null) {
            return 0;
        } else {
            return this.f1289b.getDefaultFontSize();
        }
    }

    public synchronized String getDefaultTextEncodingName() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getDefaultTextEncodingName();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getDefaultTextEncodingName();
        }
    }

    @TargetApi(7)
    public ZoomDensity getDefaultZoom() {
        android.webkit.WebSettings webSettings;
        String name;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            name = iX5WebSettings.getDefaultZoom().name();
        } else if (this.f1290c || (webSettings = this.f1289b) == null) {
            return null;
        } else {
            name = webSettings.getDefaultZoom().name();
        }
        return ZoomDensity.valueOf(name);
    }

    @TargetApi(11)
    public boolean getDisplayZoomControls() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getDisplayZoomControls();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(webSettings, "getDisplayZoomControls");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(7)
    public synchronized boolean getDomStorageEnabled() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getDomStorageEnabled();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getDomStorageEnabled();
        }
    }

    public synchronized String getFantasyFontFamily() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getFantasyFontFamily();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getFantasyFontFamily();
        }
    }

    public synchronized String getFixedFontFamily() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getFixedFontFamily();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getFixedFontFamily();
        }
    }

    public synchronized boolean getJavaScriptCanOpenWindowsAutomatically() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getJavaScriptCanOpenWindowsAutomatically();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getJavaScriptCanOpenWindowsAutomatically();
        }
    }

    public synchronized boolean getJavaScriptEnabled() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getJavaScriptEnabled();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getJavaScriptEnabled();
        }
    }

    public synchronized LayoutAlgorithm getLayoutAlgorithm() {
        if (this.f1290c && this.f1288a != null) {
            return LayoutAlgorithm.valueOf(this.f1288a.getLayoutAlgorithm().name());
        } else if (this.f1290c || this.f1289b == null) {
            return null;
        } else {
            return LayoutAlgorithm.valueOf(this.f1289b.getLayoutAlgorithm().name());
        }
    }

    public boolean getLightTouchEnabled() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getLightTouchEnabled();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.getLightTouchEnabled();
    }

    @TargetApi(7)
    public boolean getLoadWithOverviewMode() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getLoadWithOverviewMode();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.getLoadWithOverviewMode();
    }

    public synchronized boolean getLoadsImagesAutomatically() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getLoadsImagesAutomatically();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getLoadsImagesAutomatically();
        }
    }

    @TargetApi(17)
    public boolean getMediaPlaybackRequiresUserGesture() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getMediaPlaybackRequiresUserGesture();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        int i = Build.VERSION.SDK_INT;
        Object a2 = i.a(webSettings, "getMediaPlaybackRequiresUserGesture");
        if (a2 == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized int getMinimumFontSize() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getMinimumFontSize();
        } else if (this.f1290c || this.f1289b == null) {
            return 0;
        } else {
            return this.f1289b.getMinimumFontSize();
        }
    }

    public synchronized int getMinimumLogicalFontSize() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getMinimumLogicalFontSize();
        } else if (this.f1290c || this.f1289b == null) {
            return 0;
        } else {
            return this.f1289b.getMinimumLogicalFontSize();
        }
    }

    public synchronized int getMixedContentMode() {
        int i = -1;
        if (!this.f1290c || this.f1288a == null) {
            int i2 = Build.VERSION.SDK_INT;
            Object a2 = i.a(this.f1289b, "getMixedContentMode", new Class[0], new Object[0]);
            if (a2 != null) {
                i = ((Integer) a2).intValue();
            }
            return i;
        }
        return this.f1288a.getMixedContentMode();
    }

    public boolean getNavDump() {
        android.webkit.WebSettings webSettings;
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getNavDump();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null || (a2 = i.a(webSettings, "getNavDump")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    @TargetApi(8)
    @Deprecated
    public synchronized PluginState getPluginState() {
        if (this.f1290c && this.f1288a != null) {
            return PluginState.valueOf(this.f1288a.getPluginState().name());
        } else if (this.f1290c || this.f1289b == null) {
            return null;
        } else {
            int i = Build.VERSION.SDK_INT;
            Object a2 = i.a(this.f1289b, "getPluginState");
            if (a2 == null) {
                return null;
            }
            return PluginState.valueOf(((WebSettings.PluginState) a2).name());
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized boolean getPluginsEnabled() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getPluginsEnabled();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            int i = Build.VERSION.SDK_INT;
            int i2 = Build.VERSION.SDK_INT;
            return false;
        }
    }

    @Deprecated
    public synchronized String getPluginsPath() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getPluginsPath();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            int i = Build.VERSION.SDK_INT;
            return BuildConfig.FLAVOR;
        }
    }

    public synchronized String getSansSerifFontFamily() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getSansSerifFontFamily();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getSansSerifFontFamily();
        }
    }

    public boolean getSaveFormData() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getSaveFormData();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.getSaveFormData();
    }

    public boolean getSavePassword() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getSavePassword();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.getSavePassword();
    }

    public synchronized String getSerifFontFamily() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getSerifFontFamily();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getSerifFontFamily();
        }
    }

    public synchronized String getStandardFontFamily() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getStandardFontFamily();
        } else if (this.f1290c || this.f1289b == null) {
            return BuildConfig.FLAVOR;
        } else {
            return this.f1289b.getStandardFontFamily();
        }
    }

    public TextSize getTextSize() {
        android.webkit.WebSettings webSettings;
        String name;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            name = iX5WebSettings.getTextSize().name();
        } else if (this.f1290c || (webSettings = this.f1289b) == null) {
            return null;
        } else {
            name = webSettings.getTextSize().name();
        }
        return TextSize.valueOf(name);
    }

    @TargetApi(14)
    public synchronized int getTextZoom() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getTextZoom();
        } else if (this.f1290c || this.f1289b == null) {
            return 0;
        } else {
            int i = Build.VERSION.SDK_INT;
            try {
                return this.f1289b.getTextZoom();
            } catch (Exception unused) {
                Object a2 = i.a(this.f1289b, "getTextZoom");
                if (a2 == null) {
                    return 0;
                }
                return ((Integer) a2).intValue();
            }
        }
    }

    @Deprecated
    public boolean getUseWebViewBackgroundForOverscrollBackground() {
        android.webkit.WebSettings webSettings;
        Object a2;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.getUseWebViewBackgroundForOverscrollBackground();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null || (a2 = i.a(webSettings, "getUseWebViewBackgroundForOverscrollBackground")) == null) {
            return false;
        }
        return ((Boolean) a2).booleanValue();
    }

    public synchronized boolean getUseWideViewPort() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.getUseWideViewPort();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.getUseWideViewPort();
        }
    }

    @TargetApi(3)
    public String getUserAgentString() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        return (!this.f1290c || (iX5WebSettings = this.f1288a) == null) ? (this.f1290c || (webSettings = this.f1289b) == null) ? BuildConfig.FLAVOR : webSettings.getUserAgentString() : iX5WebSettings.getUserAgentString();
    }

    @TargetApi(11)
    public void setAllowContentAccess(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAllowContentAccess(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            int i = Build.VERSION.SDK_INT;
            i.a(webSettings, "setAllowContentAccess", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(3)
    public void setAllowFileAccess(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAllowFileAccess(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setAllowFileAccess(z);
        }
    }

    @TargetApi(16)
    public void setAllowFileAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAllowFileAccessFromFileURLs(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            i.a(webSettings, "setAllowFileAccessFromFileURLs", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(16)
    public void setAllowUniversalAccessFromFileURLs(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAllowUniversalAccessFromFileURLs(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            i.a(webSettings, "setAllowUniversalAccessFromFileURLs", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(7)
    public void setAppCacheEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAppCacheEnabled(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setAppCacheEnabled(z);
        }
    }

    @TargetApi(7)
    public void setAppCacheMaxSize(long j) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAppCacheMaxSize(j);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setAppCacheMaxSize(j);
        }
    }

    @TargetApi(7)
    public void setAppCachePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setAppCachePath(str);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setAppCachePath(str);
        }
    }

    public void setBlockNetworkImage(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setBlockNetworkImage(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setBlockNetworkImage(z);
        }
    }

    @TargetApi(8)
    public synchronized void setBlockNetworkLoads(boolean z) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setBlockNetworkLoads(z);
        } else if (!this.f1290c && this.f1289b != null) {
            int i = Build.VERSION.SDK_INT;
            this.f1289b.setBlockNetworkLoads(z);
        }
    }

    @TargetApi(3)
    public void setBuiltInZoomControls(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setBuiltInZoomControls(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setBuiltInZoomControls(z);
        }
    }

    public void setCacheMode(int i) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setCacheMode(i);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setCacheMode(i);
        }
    }

    public synchronized void setCursiveFontFamily(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setCursiveFontFamily(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setCursiveFontFamily(str);
        }
    }

    @TargetApi(5)
    public void setDatabaseEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setDatabaseEnabled(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setDatabaseEnabled(z);
        }
    }

    @TargetApi(5)
    @Deprecated
    public void setDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setDatabasePath(str);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            i.a(webSettings, "setDatabasePath", new Class[]{String.class}, str);
        }
    }

    public synchronized void setDefaultFixedFontSize(int i) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setDefaultFixedFontSize(i);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setDefaultFixedFontSize(i);
        }
    }

    public synchronized void setDefaultFontSize(int i) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setDefaultFontSize(i);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setDefaultFontSize(i);
        }
    }

    public synchronized void setDefaultTextEncodingName(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setDefaultTextEncodingName(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setDefaultTextEncodingName(str);
        }
    }

    @TargetApi(7)
    public void setDefaultZoom(ZoomDensity zoomDensity) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setDefaultZoom(IX5WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.valueOf(zoomDensity.name()));
        }
    }

    @TargetApi(11)
    public void setDisplayZoomControls(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setDisplayZoomControls(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            int i = Build.VERSION.SDK_INT;
            i.a(webSettings, "setDisplayZoomControls", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @TargetApi(7)
    public void setDomStorageEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setDomStorageEnabled(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setDomStorageEnabled(z);
        }
    }

    @TargetApi(11)
    @Deprecated
    public void setEnableSmoothTransition(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setEnableSmoothTransition(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            int i = Build.VERSION.SDK_INT;
            i.a(webSettings, "setEnableSmoothTransition", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setFantasyFontFamily(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setFantasyFontFamily(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setFantasyFontFamily(str);
        }
    }

    public synchronized void setFixedFontFamily(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setFixedFontFamily(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setFixedFontFamily(str);
        }
    }

    @TargetApi(5)
    public void setGeolocationDatabasePath(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setGeolocationDatabasePath(str);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setGeolocationDatabasePath(str);
        }
    }

    @TargetApi(5)
    public void setGeolocationEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setGeolocationEnabled(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setGeolocationEnabled(z);
        }
    }

    public synchronized void setJavaScriptCanOpenWindowsAutomatically(boolean z) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setJavaScriptCanOpenWindowsAutomatically(z);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setJavaScriptCanOpenWindowsAutomatically(z);
        }
    }

    @Deprecated
    public void setJavaScriptEnabled(boolean z) {
        try {
            if (this.f1290c && this.f1288a != null) {
                this.f1288a.setJavaScriptEnabled(z);
            } else if (!this.f1290c && this.f1289b != null) {
                this.f1289b.setJavaScriptEnabled(z);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void setLayoutAlgorithm(LayoutAlgorithm layoutAlgorithm) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setLayoutAlgorithm(IX5WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.valueOf(layoutAlgorithm.name()));
        }
    }

    public void setLightTouchEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setLightTouchEnabled(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setLightTouchEnabled(z);
        }
    }

    @TargetApi(7)
    public void setLoadWithOverviewMode(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setLoadWithOverviewMode(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setLoadWithOverviewMode(z);
        }
    }

    public void setLoadsImagesAutomatically(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setLoadsImagesAutomatically(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setLoadsImagesAutomatically(z);
        }
    }

    @TargetApi(17)
    public void setMediaPlaybackRequiresUserGesture(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setMediaPlaybackRequiresUserGesture(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            int i = Build.VERSION.SDK_INT;
            i.a(webSettings, "setMediaPlaybackRequiresUserGesture", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public synchronized void setMinimumFontSize(int i) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setMinimumFontSize(i);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setMinimumFontSize(i);
        }
    }

    public synchronized void setMinimumLogicalFontSize(int i) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setMinimumLogicalFontSize(i);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setMinimumLogicalFontSize(i);
        }
    }

    @TargetApi(21)
    public void setMixedContentMode(int i) {
        android.webkit.WebSettings webSettings;
        if ((!this.f1290c || this.f1288a == null) && !this.f1290c && (webSettings = this.f1289b) != null) {
            int i2 = Build.VERSION.SDK_INT;
            i.a(webSettings, "setMixedContentMode", new Class[]{Integer.TYPE}, Integer.valueOf(i));
        }
    }

    public void setNavDump(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setNavDump(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            i.a(webSettings, "setNavDump", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setNeedInitialFocus(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setNeedInitialFocus(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setNeedInitialFocus(z);
        }
    }

    @TargetApi(8)
    @Deprecated
    public synchronized void setPluginState(PluginState pluginState) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setPluginState(IX5WebSettings.PluginState.valueOf(pluginState.name()));
        } else if (!this.f1290c && this.f1289b != null) {
            int i = Build.VERSION.SDK_INT;
            i.a(this.f1289b, "setPluginState", new Class[]{WebSettings.PluginState.class}, WebSettings.PluginState.valueOf(pluginState.name()));
        }
    }

    @Deprecated
    public void setPluginsEnabled(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setPluginsEnabled(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            i.a(webSettings, "setPluginsEnabled", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    @Deprecated
    public synchronized void setPluginsPath(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setPluginsPath(str);
        } else if (!this.f1290c && this.f1289b != null) {
            i.a(this.f1289b, "setPluginsPath", new Class[]{String.class}, str);
        }
    }

    public void setRenderPriority(RenderPriority renderPriority) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setRenderPriority(IX5WebSettings.RenderPriority.valueOf(renderPriority.name()));
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setRenderPriority(WebSettings.RenderPriority.valueOf(renderPriority.name()));
        }
    }

    public synchronized void setSansSerifFontFamily(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setSansSerifFontFamily(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setSansSerifFontFamily(str);
        }
    }

    public void setSaveFormData(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setSaveFormData(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setSaveFormData(z);
        }
    }

    public void setSavePassword(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setSavePassword(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setSavePassword(z);
        }
    }

    public synchronized void setSerifFontFamily(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setSerifFontFamily(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setSerifFontFamily(str);
        }
    }

    public synchronized void setStandardFontFamily(String str) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setStandardFontFamily(str);
        } else if (!this.f1290c && this.f1289b != null) {
            this.f1289b.setStandardFontFamily(str);
        }
    }

    public void setSupportMultipleWindows(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setSupportMultipleWindows(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setSupportMultipleWindows(z);
        }
    }

    public void setSupportZoom(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setSupportZoom(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setSupportZoom(z);
        }
    }

    public void setTextSize(TextSize textSize) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setTextSize(IX5WebSettings.TextSize.valueOf(textSize.name()));
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setTextSize(WebSettings.TextSize.valueOf(textSize.name()));
        }
    }

    @TargetApi(14)
    public synchronized void setTextZoom(int i) {
        if (this.f1290c && this.f1288a != null) {
            this.f1288a.setTextZoom(i);
        } else if (!this.f1290c && this.f1289b != null) {
            int i2 = Build.VERSION.SDK_INT;
            try {
                this.f1289b.setTextZoom(i);
            } catch (Exception unused) {
                i.a(this.f1289b, "setTextZoom", new Class[]{Integer.TYPE}, Integer.valueOf(i));
            }
        }
    }

    @Deprecated
    public void setUseWebViewBackgroundForOverscrollBackground(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setUseWebViewBackgroundForOverscrollBackground(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            i.a(webSettings, "setUseWebViewBackgroundForOverscrollBackground", new Class[]{Boolean.TYPE}, Boolean.valueOf(z));
        }
    }

    public void setUseWideViewPort(boolean z) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setUseWideViewPort(z);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setUseWideViewPort(z);
        }
    }

    public void setUserAgent(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setUserAgent(str);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setUserAgentString(str);
        }
    }

    @TargetApi(3)
    public void setUserAgentString(String str) {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            iX5WebSettings.setUserAgentString(str);
        } else if (!this.f1290c && (webSettings = this.f1289b) != null) {
            webSettings.setUserAgentString(str);
        }
    }

    public synchronized boolean supportMultipleWindows() {
        if (this.f1290c && this.f1288a != null) {
            return this.f1288a.supportMultipleWindows();
        } else if (this.f1290c || this.f1289b == null) {
            return false;
        } else {
            return this.f1289b.supportMultipleWindows();
        }
    }

    public boolean supportZoom() {
        android.webkit.WebSettings webSettings;
        IX5WebSettings iX5WebSettings;
        if (this.f1290c && (iX5WebSettings = this.f1288a) != null) {
            return iX5WebSettings.supportZoom();
        }
        if (this.f1290c || (webSettings = this.f1289b) == null) {
            return false;
        }
        return webSettings.supportZoom();
    }
}
