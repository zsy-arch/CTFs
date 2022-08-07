package com.vsf2f.f2f.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cdlinglu.common.MyHttpClient;
import com.cdlinglu.utils.ComUtil;
import com.cdlinglu.utils.DeviceTool;
import com.em.DemoHelper;
import com.hy.frame.bean.ResultInfo;
import com.hy.frame.util.AppShare;
import com.hy.frame.util.HyUtil;
import com.hy.frame.util.MyLog;
import com.hy.http.IMyHttpListener;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.vsf2f.f2f.R;
import com.vsf2f.f2f.ui.utils.ConfigUtil;
import com.vsf2f.f2f.ui.utils.Constant;

/* loaded from: classes2.dex */
public class LaunchActivity extends AppCompatActivity implements IMyHttpListener {
    private AppShare appShare;
    private String cusVersion;
    private boolean enterGuide;
    private boolean isNext = false;
    private boolean isReady = false;
    private boolean isStart = false;
    private Handler handler = new Handler();
    private Runnable runable1 = new Runnable() { // from class: com.vsf2f.f2f.ui.LaunchActivity.2
        @Override // java.lang.Runnable
        public void run() {
            if (!LaunchActivity.this.isNext) {
                LaunchActivity.this.isNext = true;
                LaunchActivity.this.appShare.putBoolean("tree", false);
                View adv = LaunchActivity.this.getLayoutInflater().inflate(R.layout.layout_launch_adv, (ViewGroup) null);
                adv.setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.LaunchActivity.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LaunchActivity.this.appShare.putBoolean("tree", true);
                        LaunchActivity.this.handler.post(LaunchActivity.this.runable2);
                    }
                });
                adv.findViewById(R.id.launch_txtSkip).setOnClickListener(new View.OnClickListener() { // from class: com.vsf2f.f2f.ui.LaunchActivity.2.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        LaunchActivity.this.handler.post(LaunchActivity.this.runable2);
                    }
                });
                ((RelativeLayout) LaunchActivity.this.findViewById(R.id.rly_parent)).addView(adv);
                LaunchActivity.this.handler.postDelayed(LaunchActivity.this.runable2, 3000L);
            }
        }
    };
    private Runnable runable2 = new Runnable() { // from class: com.vsf2f.f2f.ui.LaunchActivity.3
        @Override // java.lang.Runnable
        public void run() {
            LaunchActivity.this.startMain();
        }
    };

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_launch);
        isFirstEnter();
        firstEnter();
        getWindow().setFlags(1024, 1024);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        this.cusVersion = ComUtil.getVersion(this);
        DemoHelper.getInstance().setVersion(this.cusVersion);
        ((TextView) findViewById(R.id.launch_txtVersion)).setText("V " + this.cusVersion);
        if (!this.enterGuide) {
            updateConfig();
            if (DeviceTool.isNetworkConnected(this)) {
                checkOnline();
            }
            this.handler.postDelayed(this.runable2, 3000L);
        }
        DemoHelper.getInstance().getServiceTime(this);
    }

    private void checkOnline() {
        new MyHttpClient(this, this).get(R.string.API_CHECK_ONLINE, ComUtil.getZCApi(this, getString(R.string.API_CHECK_ONLINE)));
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestSuccess(ResultInfo result) {
        switch (result.getRequestCode()) {
            case R.string.API_CHECK_ONLINE /* 2131296303 */:
                if (result.getObj() != null) {
                    String isEnter = (String) result.getObj();
                    if (isEnter.equals("true")) {
                        String userName = DemoHelper.getInstance().getCurrentUserName();
                        String password = this.appShare.getString(Constant.PASSWORD);
                        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(password)) {
                            EMClient.getInstance().login(userName, password, new EMCallBack() { // from class: com.vsf2f.f2f.ui.LaunchActivity.1
                                @Override // com.hyphenate.EMCallBack
                                public void onSuccess() {
                                    MyLog.e(Integer.valueOf((int) R.string.em_login_success));
                                    EMClient.getInstance().chatManager().loadAllConversations();
                                }

                                @Override // com.hyphenate.EMCallBack
                                public void onError(int i, String s) {
                                    MyLog.e(Integer.valueOf((int) R.string.em_login_failed));
                                }

                                @Override // com.hyphenate.EMCallBack
                                public void onProgress(int i, String s) {
                                }
                            });
                            return;
                        }
                        return;
                    } else if (isEnter.equals("false")) {
                        DemoHelper.getInstance().logout(null);
                        return;
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    @Override // com.hy.http.IMyHttpListener
    public void onRequestError(ResultInfo result) {
        setReady();
    }

    public void setReady() {
        if (this.isReady) {
            new Handler().post(this.runable2);
        } else {
            this.isReady = true;
        }
    }

    public void isFirstEnter() {
        this.appShare = AppShare.get(this);
        this.appShare.putBoolean("circle", false);
        int lastVersion = this.appShare.getInt(com.hy.frame.util.Constant.LAST_VERSION_CODE);
        PackageInfo info = HyUtil.getAppVersion(this);
        if (info != null && info.versionCode != lastVersion) {
            this.appShare.putInt(com.hy.frame.util.Constant.LAST_VERSION_CODE, info.versionCode);
            this.enterGuide = true;
        }
    }

    public void firstEnter() {
        if (this.enterGuide) {
            startActivity(new Intent(this, GuideActivity.class));
            overridePendingTransition(17432576, 17432577);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startMain() {
        if (!this.isStart) {
            this.isStart = true;
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constant.FLAG_ACT, Constant.ACT_LAUNCH);
            startActivity(intent);
            overridePendingTransition(17432576, 17432577);
            finish();
        }
    }

    public void updateConfig() {
        ConfigUtil.updateConfig(this, this.cusVersion);
    }

    public void dismiss(View view) {
        view.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.app.AppCompatActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        MyLog.e("launchactivity:onDestroy");
    }
}
