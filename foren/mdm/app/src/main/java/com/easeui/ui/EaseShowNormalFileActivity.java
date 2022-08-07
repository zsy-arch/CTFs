package com.easeui.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMFileMessageBody;
import com.hyphenate.chat.a.c;
import com.hyphenate.util.FileUtils;
import com.vsf2f.f2f.R;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class EaseShowNormalFileActivity extends EaseBaseActivity {
    private File file;
    private ProgressBar progressBar;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ease_activity_show_file);
        this.progressBar = (ProgressBar) findViewById(R.id.progressBar);
        EMFileMessageBody messageBody = (EMFileMessageBody) getIntent().getParcelableExtra(c.b);
        this.file = new File(messageBody.getLocalUrl());
        Map<String, String> maps = new HashMap<>();
        if (!TextUtils.isEmpty(messageBody.getSecret())) {
            maps.put("share-secret", messageBody.getSecret());
        }
        EMClient.getInstance().chatManager().downloadFile(messageBody.getRemoteUrl(), messageBody.getLocalUrl(), maps, new EMCallBack() { // from class: com.easeui.ui.EaseShowNormalFileActivity.1
            @Override // com.hyphenate.EMCallBack
            public void onSuccess() {
                EaseShowNormalFileActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowNormalFileActivity.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        FileUtils.openFile(EaseShowNormalFileActivity.this.file, EaseShowNormalFileActivity.this);
                        EaseShowNormalFileActivity.this.finish();
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onProgress(final int progress, String status) {
                EaseShowNormalFileActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowNormalFileActivity.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EaseShowNormalFileActivity.this.progressBar.setProgress(progress);
                    }
                });
            }

            @Override // com.hyphenate.EMCallBack
            public void onError(int error, final String msg) {
                EaseShowNormalFileActivity.this.runOnUiThread(new Runnable() { // from class: com.easeui.ui.EaseShowNormalFileActivity.1.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (EaseShowNormalFileActivity.this.file != null && EaseShowNormalFileActivity.this.file.exists() && EaseShowNormalFileActivity.this.file.isFile()) {
                            EaseShowNormalFileActivity.this.file.delete();
                        }
                        Toast.makeText(EaseShowNormalFileActivity.this, EaseShowNormalFileActivity.this.getResources().getString(R.string.failed_to_download_file) + msg, 0).show();
                        EaseShowNormalFileActivity.this.finish();
                    }
                });
            }
        });
    }
}
