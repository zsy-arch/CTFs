package com.parse;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.SSLSessionCache;
import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import com.parse.http.ParseHttpRequest;
import com.parse.http.ParseHttpResponse;
import com.parse.http.ParseNetworkInterceptor;
import java.io.File;
import java.io.IOException;

/* loaded from: classes.dex */
public class ParsePlugins {
    private static final String INSTALLATION_ID_LOCATION = "installationId";
    private static final Object LOCK = new Object();
    private static ParsePlugins instance;
    private final String applicationId;
    File cacheDir;
    private final String clientKey;
    File filesDir;
    private InstallationId installationId;
    final Object lock;
    File parseDir;
    private ParseHttpClient restClient;

    static void initialize(String applicationId, String clientKey) {
        set(new ParsePlugins(applicationId, clientKey));
    }

    static void set(ParsePlugins plugins) {
        synchronized (LOCK) {
            if (instance != null) {
                throw new IllegalStateException("ParsePlugins is already initialized");
            }
            instance = plugins;
        }
    }

    public static ParsePlugins get() {
        ParsePlugins parsePlugins;
        synchronized (LOCK) {
            parsePlugins = instance;
        }
        return parsePlugins;
    }

    public static void reset() {
        synchronized (LOCK) {
            instance = null;
        }
    }

    private ParsePlugins(String applicationId, String clientKey) {
        this.lock = new Object();
        this.applicationId = applicationId;
        this.clientKey = clientKey;
    }

    public String applicationId() {
        return this.applicationId;
    }

    public String clientKey() {
        return this.clientKey;
    }

    public ParseHttpClient newHttpClient() {
        return ParseHttpClient.createClient(10000, null);
    }

    public ParseHttpClient restClient() {
        ParseHttpClient parseHttpClient;
        synchronized (this.lock) {
            if (this.restClient == null) {
                this.restClient = newHttpClient();
                this.restClient.addInternalInterceptor(new ParseNetworkInterceptor() { // from class: com.parse.ParsePlugins.1
                    @Override // com.parse.http.ParseNetworkInterceptor
                    public ParseHttpResponse intercept(ParseNetworkInterceptor.Chain chain) throws IOException {
                        ParseHttpRequest request = chain.getRequest();
                        ParseHttpRequest.Builder builder = new ParseHttpRequest.Builder(request).addHeader("X-Parse-Application-Id", ParsePlugins.this.applicationId).addHeader("X-Parse-Client-Key", ParsePlugins.this.clientKey).addHeader("X-Parse-Client-Version", Parse.externalVersionName()).addHeader("X-Parse-App-Build-Version", String.valueOf(ManifestInfo.getVersionCode())).addHeader("X-Parse-App-Display-Version", ManifestInfo.getVersionName()).addHeader("X-Parse-OS-Version", Build.VERSION.RELEASE).addHeader("User-Agent", ParsePlugins.this.userAgent());
                        if (request.getHeader("X-Parse-Installation-Id") == null) {
                            builder.addHeader("X-Parse-Installation-Id", ParsePlugins.this.installationId().get());
                        }
                        return chain.proceed(builder.build());
                    }
                });
            }
            parseHttpClient = this.restClient;
        }
        return parseHttpClient;
    }

    String userAgent() {
        return "Parse Java SDK";
    }

    public InstallationId installationId() {
        InstallationId installationId;
        synchronized (this.lock) {
            if (this.installationId == null) {
                this.installationId = new InstallationId(new File(getParseDir(), INSTALLATION_ID_LOCATION));
            }
            installationId = this.installationId;
        }
        return installationId;
    }

    @Deprecated
    public File getParseDir() {
        throw new IllegalStateException("Stub");
    }

    public File getCacheDir() {
        throw new IllegalStateException("Stub");
    }

    public File getFilesDir() {
        throw new IllegalStateException("Stub");
    }

    /* loaded from: classes2.dex */
    public static class Android extends ParsePlugins {
        private final Context applicationContext;

        public static void initialize(Context context, String applicationId, String clientKey) {
            ParsePlugins.set(new Android(context, applicationId, clientKey));
        }

        public static Android get() {
            return (Android) ParsePlugins.get();
        }

        private Android(Context context, String applicationId, String clientKey) {
            super(applicationId, clientKey);
            this.applicationContext = context.getApplicationContext();
        }

        public Context applicationContext() {
            return this.applicationContext;
        }

        @Override // com.parse.ParsePlugins
        public ParseHttpClient newHttpClient() {
            return ParseHttpClient.createClient(10000, new SSLSessionCache(this.applicationContext));
        }

        @Override // com.parse.ParsePlugins
        String userAgent() {
            String packageVersion = EnvironmentCompat.MEDIA_UNKNOWN;
            try {
                String packageName = this.applicationContext.getPackageName();
                packageVersion = packageName + "/" + this.applicationContext.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
            }
            return "Parse Android SDK 1.13.0 (" + packageVersion + ") API Level " + Build.VERSION.SDK_INT;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.parse.ParsePlugins
        public File getParseDir() {
            File createFileDir;
            synchronized (this.lock) {
                if (this.parseDir == null) {
                    this.parseDir = this.applicationContext.getDir("Parse", 0);
                }
                createFileDir = ParsePlugins.createFileDir(this.parseDir);
            }
            return createFileDir;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.parse.ParsePlugins
        public File getCacheDir() {
            File createFileDir;
            synchronized (this.lock) {
                if (this.cacheDir == null) {
                    this.cacheDir = new File(this.applicationContext.getCacheDir(), "com.parse");
                }
                createFileDir = ParsePlugins.createFileDir(this.cacheDir);
            }
            return createFileDir;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.parse.ParsePlugins
        public File getFilesDir() {
            File createFileDir;
            synchronized (this.lock) {
                if (this.filesDir == null) {
                    this.filesDir = new File(this.applicationContext.getFilesDir(), "com.parse");
                }
                createFileDir = ParsePlugins.createFileDir(this.filesDir);
            }
            return createFileDir;
        }
    }

    public static File createFileDir(File file) {
        if (file.exists() || !file.mkdirs()) {
        }
        return file;
    }
}
