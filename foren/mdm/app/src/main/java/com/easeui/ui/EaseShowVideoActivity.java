package com.easeui.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.alimama.mobile.csdk.umupdate.a.f;
import com.em.db.UserDao;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.hyphenate.util.PathUtil;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class EaseShowVideoActivity extends EaseBaseActivity {
    private static final String TAG = "ShowVideoActivity";
    private RelativeLayout loadingLayout;
    private String localFilePath;
    private ProgressBar progressBar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.ease_showvideo_activity);
        this.loadingLayout = (RelativeLayout) findViewById(R.id.loading_layout);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.localFilePath = getIntent().getStringExtra(UserDao.IMAGE_COLUMN_NAME_LOCALPATH);
        String remotepath = getIntent().getStringExtra("remotepath");
        String secret = getIntent().getStringExtra("secret");
        EMLog.d(TAG, "show video view file:" + this.localFilePath + " remotepath:" + remotepath + " secret:" + secret);
        if (this.localFilePath != null && new File(this.localFilePath).exists()) {
            showLocalVideo(this.localFilePath);
        } else if (!TextUtils.isEmpty(remotepath) && !remotepath.equals(f.b)) {
            EMLog.d(TAG, "download remote video file");
            Map<String, String> maps = new HashMap<>();
            if (!TextUtils.isEmpty(secret)) {
                maps.put("share-secret", secret);
            }
            downloadVideo(remotepath, maps);
        }
    }

    public String getLocalFilePath(String remoteUrl) {
        if (remoteUrl.contains("/")) {
            return PathUtil.getInstance().getVideoPath().getAbsolutePath() + "/" + remoteUrl.substring(remoteUrl.lastIndexOf("/") + 1) + ".mp4";
        }
        return PathUtil.getInstance().getVideoPath().getAbsolutePath() + "/" + remoteUrl + ".mp4";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showLocalVideo(String localPath) {
        File file = new File(localPath);
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT >= 24) {
            intent.setFlags(1);
            intent.setDataAndType(FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".fileProvider", file), "video/*");
        } else {
            Uri uri = Uri.fromFile(file);
            intent.setFlags(268435456);
            intent.setDataAndType(uri, "video/*");
        }
        startActivity(intent);
        finish();
    }

    private void downloadVideo(String remoteUrl, Map<String, String> header) {
        if (TextUtils.isEmpty(this.localFilePath)) {
            this.localFilePath = getLocalFilePath(remoteUrl);
        }
        if (new File(this.localFilePath).exists()) {
            showLocalVideo(this.localFilePath);
            return;
        }
        this.loadingLayout.setVisibility(0);
        EMClient.getInstance().chatManager().downloadFile(remoteUrl, this.localFilePath, header, new EMCallBack() { // from class: com.easeui.ui.EaseShowVideoActivity.1
            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                EaseShowVideoActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowVideoActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        EaseShowVideoActivity.this.loadingLayout.setVisibility(8);
                        EaseShowVideoActivity.this.progressBar.setProgress(0);
                        EaseShowVideoActivity.this.showLocalVideo(EaseShowVideoActivity.this.localFilePath);
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(final int progress, String status) {
                Log.d("ease", "video progress:" + progress);
                EaseShowVideoActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowVideoActivity.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EaseShowVideoActivity.this.progressBar.setProgress(progress);
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onError(int error, String msg) {
                Log.e("###", "offline file transfer error:" + msg);
                File file = new File(EaseShowVideoActivity.this.localFilePath);
                if (file.exists()) {
                    file.delete();
                }
            }
        });
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        finish();
    }
}
