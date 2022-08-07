package com.vsf2f.f2f.jpush;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import com.alipay.android.phone.mrpc.core.RpcException;
import com.vsf2f.f2f.R;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class PushSetActivity extends InstrumentedActivity implements View.OnClickListener {
    private static final int MSG_SET_ALIAS = 1001;
    private static final int MSG_SET_TAGS = 1002;
    private static final String TAG = "JPush";
    Button mSetAlias;
    Button mSetPushTime;
    Button mSetTag;
    Button mStyleBasic;
    Button mStyleCustom;
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() { // from class: com.vsf2f.f2f.jpush.PushSetActivity.1
        @Override // cn.jpush.android.api.TagAliasCallback
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(PushSetActivity.TAG, logs);
                    break;
                case RpcException.ErrorCode.SERVER_PARAMMISSING /* 6002 */:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(PushSetActivity.TAG, logs);
                    if (!JPushUtil.isConnected(PushSetActivity.this.getApplicationContext())) {
                        Log.i(PushSetActivity.TAG, "No network");
                        break;
                    } else {
                        PushSetActivity.this.mHandler.sendMessageDelayed(PushSetActivity.this.mHandler.obtainMessage(1001, alias), 60000L);
                        break;
                    }
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(PushSetActivity.TAG, logs);
                    break;
            }
            JPushUtil.showToast(logs, PushSetActivity.this.getApplicationContext());
        }
    };
    private final TagAliasCallback mTagsCallback = new TagAliasCallback() { // from class: com.vsf2f.f2f.jpush.PushSetActivity.2
        @Override // cn.jpush.android.api.TagAliasCallback
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i(PushSetActivity.TAG, logs);
                    break;
                case RpcException.ErrorCode.SERVER_PARAMMISSING /* 6002 */:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";
                    Log.i(PushSetActivity.TAG, logs);
                    if (!JPushUtil.isConnected(PushSetActivity.this.getApplicationContext())) {
                        Log.i(PushSetActivity.TAG, "No network");
                        break;
                    } else {
                        PushSetActivity.this.mHandler.sendMessageDelayed(PushSetActivity.this.mHandler.obtainMessage(1002, tags), 60000L);
                        break;
                    }
                default:
                    logs = "Failed with errorCode = " + code;
                    Log.e(PushSetActivity.TAG, logs);
                    break;
            }
            JPushUtil.showToast(logs, PushSetActivity.this.getApplicationContext());
        }
    };
    private final Handler mHandler = new Handler() { // from class: com.vsf2f.f2f.jpush.PushSetActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1001:
                    Log.d(PushSetActivity.TAG, "Set alias in handler.");
                    JPushInterface.setAliasAndTags(PushSetActivity.this.getApplicationContext(), (String) msg.obj, null, PushSetActivity.this.mAliasCallback);
                    return;
                case 1002:
                    Log.d(PushSetActivity.TAG, "Set tags in handler.");
                    JPushInterface.setAliasAndTags(PushSetActivity.this.getApplicationContext(), null, (Set) msg.obj, PushSetActivity.this.mTagsCallback);
                    return;
                default:
                    Log.i(PushSetActivity.TAG, "Unhandled msg - " + msg.what);
                    return;
            }
        }
    };

    @Override // android.app.Activity
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.push_set_dialog);
        init();
        initListener();
    }

    private void init() {
        this.mSetTag = (Button) findViewById(R.id.bt_tag);
        this.mSetAlias = (Button) findViewById(R.id.bt_alias);
        this.mStyleBasic = (Button) findViewById(R.id.setStyle1);
        this.mStyleCustom = (Button) findViewById(R.id.setStyle2);
        this.mSetPushTime = (Button) findViewById(R.id.bu_setTime);
    }

    private void initListener() {
        this.mSetTag.setOnClickListener(this);
        this.mSetAlias.setOnClickListener(this);
        this.mStyleBasic.setOnClickListener(this);
        this.mStyleCustom.setOnClickListener(this);
        this.mSetPushTime.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_tag /* 2131757091 */:
                setTag(((EditText) findViewById(R.id.et_tag)).getText().toString().trim());
                return;
            case R.id.et_alias /* 2131757092 */:
            default:
                return;
            case R.id.bt_alias /* 2131757093 */:
                setAlias(((EditText) findViewById(R.id.et_alias)).getText().toString().trim());
                return;
            case R.id.setStyle1 /* 2131757094 */:
                setStyleBasic();
                return;
            case R.id.setStyle2 /* 2131757095 */:
                setStyleCustom();
                return;
            case R.id.bu_setTime /* 2131757096 */:
                startActivity(new Intent(this, JPushSettingActivity.class));
                return;
        }
    }

    public void setTag(String tag) {
        if (!TextUtils.isEmpty(tag)) {
            String[] sArray = tag.split(",");
            Set<String> tagSet = new LinkedHashSet<>();
            for (String sTagItme : sArray) {
                if (JPushUtil.isValidTagAndAlias(sTagItme)) {
                    tagSet.add(sTagItme);
                } else {
                    return;
                }
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1002, tagSet));
        }
    }

    public void setAlias(String alias) {
        if (!TextUtils.isEmpty(alias) && JPushUtil.isValidTagAndAlias(alias)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1001, alias));
        }
    }

    private void setStyleBasic() {
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(this);
        builder.statusBarDrawable = R.mipmap.ico_logo;
        builder.notificationFlags = 16;
        builder.notificationDefaults = 1;
        JPushInterface.setPushNotificationBuilder(1, builder);
        Toast.makeText(this, "Basic Builder - 1", 0).show();
    }

    private void setStyleCustom() {
        CustomPushNotificationBuilder builder = new CustomPushNotificationBuilder(this, R.layout.customer_notitfication_layout, R.id.icon, R.id.title, R.id.text);
        builder.layoutIconDrawable = R.mipmap.ico_logo;
        builder.developerArg0 = "developerArg2";
        JPushInterface.setPushNotificationBuilder(2, builder);
        Toast.makeText(this, "Custom Builder - 2", 0).show();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
