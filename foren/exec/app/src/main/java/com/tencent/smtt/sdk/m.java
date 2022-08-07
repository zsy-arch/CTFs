package com.tencent.smtt.sdk;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import com.tencent.smtt.sdk.TbsListener;
import com.tencent.smtt.utils.FileUtil;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.b;
import dalvik.system.DexClassLoader;
import e.a.a.a.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileLock;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes.dex */
public class m {

    /* renamed from: d */
    public static m f1374d;
    public FileLock f;
    public FileOutputStream g;
    public static final ReentrantLock i = new ReentrantLock();
    public static final Lock j = new ReentrantLock();
    public static FileLock l = null;

    /* renamed from: a */
    public static ThreadLocal<Integer> f1371a = new ThreadLocal<Integer>() { // from class: com.tencent.smtt.sdk.m.1
        /* renamed from: a */
        public Integer initialValue() {
            return 0;
        }
    };
    public static Handler m = null;
    public static final Long[][] n = {new Long[]{44006L, 39094008L}, new Long[]{44005L, 39094008L}, new Long[]{43910L, 38917816L}, new Long[]{44027L, 39094008L}, new Long[]{44028L, 39094008L}, new Long[]{44029L, 39094008L}, new Long[]{44030L, 39094008L}, new Long[]{44032L, 39094008L}, new Long[]{44033L, 39094008L}, new Long[]{44034L, 39094008L}, new Long[]{43909L, 38917816L}};

    /* renamed from: b */
    public static boolean f1372b = false;

    /* renamed from: c */
    public static final FileFilter f1373c = new FileFilter() { // from class: com.tencent.smtt.sdk.m.2
        @Override // java.io.FileFilter
        public boolean accept(File file) {
            String name = file.getName();
            if (name == null || name.endsWith(".jar_is_first_load_dex_flag_file")) {
                return false;
            }
            int i2 = Build.VERSION.SDK_INT;
            if (name.endsWith(".dex")) {
                return false;
            }
            if (Build.VERSION.SDK_INT < 26 || !name.endsWith(".prof")) {
                return Build.VERSION.SDK_INT < 26 || !name.equals("oat");
            }
            return false;
        }
    };
    public static int o = 0;
    public static boolean p = false;

    /* renamed from: e */
    public int f1375e = 0;
    public boolean h = false;
    public boolean k = false;

    public m() {
        if (m == null) {
            m = new Handler(l.a().getLooper()) { // from class: com.tencent.smtt.sdk.m.3
                @Override // android.os.Handler
                public void handleMessage(Message message) {
                    QbSdk.G = true;
                    int i2 = message.what;
                    if (i2 == 1) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE");
                        Object[] objArr = (Object[]) message.obj;
                        m.this.b((Context) objArr[0], (String) objArr[1], ((Integer) objArr[2]).intValue());
                    } else if (i2 == 2) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_COPY_TBS_CORE");
                        Object[] objArr2 = (Object[]) message.obj;
                        m.this.a((Context) objArr2[0], (Context) objArr2[1], ((Integer) objArr2[2]).intValue());
                    } else if (i2 == 3) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_INSTALL_TBS_CORE_EX");
                        Object[] objArr3 = (Object[]) message.obj;
                        m.this.b((Context) objArr3[0], (Bundle) objArr3[1]);
                    } else if (i2 == 4) {
                        TbsLog.i("TbsInstaller", "TbsInstaller--handleMessage--MSG_UNZIP_TBS_CORE");
                        Object[] objArr4 = (Object[]) message.obj;
                        m.this.b((Context) objArr4[0], (File) objArr4[1], ((Integer) objArr4[2]).intValue());
                        QbSdk.G = false;
                        super.handleMessage(message);
                    }
                }
            };
        }
    }

    private void A(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--deleteOldCore");
        FileUtil.a(q(context), false);
    }

    private void B(Context context) {
        int i2;
        TbsLogReport tbsLogReport;
        TbsLog.i("TbsInstaller", "TbsInstaller--renameShareDir");
        File f = f(context, 0);
        File q = q(context);
        if (f == null || q == null) {
            TbsLog.i("TbsInstaller", "renameTbsCoreShareDir return,tmpTbsCoreUnzipDir=" + f + "tbsSharePath=" + q);
            return;
        }
        boolean renameTo = f.renameTo(q);
        a.a("renameTbsCoreShareDir rename success=", renameTo, "TbsInstaller");
        if (context != null && TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
            if (renameTo) {
                tbsLogReport = TbsLogReport.getInstance(context);
                i2 = TbsListener.ErrorCode.RENAME_SUCCESS;
            } else {
                tbsLogReport = TbsLogReport.getInstance(context);
                i2 = TbsListener.ErrorCode.RENAME_FAIL;
            }
            tbsLogReport.setInstallErrorCode(i2, " ");
        }
        g(context, false);
    }

    private void C(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsCoreCopyDir");
        File f = f(context, 1);
        File q = q(context);
        if (f != null && q != null) {
            f.renameTo(q);
            g(context, false);
        }
    }

    private void D(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--renameTbsTpatchCoreDir");
        File f = f(context, 5);
        File q = q(context);
        if (f != null && q != null) {
            f.renameTo(q);
            g(context, false);
        }
    }

    private void E(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--clearNewTbsCore");
        File f = f(context, 0);
        if (f != null) {
            FileUtil.a(f, false);
        }
        k.a(context).c(0, 5);
        k.a(context).c(-1);
        QbSdk.a(context, "TbsInstaller::clearNewTbsCore forceSysWebViewInner!");
    }

    public static synchronized m a() {
        m mVar;
        synchronized (m.class) {
            if (f1374d == null) {
                synchronized (m.class) {
                    if (f1374d == null) {
                        f1374d = new m();
                    }
                }
            }
            mVar = f1374d;
        }
        return mVar;
    }

    private void a(int i2, String str, Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        Throwable th2;
        BufferedOutputStream bufferedOutputStream;
        new File(str).delete();
        TbsLog.i("TbsInstaller", "Local tbs apk(" + str + ") is deleted!", true);
        File file = new File(QbSdk.getTbsFolderDir(context), "core_unzip_tmp");
        if (file.canRead()) {
            try {
                File file2 = new File(file, "tbs.conf");
                Properties properties = new Properties();
                BufferedOutputStream bufferedOutputStream2 = null;
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file2));
                    try {
                        try {
                            properties.load(bufferedInputStream);
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
                        } catch (Throwable th3) {
                            th2 = th3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    try {
                        properties.setProperty("tbs_local_installation", "true");
                        properties.store(bufferedOutputStream, (String) null);
                        TbsLog.i("TbsInstaller", "TBS_LOCAL_INSTALLATION is set!", true);
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        bufferedInputStream.close();
                    } catch (Throwable th5) {
                        th2 = th5;
                        bufferedOutputStream2 = bufferedOutputStream;
                        th2.printStackTrace();
                        if (bufferedOutputStream2 != null) {
                            try {
                                bufferedOutputStream2.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                    }
                } catch (Throwable th6) {
                    th = th6;
                    bufferedInputStream = null;
                }
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    public static void a(Context context) {
        String str;
        if (!v(context)) {
            if (a(context, "core_unzip_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_TBS_UNZIP_FOLDER_NAME"));
                str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_UNZIP_FOLDER_NAME";
            } else if (a(context, "core_share_backup_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_BACKUP_TBSCORE_FOLDER_NAME"));
                str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_BACKUP_TBSCORE_FOLDER_NAME";
            } else if (a(context, "core_copy_tmp")) {
                TbsCoreLoadStat.getInstance().a(context, TbsListener.ErrorCode.INFO_TEMP_CORE_EXIST_CONF_ERROR, new Throwable("TMP_TBS_COPY_FOLDER_NAME"));
                str = "TbsInstaller-UploadIfTempCoreExistConfError INFO_TEMP_CORE_EXIST_CONF_ERROR TMP_TBS_COPY_FOLDER_NAME";
            } else {
                return;
            }
            TbsLog.e("TbsInstaller", str);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x02d6 A[Catch: Exception -> 0x04c0, all -> 0x04da, TryCatch #3 {Exception -> 0x04c0, blocks: (B:71:0x0208, B:73:0x0231, B:74:0x0234, B:76:0x0250, B:78:0x0259, B:93:0x02af, B:96:0x02b5, B:106:0x02ca, B:109:0x02d0, B:112:0x02d6, B:113:0x02e4, B:115:0x02e7, B:117:0x02f3, B:119:0x02ff, B:121:0x030b, B:123:0x0311, B:126:0x031e, B:129:0x0334, B:131:0x033a, B:132:0x0356, B:134:0x0385, B:136:0x038c, B:139:0x03a4, B:142:0x03cf, B:144:0x03de, B:146:0x03e4, B:149:0x03ef, B:150:0x03f4, B:151:0x03fa, B:153:0x0406, B:154:0x040d, B:155:0x0413, B:159:0x0456, B:160:0x046e, B:164:0x0478, B:167:0x047e, B:168:0x0481, B:169:0x0482, B:171:0x04ab, B:175:0x04c5), top: B:195:0x0204 }] */
    /* JADX WARN: Removed duplicated region for block: B:138:0x03a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:144:0x03de A[Catch: Exception -> 0x04c0, all -> 0x04da, TryCatch #3 {Exception -> 0x04c0, blocks: (B:71:0x0208, B:73:0x0231, B:74:0x0234, B:76:0x0250, B:78:0x0259, B:93:0x02af, B:96:0x02b5, B:106:0x02ca, B:109:0x02d0, B:112:0x02d6, B:113:0x02e4, B:115:0x02e7, B:117:0x02f3, B:119:0x02ff, B:121:0x030b, B:123:0x0311, B:126:0x031e, B:129:0x0334, B:131:0x033a, B:132:0x0356, B:134:0x0385, B:136:0x038c, B:139:0x03a4, B:142:0x03cf, B:144:0x03de, B:146:0x03e4, B:149:0x03ef, B:150:0x03f4, B:151:0x03fa, B:153:0x0406, B:154:0x040d, B:155:0x0413, B:159:0x0456, B:160:0x046e, B:164:0x0478, B:167:0x047e, B:168:0x0481, B:169:0x0482, B:171:0x04ab, B:175:0x04c5), top: B:195:0x0204 }] */
    /* JADX WARN: Removed duplicated region for block: B:148:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x03ef A[Catch: Exception -> 0x04c0, all -> 0x04da, TryCatch #3 {Exception -> 0x04c0, blocks: (B:71:0x0208, B:73:0x0231, B:74:0x0234, B:76:0x0250, B:78:0x0259, B:93:0x02af, B:96:0x02b5, B:106:0x02ca, B:109:0x02d0, B:112:0x02d6, B:113:0x02e4, B:115:0x02e7, B:117:0x02f3, B:119:0x02ff, B:121:0x030b, B:123:0x0311, B:126:0x031e, B:129:0x0334, B:131:0x033a, B:132:0x0356, B:134:0x0385, B:136:0x038c, B:139:0x03a4, B:142:0x03cf, B:144:0x03de, B:146:0x03e4, B:149:0x03ef, B:150:0x03f4, B:151:0x03fa, B:153:0x0406, B:154:0x040d, B:155:0x0413, B:159:0x0456, B:160:0x046e, B:164:0x0478, B:167:0x047e, B:168:0x0481, B:169:0x0482, B:171:0x04ab, B:175:0x04c5), top: B:195:0x0204 }] */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0406 A[Catch: Exception -> 0x04c0, all -> 0x04da, TryCatch #3 {Exception -> 0x04c0, blocks: (B:71:0x0208, B:73:0x0231, B:74:0x0234, B:76:0x0250, B:78:0x0259, B:93:0x02af, B:96:0x02b5, B:106:0x02ca, B:109:0x02d0, B:112:0x02d6, B:113:0x02e4, B:115:0x02e7, B:117:0x02f3, B:119:0x02ff, B:121:0x030b, B:123:0x0311, B:126:0x031e, B:129:0x0334, B:131:0x033a, B:132:0x0356, B:134:0x0385, B:136:0x038c, B:139:0x03a4, B:142:0x03cf, B:144:0x03de, B:146:0x03e4, B:149:0x03ef, B:150:0x03f4, B:151:0x03fa, B:153:0x0406, B:154:0x040d, B:155:0x0413, B:159:0x0456, B:160:0x046e, B:164:0x0478, B:167:0x047e, B:168:0x0481, B:169:0x0482, B:171:0x04ab, B:175:0x04c5), top: B:195:0x0204 }] */
    /* JADX WARN: Removed duplicated region for block: B:154:0x040d A[Catch: Exception -> 0x04c0, all -> 0x04da, TryCatch #3 {Exception -> 0x04c0, blocks: (B:71:0x0208, B:73:0x0231, B:74:0x0234, B:76:0x0250, B:78:0x0259, B:93:0x02af, B:96:0x02b5, B:106:0x02ca, B:109:0x02d0, B:112:0x02d6, B:113:0x02e4, B:115:0x02e7, B:117:0x02f3, B:119:0x02ff, B:121:0x030b, B:123:0x0311, B:126:0x031e, B:129:0x0334, B:131:0x033a, B:132:0x0356, B:134:0x0385, B:136:0x038c, B:139:0x03a4, B:142:0x03cf, B:144:0x03de, B:146:0x03e4, B:149:0x03ef, B:150:0x03f4, B:151:0x03fa, B:153:0x0406, B:154:0x040d, B:155:0x0413, B:159:0x0456, B:160:0x046e, B:164:0x0478, B:167:0x047e, B:168:0x0481, B:169:0x0482, B:171:0x04ab, B:175:0x04c5), top: B:195:0x0204 }] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0478 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(android.content.Context r19, android.content.Context r20, int r21) {
        /*
            Method dump skipped, instructions count: 1362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.a(android.content.Context, android.content.Context, int):void");
    }

    private boolean a(Context context, File file) {
        return a(context, file, false);
    }

    public static boolean a(Context context, String str) {
        String str2;
        File file = new File(QbSdk.getTbsFolderDir(context), str);
        if (!file.exists()) {
            str2 = "TbsInstaller-isPrepareTbsCore, #1";
        } else if (!new File(file, "tbs.conf").exists()) {
            str2 = "TbsInstaller-isPrepareTbsCore, #2";
        } else {
            TbsLog.i("TbsInstaller", "TbsInstaller-isPrepareTbsCore, #3");
            return true;
        }
        TbsLog.i("TbsInstaller", str2);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0075  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean a(java.io.File r13, android.content.Context r14) {
        /*
            Method dump skipped, instructions count: 310
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.a(java.io.File, android.content.Context):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:251:0x0482 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @android.annotation.TargetApi(11)
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(android.content.Context r13, java.lang.String r14, int r15) {
        /*
            Method dump skipped, instructions count: 1629
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.b(android.content.Context, java.lang.String, int):void");
    }

    private boolean b(Context context, File file) {
        try {
            File[] listFiles = file.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.m.6
                @Override // java.io.FileFilter
                public boolean accept(File file2) {
                    return file2.getName().endsWith(".jar");
                }
            });
            int length = listFiles.length;
            int i2 = Build.VERSION.SDK_INT;
            ClassLoader classLoader = context.getClassLoader();
            for (int i3 = 0; i3 < length; i3++) {
                TbsLog.i("TbsInstaller", "jarFile: " + listFiles[i3].getAbsolutePath());
                new DexClassLoader(listFiles[i3].getAbsolutePath(), file.getAbsolutePath(), null, classLoader);
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DEXOPT_EXCEPTION, e2.toString());
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return false;
        }
    }

    private int c(Context context, Bundle bundle) {
        TbsLogReport tbsLogReport;
        int i2;
        try {
            Bundle a2 = QbSdk.a(context, bundle);
            TbsLog.i("TbsInstaller", "tpatch finished,ret is" + a2);
            int i3 = a2.getInt("patch_result");
            if (i3 == 0) {
                String string = bundle.getString("new_apk_location");
                int i4 = bundle.getInt("new_core_ver");
                int a3 = a(new File(string));
                if (i4 != a3) {
                    TbsLog.i("TbsInstaller", "version not equals!!!" + i4 + "patchVersion:" + a3);
                    TbsLogReport instance = TbsLogReport.getInstance(context);
                    instance.setInstallErrorCode(TbsListener.ErrorCode.TPATCH_VERSION_FAILED, "version=" + i4 + ",patchVersion=" + a3);
                    return 1;
                }
                File file = new File(bundle.getString("backup_apk"));
                String a4 = b.a(context, true, file);
                if (!"3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a".equals(a4)) {
                    TbsLog.i("TbsInstaller", "tpatch sig not equals!!!" + file + "signature:" + a4);
                    TbsLogReport instance2 = TbsLogReport.getInstance(context);
                    instance2.setInstallErrorCode(TbsListener.ErrorCode.TPATCH_BACKUP_NOT_VALID, "version=" + i4 + ",patchVersion=" + a3);
                    FileUtil.a(file, false);
                    return 0;
                }
                if (TbsDownloader.a(context)) {
                    TbsLog.i("TbsInstaller", "Tpatch decouple success!");
                    tbsLogReport = TbsLogReport.getInstance(context);
                    i2 = TbsListener.ErrorCode.DECOUPLE_TPATCH_INSTALL_SUCCESS;
                } else {
                    TbsLog.i("TbsInstaller", "Tpatch success!");
                    tbsLogReport = TbsLogReport.getInstance(context);
                    i2 = TbsListener.ErrorCode.TPATCH_INSTALL_SUCCESS;
                }
                tbsLogReport.setInstallErrorCode(i2, BuildConfig.FLAVOR);
                return 0;
            }
            String string2 = bundle.getString("new_apk_location");
            if (!TextUtils.isEmpty(string2)) {
                FileUtil.a(new File(string2), false);
            }
            TbsLogReport instance3 = TbsLogReport.getInstance(context);
            instance3.setInstallErrorCode(i3, "tpatch fail,patch error_code=" + i3);
            return 1;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport instance4 = TbsLogReport.getInstance(context);
            StringBuilder a5 = a.a("patch exception");
            a5.append(Log.getStackTraceString(e2));
            instance4.setInstallErrorCode(TbsListener.ErrorCode.DECOUPLE_TPATCH_FAIL, a5.toString());
            return 1;
        }
    }

    private boolean c(Context context, File file) {
        try {
            File file2 = new File(file, "tbs_sdk_extension_dex.jar");
            File file3 = new File(file, "tbs_sdk_extension_dex.dex");
            new DexClassLoader(file2.getAbsolutePath(), file.getAbsolutePath(), null, context.getClassLoader());
            String a2 = c.a(context, file3.getAbsolutePath());
            if (TextUtils.isEmpty(a2)) {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DEXOAT_EXCEPTION, "can not find oat command");
                return false;
            }
            File[] listFiles = file.listFiles(new FileFilter() { // from class: com.tencent.smtt.sdk.m.7
                @Override // java.io.FileFilter
                public boolean accept(File file4) {
                    return file4.getName().endsWith(".jar");
                }
            });
            for (File file4 : listFiles) {
                String substring = file4.getName().substring(0, file4.getName().length() - 4);
                Runtime.getRuntime().exec("/system/bin/dex2oat " + a2.replaceAll("tbs_sdk_extension_dex", substring) + " --dex-location=" + a().q(context) + File.separator + substring + ".jar").waitFor();
            }
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DEXOAT_EXCEPTION, e2);
            return false;
        }
    }

    /* JADX WARN: Finally extract failed */
    private synchronized boolean c(Context context, boolean z) {
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch");
        boolean z2 = false;
        if (!t(context)) {
            return false;
        }
        boolean tryLock = i.tryLock();
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch Locked =" + tryLock);
        if (tryLock) {
            try {
                int b2 = k.a(context).b("tpatch_status");
                int a2 = a(false, context);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch copyStatus =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer =" + a2);
                if (b2 == 1) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer = 0", true);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromTpatch tbsCoreInstalledVer != 0", true);
                    }
                    y(context);
                    z2 = true;
                }
                i.unlock();
            } catch (Throwable th) {
                i.unlock();
                throw th;
            }
        }
        b();
        return z2;
    }

    /* JADX WARN: Finally extract failed */
    private synchronized boolean d(Context context, boolean z) {
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy");
        boolean z2 = false;
        try {
        } catch (Throwable th) {
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.COPY_EXCEPTION, th.toString());
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromCopy exception:" + Log.getStackTraceString(th));
        }
        if (!t(context)) {
            return false;
        }
        boolean tryLock = i.tryLock();
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy Locked =" + tryLock);
        if (tryLock) {
            try {
                int b2 = k.a(context).b("copy_status");
                int a2 = a(false, context);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy copyStatus =" + b2);
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer =" + a2);
                if (b2 == 1) {
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer = 0", true);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromCopy tbsCoreInstalledVer != 0", true);
                    }
                    z(context);
                    z2 = true;
                }
                i.unlock();
            } catch (Throwable th2) {
                i.unlock();
                throw th2;
            }
        }
        b();
        return z2;
    }

    private boolean e(Context context, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            packageInfo = null;
        }
        return packageInfo != null;
    }

    /* JADX WARN: Finally extract failed */
    private synchronized boolean e(Context context, boolean z) {
        if (context != null) {
            if (TbsConfig.APP_WX.equals(context.getApplicationContext().getApplicationInfo().packageName)) {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.INSTALL_FROM_UNZIP, " ");
            }
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip canRenameTmpDir =" + z);
        TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #1 ");
        boolean z2 = false;
        try {
        } catch (Exception e2) {
            QbSdk.a(context, "TbsInstaller::enableTbsCoreFromUnzip Exception: " + e2);
            e2.printStackTrace();
        }
        if (!t(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #2 ");
        boolean tryLock = i.tryLock();
        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip locked=" + tryLock);
        if (tryLock) {
            try {
                int c2 = k.a(context).c();
                TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip installStatus=" + c2);
                int a2 = a(false, context);
                if (c2 == 2) {
                    TbsLog.i("TbsInstaller", "Tbsinstaller enableTbsCoreFromUnzip #4 ");
                    if (a2 == 0) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer = 0", false);
                    } else if (z) {
                        TbsLog.i("TbsInstaller", "TbsInstaller-enableTbsCoreFromUnzip tbsCoreInstalledVer != 0", false);
                    }
                    x(context);
                    z2 = true;
                }
                i.unlock();
            } catch (Throwable th) {
                i.unlock();
                throw th;
            }
        }
        b();
        return z2;
    }

    private synchronized boolean f(Context context, boolean z) {
        return false;
    }

    private void g(Context context, boolean z) {
        if (context == null) {
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, "setTmpFolderCoreToRead context is null");
            return;
        }
        try {
            File file = new File(QbSdk.getTbsFolderDir(context), "tmp_folder_core_to_read.conf");
            if (!z) {
                FileUtil.a(file, false);
            } else if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e2) {
            TbsLogReport instance = TbsLogReport.getInstance(context);
            StringBuilder a2 = a.a("setTmpFolderCoreToRead Exception message is ");
            a2.append(e2.getMessage());
            a2.append(" Exception cause is ");
            a2.append(e2.getCause());
            instance.setInstallErrorCode(TbsListener.ErrorCode.CREATE_TEMP_CONF_ERROR, a2.toString());
        }
    }

    private void h(Context context, int i2) {
        TbsLog.i("TbsInstaller", "proceedTpatchStatus,result=" + i2);
        if (i2 == 0) {
            if (TbsDownloader.a(context)) {
                i(context, 6);
            } else {
                g(context, true);
                k.a(context).b(TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_TBS_DOWNLOAD_V, 0), 1);
            }
        }
        QbSdk.G = false;
    }

    private void i(Context context, int i2) {
        File f = a().f(context, i2);
        a().g(context, true);
        File p2 = p(context);
        FileUtil.a(p2, true);
        f.renameTo(p2);
        TbsShareManager.b(context);
    }

    private boolean j(Context context, int i2) {
        File file;
        boolean z;
        TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt start - dirMode: " + i2);
        boolean z2 = false;
        try {
            if (i2 != 0) {
                if (i2 == 1) {
                    file = f(context, 1);
                } else if (i2 != 2) {
                    TbsLog.e("TbsInstaller", "doDexoptOrDexoat mode error: " + i2);
                    return false;
                } else {
                    file = q(context);
                }
            } else if (TbsDownloader.a(context)) {
                return true;
            } else {
                file = f(context, 0);
            }
            String property = System.getProperty("java.vm.version");
            z = property != null && property.startsWith("2");
            boolean z3 = Build.VERSION.SDK_INT == 23;
            boolean z4 = TbsDownloadConfig.getInstance(context).mPreferences.getBoolean(TbsDownloadConfig.TbsConfigKey.KEY_STOP_PRE_OAT, false);
            if (z && z3 && !z4) {
                z2 = true;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.DEXOPT_EXCEPTION, e2.toString());
        }
        if (z2 && c(context, file)) {
            TbsLog.i("TbsInstaller", "doTbsDexOpt -- doDexoatForArtVm");
            return true;
        } else if (z) {
            TbsLog.i("TbsInstaller", "doTbsDexOpt -- is ART mode, skip!");
            TbsLog.i("TbsInstaller", "TbsInstaller-doTbsDexOpt done");
            return true;
        } else {
            TbsLog.i("TbsInstaller", "doTbsDexOpt -- doDexoptForDavlikVM");
            return b(context, file);
        }
    }

    public static File s(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_private");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    private int u(Context context) {
        boolean z = true;
        if (k.a(context).d() != 1) {
            z = false;
        }
        boolean a2 = TbsDownloader.a(context);
        if (z) {
            return a2 ? TbsListener.ErrorCode.DECOUPLE_INCURUPDATE_SUCCESS : TbsListener.ErrorCode.INCRUPDATE_INSTALL_SUCCESS;
        }
        if (a2) {
            return TbsListener.ErrorCode.DECOUPLE_INSTLL_SUCCESS;
        }
        return 200;
    }

    public static boolean v(Context context) {
        String str;
        if (context == null) {
            str = "TbsInstaller-getTmpFolderCoreToRead, #1";
        } else {
            try {
                if (new File(QbSdk.getTbsFolderDir(context), "tmp_folder_core_to_read.conf").exists()) {
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #2");
                    return true;
                }
                TbsLog.i("TbsInstaller", "TbsInstaller-getTmpFolderCoreToRead, #3");
                return false;
            } catch (Exception unused) {
                str = "TbsInstaller-getTmpFolderCoreToRead, #4";
            }
        }
        TbsLog.i("TbsInstaller", str);
        return true;
    }

    private boolean w(Context context) {
        boolean z;
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock #1 ");
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        a.a("Tbsinstaller getTbsCoreRenameFileLock #2  enabled is ", z, "TbsInstaller");
        l = !z ? u.a().b(context) : FileUtil.f(context);
        if (l == null) {
            TbsLog.i("TbsInstaller", "getTbsCoreRenameFileLock## failed!");
            return false;
        }
        TbsLog.i("TbsInstaller", "Tbsinstaller getTbsCoreRenameFileLock true ");
        return true;
    }

    private void x(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip");
        if (!w(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            A(context);
            B(context);
            TbsLog.i("TbsInstaller", "after renameTbsCoreShareDir");
            if (!TbsShareManager.isThirdPartyApp(context)) {
                TbsLog.i("TbsInstaller", "prepare to shareTbsCore");
                TbsShareManager.a(context);
            } else {
                TbsLog.i("TbsInstaller", "is thirdapp and not chmod");
            }
            k.a(context).a(0);
            k.a(context).b(0);
            k.a(context).d(0);
            k.a(context).a("incrupdate_retry_num", 0);
            k.a(context).c(0, 3);
            k.a(context).a(BuildConfig.FLAVOR);
            k.a(context).a("tpatch_num", 0);
            k.a(context).c(-1);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().h(context) || i2 != a().i(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().h(context) + " getTbsCoreInstalledVerInNolock is " + a().i(context));
                } else {
                    n(context);
                }
            }
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.writeCoreInfoForThirdPartyApp(context, m(context), true);
            }
            f1371a.set(0);
            o = 0;
        } catch (Throwable th) {
            th.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            StringBuilder a2 = a.a("exception when renameing from unzip:");
            a2.append(th.toString());
            instance.setInstallErrorCode(TbsListener.ErrorCode.RENAME_EXCEPTION, a2.toString());
            TbsLog.e("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromUnzip Exception", true);
        }
        g(context);
    }

    private void y(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromTpatch");
        if (!w(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            A(context);
            D(context);
            TbsShareManager.a(context);
            k.a(context).b(0, -1);
            k.a(context).a("tpatch_num", 0);
            f1371a.set(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            StringBuilder a2 = a.a("exception when renameing from tpatch:");
            a2.append(e2.toString());
            instance.setInstallErrorCode(TbsListener.ErrorCode.TPATCH_ENABLE_EXCEPTION, a2.toString());
        }
        g(context);
    }

    private void z(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy");
        if (!w(context)) {
            TbsLog.i("TbsInstaller", "get rename fileLock#4 ## failed!");
            return;
        }
        try {
            A(context);
            C(context);
            TbsShareManager.a(context);
            k.a(context).a(0, 3);
            k.a(context).a("tpatch_num", 0);
            if (!TbsShareManager.isThirdPartyApp(context)) {
                int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_DECOUPLECOREVERSION, 0);
                if (i2 <= 0 || i2 == a().h(context) || i2 != a().i(context)) {
                    TbsLog.i("TbsInstaller", "TbsInstaller--generateNewTbsCoreFromCopy #1 deCoupleCoreVersion is " + i2 + " getTbsCoreShareDecoupleCoreVersion is " + a().h(context) + " getTbsCoreInstalledVerInNolock is " + a().i(context));
                } else {
                    n(context);
                }
            }
            f1371a.set(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            TbsLogReport instance = TbsLogReport.getInstance(context);
            StringBuilder a2 = a.a("exception when renameing from copy:");
            a2.append(e2.toString());
            instance.setInstallErrorCode(TbsListener.ErrorCode.RENAME_EXCEPTION, a2.toString());
        }
        g(context);
    }

    public int a(File file) {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            TbsLog.i("TbsInstaller", "TbsInstaller--getTbsVersion  tbsShareDir is " + file);
            File file2 = new File(file, "tbs.conf");
            if (!file2.exists()) {
                return 0;
            }
            Properties properties = new Properties();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file2));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                String property = properties.getProperty("tbs_core_version");
                if (property == null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return 0;
                }
                int parseInt = Integer.parseInt(property);
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
                return parseInt;
            } catch (Exception unused3) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public int a(String str) {
        Throwable th;
        if (str == null) {
            return 0;
        }
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(new File(str), "tbs.conf");
            if (!file.exists()) {
                return 0;
            }
            Properties properties = new Properties();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                String property = properties.getProperty("tbs_core_version");
                if (property == null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return 0;
                }
                int parseInt = Integer.parseInt(property);
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
                return parseInt;
            } catch (Exception unused3) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public int a(boolean z, Context context) {
        if (z || f1371a.get().intValue() <= 0) {
            f1371a.set(Integer.valueOf(i(context)));
        }
        return f1371a.get().intValue();
    }

    public File a(Context context, int i2, boolean z) {
        String str;
        String str2;
        File tbsFolderDir = QbSdk.getTbsFolderDir(context);
        switch (i2) {
            case 0:
                str = "core_unzip_tmp";
                break;
            case 1:
                str = "core_copy_tmp";
                break;
            case 2:
                str = "core_unzip_tmp_decouple";
                break;
            case 3:
                str = "core_share_backup";
                break;
            case 4:
                str = "core_share_backup_tmp";
                break;
            case 5:
                str = "tpatch_tmp";
                break;
            case 6:
                str = "tpatch_decouple_tmp";
                break;
            default:
                str = BuildConfig.FLAVOR;
                break;
        }
        TbsLog.i("TbsInstaller", "type=" + i2 + "needMakeDir=" + z + "folder=" + str);
        File file = new File(tbsFolderDir, str);
        if (!file.isDirectory()) {
            if (!z) {
                str2 = "getCoreDir,no need mkdir";
            } else if (!file.mkdir()) {
                str2 = "getCoreDir,mkdir false";
            }
            TbsLog.i("TbsInstaller", str2);
            return null;
        }
        return file;
    }

    public void a(Context context, int i2) {
        g(context, true);
        k.a(context).c(i2, 2);
    }

    public void a(Context context, Bundle bundle) {
        if (bundle != null && context != null) {
            Object[] objArr = {context, bundle};
            Message message = new Message();
            message.what = 3;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    public void a(Context context, File file, int i2) {
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmp,ctx=" + context + "File=" + file + "coreVersion=" + i2);
        if (file != null && context != null) {
            Object[] objArr = {context, file, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 4;
            message.obj = objArr;
            m.sendMessage(message);
        }
    }

    public void a(Context context, String str, int i2) {
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsApkPath=" + str);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore tbsCoreTargetVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCore currentThreadName=" + Thread.currentThread().getName());
        Object[] objArr = {context, str, Integer.valueOf(i2)};
        Message message = new Message();
        message.what = 1;
        message.obj = objArr;
        m.sendMessage(message);
    }

    public void a(Context context, boolean z) {
        int c2;
        int b2;
        int c3;
        int b3;
        boolean z2 = true;
        if (z) {
            this.k = true;
        }
        StringBuilder a2 = a.a("TbsInstaller-continueInstallTbsCore currentProcessName=");
        a2.append(context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", a2.toString());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentThreadName=" + Thread.currentThread().getName());
        if (t(context)) {
            String d2 = null;
            if (i.tryLock()) {
                try {
                    c2 = k.a(context).c();
                    b2 = k.a(context).b();
                    d2 = k.a(context).d("install_apk_path");
                    c3 = k.a(context).c("copy_core_ver");
                    b3 = k.a(context).b("copy_status");
                } finally {
                    i.unlock();
                }
            } else {
                c3 = 0;
                b3 = -1;
                c2 = -1;
                b2 = 0;
            }
            b();
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore installStatus=" + c2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreInstallVer=" + b2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsApkPath=" + d2);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore tbsCoreCopyVer=" + c3);
            TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore copyStatus=" + b3);
            if (TbsShareManager.isThirdPartyApp(context)) {
                TbsShareManager.b(context, false);
                c(context, TbsShareManager.f1267e);
                return;
            }
            int i2 = TbsDownloadConfig.getInstance(context).mPreferences.getInt(TbsDownloadConfig.TbsConfigKey.KEY_RESPONSECODE, 0);
            if (!(i2 == 1 || i2 == 2 || i2 == 4)) {
                z2 = false;
            }
            if (!(z2 || i2 == 0 || i2 == 5)) {
                Bundle bundle = new Bundle();
                bundle.putInt("operation", 10001);
                a(context, bundle);
            }
            if (c2 > -1 && c2 < 2) {
                a(context, d2, b2);
            }
            if (b3 == 0) {
                b(context, c3);
            }
        }
    }

    public synchronized boolean a(final Context context, final Context context2) {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp");
        if (p) {
            return true;
        }
        p = true;
        new Thread() { // from class: com.tencent.smtt.sdk.m.4

            /* renamed from: com.tencent.smtt.sdk.m$4$1 */
            /* loaded from: classes.dex */
            class AnonymousClass1 implements FileFilter {
                public AnonymousClass1() {
                    AnonymousClass4.this = r1;
                }

                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return file.getName().endsWith(".dex");
                }
            }

            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                File file;
                m mVar;
                Context context3;
                TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
                try {
                    if (context2 == null) {
                        file = new File(TbsShareManager.f1265c);
                    } else {
                        if (TbsShareManager.isThirdPartyApp(context)) {
                            TbsShareManager.j(context);
                            if (TbsShareManager.f1266d != null) {
                                TbsShareManager.j(context);
                                if (TbsShareManager.f1266d.contains("decouple")) {
                                    file = m.this.p(context2);
                                }
                            }
                            mVar = m.this;
                            context3 = context2;
                        } else {
                            mVar = m.this;
                            context3 = context2;
                        }
                        file = mVar.q(context3);
                    }
                    File q = m.this.q(context);
                    int i2 = Build.VERSION.SDK_INT;
                    FileUtil.a(file, q, new FileFilter() { // from class: com.tencent.smtt.sdk.m.4.2
                        @Override // java.io.FileFilter
                        public boolean accept(File file2) {
                            return file2.getName().endsWith("tbs.conf");
                        }
                    });
                    TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }.start();
        return true;
    }

    public boolean a(Context context, File[] fileArr) {
        return false;
    }

    public Context b(Context context, String str) {
        try {
            if (context.getPackageName() == str || !TbsPVConfig.getInstance(context).isEnableNoCoreGray()) {
                return context.createPackageContext(str, 2);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public File b(Context context, Context context2) {
        File file = new File(QbSdk.getTbsFolderDir(context2), "core_share");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        TbsLog.i("TbsInstaller", "getTbsCoreShareDir,mkdir false");
        return null;
    }

    public synchronized void b() {
        if (this.f1375e <= 0) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock currentTbsFileLockStackCount=" + this.f1375e + "call stack:" + Log.getStackTraceString(new Throwable()));
        } else if (this.f1375e > 1) {
            TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock with skip");
            this.f1375e--;
        } else {
            if (this.f1375e == 1) {
                TbsLog.i("TbsInstaller", "releaseTbsInstallingFileLock without skip");
                FileUtil.a(this.f, this.g);
                this.f1375e = 0;
            }
        }
    }

    public void b(Context context) {
        g(context, true);
        k.a(context).c(h(context), 2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02ba  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x03cc  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x03d1  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0426  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01a2 A[Catch: Exception -> 0x038a, all -> 0x0382, TryCatch #9 {Exception -> 0x038a, all -> 0x0382, blocks: (B:20:0x00b7, B:22:0x00bb, B:46:0x0176, B:48:0x017c, B:61:0x0198, B:63:0x01a2, B:65:0x01bf, B:66:0x01c8, B:68:0x01ce, B:69:0x01d7, B:71:0x01dd, B:72:0x01e6, B:79:0x021b, B:81:0x0229, B:83:0x0236, B:118:0x0369), top: B:173:0x00b7 }] */
    /* JADX WARN: Removed duplicated region for block: B:79:0x021b A[Catch: Exception -> 0x038a, all -> 0x0382, TRY_ENTER, TryCatch #9 {Exception -> 0x038a, all -> 0x0382, blocks: (B:20:0x00b7, B:22:0x00bb, B:46:0x0176, B:48:0x017c, B:61:0x0198, B:63:0x01a2, B:65:0x01bf, B:66:0x01c8, B:68:0x01ce, B:69:0x01d7, B:71:0x01dd, B:72:0x01e6, B:79:0x021b, B:81:0x0229, B:83:0x0236, B:118:0x0369), top: B:173:0x00b7 }] */
    /* JADX WARN: Type inference failed for: r6v12, types: [int] */
    /* JADX WARN: Type inference failed for: r6v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v1, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r8v16, types: [android.os.Bundle] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Unknown variable types count: 2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(android.content.Context r14, android.os.Bundle r15) {
        /*
            Method dump skipped, instructions count: 1249
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.b(android.content.Context, android.os.Bundle):void");
    }

    public boolean b(Context context, int i2) {
        if (TbsDownloader.getOverSea(context)) {
            return false;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore targetTbsCoreVer=" + i2);
        TbsLog.i("TbsInstaller", "TbsInstaller-continueInstallTbsCore currentProcessName=" + context.getApplicationInfo().processName);
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentProcessId=" + Process.myPid());
        TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore currentThreadName=" + Thread.currentThread().getName());
        Context d2 = d(context, i2);
        if (d2 != null) {
            Object[] objArr = {d2, context, Integer.valueOf(i2)};
            Message message = new Message();
            message.what = 2;
            message.obj = objArr;
            m.sendMessage(message);
            return true;
        }
        TbsLog.i("TbsInstaller", "TbsInstaller--installLocalTbsCore copy from null");
        return false;
    }

    public boolean b(Context context, File file, int i2) {
        TbsLog.i("TbsInstaller", "unzipTbsCoreToThirdAppTmpInThread #1");
        boolean a2 = a(context, file, false);
        a.a("unzipTbsCoreToThirdAppTmpInThread result is ", a2, "TbsInstaller");
        if (a2) {
            a().a(context, i2);
        }
        return a2;
    }

    public int c(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 0);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.versionCode;
        }
        return 0;
    }

    public File c(Context context, Context context2) {
        File file = new File(QbSdk.getTbsFolderDir(context2), "core_share_decouple");
        if (file.isDirectory() || ((context != null && TbsShareManager.isThirdPartyApp(context)) || file.mkdir())) {
            return file;
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0086 A[Catch: IOException -> 0x006f, TRY_ENTER, TRY_LEAVE, TryCatch #2 {IOException -> 0x006f, blocks: (B:15:0x006b, B:29:0x0086), top: B:41:0x0013 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(android.content.Context r10) {
        /*
            r9 = this;
            java.io.File r10 = r9.q(r10)
            java.io.File r0 = new java.io.File
            java.lang.String r1 = "tbs.conf"
            r0.<init>(r10, r1)
            boolean r10 = r0.exists()
            r1 = 0
            if (r10 != 0) goto L_0x0013
            return r1
        L_0x0013:
            java.util.Properties r10 = new java.util.Properties
            r10.<init>()
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: Throwable -> 0x007f, all -> 0x007c
            r3.<init>(r0)     // Catch: Throwable -> 0x007f, all -> 0x007c
            java.io.BufferedInputStream r4 = new java.io.BufferedInputStream     // Catch: Throwable -> 0x007f, all -> 0x007c
            r4.<init>(r3)     // Catch: Throwable -> 0x007f, all -> 0x007c
            r10.load(r4)     // Catch: Throwable -> 0x0079, all -> 0x0077
            java.lang.String r2 = "tbs_local_installation"
            java.lang.String r3 = "false"
            java.lang.String r10 = r10.getProperty(r2, r3)     // Catch: Throwable -> 0x0079, all -> 0x0077
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)     // Catch: Throwable -> 0x0079, all -> 0x0077
            boolean r10 = r10.booleanValue()     // Catch: Throwable -> 0x0079, all -> 0x0077
            r2 = 1
            if (r10 == 0) goto L_0x004a
            long r5 = java.lang.System.currentTimeMillis()     // Catch: Throwable -> 0x0074, all -> 0x0077
            long r7 = r0.lastModified()     // Catch: Throwable -> 0x0074, all -> 0x0077
            long r5 = r5 - r7
            r7 = 259200000(0xf731400, double:1.280618154E-315)
            int r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r0 <= 0) goto L_0x004a
            r1 = 1
        L_0x004a:
            java.lang.String r0 = "TbsInstaller"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: Throwable -> 0x0074, all -> 0x0077
            r3.<init>()     // Catch: Throwable -> 0x0074, all -> 0x0077
            java.lang.String r5 = "TBS_LOCAL_INSTALLATION is:"
            r3.append(r5)     // Catch: Throwable -> 0x0074, all -> 0x0077
            r3.append(r10)     // Catch: Throwable -> 0x0074, all -> 0x0077
            java.lang.String r5 = " expired="
            r3.append(r5)     // Catch: Throwable -> 0x0074, all -> 0x0077
            r3.append(r1)     // Catch: Throwable -> 0x0074, all -> 0x0077
            java.lang.String r3 = r3.toString()     // Catch: Throwable -> 0x0074, all -> 0x0077
            com.tencent.smtt.utils.TbsLog.i(r0, r3)     // Catch: Throwable -> 0x0074, all -> 0x0077
            r0 = r1 ^ 1
            r10 = r10 & r0
            r4.close()     // Catch: IOException -> 0x006f
            goto L_0x0089
        L_0x006f:
            r0 = move-exception
            r0.printStackTrace()
            goto L_0x0089
        L_0x0074:
            r0 = move-exception
            r2 = r4
            goto L_0x0081
        L_0x0077:
            r10 = move-exception
            goto L_0x008a
        L_0x0079:
            r0 = move-exception
            r2 = r4
            goto L_0x0080
        L_0x007c:
            r10 = move-exception
            r4 = r2
            goto L_0x008a
        L_0x007f:
            r0 = move-exception
        L_0x0080:
            r10 = 0
        L_0x0081:
            r0.printStackTrace()     // Catch: all -> 0x007c
            if (r2 == 0) goto L_0x0089
            r2.close()     // Catch: IOException -> 0x006f
        L_0x0089:
            return r10
        L_0x008a:
            if (r4 == 0) goto L_0x0094
            r4.close()     // Catch: IOException -> 0x0090
            goto L_0x0094
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0094:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.c(android.content.Context):boolean");
    }

    public Context d(Context context, int i2) {
        Context b2;
        TbsLog.i("TbsInstaller", "TbsInstaller--getTbsCoreHostContext tbsCoreTargetVer=" + i2);
        if (i2 <= 0) {
            return null;
        }
        String[] coreProviderAppList = TbsShareManager.getCoreProviderAppList();
        for (int i3 = 0; i3 < coreProviderAppList.length; i3++) {
            if (!context.getPackageName().equalsIgnoreCase(coreProviderAppList[i3]) && e(context, coreProviderAppList[i3]) && (b2 = b(context, coreProviderAppList[i3])) != null) {
                if (!f(b2)) {
                    StringBuilder a2 = a.a("TbsInstaller--getTbsCoreHostContext ");
                    a2.append(coreProviderAppList[i3]);
                    a2.append(" illegal signature go on next");
                    TbsLog.e("TbsInstaller", a2.toString());
                } else {
                    int i4 = i(b2);
                    TbsLog.i("TbsInstaller", "TbsInstaller-getTbsCoreHostContext hostTbsCoreVer=" + i4);
                    if (i4 != 0 && i4 == i2) {
                        StringBuilder a3 = a.a("TbsInstaller-getTbsCoreHostContext targetApp=");
                        a3.append(coreProviderAppList[i3]);
                        TbsLog.i("TbsInstaller", a3.toString());
                        return b2;
                    }
                }
            }
        }
        return null;
    }

    public String d(Context context, String str) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        BufferedInputStream bufferedInputStream2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            File file = new File(q(context), "tbs.conf");
            if (!file.exists()) {
                return null;
            }
            Properties properties = new Properties();
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream);
                bufferedInputStream.close();
                String property = properties.getProperty(str);
                try {
                    bufferedInputStream.close();
                } catch (IOException unused) {
                }
                return property;
            } catch (Exception unused2) {
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused3) {
                    }
                }
                return null;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream2 = bufferedInputStream;
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused4) {
                    }
                }
                throw th;
            }
        } catch (Exception unused5) {
            bufferedInputStream = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public void d(Context context) {
        BufferedInputStream bufferedInputStream;
        Throwable th;
        try {
            File file = new File(q(context), "tbs.conf");
            Properties properties = new Properties();
            BufferedOutputStream bufferedOutputStream = null;
            try {
                bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                try {
                    properties.load(bufferedInputStream);
                    BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(file));
                    try {
                        properties.setProperty("tbs_local_installation", "false");
                        properties.store(bufferedOutputStream2, (String) null);
                        try {
                            bufferedOutputStream2.close();
                        } catch (IOException unused) {
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException unused2) {
                            }
                        }
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Throwable unused4) {
                bufferedInputStream = null;
            }
            bufferedInputStream.close();
        } catch (Throwable unused5) {
        }
    }

    public int e(Context context, int i2) {
        return a(f(context, i2));
    }

    public boolean e(Context context) {
        try {
            File file = new File(FileUtil.a(context, 4), TbsDownloader.getBackupFileName(true));
            File f = a().f(context, 2);
            FileUtil.a(f);
            FileUtil.a(f, true);
            FileUtil.a(file, f);
            for (String str : f.list()) {
                File file2 = new File(f, str);
                if (file2.getName().endsWith(".dex")) {
                    file2.delete();
                }
            }
            i(context, 2);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public File f(Context context, int i2) {
        return a(context, i2, true);
    }

    public boolean f(Context context) {
        if (TbsShareManager.f1265c != null) {
            return true;
        }
        try {
            Signature signature = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures[0];
            if (context.getPackageName().equals(TbsConfig.APP_QB)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_WX)) {
                if (!signature.toCharsString().equals("308202eb30820254a00302010202044d36f7a4300d06092a864886f70d01010505003081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e74301e170d3131303131393134333933325a170d3431303131313134333933325a3081b9310b300906035504061302383631123010060355040813094775616e67646f6e673111300f060355040713085368656e7a68656e31353033060355040a132c54656e63656e7420546563686e6f6c6f6779285368656e7a68656e2920436f6d70616e79204c696d69746564313a3038060355040b133154656e63656e74204775616e677a686f7520526573656172636820616e6420446576656c6f706d656e742043656e7465723110300e0603550403130754656e63656e7430819f300d06092a864886f70d010101050003818d0030818902818100c05f34b231b083fb1323670bfbe7bdab40c0c0a6efc87ef2072a1ff0d60cc67c8edb0d0847f210bea6cbfaa241be70c86daf56be08b723c859e52428a064555d80db448cdcacc1aea2501eba06f8bad12a4fa49d85cacd7abeb68945a5cb5e061629b52e3254c373550ee4e40cb7c8ae6f7a8151ccd8df582d446f39ae0c5e930203010001300d06092a864886f70d0101050500038181009c8d9d7f2f908c42081b4c764c377109a8b2c70582422125ce545842d5f520aea69550b6bd8bfd94e987b75a3077eb04ad341f481aac266e89d3864456e69fba13df018acdc168b9a19dfd7ad9d9cc6f6ace57c746515f71234df3a053e33ba93ece5cd0fc15f3e389a3f365588a9fcb439e069d3629cd7732a13fff7b891499")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_QQ)) {
                if (!signature.toCharsString().equals("30820253308201bca00302010202044bbb0361300d06092a864886f70d0101050500306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b30090603550403130251513020170d3130303430363039343831375a180f32323834303132303039343831375a306d310e300c060355040613054368696e61310f300d06035504080c06e58c97e4baac310f300d06035504070c06e58c97e4baac310f300d060355040a0c06e885bee8aeaf311b3019060355040b0c12e697a0e7babfe4b89ae58aa1e7b3bbe7bb9f310b300906035504031302515130819f300d06092a864886f70d010101050003818d0030818902818100a15e9756216f694c5915e0b529095254367c4e64faeff07ae13488d946615a58ddc31a415f717d019edc6d30b9603d3e2a7b3de0ab7e0cf52dfee39373bc472fa997027d798d59f81d525a69ecf156e885fd1e2790924386b2230cc90e3b7adc95603ddcf4c40bdc72f22db0f216a99c371d3bf89cba6578c60699e8a0d536950203010001300d06092a864886f70d01010505000381810094a9b80e80691645dd42d6611775a855f71bcd4d77cb60a8e29404035a5e00b21bcc5d4a562482126bd91b6b0e50709377ceb9ef8c2efd12cc8b16afd9a159f350bb270b14204ff065d843832720702e28b41491fbc3a205f5f2f42526d67f17614d8a974de6487b2c866efede3b4e49a0f916baa3c1336fd2ee1b1629652049")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_DEMO)) {
                if (!signature.toCharsString().equals("3082023f308201a8a00302010202044c46914a300d06092a864886f70d01010505003064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f301e170d3130303732313036313835305a170d3430303731333036313835305a3064310b30090603550406130238363110300e060355040813074265696a696e673110300e060355040713074265696a696e673110300e060355040a130754656e63656e74310c300a060355040b13035753443111300f0603550403130873616d75656c6d6f30819f300d06092a864886f70d010101050003818d0030818902818100c209077044bd0d63ea00ede5b839914cabcc912a87f0f8b390877e0f7a2583f0d5933443c40431c35a4433bc4c965800141961adc44c9625b1d321385221fd097e5bdc2f44a1840d643ab59dc070cf6c4b4b4d98bed5cbb8046e0a7078ae134da107cdf2bfc9b440fe5cb2f7549b44b73202cc6f7c2c55b8cfb0d333a021f01f0203010001300d06092a864886f70d010105050003818100b007db9922774ef4ccfee81ba514a8d57c410257e7a2eba64bfa17c9e690da08106d32f637ac41fbc9f205176c71bde238c872c3ee2f8313502bee44c80288ea4ef377a6f2cdfe4d3653c145c4acfedbfbadea23b559d41980cc3cdd35d79a68240693739aabf5c5ed26148756cf88264226de394c8a24ac35b712b120d4d23a")) {
                    return false;
                }
            } else if (context.getPackageName().equals(TbsConfig.APP_QZONE)) {
                if (!signature.toCharsString().equals("308202ad30820216a00302010202044c26cea2300d06092a864886f70d010105050030819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d301e170d3130303632373034303830325a170d3335303632313034303830325a30819a310b3009060355040613023836311530130603550408130c4265696a696e672043697479311530130603550407130c4265696a696e67204369747931263024060355040a131d515a6f6e65205465616d206f662054656e63656e7420436f6d70616e7931183016060355040b130f54656e63656e7420436f6d70616e79311b301906035504031312416e64726f696420515a6f6e65205465616d30819f300d06092a864886f70d010101050003818d003081890281810082d6aca037a9843fbbe88b6dd19f36e9c24ce174c1b398f3a529e2a7fe02de99c27539602c026edf96ad8d43df32a85458bca1e6fbf11958658a7d6751a1d9b782bf43a8c19bd1c06bdbfd94c0516326ae3cf638ac42bb470580e340c46e6f306a772c1ef98f10a559edf867f3f31fe492808776b7bd953b2cba2d2b2d66a44f0203010001300d06092a864886f70d0101050500038181006003b04a8a8c5be9650f350cda6896e57dd13e6e83e7f891fc70f6a3c2eaf75cfa4fc998365deabbd1b9092159edf4b90df5702a0d101f8840b5d4586eb92a1c3cd19d95fbc1c2ac956309eda8eef3944baf08c4a49d3b9b3ffb06bc13dab94ecb5b8eb74e8789aa0ba21cb567f538bbc59c2a11e6919924a24272eb79251677")) {
                    return false;
                }
            } else if (context.getPackageName().equals("com.tencent.qqpimsecure") && !signature.toCharsString().equals("30820239308201a2a00302010202044c96f48f300d06092a864886f70d01010505003060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e57753020170d3130303932303035343334335a180f32303635303632333035343334335a3060310b300906035504061302434e310b300906035504081302474431123010060355040713094775616e677a686f753110300e060355040a130754656e63656e74310b3009060355040b130233473111300f0603550403130857696c736f6e577530819f300d06092a864886f70d010101050003818d0030818902818100b56e79dbb1185a79e52d792bb3d0bb3da8010d9b87da92ec69f7dc5ad66ab6bfdff2a6a1ed285dd2358f28b72a468be7c10a2ce30c4c27323ed4edcc936080e5bedc2cbbca0b7e879c08a631182793f44bb3ea284179b263410c298e5f6831032c9702ba4a74e2ccfc9ef857f12201451602fc8e774ac59d6398511586c83d1d0203010001300d06092a864886f70d0101050500038181002475615bb65b8d8786b890535802948840387d06b1692ff3ea47ef4c435719ba1865b81e6bfa6293ce31747c3cd6b34595b485cc1563fd90107ba5845c28b95c79138f0dec288940395bc10f92f2b69d8dc410999deb38900974ce9984b678030edfba8816582f56160d87e38641288d8588d2a31e20b89f223d788dd35cc9c8")) {
                return false;
            }
            return true;
        } catch (Exception unused) {
            TbsLog.i("TbsInstaller", "TbsInstaller-installLocalTbsCore getPackageInfo fail");
            return false;
        }
    }

    public void g(Context context) {
        boolean z;
        FileLock fileLock;
        try {
            z = TbsDownloadConfig.getInstance().getTbsCoreLoadRenameFileLockEnable();
        } catch (Throwable unused) {
            z = true;
        }
        if (z && (fileLock = l) != null) {
            FileUtil.a(context, fileLock);
        }
    }

    public int h(Context context) {
        Throwable th;
        BufferedInputStream bufferedInputStream = null;
        try {
            File file = new File(p(context), "tbs.conf");
            if (!file.exists()) {
                return 0;
            }
            Properties properties = new Properties();
            BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
            try {
                properties.load(bufferedInputStream2);
                bufferedInputStream2.close();
                String property = properties.getProperty("tbs_core_version");
                if (property == null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException unused) {
                    }
                    return 0;
                }
                int parseInt = Integer.parseInt(property);
                try {
                    bufferedInputStream2.close();
                } catch (IOException unused2) {
                }
                return parseInt;
            } catch (Exception unused3) {
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused4) {
                    }
                }
                return 0;
            } catch (Throwable th2) {
                th = th2;
                bufferedInputStream = bufferedInputStream2;
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException unused5) {
                    }
                }
                throw th;
            }
        } catch (Exception unused6) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 3, insn: 0x0075: MOVE  (r4 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:24:0x0075
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:41)
        */
    public int i(
    /*  JADX ERROR: JadxRuntimeException in pass: SSATransform
        jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 3, insn: 0x0075: MOVE  (r4 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:24:0x0075
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVarsInBlock(SSATransform.java:171)
        	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:143)
        	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:60)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r7v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:233)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:209)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:162)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:364)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:304)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:270)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(Unknown Source)
        	at java.base/java.util.ArrayList.forEach(Unknown Source)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(Unknown Source)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Unknown Source)
        */

    public int j(Context context) {
        int i2 = o;
        return i2 != 0 ? i2 : i(context);
    }

    public void k(Context context) {
        if (o == 0) {
            o = i(context);
        }
    }

    public boolean l(Context context) {
        return new File(q(context), "tbs.conf").exists();
    }

    /* JADX WARN: Type inference failed for: r2v2, types: [boolean] */
    /* JADX WARN: Unknown variable types count: 1 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m(android.content.Context r8) {
        /*
            Method dump skipped, instructions count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.m.m(android.content.Context):int");
    }

    public boolean n(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple #0");
        File q = q(context);
        File p2 = p(context);
        try {
            FileUtil.a(p2, true);
            FileUtil.a(q, p2, new FileFilter() { // from class: com.tencent.smtt.sdk.m.5
                @Override // java.io.FileFilter
                public boolean accept(File file) {
                    return !file.getName().endsWith(".dex") && !file.getName().endsWith(".jar_is_first_load_dex_flag_file");
                }
            });
            TbsShareManager.b(context);
            TbsLog.i("TbsInstaller", "TbsInstaller--coreShareCopyToDecouple success!!!");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void o(Context context) {
        TbsLog.i("TbsInstaller", "TbsInstaller--cleanStatusAndTmpDir");
        k.a(context).a(0);
        k.a(context).b(0);
        k.a(context).d(0);
        k.a(context).a("incrupdate_retry_num", 0);
        if (!TbsDownloader.a(context)) {
            k.a(context).c(0, -1);
            k.a(context).a(BuildConfig.FLAVOR);
            k.a(context).a("copy_retry_num", 0);
            k.a(context).c(-1);
            k.a(context).a(0, -1);
            FileUtil.a(f(context, 0), true);
            FileUtil.a(f(context, 1), true);
        }
    }

    public File p(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "core_share_decouple");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    public File q(Context context) {
        return b((Context) null, context);
    }

    public File r(Context context) {
        File file = new File(QbSdk.getTbsFolderDir(context), "share");
        if (file.isDirectory() || file.mkdir()) {
            return file;
        }
        return null;
    }

    public synchronized boolean t(Context context) {
        if (this.f1375e > 0) {
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= true");
            this.f1375e++;
            return true;
        }
        this.g = FileUtil.b(context, true, "tbslock.txt");
        if (this.g != null) {
            this.f = FileUtil.a(context, this.g);
            if (this.f == null) {
                TbsLog.i("TbsInstaller", "getTbsInstallingFileLock tbsFileLockFileLock == null");
                return false;
            }
            TbsLog.i("TbsInstaller", "getTbsInstallingFileLock success,is cached= false");
            this.f1375e++;
            return true;
        }
        TbsLog.i("TbsInstaller", "getTbsInstallingFileLock get install fos failed");
        return false;
    }

    private boolean a(Context context, File file, boolean z) {
        boolean z2;
        TbsDownloadConfig instance;
        int i2;
        TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs start");
        if (!FileUtil.c(file)) {
            TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.APK_INVALID, "apk is invalid!");
            instance = TbsDownloadConfig.getInstance(context);
            i2 = -520;
        } else {
            try {
                File tbsFolderDir = QbSdk.getTbsFolderDir(context);
                File file2 = z ? new File(tbsFolderDir, "core_share_decouple") : new File(tbsFolderDir, "core_unzip_tmp");
                if (file2.exists() && !TbsDownloader.a(context)) {
                    FileUtil.a(file2, false);
                }
            } catch (Throwable th) {
                StringBuilder a2 = a.a("TbsInstaller-unzipTbs -- delete unzip folder if exists exception");
                a2.append(Log.getStackTraceString(th));
                TbsLog.e("TbsInstaller", a2.toString());
            }
            File f = z ? f(context, 2) : f(context, 0);
            if (f == null) {
                TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.UNZIP_DIR_ERROR, "tmp unzip dir is null!");
                instance = TbsDownloadConfig.getInstance(context);
                i2 = -521;
            } else {
                try {
                    z2 = true;
                    FileUtil.a(f);
                    if (z) {
                        FileUtil.a(f, true);
                    }
                    boolean a3 = FileUtil.a(file, f);
                    if (a3) {
                        a3 = a(f, context);
                    }
                    if (z) {
                        for (String str : f.list()) {
                            File file3 = new File(f, str);
                            if (file3.getName().endsWith(".dex")) {
                                file3.delete();
                            }
                        }
                        try {
                            new File(s(context), "x5.tbs").delete();
                        } catch (Exception unused) {
                        }
                    }
                    if (!a3) {
                        FileUtil.a(f, false);
                        TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-522);
                        TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#1! exist:" + f.exists());
                    } else {
                        g(context, true);
                        if (z) {
                            File p2 = p(context);
                            FileUtil.a(p2, true);
                            f.renameTo(p2);
                            TbsShareManager.b(context);
                        }
                    }
                    return a3;
                } catch (IOException e2) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                    TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.UNZIP_IO_ERROR, e2);
                    if (!f.exists()) {
                        z2 = false;
                    }
                    if (z2) {
                        try {
                            FileUtil.a(f, false);
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + f.exists());
                        } catch (Throwable th2) {
                            StringBuilder a4 = a.a("copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:");
                            a4.append(Log.getStackTraceString(th2));
                            TbsLog.e("TbsInstaller", a4.toString());
                        }
                    }
                    return false;
                } catch (Exception e3) {
                    TbsDownloadConfig.getInstance(context).setInstallInterruptCode(-523);
                    TbsLogReport.getInstance(context).setInstallErrorCode(TbsListener.ErrorCode.UNZIP_OTHER_ERROR, e3);
                    if (!f.exists()) {
                        z2 = false;
                    }
                    if (z2) {
                        try {
                            FileUtil.a(f, false);
                            TbsLog.e("TbsInstaller", "copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exist:" + f.exists());
                        } catch (Throwable th3) {
                            StringBuilder a5 = a.a("copyFileIfChanged -- delete tmpTbsCoreUnzipDir#2! exception:");
                            a5.append(Log.getStackTraceString(th3));
                            TbsLog.e("TbsInstaller", a5.toString());
                        }
                    }
                    return false;
                } finally {
                    TbsLog.i("TbsInstaller", "TbsInstaller-unzipTbs done");
                }
            }
        }
        instance.setInstallInterruptCode(i2);
        return false;
    }

    public boolean g(Context context, int i2) {
        File file;
        String str;
        try {
            boolean isThirdPartyApp = TbsShareManager.isThirdPartyApp(context);
            if (!isThirdPartyApp) {
                file = q(context);
            } else if (TbsShareManager.j(context)) {
                TbsShareManager.j(context);
                file = new File(TbsShareManager.f1266d);
                if (file.getAbsolutePath().contains(TbsConfig.APP_DEMO)) {
                    return true;
                }
            } else {
                TbsLog.e("TbsInstaller", "321");
                return false;
            }
            if (file != null) {
                Long[][] lArr = n;
                int length = lArr.length;
                int i3 = 0;
                while (true) {
                    if (i3 >= length) {
                        break;
                    }
                    Long[] lArr2 = lArr[i3];
                    if (i2 == lArr2[0].intValue()) {
                        File file2 = new File(file, "libmttwebview.so");
                        if (!file2.exists() || file2.length() != lArr2[1].longValue()) {
                            if (!isThirdPartyApp) {
                                FileUtil.a(QbSdk.getTbsFolderDir(context), false);
                            }
                            f1371a.set(0);
                            str = "322";
                        } else {
                            TbsLog.d("TbsInstaller", "check so success: " + i2 + "; file: " + file2);
                        }
                    } else {
                        i3++;
                    }
                }
                return true;
            }
            str = "323";
            TbsLog.e("TbsInstaller", str);
            return false;
        } catch (Throwable th) {
            StringBuilder a2 = a.a("ISTBSCORELEGAL exception getMessage is ");
            a2.append(th.getMessage());
            TbsLog.e("TbsInstaller", a2.toString());
            TbsLog.e("TbsInstaller", "ISTBSCORELEGAL exception getCause is " + th.getCause());
            return false;
        }
    }

    public void c(Context context, int i2) {
        int i3;
        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreForThirdPartyApp");
        if (i2 > 0 && (i3 = i(context)) != i2) {
            Context e2 = TbsShareManager.e(context);
            if (e2 != null || TbsShareManager.f1265c != null) {
                TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp hostContext != null");
                a(context, e2);
            } else if (i3 <= 0) {
                TbsLog.i("TbsInstaller", "TbsInstaller--installTbsCoreForThirdPartyApp hostContext == null");
                QbSdk.a(context, "TbsInstaller::installTbsCoreForThirdPartyApp forceSysWebViewInner #2");
            }
        }
    }

    public void b(Context context, boolean z) {
        String str;
        if (!QbSdk.f1128b) {
            int i2 = Build.VERSION.SDK_INT;
            TbsLog.i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #1 ");
            if (TbsShareManager.isThirdPartyApp(context) && k.a(context).b("remove_old_core") == 1 && z) {
                try {
                    FileUtil.a(a().q(context), false);
                    TbsLog.i("TbsInstaller", "thirdAPP success--> delete old core_share Directory");
                } catch (Throwable th) {
                    th.printStackTrace();
                }
                k.a(context).a("remove_old_core", 0);
            }
            if (v(context)) {
                TbsLog.i("TbsInstaller", "Tbsinstaller installTbsCoreIfNeeded #2 ");
                if (a(context, "core_unzip_tmp") && e(context, z)) {
                    str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromUnzip!!";
                } else {
                    if (a(context, "core_share_backup_tmp")) {
                        f(context, z);
                    }
                    if (a(context, "core_copy_tmp") && d(context, z)) {
                        str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromCopy!!";
                    } else if (a(context, "tpatch_tmp") && c(context, z)) {
                        str = "TbsInstaller-installTbsCoreIfNeeded, enableTbsCoreFromTpatch!!";
                    } else {
                        TbsLog.i("TbsInstaller", "TbsInstaller-installTbsCoreIfNeeded, error !!", true);
                        return;
                    }
                }
                TbsLog.i("TbsInstaller", str, true);
            }
        }
    }
}
