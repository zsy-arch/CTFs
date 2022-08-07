package com.vsf2f.f2f.ui.utils.upload;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import com.em.DemoHelper;
import com.hy.frame.common.BaseDialog;
import com.hy.frame.util.MyToast;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.bean.AppVersion;
import com.vsf2f.f2f.ui.dialog.UpAppDialog;
import com.vsf2f.f2f.ui.utils.ThreadPool;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/* loaded from: classes2.dex */
public class UpAppUtils {
    private String APK_NAME;
    private int APK_TYPE;
    private String UP_DOWNURL;
    private String UP_SAVENAME;
    private String UP_SAVEPATH;
    private Boolean isCanceled;
    private Boolean isFinished;
    private Boolean isTesting;
    private Context mContext;
    private AppVersion newVersion;
    private String updateURL;

    public UpAppUtils(Context context, AppVersion ver) {
        this.APK_TYPE = 1;
        this.APK_NAME = "VSF2F";
        this.UP_SAVENAME = "VSF2F(%s).apk";
        this.UP_SAVEPATH = getSDPath() + "/Download/";
        this.UP_DOWNURL = "http://f2f-apps.oss-cn-hangzhou.aliyuncs.com/android/%s(%s).apk";
        this.isTesting = false;
        this.isCanceled = false;
        this.isFinished = false;
        this.mContext = context;
        this.newVersion = ver;
        contrastVersion();
    }

    public UpAppUtils(Context context, AppVersion ver, Boolean isTesting) {
        this.APK_TYPE = 1;
        this.APK_NAME = "VSF2F";
        this.UP_SAVENAME = "VSF2F(%s).apk";
        this.UP_SAVEPATH = getSDPath() + "/Download/";
        this.UP_DOWNURL = "http://f2f-apps.oss-cn-hangzhou.aliyuncs.com/android/%s(%s).apk";
        this.isTesting = false;
        this.isCanceled = false;
        this.isFinished = false;
        this.mContext = context;
        this.newVersion = ver;
        this.isTesting = isTesting;
        contrastVersion();
    }

    private void contrastVersion() {
        if (this.newVersion != null) {
            String newVersionName = this.newVersion.getVersion();
            String cusVersionName = DemoHelper.getInstance().getVersion();
            this.updateURL = this.newVersion.getUrl();
            if (!newVersionName.equals(cusVersionName)) {
                String[] newV = newVersionName.split("\\.");
                String[] cusV = cusVersionName.split("\\.");
                for (int i = 0; i < 3; i++) {
                    if (!newV[i].equals(cusV[i])) {
                        if (Integer.parseInt(newV[i]) > Integer.parseInt(cusV[i])) {
                            showUpAppDialog(newVersionName);
                            return;
                        } else if (Integer.parseInt(newV[i]) < Integer.parseInt(cusV[i])) {
                            showLatest();
                            return;
                        }
                    }
                }
            }
            showLatest();
        }
    }

    private void showUpAppDialog(String newVersionName) {
        UpAppDialog upAppDialog = new UpAppDialog(this.mContext, newVersionName, this.newVersion.getDes(), !this.newVersion.getForcedUpdate().equals("1"));
        upAppDialog.setListener(new BaseDialog.IConfirmListener() { // from class: com.vsf2f.f2f.ui.utils.upload.UpAppUtils.1
            @Override // com.hy.frame.common.BaseDialog.IConfirmListener
            public void onDlgConfirm(BaseDialog dlg, int flag) {
                if (flag == 0) {
                    UpAppUtils.this.checkUpdateCompleted();
                }
            }
        });
        upAppDialog.show();
    }

    public void checkUpdateCompleted() {
        final ProgressDialog pBar = new ProgressDialog(this.mContext);
        pBar.setProgressStyle(1);
        pBar.setIcon(R.mipmap.ico_logo);
        pBar.setTitle(R.string.down_waiting);
        pBar.setProgress(0);
        pBar.setMax(100);
        pBar.setCancelable(false);
        pBar.setButton(-1, "后台下载", new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.upload.UpAppUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                pBar.dismiss();
            }
        });
        pBar.setButton(-2, "取消下载", new DialogInterface.OnClickListener() { // from class: com.vsf2f.f2f.ui.utils.upload.UpAppUtils.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialog, int which) {
                pBar.dismiss();
                UpAppUtils.this.cancelDownload();
            }
        });
        pBar.show();
        ThreadPool.newThreadPool(new Runnable() { // from class: com.vsf2f.f2f.ui.utils.upload.UpAppUtils.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(UpAppUtils.this.updateURL).openConnection();
                    conn.connect();
                    int length = conn.getContentLength();
                    InputStream is = conn.getInputStream();
                    File ApkFile = new File(UpAppUtils.this.UP_SAVEPATH, String.format(UpAppUtils.this.UP_SAVENAME, UpAppUtils.this.newVersion.getVersion()));
                    if (ApkFile.exists()) {
                        ApkFile.delete();
                    }
                    FileOutputStream fos = new FileOutputStream(ApkFile);
                    int count = 0;
                    byte[] buf = new byte[1024];
                    do {
                        int numRead = is.read(buf);
                        count += numRead;
                        pBar.setProgress((int) ((count / length) * 100.0f));
                        if (numRead <= 0) {
                            UpAppUtils.this.isFinished = true;
                            pBar.dismiss();
                            UpAppUtils.this.installApk(UpAppUtils.this.mContext);
                        } else {
                            fos.write(buf, 0, numRead);
                        }
                        if (UpAppUtils.this.isCanceled.booleanValue()) {
                            break;
                        }
                    } while (!UpAppUtils.this.isFinished.booleanValue());
                    fos.close();
                    is.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            }
        });
    }

    public static String getSDPath() {
        return Environment.getExternalStorageDirectory().toString();
    }

    public static boolean hasSDCard() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public void cancelDownload() {
        MyToast.show(this.mContext, (int) R.string.download_cancel);
        this.isCanceled = true;
    }

    public void installApk(Context mContext) {
        Uri fileUri;
        File file = new File(this.UP_SAVEPATH, String.format(this.UP_SAVENAME, this.newVersion.getVersion()));
        if (Build.VERSION.SDK_INT >= 24) {
            fileUri = FileProvider.getUriForFile(mContext.getApplicationContext(), mContext.getPackageName() + ".fileProvider", file);
        } else {
            fileUri = Uri.fromFile(file);
        }
        Intent installIntent = new Intent("android.intent.action.VIEW");
        installIntent.addFlags(268435456);
        installIntent.setFlags(1);
        installIntent.setAction("android.intent.action.VIEW");
        installIntent.setDataAndType(fileUri, "application/vnd.android.package-archive");
        mContext.startActivity(installIntent);
    }

    public void showLatest() {
        if (this.isTesting.booleanValue()) {
            MyToast.show(this.mContext, (int) R.string.UMLatestVersion);
        }
    }
}
