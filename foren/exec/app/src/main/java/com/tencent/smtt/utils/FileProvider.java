package com.tencent.smtt.utils;

import android.content.ComponentName;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.tencent.smtt.sdk.BuildConfig;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsConfig;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f1475a = {"_display_name", "_size"};

    /* renamed from: b  reason: collision with root package name */
    public static final File f1476b = new File("/");

    /* renamed from: c  reason: collision with root package name */
    public static HashMap<String, a> f1477c = new HashMap<>();

    /* renamed from: d  reason: collision with root package name */
    public a f1478d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface a {
        Uri a(File file);

        File a(Uri uri);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b implements a {

        /* renamed from: a  reason: collision with root package name */
        public final String f1479a;

        /* renamed from: b  reason: collision with root package name */
        public final HashMap<String, File> f1480b = new HashMap<>();

        public b(String str) {
            this.f1479a = str;
        }

        @Override // com.tencent.smtt.utils.FileProvider.a
        public Uri a(File file) {
            try {
                String canonicalPath = file.getCanonicalPath();
                Map.Entry<String, File> entry = null;
                for (Map.Entry<String, File> entry2 : this.f1480b.entrySet()) {
                    String path = entry2.getValue().getPath();
                    if (canonicalPath.startsWith(path) && (entry == null || path.length() > entry.getValue().getPath().length())) {
                        entry = entry2;
                    }
                }
                if (entry != null) {
                    String path2 = entry.getValue().getPath();
                    String substring = canonicalPath.substring(path2.endsWith("/") ? path2.length() : path2.length() + 1);
                    return new Uri.Builder().scheme("content").authority(this.f1479a).encodedPath(Uri.encode(entry.getKey()) + '/' + Uri.encode(substring, "/")).build();
                }
                throw new IllegalArgumentException(e.a.a.a.a.a("Failed to find configured root that contains ", canonicalPath));
            } catch (IOException unused) {
                throw new IllegalArgumentException(e.a.a.a.a.a("Failed to resolve canonical path for ", file));
            }
        }

        @Override // com.tencent.smtt.utils.FileProvider.a
        public File a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.f1480b.get(decode);
            if (file != null) {
                File file2 = new File(file, decode2);
                try {
                    File canonicalFile = file2.getCanonicalFile();
                    if (canonicalFile.getPath().startsWith(file.getPath())) {
                        return canonicalFile;
                    }
                    throw new SecurityException("Resolved path jumped beyond configured root");
                } catch (IOException unused) {
                    throw new IllegalArgumentException(e.a.a.a.a.a("Failed to resolve canonical path for ", file2));
                }
            } else {
                throw new IllegalArgumentException(e.a.a.a.a.a("Unable to find configured root for ", uri));
            }
        }

        public void a(String str, File file) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    this.f1480b.put(str, file.getCanonicalFile());
                } catch (IOException e2) {
                    throw new IllegalArgumentException(e.a.a.a.a.a("Failed to resolve canonical path for ", file), e2);
                }
            } else {
                throw new IllegalArgumentException("Name must not be empty");
            }
        }
    }

    public static int a(String str) {
        if ("r".equals(str)) {
            return 268435456;
        }
        if ("w".equals(str) || "wt".equals(str)) {
            return 738197504;
        }
        if ("wa".equals(str)) {
            return 704643072;
        }
        if ("rw".equals(str)) {
            return 939524096;
        }
        if ("rwt".equals(str)) {
            return 1006632960;
        }
        throw new IllegalArgumentException(e.a.a.a.a.a("Invalid mode: ", str));
    }

    public static Uri a(Context context, File file) {
        String str;
        Method declaredMethod;
        if (Build.VERSION.SDK_INT < 24) {
            return null;
        }
        try {
            str = context.getPackageManager().getProviderInfo(new ComponentName(context.getPackageName(), "androidx.core.content.FileProvider"), 0).authority;
        } catch (Exception e2) {
            e2.printStackTrace();
            str = BuildConfig.FLAVOR;
        }
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Class<?> cls = Class.forName("androidx.core.content.FileProvider");
            if (cls == null || (declaredMethod = cls.getDeclaredMethod("getUriForFile", Context.class, String.class, File.class)) == null) {
                return null;
            }
            Object invoke = declaredMethod.invoke(null, context, str, file);
            if (invoke instanceof Uri) {
                return (Uri) invoke;
            }
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
            return null;
        }
    }

    public static Uri a(Context context, String str) {
        Uri uri = null;
        if (context == null || context.getApplicationContext() == null || !TbsConfig.APP_QQ.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
            if (context != null && context.getApplicationInfo().targetSdkVersion >= 24 && Build.VERSION.SDK_INT >= 24 && (uri = a(context, new File(str))) == null && QbSdk.checkContentProviderPrivilage(context)) {
                uri = a(context, context.getApplicationInfo().packageName + ".provider", new File(str));
            }
            if (uri != null) {
                return uri;
            }
            try {
                return Uri.fromFile(new File(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                return uri;
            }
        } else {
            try {
                return (Uri) i.a(Class.forName("com.tencent.mobileqq.utils.kapalaiadapter.FileProvider7Helper"), "getUriForFile", (Class<?>[]) new Class[]{Context.class, File.class}, context, new File(str));
            } catch (Exception e3) {
                e3.printStackTrace();
                return null;
            }
        }
    }

    public static Uri a(Context context, String str, File file) {
        return b(context, str).a(file);
    }

    public static File a(File file, String... strArr) {
        for (String str : strArr) {
            if (str != null) {
                file = new File(file, str);
            }
        }
        return file;
    }

    public static Object[] a(Object[] objArr, int i) {
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        return objArr2;
    }

    public static String[] a(String[] strArr, int i) {
        String[] strArr2 = new String[i];
        System.arraycopy(strArr, 0, strArr2, 0, i);
        return strArr2;
    }

    public static a b(Context context, String str) {
        a aVar;
        synchronized (f1477c) {
            aVar = f1477c.get(str);
            if (aVar == null) {
                try {
                    aVar = c(context, str);
                    f1477c.put(str, aVar);
                } catch (IOException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                } catch (XmlPullParserException e3) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e3);
                }
            }
        }
        return aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0081 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x001d A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.tencent.smtt.utils.FileProvider.a c(android.content.Context r8, java.lang.String r9) {
        /*
            com.tencent.smtt.utils.FileProvider$b r0 = new com.tencent.smtt.utils.FileProvider$b
            r0.<init>(r9)
            android.content.pm.PackageManager r1 = r8.getPackageManager()
            r2 = 128(0x80, float:1.794E-43)
            android.content.pm.ProviderInfo r9 = r1.resolveContentProvider(r9, r2)
            if (r9 == 0) goto L_0x008e
            android.content.pm.PackageManager r1 = r8.getPackageManager()
            java.lang.String r2 = "android.support.FILE_PROVIDER_PATHS"
            android.content.res.XmlResourceParser r9 = r9.loadXmlMetaData(r1, r2)
            if (r9 == 0) goto L_0x0086
        L_0x001d:
            int r1 = r9.next()
            r2 = 1
            if (r1 == r2) goto L_0x0085
            r3 = 2
            if (r1 != r3) goto L_0x001d
            java.lang.String r1 = r9.getName()
            r3 = 0
            java.lang.String r4 = "name"
            java.lang.String r4 = r9.getAttributeValue(r3, r4)
            java.lang.String r5 = "path"
            java.lang.String r5 = r9.getAttributeValue(r3, r5)
            java.lang.String r6 = "root-path"
            boolean r6 = r6.equals(r1)
            r7 = 0
            if (r6 == 0) goto L_0x004c
            java.io.File r1 = com.tencent.smtt.utils.FileProvider.f1476b
            java.lang.String[] r2 = new java.lang.String[r2]
            r2[r7] = r5
        L_0x0047:
            java.io.File r3 = a(r1, r2)
            goto L_0x007f
        L_0x004c:
            java.lang.String r6 = "files-path"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x005d
            java.io.File r1 = r8.getFilesDir()
            java.lang.String[] r2 = new java.lang.String[r2]
            r2[r7] = r5
            goto L_0x0047
        L_0x005d:
            java.lang.String r6 = "cache-path"
            boolean r6 = r6.equals(r1)
            if (r6 == 0) goto L_0x006e
            java.io.File r1 = r8.getCacheDir()
            java.lang.String[] r2 = new java.lang.String[r2]
            r2[r7] = r5
            goto L_0x0047
        L_0x006e:
            java.lang.String r6 = "external-path"
            boolean r1 = r6.equals(r1)
            if (r1 == 0) goto L_0x007f
            java.io.File r1 = android.os.Environment.getExternalStorageDirectory()
            java.lang.String[] r2 = new java.lang.String[r2]
            r2[r7] = r5
            goto L_0x0047
        L_0x007f:
            if (r3 == 0) goto L_0x001d
            r0.a(r4, r3)
            goto L_0x001d
        L_0x0085:
            return r0
        L_0x0086:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            java.lang.String r9 = "Missing android.support.FILE_PROVIDER_PATHS meta-data"
            r8.<init>(r9)
            throw r8
        L_0x008e:
            java.lang.RuntimeException r8 = new java.lang.RuntimeException
            java.lang.String r9 = "Must declare com.tencent.smtt.utils.FileProvider in AndroidManifest above Android 7.0,please view document in x5.tencent.com"
            r8.<init>(r9)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.utils.FileProvider.c(android.content.Context, java.lang.String):com.tencent.smtt.utils.FileProvider$a");
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.f1478d = b(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return this.f1478d.a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        String mimeTypeFromExtension;
        File a2 = this.f1478d.a(uri);
        int lastIndexOf = a2.getName().lastIndexOf(46);
        return (lastIndexOf < 0 || (mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(a2.getName().substring(lastIndexOf + 1))) == null) ? "application/octet-stream" : mimeTypeFromExtension;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("No external inserts");
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return true;
    }

    @Override // android.content.ContentProvider
    public ParcelFileDescriptor openFile(Uri uri, String str) {
        return ParcelFileDescriptor.open(this.f1478d.a(uri), a(str));
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        File a2 = this.f1478d.a(uri);
        if (strArr == null) {
            strArr = f1475a;
        }
        String[] strArr3 = new String[strArr.length];
        Object[] objArr = new Object[strArr.length];
        int i = 0;
        for (String str3 : strArr) {
            if ("_display_name".equals(str3)) {
                strArr3[i] = "_display_name";
                i++;
                objArr[i] = a2.getName();
            } else if ("_size".equals(str3)) {
                strArr3[i] = "_size";
                i++;
                objArr[i] = Long.valueOf(a2.length());
            }
        }
        String[] a3 = a(strArr3, i);
        Object[] a4 = a(objArr, i);
        MatrixCursor matrixCursor = new MatrixCursor(a3, 1);
        matrixCursor.addRow(a4);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
