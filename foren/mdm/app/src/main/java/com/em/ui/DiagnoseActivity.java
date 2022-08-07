package com.em.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.vsf2f.f2f.R;

/* loaded from: classes.dex */
public class DiagnoseActivity extends BaseActivity implements View.OnClickListener {
    private ProgressDialog progressDialog;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.em.ui.BaseActivity, com.easeui.ui.EaseBaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_diagnose);
        TextView currentVersion = (TextView) findViewById(R.id.tv_version);
        ((Button) findViewById(R.id.button_uploadlog)).setOnClickListener(this);
        String strVersion = "";
        try {
            strVersion = getVersionName();
        } catch (Exception e) {
        }
        if (!TextUtils.isEmpty(strVersion)) {
            currentVersion.setText("V" + strVersion);
        } else {
            currentVersion.setText(getResources().getString(R.string.Not_Set));
        }
    }

    @Override // com.easeui.ui.EaseBaseActivity
    public void back(View view) {
        finish();
    }

    private String getVersionName() throws Exception {
        EMClient.getInstance();
        return EMClient.VERSION;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_uploadlog /* 2131756526 */:
                uploadlog();
                return;
            default:
                return;
        }
    }

    public void uploadlog() {
        if (this.progressDialog == null) {
            this.progressDialog = new ProgressDialog(this);
        }
        this.progressDialog.setMessage(getResources().getString(R.string.Upload_the_log));
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
        EMClient.getInstance().uploadLog(new AnonymousClass1(getResources().getString(R.string.log_upload_success)));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.em.ui.DiagnoseActivity$1  reason: invalid class name */
    /* loaded from: classes.dex */
    public class AnonymousClass1 implements EMCallBack {
        String st3;
        final /* synthetic */ String val$st;

        AnonymousClass1(String str) {
            this.val$st = str;
            this.st3 = DiagnoseActivity.this.getResources().getString(R.string.log_upload_failed);
        }

        @Override // com.hyphenate.EMCallBack
        public void onSuccess() {
            DiagnoseActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.DiagnoseActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    DiagnoseActivity.this.progressDialog.dismiss();
                    Toast.makeText(DiagnoseActivity.this, AnonymousClass1.this.val$st, 0).show();
                }
            });
        }

        @Override // com.hyphenate.EMCallBack
        public void onProgress(int progress, String status) {
        }

        @Override // com.hyphenate.EMCallBack
        public void onError(int code, String message) {
            EMLog.e("###", message);
            DiagnoseActivity.this.runOnUiThread(new Runnable() { // from class: com.em.ui.DiagnoseActivity.1.2
                @Override // java.lang.Runnable
                public void run() {
                    DiagnoseActivity.this.progressDialog.dismiss();
                    Toast.makeText(DiagnoseActivity.this, AnonymousClass1.this.st3, 0).show();
                }
            });
        }
    }
}
