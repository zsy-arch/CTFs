package androidx.core.content;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ProviderInfo;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.tencent.smtt.sdk.TbsListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes.dex */
public class FileProvider extends ContentProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String[] f200a = {"_display_name", "_size"};

    /* renamed from: b  reason: collision with root package name */
    public static final File f201b = new File("/");

    /* renamed from: c  reason: collision with root package name */
    public static HashMap<String, a> f202c = new HashMap<>();

    /* renamed from: d  reason: collision with root package name */
    public a f203d;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public interface a {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static class b implements a {

        /* renamed from: a  reason: collision with root package name */
        public final String f204a;

        /* renamed from: b  reason: collision with root package name */
        public final HashMap<String, File> f205b = new HashMap<>();

        public b(String str) {
            this.f204a = str;
        }

        public File a(Uri uri) {
            String encodedPath = uri.getEncodedPath();
            int indexOf = encodedPath.indexOf(47, 1);
            String decode = Uri.decode(encodedPath.substring(1, indexOf));
            String decode2 = Uri.decode(encodedPath.substring(indexOf + 1));
            File file = this.f205b.get(decode);
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
    }

    public static a a(Context context, String str) {
        a aVar;
        synchronized (f202c) {
            aVar = f202c.get(str);
            if (aVar == null) {
                try {
                    aVar = b(context, str);
                    f202c.put(str, aVar);
                } catch (IOException e2) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e2);
                } catch (XmlPullParserException e3) {
                    throw new IllegalArgumentException("Failed to parse android.support.FILE_PROVIDER_PATHS meta-data", e3);
                }
            }
        }
        return aVar;
    }

    public static a b(Context context, String str) {
        b bVar = new b(str);
        XmlResourceParser loadXmlMetaData = context.getPackageManager().resolveContentProvider(str, TbsListener.ErrorCode.DOWNLOAD_INTERRUPT).loadXmlMetaData(context.getPackageManager(), "android.support.FILE_PROVIDER_PATHS");
        if (loadXmlMetaData != null) {
            while (true) {
                int next = loadXmlMetaData.next();
                if (next == 1) {
                    return bVar;
                }
                if (next == 2) {
                    String name = loadXmlMetaData.getName();
                    File file = null;
                    String attributeValue = loadXmlMetaData.getAttributeValue(null, "name");
                    String attributeValue2 = loadXmlMetaData.getAttributeValue(null, "path");
                    if ("root-path".equals(name)) {
                        file = f201b;
                    } else if ("files-path".equals(name)) {
                        file = context.getFilesDir();
                    } else if ("cache-path".equals(name)) {
                        file = context.getCacheDir();
                    } else if ("external-path".equals(name)) {
                        file = Environment.getExternalStorageDirectory();
                    } else if ("external-files-path".equals(name)) {
                        File[] b2 = c.e.b.a.b(context, (String) null);
                        if (b2.length > 0) {
                            file = b2[0];
                        }
                    } else if ("external-cache-path".equals(name)) {
                        File[] a2 = c.e.b.a.a(context);
                        if (a2.length > 0) {
                            file = a2[0];
                        }
                    } else {
                        int i = Build.VERSION.SDK_INT;
                        if ("external-media-path".equals(name)) {
                            File[] externalMediaDirs = context.getExternalMediaDirs();
                            if (externalMediaDirs.length > 0) {
                                file = externalMediaDirs[0];
                            }
                        }
                    }
                    if (file != null) {
                        String[] strArr = {attributeValue2};
                        for (String str2 : strArr) {
                            if (str2 != null) {
                                file = new File(file, str2);
                            }
                        }
                        if (!TextUtils.isEmpty(attributeValue)) {
                            try {
                                bVar.f205b.put(attributeValue, file.getCanonicalFile());
                            } catch (IOException e2) {
                                throw new IllegalArgumentException(e.a.a.a.a.a("Failed to resolve canonical path for ", file), e2);
                            }
                        } else {
                            throw new IllegalArgumentException("Name must not be empty");
                        }
                    } else {
                        continue;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Missing android.support.FILE_PROVIDER_PATHS meta-data");
        }
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        super.attachInfo(context, providerInfo);
        if (providerInfo.exported) {
            throw new SecurityException("Provider must not be exported");
        } else if (providerInfo.grantUriPermissions) {
            this.f203d = a(context, providerInfo.authority);
        } else {
            throw new SecurityException("Provider must grant uri permissions");
        }
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return ((b) this.f203d).a(uri).delete() ? 1 : 0;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        String mimeTypeFromExtension;
        File a2 = ((b) this.f203d).a(uri);
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
        int i;
        File a2 = ((b) this.f203d).a(uri);
        if ("r".equals(str)) {
            i = 268435456;
        } else if ("w".equals(str) || "wt".equals(str)) {
            i = 738197504;
        } else if ("wa".equals(str)) {
            i = 704643072;
        } else if ("rw".equals(str)) {
            i = 939524096;
        } else if ("rwt".equals(str)) {
            i = 1006632960;
        } else {
            throw new IllegalArgumentException(e.a.a.a.a.a("Invalid mode: ", str));
        }
        return ParcelFileDescriptor.open(a2, i);
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        File a2 = ((b) this.f203d).a(uri);
        if (strArr == null) {
            strArr = f200a;
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
        String[] strArr4 = new String[i];
        System.arraycopy(strArr3, 0, strArr4, 0, i);
        Object[] objArr2 = new Object[i];
        System.arraycopy(objArr, 0, objArr2, 0, i);
        MatrixCursor matrixCursor = new MatrixCursor(strArr4, 1);
        matrixCursor.addRow(objArr2);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("No external updates");
    }
}
