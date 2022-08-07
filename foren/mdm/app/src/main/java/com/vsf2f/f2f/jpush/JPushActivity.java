package com.vsf2f.f2f.jpush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import com.vsf2f.f2f.R;

/* loaded from: classes2.dex */
public class JPushActivity extends InstrumentedActivity implements View.OnClickListener {
    public static final String KEY_EXTRAS = "extras";
    public static final String KEY_MESSAGE = "message";
    public static final String KEY_TITLE = "title";
    public static final String MESSAGE_RECEIVED_ACTION = "com.vsf2f.f2f.ui.MESSAGE_RECEIVED_ACTION";
    public static boolean isForeground = false;
    private Button mGetRid;
    private Button mInit;
    private MessageReceiver mMessageReceiver;
    private TextView mRegId;
    private Button mResumePush;
    private Button mSetting;
    private Button mStopPush;
    private EditText msgText;

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jpush_main);
        initView();
        registerMessageReceiver();
    }

    private void initView() {
        TextView mImei = (TextView) findViewById(R.id.tv_imei);
        String udid = JPushUtil.getImei(getApplicationContext(), "");
        if (udid != null) {
            mImei.setText("IMEI: " + udid);
        }
        TextView mAppKey = (TextView) findViewById(R.id.tv_appkey);
        String appKey = JPushUtil.getAppKey(getApplicationContext());
        if (appKey == null) {
            appKey = "AppKey异常";
        }
        mAppKey.setText("AppKey: " + appKey);
        this.mRegId = (TextView) findViewById(R.id.tv_regId);
        this.mRegId.setText("RegId:");
        ((TextView) findViewById(R.id.tv_package)).setText("PackageName: " + getPackageName());
        ((TextView) findViewById(R.id.tv_device_id)).setText("deviceId:" + JPushUtil.getDeviceId(getApplicationContext()));
        ((TextView) findViewById(R.id.tv_version)).setText("Version: " + JPushUtil.GetVersion(getApplicationContext()));
        this.mInit = (Button) findViewById(R.id.init);
        this.mInit.setOnClickListener(this);
        this.mStopPush = (Button) findViewById(R.id.stopPush);
        this.mStopPush.setOnClickListener(this);
        this.mResumePush = (Button) findViewById(R.id.resumePush);
        this.mResumePush.setOnClickListener(this);
        this.mGetRid = (Button) findViewById(R.id.getRegistrationId);
        this.mGetRid.setOnClickListener(this);
        this.mSetting = (Button) findViewById(R.id.setting);
        this.mSetting.setOnClickListener(this);
        this.msgText = (EditText) findViewById(R.id.msg_rec);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.init /* 2131756934 */:
                init();
                return;
            case R.id.stopPush /* 2131756935 */:
                JPushInterface.stopPush(getApplicationContext());
                return;
            case R.id.resumePush /* 2131756936 */:
                JPushInterface.resumePush(getApplicationContext());
                return;
            case R.id.getRegistrationId /* 2131756937 */:
                String rid = JPushInterface.getRegistrationID(getApplicationContext());
                if (!rid.isEmpty()) {
                    this.mRegId.setText("RegId:" + rid);
                    return;
                } else {
                    Toast.makeText(this, "Get registration fail, JPush init failed!", 0).show();
                    return;
                }
            case R.id.msg_rec /* 2131756938 */:
            default:
                return;
            case R.id.setting /* 2131756939 */:
                startActivity(new Intent(this, PushSetActivity.class));
                return;
        }
    }

    private void init() {
        JPushInterface.init(getApplicationContext());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jpush.android.api.InstrumentedActivity, android.app.Activity
    public void onResume() {
        isForeground = true;
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.jpush.android.api.InstrumentedActivity, android.app.Activity
    public void onPause() {
        isForeground = false;
        super.onPause();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        unregisterReceiver(this.mMessageReceiver);
        super.onDestroy();
    }

    public void registerMessageReceiver() {
        this.mMessageReceiver = new MessageReceiver();
        IntentFilter filter = new IntentFilter();
        filter.setPriority(1000);
        filter.addAction("com.vsf2f.f2f.ui.MESSAGE_RECEIVED_ACTION");
        registerReceiver(this.mMessageReceiver, filter);
    }

    /* loaded from: classes2.dex */
    public class MessageReceiver extends BroadcastReceiver {
        public MessageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("com.vsf2f.f2f.ui.MESSAGE_RECEIVED_ACTION".equals(intent.getAction())) {
                String messge = intent.getStringExtra("message");
                String extras = intent.getStringExtra("extras");
                StringBuilder showMsg = new StringBuilder();
                showMsg.append("message : " + messge + "\n");
                if (!JPushUtil.isEmpty(extras)) {
                    showMsg.append("extras : " + extras + "\n");
                }
                JPushActivity.this.setCostomMsg(showMsg.toString());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCostomMsg(String msg) {
        if (this.msgText != null) {
            this.msgText.setText(msg);
            this.msgText.setVisibility(0);
        }
    }
}
